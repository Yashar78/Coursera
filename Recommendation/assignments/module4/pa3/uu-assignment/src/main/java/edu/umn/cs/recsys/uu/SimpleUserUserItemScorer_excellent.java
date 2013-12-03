package edu.umn.cs.recsys.uu;

import it.unimi.dsi.fastutil.longs.LongSet;
import org.grouplens.lenskit.basic.AbstractItemScorer;
import org.grouplens.lenskit.data.dao.ItemEventDAO;
import org.grouplens.lenskit.data.dao.UserEventDAO;
import org.grouplens.lenskit.data.event.Rating;
import org.grouplens.lenskit.data.history.History;
import org.grouplens.lenskit.data.history.RatingVectorUserHistorySummarizer;
import org.grouplens.lenskit.data.history.UserHistory;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.grouplens.lenskit.vectors.VectorEntry;
import org.grouplens.lenskit.vectors.similarity.CosineVectorSimilarity;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * User-user item scorer.
 */
public class SimpleUserUserItemScorer_excellent extends AbstractItemScorer {

    private static final CosineVectorSimilarity COS_VS = new CosineVectorSimilarity();

    private static final Comparator<Neighbor> NEIGHBOR_COMPARATOR = new Comparator<Neighbor>() {
        public int compare(Neighbor n1, Neighbor n2) {
            return new Double(n1.similarity).compareTo(n2.similarity);
        }
    };

    private static final int NEIGHBORHOOD_SIZE = 30;

    private final ItemEventDAO itemDao;

    private final UserEventDAO userDao;

    private class Neighbor {
        long userID;
        double similarity;
        private SparseVector normalizedRatingsVector;

        public Neighbor(long userID,
                        double similarity,
                        SparseVector normalizedRatingsVector) {
            this.userID = userID;
            this.similarity = similarity;
            this.normalizedRatingsVector = normalizedRatingsVector;
        }

        public double weightedOffset(long itemID) {
            double normalizedRating = normalizedRatingsVector.get(itemID);
            return similarity * normalizedRating;
        }
    }

    @Inject
    public SimpleUserUserItemScorer_excellent(UserEventDAO udao, ItemEventDAO idao) {
        userDao = udao;
        itemDao = idao;
    }

    @Override
    public void score(long userID, @Nonnull MutableSparseVector scores) {
        SparseVector userVector = getUserRatingVector(userID);
        double userMean = userVector.mean();

        for (VectorEntry e : scores.fast(VectorEntry.State.EITHER)) {
            scores.set(e, scoreItem(userID, userMean, e.getKey()));
        }
    }

    // Return the mean-centered, normalized score for the item
    private double scoreItem(long userID, double userMean, long itemID) {
        double numerator   = 0;
        double denominator = 0;
        PriorityQueue<Neighbor> neighborhood = getNeighborhoodForItem(userID, itemID);
        for (Neighbor neighbor : neighborhood) {
            numerator = numerator + neighbor.weightedOffset(itemID);
            denominator = denominator + Math.abs(neighbor.similarity);
        }
        return userMean + numerator / denominator;
    }

    // Return the 30 most similar users who have rated the item.
    private PriorityQueue<Neighbor> getNeighborhoodForItem(long userID, long itemID) {
        PriorityQueue<Neighbor> neighborhood = new PriorityQueue<Neighbor>(NEIGHBORHOOD_SIZE + 1, NEIGHBOR_COMPARATOR);
        SparseVector userVector = getUserMeanCenteredRatingVector(userID);
        LongSet usersForItem = itemDao.getUsersForItem(itemID);
        usersForItem.remove(userID);
        for (Long neighborID : usersForItem) {
            neighborhood.add(createNeighbor(userVector, neighborID));
            if (neighborhood.size() > NEIGHBORHOOD_SIZE) {
                neighborhood.remove();
            }
        }
        return neighborhood;
    }

    // Returns a new neighbor. Similarities are computed by taking the cosine
    // between the user's mean-centered rating vectors.
    private Neighbor createNeighbor(SparseVector userVector, Long neighborID) {
        SparseVector neighborVector = getUserMeanCenteredRatingVector(neighborID);
        double similarity = COS_VS.similarity(userVector, neighborVector);
        return new Neighbor(neighborID, similarity, neighborVector);
    }

    // Returns the mean-centered rating vector for the specified user
    private SparseVector getUserMeanCenteredRatingVector(long userID) {
        MutableSparseVector userVector = getUserRatingVector(userID).mutableCopy();
        userVector.add(-userVector.mean());
        return userVector.freeze();
    }

    // Returns the raw ratings vector for the specified user
    private SparseVector getUserRatingVector(long userID) {
        UserHistory<Rating> history = userDao.getEventsForUser(userID, Rating.class);
        if (history == null) {
            history = History.forUser(userID);
        }
        return RatingVectorUserHistorySummarizer.makeRatingVector(history);
    }
}