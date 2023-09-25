package com.dynamic.program.graphs.bfs

import java.util.PriorityQueue
import java.util.TreeSet

/**
 * You are given an m x n grid, grid of values 0, 1, or 2,
 *
 * where:
 * each 0 marks an empty land that you can pass by freely,
 * each 1 marks a building that you cannot pass through, and
 * each 2 marks an obstacle that you cannot pass through.
 *
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * Example 1:
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 *
 * Example 2:
 * Input: grid = [[1,0]]
 * Output: 1
 *
 * Example 3:
 * Input: grid = [[1]]
 * Output: -1
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid\[i].length
 * 1 <= m, n <= 50
 * grid\[i]\[j] is either 0, 1, or 2.
 * There will be at least one building in the grid.
 */
class ShortestDistanceFromAllBuildings {
    data class Data(val r: Int, val c: Int)
    data class Distance(val pos: Data, val cnt: Int = 0)

    val dir = listOf(Data(1, 0), Data(-1, 0), Data(0, 1), Data(0, -1))
    fun shortestDistance(grid: Array<IntArray>): Int {
        var minDist = Int.MAX_VALUE
        val houses = HashSet<Data>()
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 1) {
                    houses.add(Data(r, c))
                }
            }
        }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 0) {
                    minDist = minOf(minDist, dfs(grid, houses, Data(r, c)))
                }
            }
        }
        return if (minDist == Int.MAX_VALUE) -1 else minDist
    }

    private fun dfs(grid: Array<IntArray>, houses: HashSet<Data>, start: Data): Int {
        val map = HashMap<Data, Int>()
        val seen = HashSet<Data>()
        var count = houses.size
        val q = PriorityQueue<Distance> { x, y -> x.cnt - y.cnt }
        q.add(Distance(start))
        seen.add(start)

        while (count > 0 && q.isNotEmpty()) {
            val (pos, cnt) = q.remove()
            if (grid[pos.r][pos.c] == 1) {
                count--
                map[pos] = cnt
                continue
            } else {
                dir.forEach { (r1, c1) ->
                    val nr = pos.r + r1
                    val nc = pos.c + c1
                    val newPos = Data(nr, nc)
                    if (nr in grid.indices && nc in grid[nr].indices && !seen.contains(newPos) && grid[nr][nc] != 2) {
                        seen.add(newPos)
                        q.add(Distance(newPos, cnt + 1))
                    }
                }
            }
        }
        data class Data(val t: Int, val s: String)


        if(map.size != houses.size) return Int.MAX_VALUE
        val sum = map.values.sum()
        return if (sum == 0) Int.MAX_VALUE else sum
    }

    val dir1 = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    fun shortestDistance1(grid: Array<IntArray>): Int {
        val rows = grid.size
        val cols = grid[0].size
        val total = Array(rows) { IntArray(cols) { 0 } }

        val q = ArrayDeque<Pair<Int, Int>>()

        var minDis = Int.MAX_VALUE
        var emptyLandValue = 0
        for (r in grid.indices) {
            for (c in grid[r].indices) {

                if (grid[r][c] == 1) {
                    q.addLast(Pair(r, c))
                    var steps = 0
                    minDis = Integer.MAX_VALUE
                    while (q.isNotEmpty()) {
                        steps++
                        for (i in q.size - 1 downTo 0) {
                            val cur = q.removeFirst()
                            dir1.forEach { p ->
                                val nr = cur.first + p.first
                                val nc = cur.second + p.second

                                if (nr in 0 until rows && nc in 0 until cols && grid[nr][nc] == emptyLandValue) {
                                    q.addLast(Pair(nr, nc))
                                    grid[nr][nc]--
                                    total[nr][nc] += steps
                                    minDis = Math.min(minDis, total[nr][nc])
                                }
                            }
                        }
                    }
                    emptyLandValue--
                }
            }
        }

        return if (minDis == Int.MAX_VALUE) -1 else minDis
    }
}

fun main() {
    println(ShortestDistanceFromAllBuildings().shortestDistance(arrayOf(intArrayOf(1, 2, 0))))
    //println(ShortestDistanceFromAllBuildings().shortestDistance(arrayOf(intArrayOf(1, 0, 2, 0, 1), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 1, 0, 0))))
}
