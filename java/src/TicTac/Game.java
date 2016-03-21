package TicTac;

import java.util.ArrayList;

public class Game {
	
	private int[][] board;
	public final int N;
	
	private boolean goX = false;
	private int winner = 0;
	
	private Ai ai;
	
	public Game(int boardSize){
		N = boardSize;
		board = new int[N][N];
		ai = new DummyAi(this);
	}
	
	public int at(int x, int y){
		return board[x][y];
	}
	
	public void placeMark(int x, int y){
		if(board[x][y]!=0){
			return;
		}
		board[x][y] = goX ? 1: -1;
		goX = !goX;
		if(goX){
			ai.move();
		}
		ai.checkWin();
	}
	
	public String getMark(int x, int y){
		String result;
		switch(board[x][y]){
			case -1:
				result = "O";
				break;
			case 1:
				result = "X";
				break;
			default:
				result = null;
				break;
		}
		return result;
	}
	
	public void printBoard(){
		System.out.println("====");
		for(int y=0; y<N; y++){
			String row = "";
			for(int x=0; x<N; x++){
				row += board[x][y]+" ";
			}
			System.out.println(row);
		}
	}
	
	public boolean handleSum(int sum){
		if(Math.abs(sum)==N){
			winner = (sum>0 ? 1 : -1);
			return true;
		}
		return false;
	}
	
	public String getWinner(){
		if(winner==0){
			return null;
		}
		return winner==1 ? "X" : "O";
	}

}
