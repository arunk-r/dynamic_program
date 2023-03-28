package com.dynamic.program.arrays.matrix.medium

/**
 * 1314. Matrix Block Sum
Medium

company
Amazon
company
Microsoft
company
Google
Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:

i - k <= r <= i + k,
j - k <= c <= j + k, and
(r, c) is a valid position in the matrix.


Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, k <= 100
1 <= mat[i][j] <= 100
 */
class MatrixBlockSum {
    fun matrixBlockSum(mat: Array<IntArray>, k: Int): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size

        val ps = Array(m+1){IntArray(n+1)}

        for(r in 1 .. m) {
            for(c in 1..n) {
                ps[r][c] = mat[r-1][c-1] + ps[r-1][c] + ps[r][c-1] - ps[r-1][c-1]
            }
        }

        val res = Array(m){IntArray(n)}

        for(r in 1 .. m) {
            for(c in 1..n) {

                val endI = minOf(m, r+k)
                val endJ = minOf(n, c+k)

                val stI = maxOf(1, r-k)
                val stJ = maxOf(1, c-k)

                res[r-1][c-1] = ps[endI][endJ] - ps[stI-1][endJ] - ps[endI][stJ-1] + ps[stI-1][stJ-1]
            }
        }
        return res
    }
}
