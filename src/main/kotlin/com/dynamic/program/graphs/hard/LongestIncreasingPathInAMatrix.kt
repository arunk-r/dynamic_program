package com.dynamic.program.graphs.hard

/**
 * 329. Longest Increasing Path in a Matrix
Hard

DoorDash
Google
TikTok
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1
 */
class LongestIncreasingPathInAMatrix {
    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        var max = 0
        val mem = Array(matrix.size) { IntArray(matrix[0].size) { -1 } }
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                max = maxOf(max, findPath(matrix, r, c, mem))
            }
        }
        return max+1
    }

    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(-1, 0), Pair(0, -1))
    private fun findPath(matrix: Array<IntArray>, r: Int, c: Int, mem: Array<IntArray>): Int {
        if (mem[r][c] != -1) return mem[r][c]
        dir.forEach { (r1, c1) ->
            val nr = r + r1
            val nc = c + c1

            if (valid(nr, nc, matrix, matrix[r][c])) {
                mem[r][c] = maxOf(mem[r][c], findPath(matrix, nr, nc, mem))
            }
        }
        return ++mem[r][c]
    }

    fun valid(r: Int, c: Int, matrix: Array<IntArray>, curValue: Int): Boolean =
        (r in matrix.indices && c in matrix[r].indices && matrix[r][c] > curValue)
}

fun main() {
    //println(LongestIncreasingPathInAMatrix().longestIncreasingPath(arrayOf(intArrayOf(0), intArrayOf(1), intArrayOf(5), intArrayOf(5))))
    println(LongestIncreasingPathInAMatrix().longestIncreasingPath(arrayOf(intArrayOf(9, 9, 4), intArrayOf(6, 6, 8), intArrayOf(2, 1, 1))))
}
