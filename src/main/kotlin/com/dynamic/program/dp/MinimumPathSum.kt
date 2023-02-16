package com.dynamic.program.dp

/**
 * Minimum Path Sum
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example 1:
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 *
 *
 * Example 2:
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 */
class MinimumPathSum {
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) { -1 } }
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (i == 0) {
                    if (j-1 >=0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j]
                    } else {
                        dp[i][j] = grid[i][j]
                    }
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j]
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j])
                }
            }
        }
        return dp[m - 1][n - 1]
    }
}

fun main() {
    println(MinimumPathSum().minPathSum(arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1))))
}