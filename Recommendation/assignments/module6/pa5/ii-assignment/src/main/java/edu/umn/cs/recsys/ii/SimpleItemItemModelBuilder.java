package edu.umn.cs.recsys.ii;

import com.google.common.collect.ImmutableMap;

import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSortedSet;

import org.grouplens.lenskit.collections.LongUtils;
import org.grouplens.lenskit.core.Transient;
import org.grouplens.lenskit.cursors.Cursor;
import org.grouplens.lenskit.data.dao.ItemDAO;
import org.grouplens.lenskit.data.dao.UserEventDAO;
import org.grouplens.lenskit.data.event.Event;
import org.grouplens.lenskit.data.history.RatingVectorUserHistorySummarizer;
import org.grouplens.lenskit.data.history.UserHistory;
import org.grouplens.lenskit.scored.ScoredId;
import org.grouplens.lenskit.scored.ScoredIdListBuilder;
import org.grouplens.lenskit.scored.ScoredIds;
import org.grouplens.lenskit.util.TopNScoredItemAccumulator;
import org.grouplens.lenskit.vectors.ImmutableSparseVector;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.SparseVector;
import org.grouplens.lenskit.vectors.VectorEntry;
import org.grouplens.lenskit.vectors.similarity.CosineVectorSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SimpleItemItemModelBuilder implements Provider<SimpleItemItemModel> {
    private final ItemDAO itemDao;
    private final UserEventDAO userEventDao;
    private static final Logger logger = LoggerFactory.getLogger(SimpleItemItemModelBuilder.class);;
    private static final CosineVectorSimilarity COS_VS = new CosineVectorSimilarity();


    @Inject
    public SimpleItemItemModelBuilder(@Transient ItemDAO idao,
                                      @Transient UserEventDAO uedao) {
        itemDao = idao;
        userEventDao = uedao;
    }


    
    @Override
    public SimpleItemItemModel get() {
        // Get the transposed rating matrix
        // This gives us a map of item IDs to those items' rating vectors
        Map<Long, ImmutableSparseVector> itemVectors = getItemVectors();

        // Get all items - you might find this useful
        LongSortedSet items = LongUtils.packedSet(itemVectors.keySet());
        // Map items to vectors of item similarities
        Map<Long,MutableSparseVector> itemSimilarities = new HashMap<Long, MutableSparseVector>();

        // TODO Compute the similarities between each pair of items
        // It will need to be in a map of longs to lists of Scored IDs to store in the model
        
        //TopNScoredItemAccumulator accumulator = new TopNScoredItemAccumulator(0);

      //accumulate
      //accumulator.put(itemId, similarity);

      //get the final list of sorted neighbors
      //List<ScoredId> similarities = accumulator.finish();
        
        Map<Long,List<ScoredId>> neighborhoods = new HashMap<Long, List<ScoredId>>();
        for (long item_1  : items) {
        	ScoredIdListBuilder slb = new ScoredIdListBuilder();
        	
        	//TopNScoredItemAccumulator accumulator = new TopNScoredItemAccumulator(items.size()-1);

        	
        	for (long item_2 : items) {
    			if ((long)item_1==(long)item_2)
    				continue;
    			
    			
    			
    			ImmutableSparseVector iVec_1 = itemVectors.get(item_1);
    			ImmutableSparseVector iVec_2 = itemVectors.get(item_2);
    			//LongSortedSet uSet_1 = LongUtils.packedSet(iVec_1.keySet());
    			//LongSortedSet uSet_2 = LongUtils.packedSet(iVec_2.keySet());
    			
    			double similarity = COS_VS.similarity(iVec_1, iVec_2);
    			
    			if (similarity > 0)
    				slb.add(item_2, similarity);
    			
    			/*System.out.println("similarity is: "+ similarity);
    			
    			Set<Long> commonUsers = new HashSet<Long>();
    			System.out.println(uSet_1.size());
    			System.out.println(uSet_2.size());
    			
    			
    			for (Long u : uSet_1) 
					if (uSet_2.contains(u))
						commonUsers.add(u);
    			
    			//MutableSparseVector rank_1 = MutableSparseVector.create(co, value)
    			MutableSparseVector m1 = MutableSparseVector.create(commonUsers, 0d);
    			MutableSparseVector m2 = MutableSparseVector.create(commonUsers, 0d);
    			for (Long u : commonUsers) {
					m1.add(u, iVec_1.get(u));
					m2.add(u, iVec_2.get(u));
				}
    			similarity = COS_VS.similarity(iVec_1, iVec_2);
    			System.out.println("similarity on commodn set is: "+ similarity);
    			
    			
    			System.out.println(iVec_1.countCommonKeys(iVec_2));
    			
    			System.out.println(commonUsers.size());
    			
    			
    			System.out.println(commonUsers.toString());
    			System.exit(0);*/
    			
    			
    			
    		}
        	
        	MyScoreIdComparator myc = new MyScoreIdComparator();
        	ScoredIdListBuilder lb = slb.sort(myc);
        	
        	neighborhoods.put(item_1, lb.finish());
        	//System.out.println("size= "+neighborhoods.get(item_1).size());
		} 
        
        //System.out.println(neighborhoods.get(k));
        
        return new SimpleItemItemModel(neighborhoods);
    }

    /**
     * Load the data into memory, indexed by item.
     * @return A map from item IDs to item rating vectors. Each vector contains users' ratings for
     * the item, keyed by user ID.
     */
    public Map<Long,ImmutableSparseVector> getItemVectors() {
        // set up storage for building each item's rating vector
        LongSet items = itemDao.getItemIds();
        // map items to maps from users to ratings
        Map<Long,Map<Long,Double>> itemData = new HashMap<Long, Map<Long, Double>>();
        for (long item: items) {
            itemData.put(item, new HashMap<Long, Double>());
        }
        // itemData should now contain a map to accumulate the ratings of each item

        // stream over all user events
        Cursor<UserHistory<Event>> stream = userEventDao.streamEventsByUser();
        try {
            for (UserHistory<Event> evt: stream) {
                MutableSparseVector vector = RatingVectorUserHistorySummarizer.makeRatingVector(evt).mutableCopy();
                // vector is now the user's rating vector
                // TODO Normalize this vector and store the ratings in the item data
                double avg = vector.mean();
                vector.add(-avg);
                long userId = evt.getUserId();
                for (VectorEntry item : vector) {
					itemData.get(item.getKey()).put(userId, item.getValue());
				}
                //System.out.println(vector.size());
                
            }
        } finally {
            stream.close();
        }

        // This loop converts our temporary item storage to a map of item vectors
        Map<Long,ImmutableSparseVector> itemVectors = new HashMap<Long, ImmutableSparseVector>();
        for (Map.Entry<Long,Map<Long,Double>> entry: itemData.entrySet()) {
            MutableSparseVector vec = MutableSparseVector.create(entry.getValue());
            itemVectors.put(entry.getKey(), vec.immutable());
        }
        return itemVectors;
    }
}
