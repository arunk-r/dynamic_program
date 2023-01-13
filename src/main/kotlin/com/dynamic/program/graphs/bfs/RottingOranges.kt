package com.dynamic.program.graphs.bfs

/**
 * Rotting Oranges
 * You are given an m x n grid where each cell can have one of three values:
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Example 1:
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Example 2:
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 *
 * Example 3:
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 *
 */
class RottingOranges {
    val dir = listOf(Pair(-1,0),Pair(0,-1), Pair(0,1),Pair(1,0))
    fun orangesRotting(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val trk = mutableSetOf<Pair<Int, Int>>()
        val q = ArrayDeque<Pair<Int, Int>>()
        var cnt = 0

        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 2) {
                    dir.forEach { p ->
                        val nr = r + p.first
                        val nc = c + p.second

                        if (valid(nr, nc, m, n, grid)) {
                            grid[nr][nc] = -1
                            q.addLast(Pair(nr, nc))
                            if (trk.contains(Pair(nr, nc))) {
                                trk.remove(Pair(nr, nc))
                            }
                        }

                    }
                } else if (grid[r][c] == 1) {
                    trk.add(Pair(r,c))
                }
            }
        }

        while(q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until  size) {
                val cur = q.removeFirst()
                dir.forEach { p ->
                    val nr = cur.first + p.first
                    val nc = cur.second + p.second

                    if (valid(nr, nc, m, n, grid)) {
                        grid[nr][nc] = -1
                        q.addLast(Pair(nr, nc))
                        if (trk.contains(Pair(nr, nc))) {
                            trk.remove(Pair(nr, nc))
                        }
                    }

                }
            }
            cnt++
        }

        if(trk.size > 0) return -1
        return cnt
    }

    fun valid(r: Int, c: Int, m: Int, n: Int, grid: Array<IntArray>): Boolean =
        (r in 0 until m && c in 0 until n && grid[r][c] == 1)
}

fun main() {
    println(RottingOranges().orangesRotting(arrayOf(intArrayOf(2,1,1),intArrayOf(1,1,0),intArrayOf(0,1,1))))
    println(RottingOranges().orangesRotting(arrayOf(intArrayOf(2,1,1),intArrayOf(1,1,1),intArrayOf(0,1,2))))
}