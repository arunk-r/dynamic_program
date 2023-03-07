package com.dynamic.program.backtracking

/**
 * N-Queens II
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example 1:
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 *
 * Example 2:
 * Input: n = 1
 * Output: 1
 *
 *
 * Constraints:
 * 1 <= n <= 9
 */
class NQueensII {
    var count = 0

    fun totalNQueens(n: Int): Int {
        val matrix = Array<IntArray>(n) { IntArray(n) { 0 } }
        solution(matrix, n, 0)
        return count
    }

    private fun solution(matrix: Array<IntArray>, n: Int, row: Int) {
        for (i in 0 until n) {
            if (canPlaceQueen(matrix, row, i)) { // can I place queen in current position -> cell value should be zero
                setConditions(matrix, n, i, row) // block all row, col, diagonal and antiDiagonal cells
                if (row == n - 1) { // if row count reached n. if yes increase count
                    count++
                } else {
                    solution(matrix, n, row+1) // check for next possible queen place
                }
                removeConditions(matrix, n, i, row) // undo last operations
            }
        }
    }

    private fun setConditions(matrix: Array<IntArray>, n: Int, col: Int, row: Int){
        set(matrix, n, col, row, 1)
    }

    private fun removeConditions(matrix: Array<IntArray>, n: Int, col: Int, row: Int){
        set(matrix, n, col, row, -1)
    }

    private fun set(matrix: Array<IntArray>, n: Int, col: Int, row: Int, value: Int){
        // set row
        for (i in 0 until n) {
            matrix[row][i] += value
        }

        // set col
        for (i in 0 until n) {
            matrix[i][col] += value
        }

        //set diagonal right down
        var r = row+1
        var c = col+1
        while (r < n && c < n) {
            matrix[r++][c++] += value
        }

        //set diagonal down left
        r = row+1
        c = col-1
        while (r < n && c >= 0) {
            matrix[r++][c--] += value
        }

        //set diagonal up left
        r = row-1
        c = col-1
        while (r >= 0 && c >= n) {
            matrix[r--][c--] += value
        }

        //set diagonal up right
        r = row-1
        c = col+1
        while (r >=0 && c < n) {
            matrix[r--][c++] += value
        }

    }

    private fun canPlaceQueen(matrix: Array<IntArray>, row: Int, col: Int) = matrix[row][col] == 0
}

fun main() {
    val nQueensII = NQueensII()
    println(nQueensII.totalNQueens(4))
}
