package com.dynamic.program.arrays.matrix.hard

/**
 * 2132. Stamping the Grid
Hard

Google
Rubrik
You are given an m x n binary matrix grid where each cell is either 0 (empty) or 1 (occupied).

You are then given stamps of size stampHeight x stampWidth. We want to fit the stamps such that they follow the given restrictions and requirements:

Cover all the empty cells.
Do not cover any of the occupied cells.
We can put as many stamps as we want.
Stamps can overlap with each other.
Stamps are not allowed to be rotated.
Stamps must stay completely inside the grid.
Return true if it is possible to fit the stamps while following the given restrictions and requirements. Otherwise, return false.



Example 1:


Input: grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
Output: true
Explanation: We have two overlapping stamps (labeled 1 and 2 in the image) that are able to cover all the empty cells.
Example 2:


Input: grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
Output: false
Explanation: There is no way to fit the stamps onto all the empty cells without the stamps going outside the grid.


Constraints:

m == grid.length
n == grid[r].length
1 <= m, n <= 105
1 <= m * n <= 2 * 105
grid[r][c] is either 0 or 1.
1 <= stampHeight, stampWidth <= 105
 */
class StampingTheGrid {
    private fun withInBounds(ps: Array<IntArray>, r1: Int, c1: Int, r2: Int, c2: Int): Int {
        val topRight = if (r1 - 1 in ps.indices) ps[r1 - 1][c2] else 0
        val bottomLeft = if (c1 - 1 in ps[0].indices) ps[r2][c1-1] else 0
        val topLeft = if (r1 - 1 in ps.indices && c1 - 1 in ps[0].indices) ps[r1 - 1][c1 - 1] else 0
        return ps[r2][c2] - topRight - bottomLeft + topLeft
    }

    fun possibleToStamp1(grid: Array<IntArray>, stampHeight: Int, stampWidth: Int): Boolean {
        val ps = Array(grid.size){IntArray(grid[0].size)}
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                ps[r][c] = grid[r][c]
            }
        }
        for (c in grid[0].indices) {
            for (r in 1 until grid.size) {
                ps[r][c] += ps[r - 1][c]
            }
        }

        for (r in grid.indices) {
            for (c in 1 until grid[r].size) {
                ps[r][c] += ps[r][c - 1]
            }
        }

        val stamped = Array(grid.size) { IntArray(grid[0].size) }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 0 && r + stampHeight-1 < grid.size && c + stampWidth-1 < grid[r].size && withInBounds(ps, r, c, r + stampHeight - 1, c + stampWidth - 1) == 0) {
                    stamped[r][c]++
                    if (c + stampWidth < grid[r].size) {
                        stamped[r][c + stampWidth]--
                    }
                }
            }
        }

        for (r in grid.indices) {
            for (c in 1 until grid[r].size) {
                stamped[r][c] += stamped[r][c - 1]
            }
        }

        val marked = Array(grid.size) { Array<Boolean?>(grid[0].size){null} }
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if (grid[r][c] == 0 && stamped[r][c] == 1) {
                    marked[r][c] = true
                }
            }
        }

        for (c in grid[0].indices) {
            for (r in grid.indices) {
                if (grid[r][c] == 1 || marked[r][c] == false) continue
                var k = r
                while (k < grid.size && stamped[k][c] == 1) {
                    k++
                }
                var down = stampHeight
                k--
                while (down-- > 0) {
                    if (k < grid.size) {
                        marked[k++][c] = true
                    }
                }
            }
        }

        for (r in grid.indices) {
            for (c in grid[r].indices) {
                if(grid[r][c] == 0 && marked[r][c] != true) return false
            }
        }
        return true
    }

    fun possibleToStamp(grid: Array<IntArray>, stampHeight: Int, stampWidth: Int): Boolean {
        var ps = Array(grid.size + 1) { IntArray(grid[0].size + 1) }
        val stamped = Array(grid.size) { IntArray(grid[0].size) }
        prefix(grid, ps)

        for (r in stampHeight - 1 until grid.size) {
            for (c in stampWidth - 1 until grid[r].size) {
                if (sum(ps, r - stampHeight + 1, c - stampWidth + 1, r, c) == 0) {
                    stamped[r][c] = 1
                }
            }
        }
        ps = Array(grid.size + 1) { IntArray(grid[0].size + 1) }
        prefix(stamped, ps)


        for (r in grid.indices) {
            for (c in grid[r].indices) {

                if (grid[r][c] == 0 &&
                    sum(ps, r, c, minOf(grid.size - 1, r + stampHeight - 1), minOf(grid[r].size - 1, c + stampWidth - 1)) == 0
                ) {
                    return false
                }
            }
        }
        return true
    }

    fun sum(ps: Array<IntArray>, r1: Int, c1: Int, r2: Int, c2: Int): Int {
        return ps[r2 + 1][c2 + 1] - ps[r1][c2 + 1] - ps[r2 + 1][c1] + ps[r1][c1]
    }

    fun prefix(grid: Array<IntArray>, ps: Array<IntArray>) {
        for (r in grid.indices) {
            for (c in grid[r].indices) {
                ps[r + 1][c + 1] = grid[r][c] + ps[r + 1][c] + ps[r][c + 1] - ps[r][c]
            }
        }
    }

}

fun main() {
    println(StampingTheGrid().possibleToStamp(arrayOf(intArrayOf(1, 0, 0, 0), intArrayOf(1, 0, 0, 0), intArrayOf(1, 0, 0, 0), intArrayOf(1, 0, 0, 0), intArrayOf(1, 0, 0, 0)), 4, 3))
}
