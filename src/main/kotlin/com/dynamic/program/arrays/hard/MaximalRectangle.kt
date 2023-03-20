package com.dynamic.program.arrays.hard

import java.util.Stack

/**
 * 85. Maximal Rectangle
Hard

Amazon
Apple
Google
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1


Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
 */
class MaximalRectangle {
    private fun maxArea(heights: IntArray): Int {
        var max = 0
        val s = Stack<Int>()
        s.add(-1)
        for(i in heights.indices) {
            while(s.peek() != -1 && heights[s.peek()] >= heights[i]) {
                val height = heights[s.pop()]
                val width = i - s.peek() - 1
                max = maxOf(max, height * width)
            }
            s.push(i)
        }

        while(s.peek() != -1) {
            val height = heights[s.pop()]
            val width = heights.size - s.peek() - 1
            max = maxOf(max, height * width)
        }
        return max
    }

    fun maximalRectangle(matrix: Array<CharArray>): Int {
        val m = matrix.size
        val n = matrix[0].size
        val dp = IntArray(n)

        var max = 0
        for(r in matrix.indices) {
            for (c in matrix[r].indices) {
                if (matrix[r][c] == '1') {
                    dp[c] = dp[c]+1
                } else {
                    dp[c] = 0
                }
            }
            println(dp.toList())
            max = maxOf(max, maxArea(dp))
        }

        return max
    }
}
