package com.dynamic.program.backtracking.hard

/**
 * 37. Sudoku Solver
Hard
company
Bloomberg
company
Google
company
Amazon
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.



Example 1:


Input: board = [['5','3','.','.','7','.','.','.','.'),charArrayOf('6','.','.','1','9','5','.','.','.'),charArrayOf('.','9','8','.','.','.','.','6','.'),charArrayOf('8','.','.','.','6','.','.','.','3'),charArrayOf('4','.','.','8','.','3','.','.','1'),charArrayOf('7','.','.','.','2','.','.','.','6'),charArrayOf('.','6','.','.','.','.','2','8','.'),charArrayOf('.','.','.','4','1','9','.','.','5'),charArrayOf('.','.','.','.','8','.','.','7','9']]
Output: [['5','3','4','6','7','8','9','1','2'),charArrayOf('6','7','2','1','9','5','3','4','8'),charArrayOf('1','9','8','3','4','2','5','6','7'),charArrayOf('8','5','9','7','6','1','4','2','3'),charArrayOf('4','2','6','8','5','3','7','9','1'),charArrayOf('7','1','3','9','2','4','8','5','6'),charArrayOf('9','6','1','5','3','7','2','8','4'),charArrayOf('2','8','7','4','1','9','6','3','5'),charArrayOf('3','4','5','2','8','6','1','7','9']]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
class SudokuSolver {
    val lst = listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
    fun solveSudoku(board: Array<CharArray>): Unit {
        val emptyList = mutableListOf<IntArray>()
        for (r in board.indices) {
            for (c in board[r].indices) {
                if (board[r][c] == '.') {
                    emptyList.add(intArrayOf(r, c))
                }
            }
        }
        sudokuBoard(0, emptyList, board)
    }

    fun sudokuBoard(idx: Int, emptyList: MutableList<IntArray>, board: Array<CharArray>): Boolean {
        if (idx == emptyList.size) {
            return true
        }

        val row = emptyList[idx][0]
        val col = emptyList[idx][1]

        for (c in lst) {
            if (valid(c, row, col, board)) {
                board[row][col] = c
                // call again
                if (sudokuBoard(idx + 1, emptyList, board)) {
                    return true
                }
                // backtrack
                board[row][col] = '.'
            }
        }
        return false
    }

    fun valid(num: Char, row: Int, col: Int, board: Array<CharArray>): Boolean {
        for (i in board.indices) {
            if (board[row][i] == num || board[i][col] == num) {
                return false
            }
        }

        // 3 x 3 grid
        val sR = row / 3 * 3
        val sC = col / 3 * 3

        for (r in sR until sR + 3) {
            for (c in sC until sC + 3) {
                if (board[r][c] == num) {
                    return false
                }
            }
        }

        return true
    }

    fun solveSudoku1(board: Array<CharArray>): Unit {
        sodoku(board)
    }

    fun sodoku(board: Array<CharArray>): Boolean {
        for (i in 0..8) {
            for (j in 0..8) {
                if (board[i][j] == '.') {
                    var c = '1'
                    while (c <= '9') {
                        if (isValid(board, i, j, c)) // checks the validity of that number.
                        {
                            board[i][j] = c // //Put c for current cell.
                            if (sodoku(board)) // //If it's the solution return true.
                                return true else board[i][j] = '.'
                        }
                        c++
                    }
                    return false
                }
            }
        }
        return true
    }

    fun isValid(board: Array<CharArray>, row: Int, col: Int, c: Char): Boolean {
        for (i in 0..8) {
            if (board[i][col] != '.' && board[i][col] == c) return false
            if (board[row][i] != '.' && board[row][i] == c) return false
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.' &&
                board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c
            ) return false
        }
        return true
    }

}

fun main() {
    val board = arrayOf(
        charArrayOf('5', '3', '.', '.', '7', '.', '.', '.', '.'),
        charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
        charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
        charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
        charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
        charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
        charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
        charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
        charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
    )
    SudokuSolver().solveSudoku(board)
    println(board.toList())
}
