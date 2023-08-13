package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/
 *
 * 1039. Minimum Score Triangulation of Polygon
 * Medium
 * 1.6K
 * 155
 * company
 * Adobe
 * company
 * Amazon
 * company
 * Apple
 * You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values where values[i] is the value of the ith vertex (i.e., clockwise order).
 *
 * You will triangulate the polygon into n - 2 triangles. For each triangle, the value of that triangle is the product of the values of its vertices, and the total score of the triangulation is the sum of these values over all n - 2 triangles in the triangulation.
 *
 * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: values = [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
 * Example 2:
 *
 *
 * Input: values = [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
 * The minimum score is 144.
 * Example 3:
 *
 *
 * Input: values = [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 *
 *
 * Constraints:
 *
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 */
class MinimumScoreTriangulationOfPolygon {
    fun minScoreTriangulation2(values: IntArray): Int {
        val n = values.size
        val dp = Array(n) { IntArray(n) }
        for (d in 1 until n - 1) {
            for (i in 1 until n - d) {
                val j = i + d
                var min = Int.MAX_VALUE
                for (k in i until j) {
                    min = minOf(
                            min,
                            dp[i][k] + dp[k+1][j] + values[i-1] * values[k] * values[j]
                    )
                }
                dp[i][j] = min
            }
        }
        return dp[1][n - 1]
    }

    fun minScoreTriangulation(values: IntArray): Int {
        val N = values.size
        val dp = Array(N){IntArray(N)}
        for(len in 2 until N) {
            for(r in 0 until (N-len)) {
                val c = r + len
                dp[r][c] = Int.MAX_VALUE
                for(k in r+1 until c) {
                    dp[r][c] = minOf(
                            dp[r][c],
                            dp[r][k] + dp[k][c] + values[r] * values[k] * values[c]
                    )
                }
            }
        }
        return dp[0][N-1]
    }
}
