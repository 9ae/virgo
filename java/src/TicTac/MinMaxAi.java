package TicTac;

public class MinMaxAi extends Ai{
	
	private int playerState;
	
	public MinMaxAi(Game game){
		super(game);
		
		playerState = 1;
	}

	@Override
	public void move() {
		//get board from game.
		int[][] board = game.copyBoard();
		
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board.length; j++){
				if(board[i][j]==0){
					State adam = new State(board, 1, j, i);
				}
			}
		}
		
		//get list of empty spaces
		// make states for them
		
		//compute state's scores
		
	}

	@Override
	public boolean checkWin() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void togglePlayerState(){
		playerState = -1*playerState;
	}

}
