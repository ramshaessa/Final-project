import java.util.*;
import java.util.Timer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Game {
	
	int[][] maze;
	int startCol;
	int startRow;
	boolean timeOut;
	boolean[][] visit;
	ArrayList<String> path = new ArrayList<String>();

	
	Game(GameBoard mazeBoard) {
		this.timeOut = false;
		int[][] mazeCopy = new int[mazeBoard.getBoard().length][];
		for(int i = 0; i < mazeCopy.length; i++) {
			int [] lineCopy = new int[mazeBoard.getBoard()[i].length];
			for (int j = 0; j < lineCopy.length; j++) {
				lineCopy[j] = mazeBoard.getBoard()[i][j];
				if(lineCopy[j] == 2) {
					this.startRow = i;
					this.startCol = j;
				}
			}
			mazeCopy[i] = lineCopy;
		}
		this.maze = mazeCopy;
		visit = new boolean[mazeCopy.length][mazeCopy[1].length];
		
	}
	
		public void printMaze() {
		for (int [] arr: maze) {
			for (int a: arr) {
				System.out.print(a + " ");
			}
			System.out.println();
		}
	}
	
	void solveMaze() {
		solve(startRow, startCol, "");
		
	}
	
	 void solve(int a, int b, String path1) {

		visit[a][b] = true;
		if (maze[a][b] != 3) {
if ((((b + 1) < maze[0].length) && maze[a][b + 1] != 1) && (maze[a][b + 1] != 7) && (visit[a][b+1] == false)) {
			solve(a, b + 1, path1 + "left ");
		} 
	if (((a + 1 < maze.length) && maze[a + 1][b] != 1) && (maze[a + 1][b] != 7) && (visit[a + 1][b] == false)) {
			solve(a + 1, b, path1 + "down ");
		} 
		if (((a > 1) && maze[a-1][b] != 1) && (maze[a-1][b] != 7) && (visit[a-1][b] == false)) {
			solve(a-1, b, path1 + "right ");
		} 
		if (((b > 0) && maze[a][b-1] != 1) && ( maze[a][b-1] != 7) && (visit[a][b-1] == false)) {
			solve(a, b-1, path1 + "up ");
		}
		} 
		else {
		path.add(path1);
		path1 = "";
		System.out.println("Cupcake located");
		System.out.println(path);
		}
}
	

	
	void start() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int count = 0;
			@Override
			public void run() {
				System.out.println("time left: " + (60 - ++count));
  		}
		}, 1000, 1000);
		ExecutorService service = Executors.newSingleThreadExecutor();

			try {
				Runnable r = new Runnable() {
	@Override
	public void run() {
		int currCol = startCol;
		int currRow = startRow;
		System.out.println("Welcome to the Guessing Game");
    	System.out.println("Type \"quit\" to quit at anytime.");
    	Scanner scan  = new Scanner(System.in);
    
		float starttime = System.nanoTime();
		while (!(maze[currRow][currCol] == 3)) {
			
			if(timeOut == true) {
				break;
			}
			printMaze();
			System.out.println("You are the 2. Where do you want to move to get to the cupcake (3)? Enter left, down, right, or up.");
		
		String guess = scan.next(); //make a new method. 
        switch (guess) {
            case "left" :
				if(currCol == 0) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow][currCol - 1] == 1) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow][currCol - 1] == 3) {
					maze[currRow][currCol] = 0;
					currCol = currCol - 1;
					System.out.println("You won!");
					float endtime = System.nanoTime();
					System.out.println("Your time is");
					System.out.println(endtime - starttime);
					timer.cancel();
				}
				else {
					maze[currRow][currCol] = 0;
					maze[currRow][currCol - 1] = 2;
					currCol = currCol - 1;	
				}
                break;
            case "down" :
                if(currRow == (maze.length - 1) ) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow + 1][currCol] == 1) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow + 1][currCol] == 3) {
					maze[currRow][currCol] = 0;
					currRow = currRow + 1;
					System.out.println("You won!");
					float endtime = System.nanoTime();
					System.out.println("Your time is");
					System.out.println(endtime - starttime);
					timer.cancel();
				}
				else {
					maze[currRow][currCol] = 0;
					maze[currRow + 1][currCol] = 2;
					currRow = currRow + 1;	
				}
                break;
            case "right" :
                if(currCol == (maze[currRow].length - 1) ) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow][currCol + 1] == 1) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow][currCol + 1] == 3) {
					maze[currRow][currCol] = 0;
					currCol = currCol + 1;
					System.out.println("You won!");
					float endtime = System.nanoTime();
					System.out.println("Your time is");
					System.out.println(endtime - starttime);
					timer.cancel();
				}
				else {
					maze[currRow][currCol] = 0;
					maze[currRow][currCol + 1] = 2;
					currCol = currCol + 1;	
				}
                break;
            case "up" :
				if(currRow == 0) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow - 1][currCol] == 1) {
					System.out.println("You cannot move there");
				}
				else if(maze[currRow - 1][currCol] == 3) {
					maze[currRow][currCol] = 0;
					currRow = currRow - 1;
					System.out.println("You won!");
					float endtime = System.nanoTime();
					System.out.println("Your time is");
					System.out.println((endtime - starttime));
					timer.cancel();
				}

				else {
					maze[currRow][currCol] = 0;
					maze[currRow - 1][currCol] = 2;
					currRow = currRow - 1;	
				}
                break;           
        }
			
			
		
		}
	}
				};

				Future<?> f = service.submit(r);
				f.get(1, TimeUnit.MINUTES);     // attempt the task for two minutes
			}
			catch (final InterruptedException e) {
				System.out.println("Program was quit");
			}
			catch (final TimeoutException e) {
				timer.cancel();
				System.out.println("You took too long!");
				System.out.println("Computer will solve now");
				solveMaze();
				System.out.println("Press any key to exit!");
				timeOut = true;
			}
			catch (final ExecutionException e) {
				System.out.println("Something went wrong");
			}
			finally {
				service.shutdown();
			}
	}
}