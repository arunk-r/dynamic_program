package com.dynamic.program.graphs.bfs.hard

import java.util.PriorityQueue
import kotlin.math.max

/**
 * 407. Trapping Rain Water II
 * Hard
 * 3.4K
 * 76
 * company
 * Amazon
 * company
 * Samsung
 * company
 * Google
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 * Example 2:
 *
 *
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 *
 * Constraints:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 */
class TrappingRainWaterII {
    data class RainData(val h: Int, val r: Int, val c: Int)

    val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    fun trapRainWater(heightMap: Array<IntArray>): Int {
        var count = 0
        val queue = PriorityQueue<RainData> { x, y -> x.h - y.h }
        val m = heightMap.size
        val n = heightMap[0].size
        val seen = hashSetOf<Pair<Int, Int>>()
        for (r in heightMap.indices) {
            queue.add(RainData(heightMap[r][0], r, 0))
            queue.add(RainData(heightMap[r][n - 1], r, n - 1))
            seen.add(Pair(r, 0))
            seen.add(Pair(r, n - 1))
        }

        for (c in heightMap[0].indices) {
            queue.add(RainData(heightMap[0][c], 0, c))
            queue.add(RainData(heightMap[m - 1][c], m - 1, c))
            seen.add(Pair(0, c))
            seen.add(Pair(m - 1, c))
        }

        while (queue.isNotEmpty()) {
            val (height, row, col) = queue.remove()
            for ((r1, c1) in dir) {
                val nr = row + r1
                val nc = col + c1
                val p = Pair(nr, nc)
                if (nr in heightMap.indices && nc in heightMap[nr].indices && !seen.contains(p)) {
                    seen.add(p)
                    val curHeight = heightMap[nr][nc]
                    count += maxOf(0, height - curHeight )
                    queue.add(RainData(maxOf(height, curHeight), nr, nc))
                }
            }
        }

        return count
    }
}

fun main() {
    println(
        TrappingRainWaterII().trapRainWater(
            arrayOf(
                intArrayOf(1, 4, 3, 1, 3, 2),
                intArrayOf(3, 2, 1, 3, 2, 4),
                intArrayOf(2, 3, 3, 2, 3, 1)
            )
        )
    )
}
