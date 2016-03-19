import java.util.LinkedList;


public class NQueens {
	
	private int N;
	private int[][] board;
	
	public int backtrackingCounter =0;
	public int checkTimes = 0;
	
	public NQueens(int N){
		this.N = N;
		board = new int[N][N];
	}
	
	public LinkedList<Integer> place(LinkedList<Integer> feasibleRows, Integer row, int col){
		board[row.intValue()][col] = 1;

		LinkedList<Integer> newRows = new LinkedList<Integer>();
		for(Integer r: feasibleRows){
			if(!r.equals(row)){
				newRows.add(r);
			}
		}
		return newRows;
	}
	
	public void reset(int row, int col){
		board[row][col] = 0;
	}
	
	private boolean isSpaceFeasible(int row, int col)
    {
		checkTimes++;
		//check row
		for(int i=0; i<N; i++){
			if(board[row][i]==1){
				return false;
			}
		}
		
		//check col
		for(int j=0; j<N; j++){
			if(board[j][col]==1){
				return false;
			}
		}
		
		//check diag
		for(int i=row, j=col; i<N && j>=0; i++, j-- ){
			if(board[i][j]==1){
				return false;
			}
		}
		for(int i=row, j=col; i>=0 && j>=0; i--, j-- ){
			if(board[i][j]==1){
				return false;
			}
		}
		for(int i=row, j=col; i>=0 && j<N; i--, j++ ){
			if(board[i][j]==1){
				return false;
			}
		}
		for(int i=row, j=col; i<N && j<N; i++, j++ ){
			if(board[i][j]==1){
				return false;
			}
		}
		
		return true;
    }
	
	private boolean isBoardFeasible(LinkedList<Integer> feasibleRows, int n){
		if(n >= N){
			return true;
		}
		// Find a place for this queen, by iterating through cols and rows

		for(Integer row: feasibleRows){
			if(isSpaceFeasible(row.intValue(), n)){
				LinkedList<Integer> newRows = place(feasibleRows, row, n);
					
				if(isBoardFeasible(newRows, n+1)){
					return true;
				}
				
				backtrackingCounter++;
				reset(row.intValue(), n);
				}
		}
		
		return false;
	}
	
	private void printSolution()
    {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j]
                                 + " ");
            System.out.println();
        }
    }

	public static void main(String[] args) {
		/*
		if(args.length != 1){
			System.out.println("Please pass an int for the # of queens");
			return;
		}
		NQueens queens = new NQueens(Integer.parseInt(args[0]));
		boolean possible = queens.isBoardFeasible(0);
		if(possible){
			queens.printSolution();
			
			System.out.println("We had to backtrack x"+backtrackingCounter);
		} else {
			System.out.println("Solution not found");
		}
	*/
		System.out.println("N\tBacktrack\tTime");
		for(int i=4; i<24; i++){
			NQueens queens = new NQueens(i);
			LinkedList<Integer> feasibleRows = new LinkedList<Integer>();
			for(int j=0; j<i; j++){
				feasibleRows.add(new Integer(j));
			}
			boolean possible = queens.isBoardFeasible(feasibleRows, 0);
			
			System.out.println(i+"\t"+queens.backtrackingCounter+"\t"+queens.checkTimes
					+"\t"+possible);
			/*if(possible){
				queens.printSolution();
			}*/
		}
	}
}
