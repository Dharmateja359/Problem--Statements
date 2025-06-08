import java.util.*;

class KnightsAndPortals {
    static class State {
        int x, y, steps;
        boolean teleported;
        State(int x, int y, int steps, boolean teleported) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.teleported = teleported;
        }
    }

    public static int shortestPathWithTeleport(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> emptyCells = new ArrayList<>();
        boolean[][][] visited = new boolean[m][n][2];

        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, 0, false));
        visited[0][0][0] = true;

        // Collect all empty '0' cells
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '0') emptyCells.add(new int[]{i, j});

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            State s = queue.poll();
            if (grid[s.x][s.y] == 'E') return s.steps;

            // Regular moves
            for (int[] d : directions) {
                int nx = s.x + d[0], ny = s.y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n &&
                    grid[nx][ny] != '1' && !visited[nx][ny][s.teleported ? 1 : 0]) {
                    visited[nx][ny][s.teleported ? 1 : 0] = true;
                    queue.offer(new State(nx, ny, s.steps + 1, s.teleported));
                }
            }

            // Teleport once
            if (!s.teleported && grid[s.x][s.y] == '0') {
                for (int[] cell : emptyCells) {
                    int tx = cell[0], ty = cell[1];
                    if (!visited[tx][ty][1] && (tx != s.x || ty != s.y)) {
                        visited[tx][ty][1] = true;
                        queue.offer(new State(tx, ty, s.steps + 1, true));
                    }
                }
            }
        }

        return -1; // Path not found
    }

    public static void main(String[] args) {
        char[][] grid1 = {
            {'S','0','1','0'},
            {'1','0','1','0'},
            {'0','0','0','1'},
            {'1','1','0','E'}
        };

        char[][] grid2 = {
            {'S','1','1'},
            {'1','0','1'},
            {'1','1','E'}
        };

        System.out.println("Grid 1 Shortest Path: " + shortestPathWithTeleport(grid1)); // Output: 7
        System.out.println("Grid 2 Shortest Path: " + shortestPathWithTeleport(grid2)); // Output: -1
    }
}