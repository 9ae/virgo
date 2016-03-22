package TicTac;

import java.util.ArrayList;

public class Ai {
	
	protected ArrayList<Projection> projections;
	protected Game game;
	
	Ai(Game game){
		this.game = game;
		projections = new ArrayList<Projection>();
		
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
	
	public void move(){
		
	}

	public boolean checkWin(){
		return false;
	}

}
