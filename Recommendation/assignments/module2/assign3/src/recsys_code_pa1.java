package src;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

import com.sun.tools.javac.code.Attribute.Array;

import cern.colt.function.DoubleDoubleFunction;
import cern.colt.matrix.impl.SparseDoubleMatrix1D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;

class NonPersRecommender {
	
	
	private  String ratingsFiles;
	
	private SparseDoubleMatrix2D ratingsMatrix;
	private HashMap<Integer, Integer> userMap = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> movieMap = new HashMap<Integer, Integer>();
	
	private HashMap<Integer, Integer> userMapR = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> movieMapR = new HashMap<Integer, Integer>();
	
	
	public NonPersRecommender(String ratingsFileName){
		this.setRatingsFiles(ratingsFileName);
		File file = new File(ratingsFileName);
		BufferedReader bufRdr;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
		//} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
		//	e1.printStackTrace();
		//}
		String line = null;
		
		//read each line of text file
		//try {
		HashMap<Integer, ArrayList<Integer>> tempMap = new HashMap<Integer, ArrayList<Integer>>();
		HashSet<Integer> tempMovieSet = new HashSet<Integer>();
		while((line = bufRdr.readLine()) != null)
			{	
				StringTokenizer st = new StringTokenizer(line,",");
				int userId = Integer.parseInt(st.nextToken());
				int movieId = Integer.parseInt(st.nextToken());
				if (!tempMap.containsKey(userId)){
					
					tempMap.put(userId, new ArrayList<Integer>());
				}
				
				tempMovieSet.add(movieId);
				tempMap.get(userId).add(movieId);
				
							
			}
		
		
		int row = 0;
		int col = 0;
		this.ratingsMatrix = new SparseDoubleMatrix2D(tempMap.keySet().size(), tempMovieSet.size());
		for ( Integer userId: tempMap.keySet()){
			if (!userMap.containsKey(userId)){
				userMap.put(userId, row);
				userMapR.put(row, userId);
				row++;
			}
			int userRowId = userMap.get(userId);
			
			for (Integer movieId: tempMap.get(userId)){
				if (!movieMap.containsKey(movieId)){
					movieMap.put(movieId, col);
					movieMapR.put(col,movieId);
					
					col++;
				}
				
			int movieColId = movieMap.get(movieId);	
			
			this.ratingsMatrix.set(userRowId, movieColId, 1);
			}
		}
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public String getRatingsFiles() {
		return ratingsFiles;
	}



	public void setRatingsFiles(String ratingsFiles) {
		this.ratingsFiles = ratingsFiles;
	}


	public ArrayList<String> getRecommSimple(int[] myMovies, int numMostSimilar){
		ArrayList<String> result = new ArrayList<String>();
		int numRows = this.ratingsMatrix.rows();
		int[] rows = new int[numRows];
		for (int i = 0; i < numRows; i++) {
			rows[i]=i;
		}
		
		int[] cols = new int[myMovies.length];
		for (int i = 0; i < myMovies.length; i++) {
			cols[i] = this.movieMap.get(myMovies[i]);
			
		}
		
		SparseDoubleMatrix2D myMovieMatarix = (SparseDoubleMatrix2D) this.ratingsMatrix.viewSelection(rows,cols).viewDice().copy();
		
		SparseDoubleMatrix2D resultMatrix = new SparseDoubleMatrix2D(myMovieMatarix.rows(), this.ratingsMatrix.columns());
		myMovieMatarix.zMult(this.ratingsMatrix, resultMatrix);
		
		cern.jet.math.Functions F = cern.jet.math.Functions.functions;

		for (int i = 0; i < myMovies.length; i++){
			double sum = (double)this.ratingsMatrix.viewColumn(cols[i]).aggregate(F.plus,F.identity);
			HashMap<Integer, Integer> tempHashMap = new HashMap<Integer, Integer>();
			for (int j = 0; j < resultMatrix.columns(); j++) {
				tempHashMap.put((int)resultMatrix.get(i, j), j);
				
			}
			String s = Integer.toString(myMovies[i]);
			SparseDoubleMatrix1D sd = (SparseDoubleMatrix1D) resultMatrix.viewRow(i).viewSorted().viewFlip().copy();
			
			for (int k = 1; k < numMostSimilar+1; k++) {
				int p1 = (int)sd.get(k);
				int thisMovieId = this.movieMapR.get(tempHashMap.get(p1));
				double rate= sd.get(k)/sum;
				s +=","+Integer.toString(thisMovieId)+","+Double.toString(Math.round(rate*100.0)/100.0);
				
			}
			
			result.add(s);
		}
		return result;
		
	}

	public ArrayList<String> getRecommSimpleAdvanced(int[] myMovies, int numMostSimilar){
		ArrayList<String> result = new ArrayList<String>();
		int numRows = this.ratingsMatrix.rows();
		int[] rows = new int[numRows];
		
		for (int i = 0; i < numRows; i++) {
			rows[i]=i;
		}
		
		int[] cols = new int[myMovies.length];
		for (int i = 0; i < myMovies.length; i++) {
			cols[i] = this.movieMap.get(myMovies[i]);
			
		}
		cern.jet.math.Functions F = cern.jet.math.Functions.functions;
		SparseDoubleMatrix2D x = (SparseDoubleMatrix2D) this.ratingsMatrix.viewSelection(rows,cols).viewDice().copy();
		
		SparseDoubleMatrix2D xMuly = new SparseDoubleMatrix2D(x.rows(), this.ratingsMatrix.columns());
		x.zMult(this.ratingsMatrix, xMuly);
		
		SparseDoubleMatrix2D xNeg = (SparseDoubleMatrix2D) x.copy();
		
		xNeg.assign(F.chain(F.mult(-1), F.plus(-1)));//, F.mult(-1))).copy();
		SparseDoubleMatrix2D xNegMuly = new SparseDoubleMatrix2D(xNeg.rows(), this.ratingsMatrix.columns());
		xNeg.zMult(this.ratingsMatrix, xNegMuly);
		
		for (int i = 0; i < myMovies.length; i++){
			double sum = (double)x.viewRow(i).aggregate(F.plus,F.identity);
			double sumNeg = (double)xNeg.viewRow(i).aggregate(F.plus,F.identity);
			
			SparseDoubleMatrix1D res = (SparseDoubleMatrix1D) xMuly.viewRow(i).assign(F.div(sum)).assign(xNegMuly.viewRow(i).assign(F.div(sumNeg)), F.div);
			HashMap<Double, Integer> tempHashMap = new HashMap<Double, Integer>();
			for (int j = 0; j < res.size(); j++) {
				tempHashMap.put(res.get(j), j);
				
			}
			
			String s = Integer.toString(myMovies[i]);
			SparseDoubleMatrix1D sd = (SparseDoubleMatrix1D) res.viewSorted().viewFlip().copy();
			
			for (int k = 1; k < numMostSimilar+1; k++) {
				double p1 = sd.get(k);
				int thisMovieId = this.movieMapR.get(tempHashMap.get(p1));
				double rate= sd.get(k);
				s +=","+Integer.toString(thisMovieId)+","+Double.toString(Math.round(rate*100.0)/100.0);
				
			}
			System.out.println(s);
			result.add(s);
			//System.out.println(res.viewSorted().viewFlip());
		
		}
		
		
		
		//System.out.println(xNeg.toString());
		//System.out.println(x.toString());
		
		return result;
		

		
	}
	
	public static void main(String[] args) {
	// Movies array contains the movie IDs of the top 5 movies.
	
	NonPersRecommender nonRec = new NonPersRecommender(args[0]);
	
	//System.out.println(nonRec.ratingsMatrix.viewPart(0,0,2,nonRec.ratingsMatrix.columns()));
	//ArrayList<String> result = nonRec.getRecommSimple(new int[]{1891,453,1892}, 5);
	ArrayList<String> result = nonRec.getRecommSimpleAdvanced(new int[]{1891,453,1892}, 5);
	
	// Write the top 5 movies, one per line, to a text file.
	try {
	    PrintWriter writer = new PrintWriter("pa1-result-advanced.txt","UTF-8");
       for(String s:result)
	    
	    {
		writer.println(s);
	    }

	    writer.close();
	    
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
}