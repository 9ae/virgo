package TicTac;

public class State {

	private int[][] board;
	private int score;
	private State next;
	public final int player;
	public final int moveX;
	public final int moveY;
	
	public State(int[][] board, int player, int x, int y){
		this.board = Game.copyArray(board);
		this.player = player;
		moveX = x;
		moveY = y;
		score = 0;
		next = null;
		
		this.board[x][y] = player;
	}
	
	
}
