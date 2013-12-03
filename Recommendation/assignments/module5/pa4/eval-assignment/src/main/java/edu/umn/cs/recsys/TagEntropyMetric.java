package edu.umn.cs.recsys;

import com.google.common.collect.ImmutableList;

import edu.umn.cs.recsys.dao.ItemTagDAO;

import org.grouplens.lenskit.core.LenskitRecommender;
import org.grouplens.lenskit.eval.algorithm.AlgorithmInstance;
import org.grouplens.lenskit.eval.data.traintest.TTDataSet;
import org.grouplens.lenskit.eval.metrics.AbstractTestUserMetric;
import org.grouplens.lenskit.eval.metrics.TestUserMetricAccumulator;
import org.grouplens.lenskit.eval.metrics.topn.ItemSelectors;
import org.grouplens.lenskit.eval.traintest.TestUser;
import org.grouplens.lenskit.scored.ScoredId;
import org.grouplens.lenskit.vectors.MutableSparseVector;
import org.grouplens.lenskit.vectors.VectorEntry;

import javax.annotation.Nonnull;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A metric that measures the tag entropy of the recommended items.
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class TagEntropyMetric extends AbstractTestUserMetric {
    private final int listSize;
    private final List<String> columns;

    /**
     * Construct a new tag entropy metric.
     * 
     * @param nitems The number of items to request.
     */
    public TagEntropyMetric(int nitems) {
        listSize = nitems;
        // initialize column labels with list length
        columns = ImmutableList.of(String.format("TagEntropy@%d", nitems));
    }

    /**
     * Make a metric accumulator.  Metrics operate with <em>accumulators</em>, which are created
     * for each algorithm and data set.  The accumulator measures each user's output, and
     * accumulates the results into a global statistic for the whole evaluation.
     *
     * @param algorithm The algorithm being tested.
     * @param data The data set being tested with.
     * @return An accumulator for analyzing this algorithm and data set.
     */
    @Override
    public TestUserMetricAccumulator makeAccumulator(AlgorithmInstance algorithm, TTDataSet data) {
        return new TagEntropyAccumulator();
    }

    /**
     * Return the labels for the (global) columns returned by this metric.
     * @return The labels for the global columns.
     */
    @Override
    public List<String> getColumnLabels() {
        return columns;
    }

    /**
     * Return the lables for the per-user columns returned by this metric.
     */
    @Override
    public List<String> getUserColumnLabels() {
        // per-user and global have the same fields, they just differ in aggregation.
        return columns;
    }


    private class TagEntropyAccumulator implements TestUserMetricAccumulator {
        private double totalEntropy = 0;
        private int userCount = 0;

        /**
         * Evaluate a single test user's recommendations or predictions.
         * @param testUser The user's recommendation result.
         * @return The values for the per-user columns.
         */
        
        private HashSet<Long> getTagIds(List<ScoredId> recommendations, ItemTagDAO tagDAO, 
        		TagVocabulary vocab){
        	
        	//MutableSparseVector tagVec = M
        	
        	HashSet<Long> tagSet = new HashSet<Long>();
        	
        	for (ScoredId sId : recommendations) {
				Long movieId = sId.getId();
				List<String> tags = tagDAO.getItemTags(movieId);
				for (String t: tags) {
					long tagId = vocab.getTagId(t);
					tagSet.add(tagId);
				}
				
			}
        	
        	return tagSet;
        	
        }
        
        private HashMap<Long, HashSet<Long>> getMovieTags(List<ScoredId> recommendations, ItemTagDAO tagDAO,
        		TagVocabulary vocab){
        	HashMap<Long, HashSet<Long>> mTags = new HashMap<Long, HashSet<Long>>();
        	
        	for (ScoredId sId : recommendations) {
				Long movieId = sId.getId();
				mTags.put(movieId, new HashSet<Long>());
				List<String> tags = tagDAO.getItemTags(movieId);
				for (String t: tags) {
					long tagId = vocab.getTagId(t);
					mTags.get(movieId).add(tagId);
				}
        	}

        	return mTags;
        }
        
        private double getPTL(long tagId, HashMap<Long, HashSet<Long>> mTags, ItemTagDAO tagDAO,
        		TagVocabulary vocab,PrintWriter w  ){
        	//long tagId = vocab.getTagId(tag);
        	double ptl = 0;
        	int lSize = mTags.size();
        	//w.println("---->> number of movoies= "+lSize);
        	
        	for (long movie : mTags.keySet()) {
        		//w.println("---->> movie = "+movie);
            	//w.println("---->> tags "+mTags.get(movie).toString());
        		if (mTags.get(movie).contains(tagId)  ){
					ptl += 1.0/(mTags.get(movie).size() * lSize );
					//w.write("----->> ptl is"+ptl);
        		}
			}
        	
        	return ptl;
        	
        }
        
        @Nonnull
        @Override
        public Object[] evaluate(TestUser testUser) {
            List<ScoredId> recommendations =
                    testUser.getRecommendations(listSize,
                                                ItemSelectors.allItems(),
                                                ItemSelectors.trainingItems());
            PrintWriter writer= null;
			/*try {
				writer = new PrintWriter("testValues.txt", "UTF-8");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            //writer.println("The first line");
            //writer.println("The second line");
            //writer.close();
            
            
            if (recommendations == null) {
                return new Object[1];
            }
            LenskitRecommender lkrec = (LenskitRecommender) testUser.getRecommender();
            ItemTagDAO tagDAO = lkrec.get(ItemTagDAO.class);
            TagVocabulary vocab = lkrec.get(TagVocabulary.class);

            double entropy = 0;

            // TODO Implement the entropy metric
            //entropy = 10;
            
            HashSet<Long> tagSet = getTagIds(recommendations, tagDAO, vocab);
            //writer.println(tagSet.size());

            HashMap<Long, HashSet<Long>> mTags = getMovieTags(recommendations, tagDAO, vocab);
            
            //writer.println("number of movies= "+mTags.size());
            //writer.println("number of movies from rec= "+recommendations.size());
            
           
            
            for (Long t : tagSet) {
            	//writer.println("calculating ptl for "+t);
            	
            	double ptl = getPTL(t, mTags, tagDAO, vocab, writer);
            	//writer.println("Ptl is "+ptl);
            	
            	entropy += -1.0*ptl*Math.log(ptl)/Math.log(2); 
            	
			}
           
            //writer.close();
            //System.exit(0);
            
            //entropy = 10;
            totalEntropy += entropy;
            userCount += 1;
            return new Object[]{entropy};
        }

        /**
         * Get the final aggregate results.  This is called after all users have been evaluated, and
         * returns the values for the columns in the global output.
         *
         * @return The final, aggregated columns.
         */
        @Nonnull
        @Override
        public Object[] finalResults() {
            // return a single field, the average entropy
            return new Object[]{totalEntropy / userCount};
        }
    }
}
