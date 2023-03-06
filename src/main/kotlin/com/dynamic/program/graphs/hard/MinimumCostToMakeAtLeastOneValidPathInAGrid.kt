package com.dynamic.program.graphs.hard

import java.util.*


/**
 * 1368. Minimum Cost to Make at Least One Valid Path in a Grid
Hard
company
Google
Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.



Example 1:


Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
Output: 3
Explanation: You will start at point (0, 0).
The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
The total cost = 3.
Example 2:


Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
Output: 0
Explanation: You can follow the path from (0, 0) to (2, 2).
Example 3:


Input: grid = [[1,2],[4,3]]
Output: 1


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 100
1 <= grid[i][j] <= 4
 */
class MinimumCostToMakeAtLeastOneValidPathInAGrid {
    data class Cost(val c: Int, val row: Int, val col: Int)

    val dir = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
    var DIR = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    fun minCost(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val q = PriorityQueue { o1: IntArray, o2: IntArray -> o1[0] - o2[0] } // minHeap by cost
        q.offer(intArrayOf(0, 0, 0))
        val dist = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        dist[0][0] = 0
        while (!q.isEmpty()) {
            val top = q.poll()
            val cost = top[0]
            val r = top[1]
            val c = top[2]
            if (dist[r][c] != cost) continue  // avoid outdated (dist[r,c], r, c) to traverse neighbors again!
            for (i in 0..3) {
                val nr: Int = r + DIR[i][0]
                val nc: Int = c + DIR[i][1]
                if (nr in 0 until m && nc in 0 until n) {
                    var ncost = cost
                    if (i != grid[r][c] - 1) ncost += 1 // change direction -> ncost = cost + 1
                    if (dist[nr][nc] > ncost) {
                        dist[nr][nc] = ncost
                        q.offer(intArrayOf(ncost, nr, nc))
                    }
                }
            }
        }
        return dist[m - 1][n - 1]
    }

    fun minCost1(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        val q = PriorityQueue<Cost> { x, y -> x.c - y.c }

        val dp = Array(m) { IntArray(n) { Int.MAX_VALUE } }
        dp[0][0] = 0
        q.add(Cost(0, 0, 0))

        while (q.isNotEmpty()) {
            val cost = q.remove()
            if (cost.c != dp[cost.row][cost.col]) continue
            var idx = 1

            dir.forEach { (r1, c1) ->
                val nr = cost.row + r1
                val nc = cost.col + c1
                var nCost = cost.c
                if (valid(nr, nc, grid)) {
                    if (idx != grid[cost.row][cost.col]) {
                        nCost++
                    }
                    if (nCost < dp[nr][nc]) {
                        dp[nr][nc] = nCost
                        q.add(Cost(nCost, nr, nc))
                    }
                }
                idx++
            }

        }
        return dp[m - 1][n - 1]
    }

    fun valid(r: Int, c: Int, grid: Array<IntArray>) = (r in grid.indices && c in grid[r].indices)
}

fun main() {
    println(MinimumCostToMakeAtLeastOneValidPathInAGrid().minCost1(arrayOf(intArrayOf(1, 1, 1, 1), intArrayOf(2, 2, 2, 2), intArrayOf(1, 1, 1, 1), intArrayOf(2, 2, 2, 2))))
}
