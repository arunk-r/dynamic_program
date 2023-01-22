package com.dynamic.program.binary.search

import java.util.*

/**
 * Path With Minimum Effort
 *
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left,
 * or right, and you wish to find a route that requires the minimum effort.
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 * Example 1:
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 *
 * Example 2:
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 *
 *
 * Example 3:
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 *
 * Constraints:
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 */
class PathWithMinimumEffort {
    val dir = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        var left = 0
        var right = 0
        for (r in heights.indices) {
            for (c in heights[r].indices) {
                right = Math.max(right, heights[r][c])
            }
        }

        while (left <= right) {
            val mid = left + (right - left) / 2
            val check = check(mid, heights)
            if (check) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    fun check(effort: Int, heights: Array<IntArray>): Boolean {
        val m = heights.size - 1
        val n = heights[0].size - 1
        val s = Stack<Pair<Int, Int>>()
        s.push(Pair(0, 0))
        val visited = MutableList(m + 1) { BooleanArray(n+1) }
        visited[0][0] = true
        while (s.isNotEmpty()) {
            val cur = s.pop()
            val l = cur.first
            val r = cur.second
            if (l == m && r == n) return true
            val idxVal = heights[l][r]
            dir.forEach { p ->
                val nr = l + p.first
                val nc = r + p.second
                if (valid(nr, nc, heights, visited)) {
                    val nIdxVal = heights[nr][nc]
                    if (Math.abs(nIdxVal - idxVal) <= effort) {
                        visited[nr][nc] = true
                        s.push(Pair(nr, nc))
                    }
                }
            }
        }
        return false
    }

    fun valid(r: Int, c: Int, heights: Array<IntArray>, visited: MutableList<BooleanArray>): Boolean =
        (r in heights.indices && c in heights[r].indices && !visited[r][c])


    fun minimumEffortPath1(heights: Array<IntArray>): Int {
        val dp = Array(heights.size) { IntArray(heights[0].size) { Int.MAX_VALUE } }
        dp[0][0] = 0

        val q = java.util.PriorityQueue<State>()
        q.offer(State(0, 0, 0))

        while (q.isNotEmpty()) {
            val (curRow, curCol, curEffortFromStart) = q.poll()

            if (curRow == heights.lastIndex && curCol == heights[0].lastIndex) return curEffortFromStart

            if (dp[curRow][curCol] < curEffortFromStart) continue

            for (dir in dir) {
                val nextRow = curRow + dir.first
                val nextCol = curCol + dir.second
                if (nextRow in heights.indices && nextCol in heights[0].indices) {
                    val weight = kotlin.math.abs(heights[nextRow][nextCol] - heights[curRow][curCol])
                    val effortToNext = maxOf(dp[curRow][curCol], weight)
                    if (effortToNext < dp[nextRow][nextCol]) {
                        dp[nextRow][nextCol] = effortToNext
                        q.offer(State(nextRow, nextCol, effortToNext))
                    }
                }
            }
        }
        return -1
    }

    data class State(val row: Int, val col: Int, val effort: Int) : Comparable<State> {
        override fun compareTo(other: State): Int {
            return effort - other.effort
        }
    }
}

fun main() {
    println(
        PathWithMinimumEffort().minimumEffortPath1(
            arrayOf(
                intArrayOf(10,8),intArrayOf(10,8),intArrayOf(1,2),intArrayOf(10,3),intArrayOf(1,3),intArrayOf(6,3),intArrayOf(5,2)
            )
        )
    )
}