package TicTac;

import java.util.Arrays;

public class Projection {

	public Coord[] cells;
	
	public Projection(Coord[] cells){
		this.cells = cells;
	}
	
	public String toString(){
		String result = "";
		for(int i=0; i<cells.length; i++){
			result += cells[i]+" \t";
		}
		return result;
	}

}
