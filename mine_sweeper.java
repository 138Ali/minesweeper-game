import java.util.Scanner;

public class mine_sweeper {
    private static final int ROWS = 4;
    private static final int COLUMNS = 3;
    private static final int MINES = 3;

    private int[][] board;
    private boolean[] mines;

    public mine_sweeper() {
        board = new int[ROWS][COLUMNS];
        mines = new boolean[ROWS * COLUMNS];
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Set the mines
        for (int i = 0; i < MINES; i++) {
            int row = (int) (Math.random() * ROWS);
            int column = (int) (Math.random() * COLUMNS);
            mines[row * COLUMNS + column] = true;
        }

        // Display the board
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        // Get the user's input
        while (true) {
            System.out.print("bir satır ve sütun girin (1-" + ROWS + ", 1-" + COLUMNS + "): ");
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;

            // Check if the input is valid
            if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
                System.out.println("geçersiz giriş");
                continue;
            }

            // Check if the user hit a mine
            if (mines[row * COLUMNS + column]) {
                System.out.println("bomba patladı kaybettin");
                break;
            } else {
                // Check the neighboring cells
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS && mines[i * COLUMNS + j]) {
                            board[i][j]++;
                        }
                    }
                }

                // If all the non-mine cells have been revealed, the user wins
                if (countUnrevealedCells() == MINES) {
                    System.out.println("You won!");
                    break;
                }
            }
        }
    }

    private int countUnrevealedCells() {
        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

  
}

