package com.dynamic.program.graphs.dfs

/**
 * Max Area of Island
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally
 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 *
 * Example 1:
 * Input: grid = [
 *  [0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 *
 * Example 2:
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid\[i].length
 * 1 <= m, n <= 50
 * grid\[i]\[j] is either 0 or 1.
 *
 */
class MaxAreaOfIsland {

    private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    val seen = mutableSetOf<String>()
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var ans = Int.MIN_VALUE
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (!seen.contains("$row,$col") && grid[row][col] == 1) {
                    ans = Math.max(ans, bfs(row, col, grid)+1)
                }
            }
        }

        return if (ans == Int.MIN_VALUE) 0 else ans
    }

    private fun isValid(row: Int, col: Int, grid: Array<IntArray>) =
            row >= 0 && col >= 0 && row < grid.size && col < grid[row].size && grid[row][col] == 1

    private fun bfs(row: Int, col: Int, grid: Array<IntArray>): Int {
        val str = "$row,$col"
        var ans = 0
        if (!seen.contains(str)) {
            seen.add(str)
            directions.forEach{ p ->
                val nRow = row + p.first
                val nCol = col + p.second
                val nStr = "$nRow,$nCol"
                if (isValid(nRow, nCol, grid) && !seen.contains(nStr)) {
                    ans++
                    ans += bfs(nRow, nCol, grid)
                }
            }
        }
        return ans
    }
}