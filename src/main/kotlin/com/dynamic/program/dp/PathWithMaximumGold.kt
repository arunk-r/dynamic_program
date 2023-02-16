package com.dynamic.program.dp

/**
 * Path with Maximum Gold
 *
 * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position, you can walk one step to the left, right, up, or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 * Example 1:
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 *
 *
 * Example 2:
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 *
 */
class PathWithMaximumGold {
    var max = 0
    fun getMaximumGold(grid: Array<IntArray>): Int {
        val seen = Array(grid.size){BooleanArray(grid[0].size)}
        for(r in grid.indices) {
            for(c in grid[r].indices) {
                if(grid[r][c] != 0) {
                    seen[r][c] = true
                    dp(r,c,grid[r][c],grid, seen)
                    seen[r][c] = false
                }
            }
        }
        return max
    }

    fun dp(r: Int, c: Int, sum: Int, grid: Array<IntArray>, seen: Array<BooleanArray>) {
        max = maxOf(max, sum)
        listOf(Pair(1,0), Pair(0,1),Pair(-1,0), Pair(0,-1)).forEach{ (r1, c1) ->
            val nr = r + r1
            val nc = c + c1
            if(valid(nr, nc, grid, seen)) {
                seen[nr][nc] = true
                dp(nr,nc, sum+grid[nr][nc], grid, seen)
                seen[nr][nc] = false
            }
        }
    }

    fun valid(r: Int, c: Int, grid: Array<IntArray>, seen: Array<BooleanArray>): Boolean =
        (r in grid.indices && c in grid[r].indices && grid[r][c] != 0 && !seen[r][c])
}