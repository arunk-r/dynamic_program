package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/sparse-matrix-multiplication/
 *
 * 311. Sparse Matrix Multiplication
 * Medium
 * 1K
 * 347
 * company
 * Bloomberg
 * company
 * Facebook
 * company
 * Snapchat
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 *
 */
class SparseMatrixMultiplication {
    fun multiply(mat1: Array<IntArray>, mat2: Array<IntArray>): Array<IntArray> {
        val result = Array(mat1.size){IntArray(mat2[0].size)}
        for(r in mat1.indices) {
            for(c1 in mat2[0].indices) {
                var sum = 0
                for(c in mat1[r].indices) {
                    sum += (mat1[r][c] * mat2[c][c1])
                }
                result[r][c1] = sum
            }
        }
        return result
    }
}
