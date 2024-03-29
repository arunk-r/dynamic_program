package com.dynamic.program.graphs.bfs

/**
 * Shortest Path in Binary Matrix
 *
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * Example 1:
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 *
 * Example 2:
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 *
 * Example 3:
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 * n == grid.length
 * n == grid\[i].length
 * 1 <= n <= 100
 * grid\[i]\[j] is 0 or 1
 */

class ShortestPathInBinaryMatrix {
    private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1),Pair(1, 1), Pair(-1, 1), Pair(-1, -1), Pair(1, -1))
    private val seen = mutableSetOf<String>()
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        if (grid[0][0] != 0)
            return -1
        val queue = ArrayDeque<State>()
        queue.add(State(0,0,1))
        while(queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            val str = "${cur.row},${cur.col}"
            if (cur.row == grid.size -1 && cur.col == grid.size -1) {
                return cur.cnt
            }
            if (!seen.contains(str)) {
                seen.add(str)
                directions.forEach{ d ->
                    val nr = cur.row + d.first
                    val nc = cur.col + d.second
                    val nStr = "$nr,$nc"
                    if (isValid(nr, nc, grid) && !seen.contains(nStr)) {
                        queue.addLast(State(nr,nc,cur.cnt+1))
                    }
                }
            }
        }
        return -1
    }

    private fun isValid(row: Int, col: Int, grid: Array<IntArray>) = row >=0 && col >= 0 && row < grid.size && col < grid[row].size && grid[row][col] == 0
}