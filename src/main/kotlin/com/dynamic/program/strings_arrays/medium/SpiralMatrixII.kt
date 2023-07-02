package com.dynamic.program.strings_arrays.medium

/**
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 * 59. Spiral Matrix II
 * Medium
 * 5.8K
 * 236
 * company
 * Amazon
 * company
 * TikTok
 * company
 * Apple
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 *
 */
class SpiralMatrixII {
    fun generateMatrix(n1: Int): Array<IntArray> {
        val mat = Array(n1){IntArray(n1)}
        var m = n1
        var n = n1

        var start = 1
        var r = 0
        var c = -1
        var dir = 1
        while(m * n > 0) {
            //col fill
            for(k in 0 until n) {
                c += dir
                mat[r][c] = start++
            }
            n -= 1

            // row fill
            for(k in 0 until m-1) {
                r += dir
                mat[r][c] = start++
            }
            m -= 1
            dir *= -1
        }
        return mat
    }
}
