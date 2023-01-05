package com.dynamic.program.graphs.bfs

/**
 * Shortest Path in a Grid with Obstacles Elimination
 *
 *
 */

data class NState(val r: Int, val c: Int, val rmn: Int, val cnt: Int)

class ShortestPathInGridWithObstaclesElimination {
    private val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    fun shortestPath(grid: Array<IntArray>, k: Int): Int {
        val n = grid.size
        val m = grid[0].size
        val seen = MutableList(n) { MutableList(m) { BooleanArray(k + 1) } }
        val q = ArrayDeque<NState>()
        q.addLast(NState(0, 0, k, 0))
        seen[0][0][k] = true
        while (q.isNotEmpty()) {
            val g = q.removeFirst()
            val remain = g.rmn
            if (g.r == n - 1 && g.c == m - 1) return g.cnt

            dir.forEach { d ->
                val nr = g.r + d.first
                val nc = g.c + d.second
                if (isValid(nr, nc, grid)) {
                    if (grid[nr][nc] == 0 && !seen[nr][nc][remain]) {
                        seen[nr][nc][remain] = true
                        q.addLast(NState(nr, nc, remain, g.cnt + 1))
                    } else if (grid[nr][nc] == 1 && remain > 0 && !seen[nr][nc][remain - 1]) {
                        seen[nr][nc][remain - 1] = true
                        q.addLast(NState(nr, nc, remain - 1, g.cnt + 1))
                    }
                }
            }
        }
        return -1
    }

    private fun isValid(r: Int, c: Int, grid: Array<IntArray>) = r >= 0 && c >= 0 && r < grid.size && c < grid[r].size
}