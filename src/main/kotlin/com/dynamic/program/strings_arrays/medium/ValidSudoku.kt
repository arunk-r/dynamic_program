package com.dynamic.program.strings_arrays.medium

/**
 * 36. Valid Sudoku
Medium

Amazon

Apple

Karat
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.


Example 1:


Input: board =
[['5','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
Output: true
Example 2:

Input: board =
[['8','3','.','.','7','.','.','.','.']
,['6','.','.','1','9','5','.','.','.']
,['.','9','8','.','.','.','.','6','.']
,['8','.','.','.','6','.','.','.','3']
,['4','.','.','8','.','3','.','.','1']
,['7','.','.','.','2','.','.','.','6']
,['.','6','.','.','.','.','2','8','.']
,['.','.','.','4','1','9','.','.','5']
,['.','.','.','.','8','.','.','7','9']]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
 */
class ValidSudoku {

    fun isValidSudoku(board: Array<CharArray>): Boolean {

        for (r in 0 until 9) {
            for (c in 0 until 9) {
                val ch = board[r][c]
                if (ch != '.' && !valid(board, r, c, ch)) {
                    return false
                }
            }
        }

        return true
    }

    fun valid(board: Array<CharArray>, r: Int, c: Int, v: Char): Boolean {
        val key = "$r$c"
        for (i in 0 until 9) {
            if ((key != "$r$i" && board[r][i] == v) || (key != "$i$c" && board[i][c] == v)) return false
        }

        val bxRSt = r / 3 * 3
        val bxCSt = c / 3 * 3

        for (r1 in bxRSt until bxRSt + 3) {
            for (c1 in bxCSt until bxCSt + 3) {
                if ((key != "$r1$c1") && board[r1][c1] == v) return false
            }
        }
        return true
    }

    fun isValidSudoku1(board: Array<CharArray>): Boolean {
        val n = 9
        val rows = Array(n) { IntArray(n) }
        val cols = Array(n) { IntArray(n) }
        val boxes = Array(n) { IntArray(n) }
        for (r in board.indices) {
            for (c in board[r].indices) {
                if (board[r][c] != '.') {
                    val boardPosition = board[r][c] - '0' - 1 // 1 - 9 => 0 - 8
                    if (rows[r][boardPosition] == 1) {
                        return false
                    }
                    rows[r][boardPosition] = 1

                    if (cols[boardPosition][c] == 1) {
                        return false
                    }
                    cols[boardPosition][c] = 1

                    // check box
                    val boxIdx = (r / 3) * 3 + c / 3
                    if (boxes[boxIdx][boardPosition] == 1) {
                        return false
                    }
                    boxes[boxIdx][boardPosition] = 1
                }
            }
        }
        return true
    }
}

fun main() {
    println(
        ValidSudoku().isValidSudoku(
            arrayOf(
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
        )
    )

    /*println(
        ValidSudoku().isValidSudoku(
            arrayOf(
                charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.'),
                charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '6', '.'),
                charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3'),
                charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )
        )
    )*/
}
