package TicTac;

import java.util.ArrayList;

public class Game {
	
	private int[][] board;
	private final int N;
	
	private boolean goX = false;
	
	private int winner = 0;
	
	private ArrayList<Projection> projections;
	private Coord[] goodSpots;
	
	public Game(int boardSize){
		N = boardSize;
		board = new int[N][N];
		projections = new ArrayList<Projection>();
		makeProjections();
		
		goodSpots = new Coord[5];
		goodSpots[0] = new Coord(N/2, N/2);
		goodSpots[1] = new Coord(0,0);
		goodSpots[2] = new Coord(N-1, 0);
		goodSpots[3] = new Coord(0, N-1);
		goodSpots[4] = new Coord(N-1, N-1);
	}
	
	public void placeMark(int x, int y){
		board[x][y] = goX ? 1: -1;
		goX = !goX;
		if(goX){
			aiMove();
		}
	}
	
	private void makeProjections(){
		
		Coord[] slashCells = new Coord[N];
		for(int i=0; i<N; i++){
			slashCells[i] = new Coord(i, i);
		}
		projections.add(new Projection(slashCells));
		
		int i=N-1; int j=0;
		Coord[] bsCells = new Coord[N];
		while(i>=0 && j<N){
			bsCells[j] = new Coord(i, j);
			i--;
			j++;
		}
		projections.add(new Projection(bsCells));
		
		for(int r=0; r<N; r++){
			Coord[] cells = new Coord[N];
			for(int c=0; c<N; c++){
				cells[c] = new Coord(c, r);
			}
			projections.add(new Projection(cells));
		}
		
		for(int r=0; r<N; r++){
			Coord[] cells = new Coord[N];
			for(int c=0; c<N; c++){
				cells[c] = new Coord(r, c);
			}
			projections.add(new Projection(cells));
		}
		
	}
	
	private int sumProjection(Projection pj){
		int sum = 0;
		for(int i=0; i<pj.cells.length; i++){
			Coord c = pj.cells[i];
			sum += board[c.x][c.y];
		}
		return sum;
	}
	
	private Coord isNear(Projection pj, int player){
		int count = 0;
		Coord freeSpace = null;
		for(int i=0; i<pj.cells.length; i++){
			Coord c = pj.cells[i];
			int v = board[c.x][c.y];
			if(v==player){
				count++;
			} else if(v==0){
				freeSpace = c;
			} else {
				break;
			}
		}
		if(count!=(N-1)){
			freeSpace = null;
		}
		return freeSpace;
	}
	
	private boolean handleSum(int sum){
		if(Math.abs(sum)==N){
			winner = sum>0 ? 1 : -1;
			return true;
		}
		return false;
	}
	
	private boolean checkWin(){
		boolean r = false;
		for(Projection pj: projections){
			boolean win = handleSum(sumProjection(pj));
			if(win){
				r = true;
				break;
			}
		}
		return r;
	}
	
	private boolean checkNearWins(int player){
		boolean done = false;
		for(Projection pj: projections){
			Coord freeSpace = isNear(pj, player);
			if(freeSpace!=null){
				placeMark(freeSpace.x, freeSpace.y);
				done = true;
				break;
			}
		}
		return done;
	}
	
	private boolean checkNiceSpots(){
		boolean done = false;
		for(int i=0; i<goodSpots.length; i++){
			Coord c = goodSpots[i];
			if(board[c.x][c.y]==0){
				placeMark(c.x, c.y);
				done = true;
				break;
			}
		}
		return done;
	}
	
	private void placeAnywhere(){
		for(int y=0; y<N; y++){
			for(int x=0; x<N; x++){
				if(board[x][y]==0){
					placeMark(x, y);
					return;
				}
			}
		}
	}

	private void aiMove(){
		
		if(!checkNearWins(1)){
			if(!checkNearWins(-1)){
				if(!checkNiceSpots()){
					placeAnywhere();
				}
			}
		}
		
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

}
