package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int move = 1;

        // Creating the board
        char[][] board = {
                {'|', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', '|'},
        };

        /*System.out.print("Enter cells: ");
        char[] cells = scanner.nextLine().toCharArray();*/

        char[] cells = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        printBoard(board, cells);

        while (checkGameStatus(board, move) == "none") {

            askingAboutMove(board, move);

            switch (checkGameStatus(board, move)) {
                case "X wins":
                case "O wins":
                case "Draw":
                case "Impossible":
                case "Game not finished":
                    printBoard(board, cells);
                    System.out.println(checkGameStatus(board, move));
                    return;
                default:
                    printBoard(board, cells);
            }
            move++;
        }
    }


    public static void printBoard(char[][] board, char[] cells) {
        // Printing out the board
        int index = 0;

        System.out.println("---------");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    board[row][col] = cells[index];
                    index++;
                }
                System.out.printf("%s ", board[row][col]);
            }
            System.out.println();
        }
        System.out.println("---------");
    }

    public static void askingAboutMove(char[][] board, int move) {
        // Asking about move
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String coordinateA = scanner.next();
        String coordinateB = scanner.next();
        char player = move % 2 != 0 ? 'X' : 'O';

        if (coordinateA.matches("\\d") && coordinateB.matches("\\d")) {

            if (Integer.parseInt(coordinateA) > 3 || Integer.parseInt(coordinateB) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                askingAboutMove(board, move);
                return;
            }

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (board[Integer.parseInt(coordinateA) - 1][Integer.parseInt(coordinateB)] == 'X'
                            || board[Integer.parseInt(coordinateA) - 1][Integer.parseInt(coordinateB)] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                        askingAboutMove(board, move);
                        return;
                    }
                }
            }

            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (row == Integer.parseInt(coordinateA) - 1 && col == Integer.parseInt(coordinateB)) {
                        board[row][col] = player;
                    }
                }
            }
        } else {
            System.out.println("You should enter numbers!");
            askingAboutMove(board, move);
        }
    }

    public static String checkGameStatus(char[][] board, int move) {

        int x = 0;
        int o = 0;

        boolean xwins = false;
        boolean owins = false;

        for (char[] row : board) {
            for (char ch : row) {
                if (ch == 'X') {
                    x++;
                } else if (ch == 'O') {
                    o++;
                }
            }
        }

        String gameStatus = "none";

        char player = move % 2 != 0 ? 'X' : 'O';

        if (board[0][1] == player && board[0][2] == player && board[0][3] == player // rows
                || board[1][1] == player && board[1][2] == player && board[1][3] == player
                || board[2][1] == player && board[2][2] == player && board[2][3] == player

                || board[0][1] == player && board[1][1] == player && board[2][1] == player //columns
                || board[0][2] == player && board[1][2] == player && board[2][2] == player
                || board[0][3] == player && board[1][3] == player && board[2][3] == player

                || board[0][1] == player && board[1][2] == player && board[2][3] == player //diagonals
                || board[0][3] == player && board[1][2] == player && board[2][1] == player) {

            if (player == 'X') {
                gameStatus = "X wins";
                xwins = true;
            }

            if (player == 'O') {
                gameStatus = "O wins";
                owins = true;
            }

        }

        if (!xwins && !owins && x + o == 9) {
            gameStatus = "Draw";
        }

        if (xwins && owins && x + o != 9) {
            gameStatus = "Game not finished";
        }

        if (xwins && owins && x > o || xwins && owins && x < o) {
            gameStatus = "Impossible";
        } else if (x - o >= 2 || o - x >= 2) {
            gameStatus = "Impossible";
        }

        return gameStatus;
    }

}