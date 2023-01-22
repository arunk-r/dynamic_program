package com.dynamic.program.binary.search

import java.util.PriorityQueue

/**
 * Path With Minimum Effort
 */
class PathWithMinimumEffort1 {
    val dirs = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1))
    fun minimumEffortPath(heights: Array<IntArray>): Int {
        val m = heights.size
        val n = heights[0].size
        val efforts = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        efforts[0][0] = 0
        val q = PriorityQueue<Triple<Int, Int, Int>>{x,y -> x.third - y.third}
        q.add(Triple(0,0,0))
        while (q.isNotEmpty()) {
            val (r, c, effort) = q.remove()
            if (r == m -1 && c == n -1) return effort
            if (efforts[r][c] < effort) continue
            dirs.forEach { (ri, ci) ->
                val nr = r + ri
                val nc = c + ci
                if (nr in 0 until m && nc in 0 until n) {
                    val weight = Math.abs(heights[r][c] - heights[nr][nc])
                    val newEfrt = maxOf(efforts[r][c], weight)
                    if (newEfrt < efforts[nr][nc]) {
                        efforts[nr][nc] = newEfrt
                        q.add(Triple(nr,nc, newEfrt))
                    }
                }
            }

        }
        return -1
    }
}

fun main() {
    println(PathWithMinimumEffort1().minimumEffortPath(arrayOf(intArrayOf(1,2,2),intArrayOf(3,8,2),intArrayOf(5,3,5))))
   /* println(
        PathWithMinimumEffort1().minimumEffortPath(
            arrayOf(
                intArrayOf(10, 8),
                intArrayOf(10, 8),
                intArrayOf(1, 2),
                intArrayOf(10, 3),
                intArrayOf(1, 3),
                intArrayOf(6, 3),
                intArrayOf(5, 2)
            )
        )
    )*/
}