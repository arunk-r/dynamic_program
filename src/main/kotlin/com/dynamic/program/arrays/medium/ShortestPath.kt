package com.dynamic.program.arrays.medium

/** Your previous Plain Text content is preserved below:

Welcome to your interviewing.io interview.

Once you and your partner have joined, a voice call will start automatically.

Use the language dropdown near the top right to select the language you would like to use.

You can run code by hitting the 'Run' button near the top left.

Enjoy your interview!

In a given grid of 0s and 1s, we have some starting row and column sr, sc and a target row and column tr, tc. Return the length of the shortest path from sr, sc to tr, tc that walks along 1 values only.
Each location in the path, including the start and the end, must be a 1. Each subsequent location in the path must be 4-directionally adjacent to the previous location.
It is guaranteed that grid[sr][sc] = grid[tr][tc] = 1, and the starting and target positions are different.
If the task is impossible, return -1.
Examples:
input:
grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 1, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: 8
(The lines below represent this grid:)
1111
0001
1111

grid = [[1, 1, 1, 1], [0, 0, 0, 1], [1, 0, 1, 1]]
sr = 0, sc = 0, tr = 2, tc = 0
output: -1
(The lines below represent this grid:)
1111
0001
1011
Constraints:
 * [time limit] 5000ms
 * [input] array.array.integer grid
 * 1 ≤ arr.length = arr[i].length ≤ 10
 * [input] integer sr
 * [input] integer sc
 * [input] integer tr
 * [input] integer tc
 * All sr, sc, tr, tc are valid locations in the grid, grid[sr][sc] = grid[tr][tc] = 1, and (sr, sc) != (tr, tc).
 * [output] integer*/

class ShortestPath {
    data class Data(val r: Int, val c: Int, val d: Int)

    fun shortestDistance(grid: Array<IntArray>, sr: Int, sc: Int, tr: Int, tc: Int): Int {

        val dir = listOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))

        val seen = hashSetOf<Pair<Int, Int>>()

        var ans = Int.MAX_VALUE
        val queue = ArrayDeque<Data>()
        queue.addLast(Data(sr, sc, 0))

        while (queue.isNotEmpty()) {
            val d = queue.removeFirst()
            seen.add(Pair(d.r, d.c))

            if (d.r == tr && d.c == tc) {
                ans = Math.min(d.d, ans)
            }

            dir.forEach { (r1, c1) ->
                val nr = d.r + r1
                val nc = d.c + c1

                if (valid(nr, nc, grid, seen)) {
                    queue.addLast(Data(nr, nc, d.d + 1))
                }
            }
        }

        if (ans == Int.MAX_VALUE) return -1

        return ans
    }

    fun valid(r: Int, c: Int, grid: Array<IntArray>, seen: HashSet<Pair<Int, Int>>): Boolean {
        return (r in grid.indices && c in grid[r].indices && grid[r][c] == 1 && !seen.contains(Pair(r, c)))
    }
}

fun main() {
    val grid = arrayOf(
        intArrayOf(1, 1, 1, 1),
        intArrayOf(0, 0, 0, 1),
        intArrayOf(1, 1, 1, 1)
    )
    println(ShortestPath().shortestDistance(grid, 0, 0, 2, 0))
}
