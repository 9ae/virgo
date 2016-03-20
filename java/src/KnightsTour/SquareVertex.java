package KnightsTour;

public class SquareVertex {
	
	public final int row;
	public final int col;
	
	private boolean visited;
	
	SquareVertex(int x, int y){
		this.row = x;
		this.col = y;
		visited = false;
	}
	
	@Override
	public String toString(){
		return "<"+row+":"+col+">";
	}

}
