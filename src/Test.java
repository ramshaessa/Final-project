public class Test{
	
	public static void main(String[] args) {
		
		
		GameBoard board = new GameBoard("maze.txt");
		//board.printBoard();
		
		Game mazeGame = new Game (board);
		mazeGame.start();
		
	}

}


