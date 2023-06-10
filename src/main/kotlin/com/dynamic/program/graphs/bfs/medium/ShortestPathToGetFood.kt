package com.dynamic.program.graphs.bfs.medium

/**
 * https://leetcode.com/problems/shortest-path-to-get-food/description/
 *
 * 1730. Shortest Path to Get Food
 * Medium
 * 615
 * 35
 * company
 * Amazon
 * company
 * DoorDash
 * company
 * Bloomberg
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 * Example 2:
 *
 *
 * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 * Example 3:
 *
 *
 * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[row][col] is '*', 'X', 'O', or '#'.
 * The grid contains exactly one '*'.
 */
class ShortestPathToGetFood {
    fun getFood(grid: Array<CharArray>): Int {
        val dest = hashSetOf<Pair<Int, Int>>()
        val q = ArrayDeque<Triple<Int, Int, Int>>()
        val seen = hashSetOf<Pair<Int, Int>>()

        for(r in grid.indices) {
            for(c in grid[r].indices) {
                if (grid[r][c] == 'X') {
                    seen.add(Pair(r, c))
                } else if (grid[r][c] == '*') {
                    q.addLast(Triple(r, c, 1))
                } else if (grid[r][c] == '#') {
                    dest.add(Pair(r, c))
                }
            }
        }

        val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(0, -1), Pair(-1, 0))
        var min = Int.MAX_VALUE
        while(q.isNotEmpty()) {
            for(i in q.size downTo 1) {
                val (r, c, d) = q.removeFirst()
                dir.forEach{ (r1, c1) ->
                    val nr = r + r1
                    val nc = c + c1
                    val p = Pair(nr, nc)
                    if(nr in grid.indices && nc in grid[nr].indices && !seen.contains(p)) {
                        if (dest.contains(p)){
                            min = minOf(min, d)
                        } else {
                            seen.add(p)
                            q.addLast(Triple(nr, nc, d+1))
                        }
                    }
                }
            }
        }
        return if (min == Int.MAX_VALUE)
            -1
        else min
    }
}
