package com.dynamic.program.dp

/**
 * Pascal's Triangle
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * Example 1:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 *
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 * 1 <= numRows <= 30
 */
class PascalsTriangle {
    fun generate(numRows: Int): List<List<Int>> {
        val dp = mutableListOf<MutableList<Int>>()
        dp.add(mutableListOf(1))
        for(i in 1 until numRows) {
            val lst = mutableListOf<Int>()
            lst.add(1)
            for(j in 1 until i) {
                lst.add(dp[i-1][j-1] + dp[i-1][j])
            }
            lst.add(1)
            dp.add(lst)
        }
        return dp
    }
}