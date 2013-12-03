package edu.umn.cs.recsys.svd;

import org.apache.commons.math3.linear.RealMatrix;
import org.grouplens.lenskit.ItemScorer;
import org.grouplens.lenskit.baseline.BaselineScorer;
import org.grouplens.lenskit.basic.AbstractItemScorer;
import org.grouplens.lenskit.data.dao.UserEventDAO;
import org.grouplens.lenskit.data.event.Rating;
import org.grouplens.lenskit.data.history.History;
import org.grouplens.lenskit.data.history.RatingVectorUserHistorySummarizer;
import org.grouplens.lenskit.data.history.UserHistory;
import org.grouplens.lenskit.indexes.IdIndexMapping;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.grouplens.lenskit.vectors.VectorEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * SVD-based item scorer.
 */
public class SVDItemScorer extends AbstractItemScorer {
    private static final Logger logger = LoggerFactory.getLogger(SVDItemScorer.class);
    private final SVDModel model;
    private final ItemScorer baselineScorer;
    private final UserEventDAO userEvents;

    /**
     * Construct an SVD item scorer using a model.
     * @param m The model to use when generating scores.
     * @param uedao A DAO to get user rating profiles.
     * @param baseline The baseline scorer (providing means).
     */
    @Inject
    public SVDItemScorer(SVDModel m, UserEventDAO uedao,
                         @BaselineScorer ItemScorer baseline) {
        model = m;
        baselineScorer = baseline;
        userEvents = uedao;
    }

    /**
     * Score items in a vector. The key domain of the provided vector is the
     * items to score, and the score method sets the values for each item to
     * its score (or unsets it, if no score can be provided). The previous
     * values are discarded.
     *
     * @param user   The user ID.
     * @param scores The score vector.
     */
    @Override
    public void score(long user, @Nonnull MutableSparseVector scores) {
        // TODO Score the items in the key domain of scores
    	//IdIndexMapping userMapping = model.getUserIndexMapping();
    	//IdIndexMapping itemMapping = model.getItemIndMapping();
    	//RealMatrix rmi = model.getItemFeatureMatrix();
        for (VectorEntry e: scores.fast(VectorEntry.State.EITHER)) {
            long item = e.getKey();
            // TODO Set the scores
            //int u = userMapping.getIndex(user);
            //int i = itemMapping.getIndex(item);
            
            RealMatrix rmu = model.getUserVector(user);
            
            if (rmu==null) {
                //scores.set(item,baselineScorer.score(user, item));
            	scores.unset(item);
            	
            	
            } else {  
            	RealMatrix rmi = model.getItemVector(item);
            	//double s = baselineScorer.score(user, item)+ rmu.multiply(rmi.transpose()).getEntry(0, 0);
            	//System.out.println(model.getFeatureWeights().toString());
            	//System.exit(0);
            	
            	//double s = baselineScorer.score(user, item)+ rmu.multiply(model.getFeatureWeights()).multiply(rmi.transpose()).getEntry(0, 0);
            	//double s = baselineScorer.score(user, item)+ rmi.multiply(model.getFeatureWeights()).multiply(rmu.transpose()).getEntry(0, 0);
            	
            	double s = baselineScorer.score(user, item)+ rmu.multiply(model.getFeatureWeights()).multiply(rmi.transpose()).getEntry(0, 0);
            	//RealMatrix score = rmu.multiply(model.getFeatureWeights()).multiply(rmi.transpose()).scalarAdd(baselineScorer.score(user, item));
            	
            	//scores.set(item, score.getEntry(0, 0));
            	scores.set(item, s);
            }
            
            
            
        }
    }

    /**
     * Get a user's ratings.
     * @param user The user ID.
     * @return The ratings to retrieve.
     */
    private SparseVector getUserRatingVector(long user) {
        UserHistory<Rating> history = userEvents.getEventsForUser(user, Rating.class);
        if (history == null) {
            history = History.forUser(user);
        }

        return RatingVectorUserHistorySummarizer.makeRatingVector(history);
    }
}
