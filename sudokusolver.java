public class SudokuSolver {

    public static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {3, 0, 6, 5, 0, 8, 4, 0, 1},
                {5, 2, 8, 0, 0, 0, 0, 0, 3},
                {0, 0, 1, 0, 0, 7, 0, 0, 2},
                {0, 5, 0, 1, 6, 0, 0, 8, 4},
                {0, 0, 4, 2, 8, 3, 1, 5, 0},
                {7, 3, 0, 0, 1, 5, 0, 0, 9},
                {0, 0, 2, 7, 0, 1, 3, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 6, 8},
                {8, 0, 0, 0, 2, 4, 5, 1, 3}
        };

        if (solve(board)) {
            System.out.println("Solved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution found");
        }
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0; // Backtrack
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true; // Solved if no empty cells
    }

    private static boolean isValidPlacement(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check subgrid
        int subgridRowStart = (row / 3) * 3;
        int subgridColStart = (col / 3) * 3;
        for (int i = subgridRowStart; i < subgridRowStart + 3; i++) {
            for (int j = subgridColStart; j < subgridColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
