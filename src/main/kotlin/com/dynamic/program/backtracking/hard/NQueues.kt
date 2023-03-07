package com.dynamic.program.backtracking.hard

/**
 * 51. N-Queens
Hard
company
Amazon
company
Adobe
company
Bloomberg
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.



Example 1:


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:

Input: n = 1
Output: [["Q"]]


Constraints:

1 <= n <= 9
 */
class NQueues {
    private val res = mutableListOf<MutableList<String>>()
    fun solveNQueens(n: Int): List<List<String>> {
        val colSet = hashSetOf<Int>()
        val positiveSet = hashSetOf<Int>()//(r+c)
        val negativeSet = hashSetOf<Int>() // r-c
        val board = Array(n) { MutableList(n) { "." } }
        backtracking(0, n, board, colSet, positiveSet, negativeSet)
        return res
    }
    fun backtracking(row: Int, n: Int, board: Array<MutableList<String>>, colSet: HashSet<Int>, positiveSet: HashSet<Int>, negativeSet: HashSet<Int>) {
        if (row == n) {
            val lst = mutableListOf<String>()
            board.forEach { r ->
                lst.add(r.joinToString(""))
            }
            res.add(lst)
            return
        }
        for (c in 0 until n) {
            if (colSet.contains(c) || positiveSet.contains(row+c) || negativeSet.contains(row-c)) {
                continue
            }
            colSet.add(c)
            positiveSet.add(row+c)
            negativeSet.add(row-c)
            board[row][c] = "Q"

            backtracking(row+1, n, board, colSet, positiveSet, negativeSet)

            colSet.remove(c)
            positiveSet.remove(row+c)
            negativeSet.remove(row-c)
            board[row][c] = "."
        }
    }
}

fun main() {
    println(NQueues().solveNQueens(4))
}
