package TicTac;


import processing.core.*;

public class BoardView extends PApplet{
	
	int boxSize = 150;
	static int gridSize = 3;
	int screenSize;
	static Game game;
	String winner;
	
	public void settings() {
		screenSize = boxSize*gridSize;
	    size(screenSize, screenSize);
	    
	  }
	
	public void setup(){
		background(0);
		textSize((int)((float)boxSize*0.9));
		textAlign(LEFT, TOP);
		noLoop();
	}
	
	public void drawGrid(){
		stroke(255);
		
		for(int i=1; i<gridSize; i++){
			line(boxSize*i, 0, boxSize*i, screenSize);
			line(0, boxSize*i, screenSize, boxSize*i);
		}

	}
	
	public void drawBoard(){
		for(int x=0; x<gridSize; x++){
			for(int y=0; y<gridSize; y++){
				String mark = game.getMark(x,y);
				if(mark==null){
					continue;
				}
				text(mark, x*boxSize+10, y*boxSize+10);
			}
		}
	}

	  public void draw() {
			  drawGrid();
			  drawBoard();
		winner = game.getWinner();
		  if(winner!=null){
			  fill(color(255,0,0, 50));
			  textSize(screenSize);
			  text(winner, 0, 0);
		  }
	  }
	  
	  public void mousePressed(){
		  if(winner!=null){
			  return;
		  }
		  int x = pmouseX/boxSize;
			int y = pmouseY/boxSize;

			game.placeMark(x, y);
			redraw();
	  }
	  
	  public static void main(String[] args) {
		  	game = new Game(gridSize);
			PApplet.main(new String[] { "--present", "TicTac.BoardView" });
		}

}
