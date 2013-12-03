package edu.umn.cs.recsys.uu;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongIterator;
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

/**
 * User-user item scorer.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleUserUserItemScorer extends AbstractItemScorer {
    private final UserEventDAO userDao;
    private final ItemEventDAO itemDao;

    @Inject
    public SimpleUserUserItemScorer(UserEventDAO udao, ItemEventDAO idao) {
        userDao = udao;
        itemDao = idao;
    }

    @Override
    public void score(long user, @Nonnull MutableSparseVector scores) {
        SparseVector userVector = getUserRatingVector(user);
        // Get the mean centered rating vector for the user U
        SparseVector meanCentredRatingVectorForU  = getMeanCentredRatingVector(userVector.mutableCopy());      
        double prediction=0.0;
        // MUu
        double meanOfUserU  =  userVector.mean();
        

        // This is the loop structure to iterate over items to score
        for (VectorEntry e: scores.fast(VectorEntry.State.EITHER)) {
        	long itemID = e.getKey();
        	// Get possible neighbors for user 'U'.
        	LongSet possibleNeighbors = itemDao.getUsersForItem(itemID);
        	LongIterator neighbourUsersiterator = possibleNeighbors.iterator();
        	// Map containing the userid as key and the similarity of userid with the user U as value.
    		HashMap<Long, Double> userSimilarityMap = new HashMap<Long, Double>();
    		// Iterate through the possible neighbours, calculate similarity to user 'U'
        	while(neighbourUsersiterator.hasNext()){        
        		Long neighbourUserId  = neighbourUsersiterator.next();   
        		if(neighbourUserId != user){
        			SparseVector userVectorForUserId =  getUserRatingVector(neighbourUserId);  		
        			MutableSparseVector userVectorForUserIdMutable = userVectorForUserId.mutableCopy();
        			//Get the mean centered rating vector for the user I
        			SparseVector meanCentredRatingVectorForV  = getMeanCentredRatingVector(userVectorForUserIdMutable);       
        			CosineVectorSimilarity cosineVectorSimilarty  = new CosineVectorSimilarity();
        			double similarity = cosineVectorSimilarty.similarity(meanCentredRatingVectorForU, meanCentredRatingVectorForV);
        			userSimilarityMap.put(neighbourUserId,similarity);
        		}
        	}
        	
        	// Construct the sparse vector with userSimilarityMap
    		MutableSparseVector mutableSparseVector =MutableSparseVector.create(userSimilarityMap);
        	LongArrayList sortedUserIds = mutableSparseVector.keysByValue(true);
    		double numerator = 0.0;
    		double denominator =0.0;
    		// Iterate through 30 similar users
        	for(int i =0;i<30; i++){
        		long userId  = sortedUserIds.getLong(i);
        		SparseVector userIVector = getUserRatingVector(userId);
        		//Construct MUi
        		double userImean =  userIVector.mean();
        		double rating = userIVector.get(itemID, 0);
        		if(rating != 0){
        			double difference  = rating -userImean;
            		double similarityValue = mutableSparseVector.get(userId);
            		numerator = numerator + (similarityValue * difference);
            		double absSimilarity = similarityValue;
            		// Construct absolute similarity
            		if(similarityValue<0){
            			absSimilarity = similarityValue * -1;
            		}
            		denominator = denominator + absSimilarity;
        		}  		        		
        	}
        	if(denominator != 0 ){
        		prediction = meanOfUserU + (numerator/denominator);
        	}
        	else{
        		prediction = meanOfUserU;
        	}
        	// Setting back the score to the user. 
        	scores.set(e.getKey(), prediction);        	
        }        
    }

    /* 
     * Takes the mutable sparse vector as argument and returns the mean-centered sparse matrix.
     */
    private SparseVector getMeanCentredRatingVector(MutableSparseVector userVectorForUserIdMutable) {
    	double mean  = userVectorForUserIdMutable.mean();
		Iterator<VectorEntry> itr= userVectorForUserIdMutable.iterator();    
		HashMap<Long, Double> meanCentredMap = new HashMap<Long, Double>();
		while(itr.hasNext()){
			VectorEntry key = itr.next();
			double rating = userVectorForUserIdMutable.get(key);
			double difference  = rating - mean;
    		meanCentredMap.put(key.getKey(), difference);
			//userVectorForUserIdMutable.add(key.getKey(), difference);
		}
		MutableSparseVector  meanCentereSparseVector  = MutableSparseVector.create(meanCentredMap);
		return meanCentereSparseVector.immutable();
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
    
}
