public class MatrixIslandsWithDiagonals {
    private static final int[] rowDir = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] colDir = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static int countIslands(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1 && !visited[r][c]) {
                    dfs(matrix, visited, r, c, rows, cols);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, int r, int c, int rows, int cols) {
        visited[r][c] = true;
        for (int d = 0; d < 8; d++) {
            int nr = r + rowDir[d];
            int nc = c + colDir[d];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols &&
                matrix[nr][nc] == 1 && !visited[nr][nc]) {
                dfs(matrix, visited, nr, nc, rows, cols);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 1, 0, 0},
            {0, 1, 0, 1},
            {1, 0, 0, 1},
            {0, 0, 1, 1}
        };

        int[][] matrix2 = {
            {1, 0, 0},
            {0, 0, 1},
            {1, 1, 0}
        };

        System.out.println("Number of islands in matrix1: " + countIslands(matrix1));
        System.out.println("Number of islands in matrix2: " + countIslands(matrix2));
    }
}