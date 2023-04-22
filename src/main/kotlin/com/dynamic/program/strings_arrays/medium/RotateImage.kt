package com.dynamic.program.strings_arrays.medium

/**
 * 48. Rotate Image
Medium

Amazon

Cisco

Adobe
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
class RotateImage {
    fun rotate(matrix: Array<IntArray>): Unit {
        val m = matrix.size
        val n = matrix[0].size

        for (r in 0 until m) {
            for (c in r until n) {
                val t = matrix[r][c]
                matrix[r][c] = matrix[c][r]
                matrix[c][r] = t
            }
        }

        for (r in 0 until m) {
            var lc = n-1
            for (c in 0 .. n/2) {
                val t = matrix[r][c]
                matrix[r][c] = matrix[r][lc]
                matrix[r][lc] = t
                lc--
            }
        }
        var t=0
    }




























    fun rotate1(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        for (r in matrix.indices){
            for (c in r until n ){
                val tmp = matrix[r][c]
                matrix[r][c] = matrix[c][r]
                matrix[c][r] = tmp
            }
        }

        for (r in matrix.indices){
            for (c in 0 until n/2){
                val tmp = matrix[r][c]
                matrix[r][c] = matrix[r][n-c-1]
                matrix[r][n-c-1] = tmp
            }
        }
    }
}

fun main() {
    println(
        RotateImage().rotate(
            arrayOf(
                intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)
            )
        )
    )
    println(
        RotateImage().rotate(
            arrayOf(
                intArrayOf(5,1,9,11), intArrayOf(2,4,8,10), intArrayOf(13,3,6,7), intArrayOf(15,14,12,16)
            )
        )
    )
}
