import java.util.*;

public class SudokuValidatorWithZones {

    public static boolean isValidSudoku(int[][] board, List<List<int[]>> customZones) {
        return validateRows(board) && validateColumns(board) &&
               validateSubGrids(board) && validateCustomZones(board, customZones);
    }

    private static boolean validateRows(int[][] board) {
        for (int row = 0; row < 9; row++) {
            Set<Integer> seen = new HashSet<>();
            for (int col = 0; col < 9; col++) {
                int num = board[row][col];
                if (num < 1 || num > 9 || !seen.add(num)) return false;
            }
        }
        return true;
    }

    private static boolean validateColumns(int[][] board) {
        for (int col = 0; col < 9; col++) {
            Set<Integer> seen = new HashSet<>();
            for (int row = 0; row < 9; row++) {
                int num = board[row][col];
                if (num < 1 || num > 9 || !seen.add(num)) return false;
            }
        }
        return true;
    }

    private static boolean validateSubGrids(int[][] board) {
        for (int block = 0; block < 9; block++) {
            Set<Integer> seen = new HashSet<>();
            int startRow = (block / 3) * 3;
            int startCol = (block % 3) * 3;
            for (int r = startRow; r < startRow + 3; r++) {
                for (int c = startCol; c < startCol + 3; c++) {
                    int num = board[r][c];
                    if (num < 1 || num > 9 || !seen.add(num)) return false;
                }
            }
        }
        return true;
    }

    private static boolean validateCustomZones(int[][] board, List<List<int[]>> zones) {
        for (List<int[]> zone : zones) {
            Set<Integer> seen = new HashSet<>();
            for (int[] pos : zone) {
                int row = pos[0], col = pos[1];
                int num = board[row][col];
                if (num < 1 || num > 9 || !seen.add(num)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = {
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}
        };

        List<List<int[]>> zones = new ArrayList<>();
        zones.add(Arrays.asList(
            new int[]{0,0}, new int[]{0,1}, new int[]{0,2},
            new int[]{1,0}, new int[]{1,1}, new int[]{1,2},
            new int[]{2,0}, new int[]{2,1}, new int[]{2,2}
        ));
        zones.add(Arrays.asList(
            new int[]{3,3}, new int[]{3,4}, new int[]{3,5},
            new int[]{4,3}, new int[]{4,4}, new int[]{4,5},
            new int[]{5,3}, new int[]{5,4}, new int[]{5,5}
        ));

        boolean result = isValidSudoku(board, zones);
        System.out.println("Sudoku board is valid with custom zones: " + result);
    }
}