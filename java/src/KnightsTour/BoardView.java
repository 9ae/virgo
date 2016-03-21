package KnightsTour;

import processing.core.*;

public class BoardView extends PApplet{
	
	public void settings() {
	    size(200,200);
	    
	  }
	
	public void setup(){
		background(0);
	}

	  public void draw() {
	    stroke(255);
	    if (mousePressed) {
	      line(mouseX,mouseY,pmouseX,pmouseY);
	    }
	  }
	  
	  public static void main(String[] args) {
			PApplet.main(new String[] { "--present", "KnightsTour.BoardView" });
		}

}
