package edu.umn.cs.recsys.ii;

import java.util.Comparator;

import org.grouplens.lenskit.scored.ScoredId;

public class MyScoreIdComparator implements Comparator<ScoredId> {
	  @Override
	  public int compare(ScoredId o1, ScoredId o2) {
	       return Double.compare(o2.getScore(), o1.getScore() );
	  }


	}
