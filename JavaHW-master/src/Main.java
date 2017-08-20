import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	
	public static void main(String[] args) {
		
		Main main = new Main();
		
		main.run();
	}
	
	/*
	 * Entry point of the program that contains the logic
	 */
	public void run() {
		
		final String RUNS_UP = "RunsUp";
		final String RUNS_DOWN = "RunsDown";
		
		List<Integer> intList = new ArrayList<>();
		List<Integer> runsUp = new ArrayList<>();
		List<Integer> runsDown = new ArrayList<>();
		List<Integer> mergedList = new ArrayList<>();
		
		intList = getInputList();
		
		runsUp = findRuns(intList, RUNS_UP);
		runsDown= findRuns(intList, RUNS_DOWN);
		
		mergedList = Merge(runsUp, runsDown); 
		
		output(mergedList);
		
		return; 
		
	}
	
	/*
	 * Gets the integers from the input file and stores them into a List
	 */
	public static List<Integer> getInputList() {
		
		File inFile = new File("p01-in.txt");
		List<Integer> intList = new ArrayList<>();
		
		try {
			
			Scanner scan = new Scanner(inFile);
			
			while (scan.hasNextLine()) {
			   
			    List<String> line = Arrays.asList(scan.nextLine().split(" "));
			    List<Integer> intLine = new ArrayList<>();
			    
			    for(int i = 0; i < line.size(); i++){
			        
			    	if (line.get(i).trim().equals("")) {
			    		
			            intLine.add(0);
			    	
			    	} else {
			            
			    		intLine.add(Integer.parseInt(line.get(i).trim()));
			        
			    	}
				    
			    	intList.add(intLine.get(i));
			    }
			   
			}
		
		scan.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return intList;
	}
	
	/*
	 * For numbers that are monotonically increasing check if index <= index + 1
	 * For numbers that are monotonically decreasing check if index >= index + 1
	 * then take the value if k (number of successful checks) and add to the count list 
	 */
	public List<Integer> findRuns(List<Integer> intList, String runFlag) {
		
		int intAtIndexK; 
		int i = 0;
	    int k = 0;
		
	    List<Integer> runCountList = createArrayList(intList.size(), 0);
	    	    
		while( i < intList.size() - 1) {

				if (runFlag.equals("RunsUp") && intList.get(i) <= intList.get(i+1)) {
					
					k++;	
				
				} else if (runFlag.equals("RunsDown") && intList.get(i)  >= intList.get(i +1)) {
					
					k++; 
				
				} else {
					
					if (k != 0) {
						intAtIndexK = runCountList.get(k);
						intAtIndexK = intAtIndexK + 1; 
						runCountList.set(k, intAtIndexK);
						k = 0 ;
					} 
				
				}
			i++;
		
		}
		
		
		if (k != 0) {
			intAtIndexK = runCountList.get(k);
			intAtIndexK = intAtIndexK + 1; 
			runCountList.set(k, intAtIndexK);
			k = 0 ;
		}
		
		return runCountList;
	
	}
	
	/*
	 * Create an array list with an initial length value and elements initialized to 0 
	 */
	public List<Integer> createArrayList(int listSize, int initVal){		
		List<Integer> intList = new ArrayList<>();
		
		for(int i = 0; i < listSize ; i++ ) {
			intList.add( i, initVal);
		}

		return intList; 
	}
	
	/*
	 * takes 2 lists of integers and adds the numbers of each at the current index
	 */
	public static List<Integer> Merge(List<Integer> runsUpList, List<Integer> runsDownList) {
	     List<Integer> mergedList = new ArrayList<>();
	     
	     for (int i = 0; i < runsUpList.size(); i++) {
	    	 	
	    	 	int sum = 0;
	    	 	
	    	 	sum = runsUpList.get(i) + runsDownList.get(i); 
	    	 	mergedList.add(sum);
	    
	     }    
	     
	     return mergedList; 
	}
	
	/*
	 * Creates the output file 
	 */
	public static void output(List<Integer> intList) {
		
		try{ 

			File outFile = new File("p01-out.txt");
			PrintWriter outFilePrint = new PrintWriter(outFile);
			
			int intListTotal = getRunsTotal(intList);
			
			outFilePrint.print("runs_total, " + intListTotal  + " \n");
			
			for (int i = 1; i < intList.size(); i++) {
				outFilePrint.print("runs_" + i + ", " + intList.get(i) + "\n");
			}
			
			outFilePrint.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * get the total of the ints in the collection. 
	 */
	public static int getRunsTotal(List<Integer> intList) {
		int total = 0;
		
		for(int i : intList) {
			total += i;
		}
		return total;
	}
	
}

