package com.dynamic.program.strings_arrays.medium

/**
 * 1182. Shortest Distance to Target Color
Medium
You are given an array colors, in which there are three colors: 1, 2 and 3.

You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given index i and the target color c. If there is no solution return -1.



Example 1:

Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
Output: [3,0,3]
Explanation:
The nearest 3 from index 1 is at index 4 (3 steps away).
The nearest 2 from index 2 is at index 2 itself (0 steps away).
The nearest 1 from index 6 is at index 3 (3 steps away).
Example 2:

Input: colors = [1,2], queries = [[0,3]]
Output: [-1]
Explanation: There is no 3 in the array.


Constraints:

1 <= colors.length <= 5*10^4
1 <= colors[i] <= 3
1 <= queries.length <= 5*10^4
queries[i].length == 2
0 <= queries[i][0] < colors.length
1 <= queries[i][1] <= 3
 */
class ShortestDistanceToTargetColor {

    fun shortestDistanceColor(colors: IntArray, queries: Array<IntArray>): List<Int> {
        val distances = Array(colors.size) { IntArray(4) { Int.MAX_VALUE } }

        for (i in colors.indices) {
            for (j in 1 .. 3) {
                if (colors[i] == j) {
                    distances[i][j] = 0
                } else if (i != 0 && distances[i-1][j] != Int.MAX_VALUE) {
                    distances[i][j] = distances[i-1][j]+1
                }
            }
        }

        for (i in colors.size -1 downTo 0) {
            for (j in 1 .. 3) {
                if (colors[i] == j) {
                    distances[i][j] = 0
                } else if(i != colors.size -1 && distances[i+1][j] != Int.MAX_VALUE) {
                    distances[i][j] = minOf(distances[i][j], distances[i+1][j]+1)
                }
            }
        }

        val result = mutableListOf<Int>()
        for (q in queries) {
            val r = distances[q[0]][q[1]]
            if (r == Int.MAX_VALUE) {
                result.add(-1)
            } else {
                result.add(r)
            }
        }
        return result
    }
}

fun main() {
    println(ShortestDistanceToTargetColor().shortestDistanceColor(intArrayOf(1,1,2,1,3,2,2,3,3), arrayOf(intArrayOf(1,3), intArrayOf(2,2), intArrayOf(6,1))))
}
