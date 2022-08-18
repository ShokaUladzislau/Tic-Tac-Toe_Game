package tictactoe

import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        var move = 1

        // Creating the board
        val board = arrayOf(
            charArrayOf('|', ' ', ' ', ' ', '|'),
            charArrayOf('|', ' ', ' ', ' ', '|'),
            charArrayOf('|', ' ', ' ', ' ', '|')
        )

        /*System.out.print("Enter cells: ");
        char[] cells = scanner.nextLine().toCharArray();*/
        val cells = charArrayOf(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
        printBoard(board, cells)
        while (checkGameStatus(board, move) === "none") {
            askingAboutMove(board, move)
            when (checkGameStatus(board, move)) {
                "X wins", "O wins", "Draw", "Impossible", "Game not finished" -> {
                    printBoard(board, cells)
                    println(checkGameStatus(board, move))
                    return
                }
                else -> printBoard(board, cells)
            }
            move++
        }
    }

    fun printBoard(board: Array<CharArray>, cells: CharArray) {
        // Printing out the board
        var index = 0
        println("---------")
        for (row in board.indices) {
            for (col in 0 until board[row].size) {
                if (board[row][col] == ' ') {
                    board[row][col] = cells[index]
                    index++
                }
                System.out.printf("%s ", board[row][col])
            }
            println()
        }
        println("---------")
    }

    fun askingAboutMove(board: Array<CharArray>, move: Int) {
        // Asking about move
        val scanner = Scanner(System.`in`)
        print("Enter the coordinates: ")
        val coordinateA = scanner.next()
        val coordinateB = scanner.next()
        val player = if (move % 2 != 0) 'X' else 'O'
        if (true) {
            if (coordinateA.toInt() > 3 || coordinateB.toInt() > 3) {
                println("Coordinates should be from 1 to 3!")
                askingAboutMove(board, move)
                return
            }
            for (row in board.indices) {
                for (col in 0 until board[row].size) {
                    if (board[coordinateA.toInt() - 1][coordinateB.toInt()] == 'X'
                        || board[coordinateA.toInt() - 1][coordinateB.toInt()] == 'O'
                    ) {
                        println("This cell is occupied! Choose another one!")
                        askingAboutMove(board, move)
                        return
                    }
                }
            }
            for (row in board.indices) {
                for (col in 0 until board[row].size) {
                    if (row == coordinateA.toInt() - 1 && col == coordinateB.toInt()) {
                        board[row][col] = player
                    }
                }
            }
        } else {
            println("You should enter numbers!")
            askingAboutMove(board, move)
        }
    }

    fun checkGameStatus(board: Array<CharArray>, move: Int): String {
        var x = 0
        var o = 0
        var xwins = false
        var owins = false
        for (row in board) {
            for (ch in row) {
                if (ch == 'X') {
                    x++
                } else if (ch == 'O') {
                    o++
                }
            }
        }
        var gameStatus = "none"
        val player = if (move % 2 != 0) 'X' else 'O'
        if (board[0][1] == player && board[0][2] == player && board[0][3] == player // rows
            || board[1][1] == player && board[1][2] == player && board[1][3] == player || board[2][1] == player && board[2][2] == player && board[2][3] == player || board[0][1] == player && board[1][1] == player && board[2][1] == player //columns
            || board[0][2] == player && board[1][2] == player && board[2][2] == player || board[0][3] == player && board[1][3] == player && board[2][3] == player || board[0][1] == player && board[1][2] == player && board[2][3] == player //diagonals
            || board[0][3] == player && board[1][2] == player && board[2][1] == player
        ) {
            if (player == 'X') {
                gameStatus = "X wins"
                xwins = true
            }
            if (player == 'O') {
                gameStatus = "O wins"
                owins = true
            }
        }
        if (!xwins && !owins && x + o == 9) {
            gameStatus = "Draw"
        }
        if (xwins && owins && x + o != 9) {
            gameStatus = "Game not finished"
        }
        if (xwins && owins && x > o || xwins && owins && x < o) {
            gameStatus = "Impossible"
        } else if (x - o >= 2 || o - x >= 2) {
            gameStatus = "Impossible"
        }
        return gameStatus
    }
}