package com.dynamic.program.graphs.dfs

/**
 * Number of Islands
 * Given an m x n 2D binary grid which represents a map of 1 (land) and 0 (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 */
class NumberOfIslands {
    private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    private val seen = HashSet<String>()

    fun numberOfIslands(grid: List<List<Int>>): Int {
        var ans = 0
        for (row in grid.indices) {
            for (col in grid[0].indices) {
                val str = getUniqueGridLoc(row, col)
                if (grid[row][col] == 1 && !seen.contains(str)) {
                    ans++
                    seen.add(str)
                    dfs(row, col, grid)
                }
            }
        }
        //println(seen)
        return ans
    }

    private fun isValid(row: Int, col: Int, grid: List<List<Int>>): Boolean {
        return row >= 0 && col >= 0 && row < grid.size && col < grid[0].size && grid[row][col] == 1
    }

    private fun getUniqueGridLoc(row: Int, col: Int) = "$row,$col"

    private fun dfs(row: Int, col: Int, grid: List<List<Int>>) {
        directions.forEach { pair ->
            val nextRow = row + pair.first
            val nextCol = col + pair.second
            val str = getUniqueGridLoc(nextRow, nextCol)
            if (isValid(nextRow, nextCol, grid) && !seen.contains(str)) {
                seen.add(str)
                dfs(nextRow, nextCol, grid)
            }
        }
    }
}

fun main() {
    val island = listOf(
            listOf(1, 1, 0, 0, 0, 1),
            listOf(0, 1, 0, 0, 0, 0),
            listOf(0, 1, 1, 0, 1, 1),
            listOf(0, 0, 0, 0, 0, 1),
            listOf(1, 1, 1, 1, 0, 1),
            listOf(1, 1, 1, 1, 0, 1)
    )
    println(NumberOfIslands().numberOfIslands(island))
}