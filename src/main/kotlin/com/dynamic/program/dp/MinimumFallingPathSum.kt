package com.dynamic.program.dp

import java.util.PriorityQueue

/**
 * Minimum Falling Path Sum
 *
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * A falling path starts at any element in the first row and chooses the element in the next row that is either
 * directly below or diagonally left/right. Specifically, the next element from position
 * (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 *
 * Example 1:
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 *
 *
 * Example 2:
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 *
 *
 * Constraints:
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
class MinimumFallingPathSum {
    data class Data(val r: Int, val c: Int, val sum: Int)

    val dir = listOf(Pair(1, 0), Pair(1, -1), Pair(1, 1))
    fun minFallingPathSum(matrix: Array<IntArray>): Int {
        var pos: Data? = null
        val n = matrix.size
        val p = PriorityQueue<Data> { x, y -> x.sum - y.sum }
        for (c in matrix[0].indices) {
            p.add(Data(0, c, matrix[0][c]))
        }
        var min = Int.MAX_VALUE
        while (p.isNotEmpty()) {
            pos = p.remove()
            if (pos.r == n - 1) {
                min = minOf(pos.sum, min)
            }
            dir.forEach { (r1, c1) ->
                val nr = pos.r + r1
                val nc = pos.c + c1
                if (nr in matrix.indices && nc in matrix[nr].indices) {
                    val sum = pos.sum + matrix[nr][nc]
                    p.add(Data(nr, nc, sum))
                }
            }
        }
        return min
    }

    fun minFallingPathSum1(matrix: Array<IntArray>): Int {
        var min = Int.MAX_VALUE
        for (c in matrix[0].indices) {
            min = Math.min(min, matrix[0][c])
        }
        for (r in 1 until matrix.size) {
            min = Int.MAX_VALUE
            for (c in matrix[r].indices) {
                matrix[r][c] += if (c - 1 < 0) {
                    Math.min(matrix[r - 1][c], matrix[r - 1][c + 1])
                } else if (c + 1 >= matrix[r].size) {
                    Math.min(matrix[r - 1][c], matrix[r - 1][c - 1])
                } else {
                    Math.min(Math.min(matrix[r - 1][c], matrix[r - 1][c - 1]), matrix[r - 1][c + 1])
                }

                min = Math.min(min, matrix[r][c])
            }
        }

        return min
    }
}

fun main() {
    val data = arrayOf(intArrayOf(-51, -35, 74), intArrayOf(-62, 14, -53), intArrayOf(94, 61, -10))
    println(MinimumFallingPathSum().minFallingPathSum(data))
}
