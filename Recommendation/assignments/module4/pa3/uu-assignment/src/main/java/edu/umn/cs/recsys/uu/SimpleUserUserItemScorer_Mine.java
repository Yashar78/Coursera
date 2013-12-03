package edu.umn.cs.recsys.uu;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import it.unimi.dsi.fastutil.longs.LongSet;

import org.grouplens.lenskit.basic.AbstractItemScorer;
import org.grouplens.lenskit.data.dao.ItemEventDAO;
import org.grouplens.lenskit.data.dao.UserDAO;
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

/**
 * User-user item scorer.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleUserUserItemScorer_Mine extends AbstractItemScorer {
    private final UserEventDAO userDao;
    private final ItemEventDAO itemDao;
    private final UserDAO userListDao;

    @Inject
    public SimpleUserUserItemScorer_Mine(UserEventDAO udao, ItemEventDAO idao, UserDAO uldao) {
        userDao = udao;
        itemDao = idao;
        userListDao = uldao;
        
    }

    @Override
    public void score(long user, @Nonnull MutableSparseVector scores) {
        SparseVector userVector = getUserRatingVector(user);

        // TODO Score items for this user using user-user collaborative filtering

        // This is the loop structure to iterate over items to score
        //System.out.println(userVector);
        
        //get the user mean rating
        double userMean = getUserRatingVector(user).mean();
        for (VectorEntry item: scores.fast(VectorEntry.State.EITHER)) {
        	long itemId = item.getKey();
        	
        	//find the most similar peers
        	MutableSparseVector topNeigborsSimilarity = getNeighbors(user, userVector, 30, itemId);
        	
        	//get the mean rating values
            SparseVector meanVec  = getMean(topNeigborsSimilarity.keySet());
        	
        	double scoreSum = 0.0;
        	double sumSim = 0.0 ;
        	//calculate the score
        	for (VectorEntry neighbor : topNeigborsSimilarity.fast()) {
        		long neighborId = neighbor.getKey();
        		SparseVector nv = getUserRatingVector(neighborId);
        		if (nv.containsKey(itemId)){
        			double nRate = nv.get(itemId);
        			scoreSum += topNeigborsSimilarity.get(neighborId) * (nRate - meanVec.get(neighborId));
        			sumSim += Math.abs(topNeigborsSimilarity.get(neighborId));
        		}
			}
        	scores.set(item, userMean+scoreSum/sumSim);
        	
        }
    }

    /**
     * Get a user's rating vector.
     * @param user The user ID.
     * @return The rating vector.
     */
    private SparseVector getUserRatingVector(long user) {
        UserHistory<Rating> history = userDao.getEventsForUser(user, Rating.class);
        if (history == null) {
            history = History.forUser(user);
        }
        return RatingVectorUserHistorySummarizer.makeRatingVector(history);
    }
    
    private MutableSparseVector getNeighbors(long user, SparseVector userRatings, int numberOfNeigbors, long whichItem){
    	/**\
    	 * This method find the top most "numberOfNeigbors" similar peers to the user "user" that have rated the item "whichItem"
    	 * input: user : userId , 
    	 * 		  userRatings: vector of the user's ratings
    	 *        numberOfNeigbors: the number of top similar users
    	 *        whichItem: the item id that similarity should be checked. 
    	 *        
    	 * return: A vector of most similar peers along with thier similarity values.
    	 * 
    	 */
    	LongSet users = userListDao.getUserIds();
    	MutableSparseVector result = MutableSparseVector.create(users, 0.0);
    	result.unset(user);
    	CosineVectorSimilarity cs = new CosineVectorSimilarity();
    	for (long u : result.keySet()) {
    			
    			SparseVector nVec = getUserRatingVector(u);
    			if (! nVec.containsKey(whichItem))
    				continue;
    			MutableSparseVector m1 = nVec.mutableCopy();
    			MutableSparseVector m2 = userRatings.mutableCopy();
    			m1.add(-1* m1.mean());
    			m2.add(-1* m2.mean());
    			double sim = cs.similarity(m1 , m2);
    			result.set(u, sim);
    	}

    	List<Long> topList = result.keysByValue(true).subList(0, numberOfNeigbors);
   		MutableSparseVector topResult = result.create(topList, 0.0);
   		
   		for (long l: topList){
   			topResult.set(l, result.get(l));
   		}
   	
   		return topResult;
    }
    
    private SparseVector getMean(LongSet neighborIds){
    	/**
    	 This method returns a vector of mean ratings for the given users.
    	 input: a set of the user ids.
    	 return: A Sparsevector if (Key: userId, value: ratingsMean) 
    	 */
    	
    	
    	Map<Long, Double> resMap = new HashMap<Long, Double>();
    	
    	
    	for (long  l : neighborIds) {
    		resMap.put(l, getUserRatingVector(l).mean());
   	}
    	
    	return MutableSparseVector.create(resMap);
    }
}
