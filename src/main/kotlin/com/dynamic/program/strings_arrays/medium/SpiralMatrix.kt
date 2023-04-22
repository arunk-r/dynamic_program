package com.dynamic.program.strings_arrays.medium

/**
 * 54. Spiral Matrix
Medium
Amazon
Microsoft
Apple
Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */
class SpiralMatrix {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var m = matrix.size
        var n = matrix[0].size

        var dir = 1

        var i = 0
        var j = -1

        val op = mutableListOf<Int>()
        while (m * n > 0) {
            for (k in 0 until n) {
                j += dir
                op.add(matrix[i][j])
            }
            n -= 1

            for (k in 1 until m) {
                i += dir
                op.add(matrix[i][j])
            }
            m -= 1

            dir *= -1
        }

        return op
    }

    fun spiralOrder1(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        val rows = matrix.size
        val cols = matrix[0].size

        var left = 0
        var up = 0
        var right = cols - 1
        var down = rows - 1
        while (result.size < rows * cols) {
            // traverse left to right
            for (col in left .. right) {
                result.add(matrix[up][col])
            }
            // traverse top to bottom
            for (row in up+1 .. down) {
                result.add(matrix[row][right])
            }
            if (up != down) {
                // traverse right to left
                for (col in right-1 downTo left) {
                    result.add(matrix[down][col])
                }
            }
            if (left != right){
                // traverse bottom to up
                for (row in down - 1 downTo up + 1) {
                    result.add(matrix[row][left])
                }
            }
            left++
            up++
            right--
            down--
        }
        return result
    }
}

fun main() {
    println(SpiralMatrix().spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))))
}
