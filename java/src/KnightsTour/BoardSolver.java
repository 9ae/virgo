package KnightsTour;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class BoardSolver {

	private int m;
	private int n;
	
	private SquareVertex[][] board;
	private UndirectedGraph<SquareVertex, DefaultEdge> land;
	private DirectedGraph<SquareVertex, DefaultEdge> path;
	
	private SquareVertex startVertex = null;
	
	public BoardSolver(int m, int n){
		this.m = m;
		this.n = n;
		
		board = new SquareVertex[m][n];
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				board[i][j] = new SquareVertex(i, j);
			}
		}
		
		createLand();
	}
	
	private void createLand(){
		land = new SimpleGraph<SquareVertex, DefaultEdge>(DefaultEdge.class);
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				SquareVertex p = board[i][j];
				land.addVertex(p);
				
				tryConnection(p, -1, 2);
				tryConnection(p, -1, -2);
				tryConnection(p, 1, 2);
				tryConnection(p, 1, -2);
				
				tryConnection(p, -2, 1);
				tryConnection(p, -2, -1);
				tryConnection(p, 2, 1);
				tryConnection(p, 2, -1);
			}
		}
	}
	
	private void tryConnection(SquareVertex vertex, int dx, int dy){
		try {
			SquareVertex friend = board[vertex.row+dx][vertex.col+dy];
			
			if(!land.containsVertex(friend)){
				land.addVertex(friend);
			}
			land.addEdge(vertex, friend);
			
		} catch(ArrayIndexOutOfBoundsException ex){
			
		}
	}
	
	public void printLand(){
		for(int i=0; i<m; i++){
			String row = "";
			for(int j=0; j<n; j++){
				row += "("+land.degreeOf(board[i][j])+") ";
			}
			System.out.println(row);
		}
	}
	
	public boolean findNextSpace(SquareVertex p){
		if(path.vertexSet().size()==(m*n)){
			System.out.println("Completed path");
			return true;
		}
		Set<DefaultEdge> edges = land.edgesOf(p);
		if(edges.size()==0){
			System.out.println("Deadend path");
			return false;
		}
		
		SquareVertex q = null;
		for(DefaultEdge e: edges){

			SquareVertex target;
			SquareVertex source = land.getEdgeSource(e);
			if(!source.equals(p)){
				target = source;
			} else {
				target = land.getEdgeTarget(e);
			}
			
			if(q==null){
				q = target;
				continue;
			}
			
			if(land.degreeOf(target)<land.degreeOf(q)){
				q = target;
			}
		}
		
		//System.out.println("Going to "+q.toString());
		
		path.addVertex(q);
		path.addEdge(p, q);
		
		land.removeVertex(p);
		
		return findNextSpace(q);
	}
	
	public void printPath(){
		SquareVertex p = startVertex;
		while(path.outDegreeOf(p)>0){
			System.out.println(p.toString());
			
			Iterator<DefaultEdge> it = path.outgoingEdgesOf(p).iterator();
			DefaultEdge link = it.next();
			p = path.getEdgeTarget(link);
		}	
	}
	
	public boolean isPossible(){
		if (n%2==1 && m%2==1){
			return false;
		}
		
		int M, N;
		if(m>n){
			M = n;
			N = m;
		} else {
			M = m;
			N = n;
		}
		
		if(M==1 || M==2 || M==4){
			return false;
		}
		
		if(M==3 && (N==4 || N==6 || N==8)){
			return false;
		}
		
		return true;
	}
	
	public void findPaths(){
		if(!isPossible()){
			System.out.println("No possible paths are found for this board");
			return;
		}
		
		int solutions = 0;
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){

				path = new DefaultDirectedGraph<SquareVertex, DefaultEdge>(DefaultEdge.class);
				startVertex = board[i][j];
				path.addVertex(startVertex);
				boolean hasPath = findNextSpace(startVertex);
		
				if(hasPath){
					solutions++; 
					break;
				} else {
					createLand();
				}
			}
			if(solutions>0){
				break;
			}
		}
		
		printPath();
		
	}
	
}
