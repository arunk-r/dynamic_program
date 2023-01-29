package com.dynamic.program.backtracking

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 * Example 1:
 * Input: grid = [[1,0,0,0),intArrayOf(0,0,0,0),intArrayOf(0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 *
 * Example 2:
 * Input: grid = [[1,0,0,0),intArrayOf(0,0,0,0),intArrayOf(0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 *
 * Example 3:
 * Input: grid = [[0,1),intArrayOf(2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 */
class UniquePathsIII {
    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    var paths = 0
    fun uniquePathsIII(grid: Array<IntArray>): Int {
        var start = Pair(0, 0)
        var end = Pair(0, 0)
        var obstacles = 0
        val m = grid.size
        val n = grid[0].size
        val seen = Array(m) { BooleanArray(n) }
        for (r in grid.indices) {
            for ((c, v) in grid[r].withIndex()) {
                if (v == 1) {
                    start = Pair(r, c)
                    seen[r][c] = true
                }
                if (v == 2) {
                    end = Pair(r, c)
                }
                if (v == -1) {
                    obstacles++
                }
            }
        }
        val target = m * n - obstacles

        backtrack(start, end, 1, target, grid, seen)
        return paths
    }

    fun backtrack(start: Pair<Int, Int>, end: Pair<Int, Int>, cellCnt: Int, target: Int, grid: Array<IntArray>, seen: Array<BooleanArray>) {
        val (sr, sc) = start
        val (er, ec) = end
        if (sr == er && sc == ec && cellCnt == target) {
            paths++
            return
        } else {
            dir.forEach { (r1, c1) ->
                val nr = sr + r1
                val nc = sc + c1
                if (valid(nr, nc, grid, seen)) {
                    seen[nr][nc] = true
                    backtrack(Pair(nr, nc), end, cellCnt + 1, target, grid, seen)
                    seen[nr][nc] = false
                }
            }
        }
    }

    fun valid(r: Int, c: Int, grid: Array<IntArray>, seen: Array<BooleanArray>): Boolean =
        r in grid.indices && c in grid[r].indices && grid[r][c] != -1 && !seen[r][c]
}

fun main() {
    println(
        UniquePathsIII().uniquePathsIII(
            arrayOf(
                intArrayOf(1, 0, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 0, 2, -1)
            )
        )
    )
}