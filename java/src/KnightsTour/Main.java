package KnightsTour;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class Main {

	public static void main(String[] args) {
		
		BoardSolver board = new BoardSolver(8,8);
		board.findPaths();

	}

}
