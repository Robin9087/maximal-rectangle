/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input: [
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"] 
]
Output: 6
*/
public class MaximalRectangle {
    private MaximalRectangle(){

    }

    private static int findMaxLen(char[] row) {
        int size = 0;
        int tempS = 0;
        for (char c : row) {
            if (c == '1') {
                tempS++;
            } else {
                if (tempS > size) {
                    size = tempS;
                }
                tempS = 0;
            }
        }
        if (tempS > size) {
            size = tempS;
        }
        return size;
    }

    private static int findCurrentMax(char[][] matrix, int n) {
        int i = n;
        char[] tempRow = matrix[n];
        int maxSize = findMaxLen(tempRow);
        int tempi = 1;
        while (i < matrix.length - 1) {
            i++;
            for (int j = 0; j < matrix[i].length; j++) {
                if (((tempRow[j] - '0') & (matrix[i][j] - '0')) == 1) {
                    tempRow[j] = '1';
                } else {
                    tempRow[j] = '0';
                }
            }
            tempi++;
            int tempSize = findMaxLen(tempRow) * tempi;
            if (maxSize < tempSize) {
                maxSize = tempSize;
            }
        }
        return maxSize;
    }

    public static int maximalRectangle(char[][] matrix) {
        int maxSize = 0;
        for (int i = 0; i < matrix.length; i++) {
            int temp = findCurrentMax(matrix, i);
            if (maxSize < temp) {
                maxSize = temp;
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(MaximalRectangle.maximalRectangle(matrix));
    }
}
