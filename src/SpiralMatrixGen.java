public class SpiralMatrixGen {

    public static int[][] generateMatrix(int n) {
        if (n == 0) {
            return null;
        }
        int[][] ret = new int[n][n];

        int rowBegin = 0;
        int rowEnd = n-1;
        int colBegin = 0;
        int colEnd = n-1;
        int num = 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j++) {
                ret[rowBegin][j] = num;
                num++;
            }
            rowBegin++;

            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j++) {
                ret[j][colEnd] =num;
                num++;
            }
            colEnd--;

            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j--) {
                    ret[rowEnd][j] =num;
                    num++;
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j--) {
                    ret[j][colBegin] =num;
                    num++;
                }
            }
            colBegin ++;
        }
        return ret;
    }

    public static void main(String args[]){
        int[][] ans = generateMatrix(3);
        for (int[]i:ans){
            for (int j:i)
                System.out.print(j+ ", ");
            System.out.println();
        }

    }
}
