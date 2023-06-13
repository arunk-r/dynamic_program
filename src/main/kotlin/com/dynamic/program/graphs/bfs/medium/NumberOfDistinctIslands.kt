package com.dynamic.program.graphs.bfs.medium

import java.util.Stack

/**
 * https://leetcode.com/problems/number-of-distinct-islands/description/
 *
 * 694. Number of Distinct Islands
 * Medium
 * 2.1K
 * 132
 * company
 * Amazon
 * company
 * TikTok
 * company
 * Bloomberg
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Return the number of distinct islands.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 * Example 2:
 *
 *
 * Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 */
class NumberOfDistinctIslands {
    fun numDistinctIslands(grid: Array<IntArray>): Int {
        val seen = hashSetOf<Pair<Int, Int>>()
        val islands = hashSetOf<String>()
        var count = 0
        for(r in grid.indices) {
            for(c in grid[r].indices) {
                if (grid[r][c] == 1 && !seen.contains(Pair(r, c))) {
                    seen.add(Pair(r, c))
                    val s = islands(r, c, grid, seen)
                    //println(s)
                    if (!islands.contains(s)) {
                        count ++
                        islands.add(s)
                    }
                }
            }
        }
        return count
    }
    val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    private fun islands(r: Int, c: Int, grid: Array<IntArray>, seen: HashSet<Pair<Int, Int>>): String {
        val q = ArrayDeque<Pair<Int, Int>>()
        val sb = StringBuffer()
        sb.append("00")
        q.addLast(Pair(r,c))
        while(q.isNotEmpty()) {
            for(i in q.size downTo 1) {
                val (r, c) = q.removeFirst()
                dir.forEach{ (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    if (nr in grid.indices && nc in grid[nr].indices && grid[nr][nc] == 1 && !seen.contains(Pair(nr, nc))) {
                        seen.add(Pair(nr, nc))
                        q.addLast(Pair(nr, nc))
                        sb.append("$r1$c1")
                    }
                }
                sb.append("e")
            }
        }
        return sb.toString()
    }
}
