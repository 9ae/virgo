
public class NQueens {
	
	private int N;
	private int[][] board;
	
	private static int backtrackingCounter =0;
	
	public NQueens(int N){
		this.N = N;
		board = new int[N][N];
		backtrackingCounter = 0;
	}
	
	private boolean isSpaceFeasible(int row, int col)
    {
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
		return true;
    }
	
	private boolean isBoardFeasible(int n){
		if(n >= N){
			return true;
		}
		// Find a place for this queen, by iterating through cols and rows
		for(int i=0; i<N; i++){
			if(isSpaceFeasible(i, n)){
				board[i][n] = 1;
				
				if(isBoardFeasible(n+1)){
					return true;
				}
				
				backtrackingCounter++;
				board[i][n] = 0;
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

	}
}
