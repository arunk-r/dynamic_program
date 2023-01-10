package com.dynamic.program.graphs

/**
 * Island Perimeter
 */
class IslandPerimeter {

    // check only left and top
    fun islandPerimeter2(grid: Array<IntArray>): Int {
        var perimeter = 0
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if(grid[r][c] == 1) {
                    perimeter += 4

                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter -= 2
                    }
                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter -= 2
                    }
                }
            }
        }
        return perimeter
    }
    //Check all 4 directions
    val dir = listOf(Pair(0,1),Pair(1,0),Pair(0,-1),Pair(-1,0))
    fun islandPerimeter(grid: Array<IntArray>): Int {
        var perimeter = 0
        val seen = MutableList(grid.size){BooleanArray(grid[0].size)}
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (valid(i,j,grid) && !seen[i][j]) {
                    seen[i][j] = true
                    perimeter += getPerimeter(i,j,grid)
                }
            }
        }
        return perimeter
    }

    fun valid(r: Int, c: Int, grid: Array<IntArray>): Boolean =
        (r in grid.indices && c in grid[r].indices && grid[r][c] == 1)

    fun getPerimeter(r: Int, c: Int, grid: Array<IntArray>): Int {
        var perimeter = 0
        val m = grid.size
        val n = grid[r].size
        dir.forEach{ side ->
            val nr = r+side.first
            val nc = c+side.second
            if (nr < 0 || nc < 0 || nr >= m || nc >= n){
                perimeter++
            } else if (grid[nr][nc] == 0) {
                perimeter++
            }
        }
        return perimeter
    }
}

fun main() {
    println(IslandPerimeter().islandPerimeter(arrayOf(intArrayOf(0,1,0,0),intArrayOf(1,1,1,0),intArrayOf(0,1,0,0),intArrayOf(1,1,0,0))))
    println(IslandPerimeter().islandPerimeter2(arrayOf(intArrayOf(0,1,0,0),intArrayOf(1,1,1,0),intArrayOf(0,1,0,0),intArrayOf(1,1,0,0))))
}
