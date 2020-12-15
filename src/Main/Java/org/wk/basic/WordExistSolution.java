package org.wk.basic;


public class WordExistSolution {
    int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    boolean[][] marked;
    char[][] board;
    String word;
    // 盘面上有多少行
    private int m;
    // 盘面上有多少列
    private int n;

    public boolean exist(char[][] board, String word) {
        board = board;
        m = board.length;
        n = board[0].length;
        marked = new boolean[m][n];
        word = word;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if(dfs(newX, newY, start + 1)){
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

}
