package TicTac;

import java.util.ArrayList;

public class DummyAi implements Ai {
	
	private ArrayList<Projection> projections;
	private Coord[] goodSpots;
	private Game game;
	
	public DummyAi(Game game) {
		
		this.game = game; 
		
		projections = new ArrayList<Projection>();
		makeProjections();
		
		goodSpots = new Coord[5];
		goodSpots[0] = new Coord(game.N/2, game.N/2);
		goodSpots[1] = new Coord(0,0);
		goodSpots[2] = new Coord(game.N-1, 0);
		goodSpots[3] = new Coord(0, game.N-1);
		goodSpots[4] = new Coord(game.N-1, game.N-1);
		
	}
	
	private void makeProjections(){
		
		Coord[] slashCells = new Coord[game.N];
		for(int i=0; i<game.N; i++){
			slashCells[i] = new Coord(i, i);
		}
		projections.add(new Projection(slashCells));
		
		int i=game.N-1; int j=0;
		Coord[] bsCells = new Coord[game.N];
		while(i>=0 && j<game.N){
			bsCells[j] = new Coord(i, j);
			i--;
			j++;
		}
		projections.add(new Projection(bsCells));
		
		for(int r=0; r<game.N; r++){
			Coord[] cells = new Coord[game.N];
			for(int c=0; c<game.N; c++){
				cells[c] = new Coord(c, r);
			}
			projections.add(new Projection(cells));
		}
		
		for(int r=0; r<game.N; r++){
			Coord[] cells = new Coord[game.N];
			for(int c=0; c<game.N; c++){
				cells[c] = new Coord(r, c);
			}
			projections.add(new Projection(cells));
		}
		
	}
	
	private int sumProjection(Projection pj){
		int sum = 0;
		for(int i=0; i<pj.cells.length; i++){
			Coord c = pj.cells[i];
			sum += game.at(c.x,c.y);
		}
		return sum;
	}
	
	private Coord isNear(Projection pj, int player){
		int count = 0;
		Coord freeSpace = null;
		for(int i=0; i<pj.cells.length; i++){
			Coord c = pj.cells[i];
			int v = game.at(c.x,c.y);
			if(v==player){
				count++;
			} else if(v==0){
				freeSpace = c;
			} else {
				break;
			}
		}
		if(count!=(game.N-1)){
			freeSpace = null;
		}
		return freeSpace;
	}
	
	@Override
	public boolean checkWin(){
		boolean r = false;
		for(Projection pj: projections){
			boolean win = game.handleSum(sumProjection(pj));
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
				game.placeMark(freeSpace.x, freeSpace.y);
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
			if(game.at(c.x, c.y)==0){
				game.placeMark(c.x, c.y);
				done = true;
				break;
			}
		}
		return done;
	}
	
	private void placeAnywhere(){
		for(int y=0; y<game.N; y++){
			for(int x=0; x<game.N; x++){
				if(game.at(x,y)==0){
					game.placeMark(x, y);
					return;
				}
			}
		}
	}

	@Override
	public void move() {
		if(!checkNearWins(1)){
			if(!checkNearWins(-1)){
				if(!checkNiceSpots()){
					placeAnywhere();
				}
			}
		}
		
	}

}
