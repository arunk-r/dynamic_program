package com.dynamic.program.dp

/**
 * Maximal Square
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 * Input: matrix = [['1','0','1','0','0'),charArrayOf('1','0','1','1','1'),charArrayOf('1','1','1','1','1'),charArrayOf('1','0','0','1','0']]
 * Output: 4
 *
 *
 * Example 2:
 * Input: matrix = [['0','1'),charArrayOf('1','0']]
 * Output: 1
 *
 *
 * Example 3:
 * Input: matrix = [['0']]
 * Output: 0
 *
 * Constraints:
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 *
 */
class MaximalSquare {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        var max = 0
        val m = matrix.size
        val n = matrix[0].size
        val dp = Array(m) { IntArray(n) { 0 } }
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == '1') {
                    if (r == 0 || c == 0) {
                        dp[r][c] = 1
                    } else {
                        dp[r][c] = minOf(minOf(dp[r - 1][c], dp[r - 1][c - 1]), dp[r][c - 1]) + 1
                    }
                }
                max = maxOf(max, dp[r][c])
            }
        }

        return max * max
    }

    fun maximalSquare1(matrix: Array<CharArray>): Int {
        var max = 0
        val m = matrix.size
        val n = matrix[0].size
        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == '1') {
                    val v = helper(r, c, matrix, Array(m) { BooleanArray(n) })
                    max = maxOf(max, v * v)
                }
            }
        }
        return max
    }

    private fun helper(r: Int, c: Int, matrix: Array<CharArray>, seen: Array<BooleanArray>): Int {
        val q = ArrayDeque<Pair<Int, Int>>()
        q.add(Pair(r, c))
        var currentSize = 1
        var maxSize = 1
        while (q.isNotEmpty()) {
            val size = q.size
            if (size == currentSize) {
                maxSize = currentSize + 1
                val set = hashSetOf<Pair<Int, Int>>()
                for (i in 0 until size) {
                    val (x, y) = q.removeFirst()
                    listOf(Pair(0, 1), Pair(1, 0), Pair(1, 1)).forEach { (r1, c1) ->
                        val nr = x + r1
                        val nc = y + c1
                        if (valid(nr, nc, matrix, seen)) {
                            seen[nr][nc] = true
                            set.add(Pair(nr, nc))
                        }
                    }
                }
                q.addAll(set)
            } else {
                q.clear()
            }
            currentSize += 2
        }
        return maxSize / 2
    }

    private fun valid(r: Int, c: Int, matrix: Array<CharArray>, seen: Array<BooleanArray>): Boolean =
        (r in matrix.indices && c in matrix[r].indices && matrix[r][c] == '1' && !seen[r][c])
}

fun main() {

    println(
        MaximalSquare().maximalSquare(
            arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('0', '0', '1', '1', '1')
            )
        )
    )
    println(
        MaximalSquare().maximalSquare(
            arrayOf(
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('0', '0', '0', '0', '0'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1')
            )
        )
    )

    println(
        MaximalSquare().maximalSquare(
            arrayOf(
                charArrayOf('1', '0', '1', '0', '0'),
                charArrayOf('1', '0', '1', '1', '1'),
                charArrayOf('1', '1', '1', '1', '1'),
                charArrayOf('1', '0', '0', '1', '0')
            )
        )
    )

    println(
        MaximalSquare().maximalSquare(
            arrayOf(
                charArrayOf('1', '0'),
                charArrayOf('1', '0')
            )
        )
    )
}