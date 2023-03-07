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


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:




Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
class SudokuSolver {
    fun solveSudoku(board: Array<CharArray>): Unit {
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
