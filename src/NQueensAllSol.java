import java.util.ArrayList;
import java.util.List;

public class NQueensAllSol {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();


        boolean board[][] = new boolean[n][n];
        for(int i=0; i< n; i++){
            for (int j=0; j<n; j++){
                board[i][j] = false;
            }
        }

        solveNQUtil(board, 0, res, n);
        return res;

    }

    public static boolean solveNQUtil(boolean board[][], int col, List<List<String>> res, int N)
    {
        /* base case: If all queens are placed
           then return true */
        if (col >= N){
            List<String> p = new ArrayList<>();
            for (int i=0; i<N; i++){
                String s="";
                for (int j=0; j<N; j++){
                    if(board[i][j])
                        s += "Q";
                    else s+= ".";
                }
                p.add(s);
            }
            res.add(p);
            return true;
        }

        boolean ret = false;
        /* Consider this column and try placing
           this queen in all rows one by one */
        for (int i = 0; i < N; i++)
        {
            /* Check if queen can be placed on
               board[i][col] */
            if (isSafe(board, i, col, N))
            {
                /* Place this queen in board[i][col] */
                board[i][col] = true;

                /* recur to place rest of the queens */
                ret = solveNQUtil(board, col + 1, res, N) || ret;

                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
                board[i][col] = false; // BACKTRACK
            }
        }

        /* If queen can not be place in any row in
           this colum col, then return false */
        return ret;
    }

    public static boolean isSafe(boolean board[][], int row, int col, int N)
    {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++)
            if (board[row][i] == true)
                return false;

        /* Check upper diagonal on left side */
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == true)
                return false;

        /* Check lower diagonal on left side */
        for (i=row, j=col; j>=0 && i<N; i++, j--)
            if (board[i][j] == true)
                return false;

        return true;
    }


    public static void main(String args[]){
        List<List<String>> res = solveNQueens(9);
        for (List<String> l:res){
            System.out.println(l);
        }
    }
}
