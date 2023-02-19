package com.dynamic.program.dp.mcm

/**
 * 1039. Minimum Score Triangulation of Polygon
 * You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values where values[i] is the value of the ith vertex (i.e., clockwise order).

You will triangulate the polygon into n - 2 triangles. For each triangle, the value of that triangle is the product of the values of its vertices, and the total score of the triangulation is the sum of these values over all n - 2 triangles in the triangulation.

Return the smallest possible total score that you can achieve with some triangulation of the polygon.



Example 1:


Input: values = [1,2,3]
Output: 6
Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
Example 2:


Input: values = [3,7,4,5]
Output: 144
Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
The minimum score is 144.
Example 3:


Input: values = [1,3,1,4,1,5]
Output: 13
Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
 */
class MinimumScoreTriangulationOfPolygon {
    fun minScoreTriangulation(values: IntArray): Int {
        val n = values.size
        return mcm(values, 1, n - 1, Array(n-1){IntArray(n){-1} })
    }

    private fun mcm(values: IntArray, i: Int, j: Int, mem: Array<IntArray>): Int {
        if (i >= j) return 0
        if (mem[i][j] != -1) {
            return mem[i][j]
        }
        var min = Int.MAX_VALUE
        for (k in i until j) {
            min = minOf(min, mcm(values, i, k, mem) + mcm(values, k + 1, j, mem) + values[i - 1] * values[k] * values[j])
        }
        return min
    }
}
fun main() {
    println(MinimumScoreTriangulationOfPolygon().minScoreTriangulation(intArrayOf(1,2,3)))
    println(MinimumScoreTriangulationOfPolygon().minScoreTriangulation(intArrayOf(3,7,4,5)))
    println(MinimumScoreTriangulationOfPolygon().minScoreTriangulation(intArrayOf(1,3,1,4,1,5)))
}