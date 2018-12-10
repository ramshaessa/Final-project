
	import java.io.*;
	import java.util.*;

	public class GameBoard {
		
		private int [][] board;
		
		
		GameBoard(int [][] board) {
			this.board = board;
		}
		
		GameBoard(String filename) {
			this.board = makeBoard(filename);
		}
		
		
		    public int[][] makeBoard(String filename) {
	        try {
	            BufferedReader myReader = new BufferedReader(new FileReader(filename));
	            String line = myReader.readLine();
	            String[] splitString, splitPrereq;
				ArrayList<Integer []> nums = new ArrayList<>();
	            
	            System.out.println("Begin Reading");
	           
				while(line != null)
	            {
					
	                String oneLine = line;
	                ArrayList<String> readline = new ArrayList<String>();
	                splitString = oneLine.split(" ");
					Integer [] linenums = new Integer[splitString.length];
					for (int i = 0; i < splitString.length; i++) {
						try {
							linenums[i] = Integer.parseInt(splitString[i]);
						}
						catch (Exception e) {
							System.out.println("Houston - we have a problem");
						}
					}
					
					nums.add(linenums);
	                line = myReader.readLine();
	                
	            }
	            myReader.close();
				
				int[][] finalL = new int[nums.size()][];
				for (int i = 0; i < finalL.length; i++) {
					int [] newline = new int[nums.get(i).length];
					for (int j = 0; j < newline.length; j++) {
						newline[j] = nums.get(i)[j];
					}
					finalL[i] = newline;
				}
				
				return finalL;
				
	        }
	        catch(Exception e)
	        {
				System.out.println("");
				return new int[][] {{-1}};
	        }
	    }
		
		public int[][] getBoard() {
			return board;
		}
		
		public void printBoard() {
			for (int [] arr: board) {
				for (int a: arr) {
					System.out.print(a + " ");
				}
				System.out.println();
			}
		}
	}
		

