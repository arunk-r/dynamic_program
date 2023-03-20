package com.dynamic.program.arrays.medium

/**
 * 130. Surrounded Regions
Medium
company
Google
company
Amazon
company
TikTok
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.



Example 1:


Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
Explanation: Notice that an 'O' should not be flipped if:
- It is on the border, or
- It is adjacent to an 'O' that should not be flipped.
The bottom 'O' is on the border, so it is not flipped.
The other three 'O' form a surrounded region, so they are flipped.
Example 2:

Input: board = [["X"]]
Output: [["X"]]


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] is 'X' or 'O'.
 */
class SurroundedRegions {
    fun solve(board: Array<CharArray>): Unit {
        val zeros = ArrayDeque<Pair<Int, Int>>()

        val m = board.size
        val n = board[0].size

        for (r in board.indices) {
            zeros.addLast(Pair(r, 0))
            zeros.addLast(Pair(r, n - 1))
        }

        for (c in board[0].indices) {
            zeros.addLast(Pair(0, c))
            zeros.addLast(Pair(m - 1, c))
        }

        while (zeros.isNotEmpty()) {
            val (r, c) = zeros.removeFirst()
            markEdges(r, c, board)
        }

        for (r in board.indices) {
            for (c in board[r].indices) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X'
                }
                if (board[r][c] == 'E') {
                    board[r][c] = 'O'
                }
            }
        }
    }

    private fun markEdges(r: Int, c: Int, board: Array<CharArray>) {
        if (r in board.indices && c in board[r].indices) {
            if (board[r][c] != 'O') return
            board[r][c] = 'E'
            dir.forEach { (r1, c1) ->
                markEdges(r + r1, c + c1, board)
            }
        }
    }

    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
}

fun main() {
    //println(SurroundedRegions().solve(arrayOf(charArrayOf('X', 'X', 'X', 'X'), charArrayOf('X', 'O', 'O', 'X'), charArrayOf('X', 'X', 'O', 'X'), charArrayOf('X', 'O', 'X', 'X'))))
    println(SurroundedRegions().solve(arrayOf(charArrayOf('O','O','O'),charArrayOf('O','O','O'),charArrayOf('O','O','O'))))
}
