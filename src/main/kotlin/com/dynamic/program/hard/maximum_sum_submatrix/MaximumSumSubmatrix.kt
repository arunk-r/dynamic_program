package com.dynamic.program.hard.maximum_sum_submatrix

import kotlin.math.max

/**
 * You're given a two-dimensional array (a matrix) of potentially unequal height
 * and width that's filled with integers. You're also given a positive integer
 * size. Write a function that returns the maximum sum that can be
 * generated from a submatrix with dimensions size * size.
 *
 * For example, consider the following matrix:
 * [
 * [2, 4],
 * [5, 6],
 * [-3, 2],
 * ]
 * If size = 2, then the 2x2 submatrices to consider are:
 * [2, 4]
 * [5, 6]
 *
 *
 * ------
 * [5, 6]
 * [-3, 2]
 *
 *
 * The sum of the elements in the first submatrix is 17, and the sum
 * of the elements in the second submatrix is 10. In this example,
 * your function should return 17.
 *
 * Note: size will always be at least 1, and the
 * dimensions of the input matrix will always be at least
 * size * size.
 *
 *
 * Sample Input
 *
 * matrix =
 * [
 * [5, 3, -1, 5],
 * [-7, 3, 7, 4],
 * [12, 8, 0, 0],
 * [1, -8, -8, 2],
 * ]
 * size = 2
 *
 *
 * Sample Output
 *
 * 18
 * // [
 * //   [., ., ., .],
 * //   [., 3, 7, .],
 * //   [., 8, 0, .],
 * //   [., ., ., .],
 * // ]
 *
 */
// O(w * h) time | O(w * h) space
fun maximumSumSubMatrix(matrix: List<List<Int>>, size: Int): Int {
    // Write your code here.
    val sums = createSumMatrix(matrix)
    var maxSubMatrix = Int.MIN_VALUE

    for (row in size -1 until sums.size) {
        for (col in size -1 until sums[row].size) {
            var totalSum = sums[row][col]

            val touchesTopBorder = row - size < 0
            if (!touchesTopBorder) {
                totalSum -= sums[row - size][col]
            }

            val touchesLeftBorder = col - size < 0
            if (!touchesLeftBorder) {
                totalSum -= sums[row][col - size]
            }

            val touchesTopOrLeftBorder = touchesTopBorder || touchesLeftBorder
            if (!touchesTopOrLeftBorder) {
                totalSum += sums[row - size][col - size]
            }

            maxSubMatrix = max(totalSum, maxSubMatrix)
        }
    }
    return maxSubMatrix
}

fun createSumMatrix(matrix: List<List<Int>>) :List<List<Int>> {
    val sums = MutableList(matrix.size) { MutableList(matrix[0].size) {0} }
    sums[0][0] = matrix[0][0]
    for (idx in 1 until matrix[0].size) {
        sums[0][idx] = sums[0][idx -1] + matrix[0][idx]
    }
    for (idx in 1 until matrix.size) {
        sums[idx][0] = sums[idx -1][0] + matrix[idx][0]
    }

    for (row in 1 until matrix.size) {
        for (col in 1 until matrix[row].size) {
            sums[row][col] = sums[row -1][col] + sums[row][col -1] - sums[row-1][col -1] + matrix[row][col]
        }
    }
    return sums
}

fun main() {
    println(maximumSumSubMatrix(listOf(listOf(5, 3, -1, 5),
        listOf(-7, 3, 7, 4),
        listOf(12, 8, 0, 0),
        listOf(1, -8, -8, 2)), 2))
}