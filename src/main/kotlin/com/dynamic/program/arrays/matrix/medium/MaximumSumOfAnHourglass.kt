package com.dynamic.program.arrays.matrix.medium

/**
 * 2428. Maximum Sum of an Hourglass
Medium
company
Nutanix
You are given an m x n integer matrix grid.

We define an hourglass as a part of the matrix with the following form:


Return the maximum sum of the elements of an hourglass.

Note that an hourglass cannot be rotated and must be entirely contained within the matrix.



Example 1:


Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
Output: 30
Explanation: The cells shown above represent the hourglass with the maximum sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.
Example 2:


Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
Output: 35
Explanation: There is only one hourglass in the matrix, with the sum: 1 + 2 + 3 + 5 + 7 + 8 + 9 = 35.


Constraints:

m == grid.length
n == grid[i].length
3 <= m, n <= 150
0 <= grid[i][j] <= 106

 */
class MaximumSumOfAnHourglass {
    fun maxSum(grid: Array<IntArray>): Int {
        var ans = 0
        for (r in 0 until grid.size - 2) {
            for (c in 0 until grid[r].size - 2)
                if ((r + 2) in grid.indices && (c + 2) in grid[r].indices) {
                    ans = maxOf(ans, getHourValue(r, c, grid))
                }
        }
        return ans
    }

    private fun getHourValue(r: Int, c: Int, grid: Array<IntArray>): Int {
        var ans = 0
        ans += grid[r + 1][c + 1]
        for (i in c..c + 2) {
            ans += grid[r][i]
            ans += grid[r + 2][i]
        }
        return ans
    }
}

fun main() {
    println(MaximumSumOfAnHourglass().maxSum(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))))
}
