package com.dynamic.program.dfs.hard

/**
 * 417. Pacific Atlantic Water Flow
Medium
company
Amazon
company
Google
company
TikTok
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.



Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean
[0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean
[1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
[1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
[2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean
[3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean
[3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean
[4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.
 */
class PacificAtlanticWaterFlow {

    val dir = listOf(Pair(0,1), Pair(1,0), Pair(0,-1), Pair(-1,0))

    fun pacificAtlantic(matrix: Array<IntArray>): List<List<Int?>?>? {
        // Check if input is empty
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return ArrayList()
        }

        // Save initial values to parameters
        val numRows = matrix.size
        val numCols = matrix[0].size

        // Setup each queue with cells adjacent to their respective ocean
        val pacificQueue =  ArrayDeque<IntArray>()
        val atlanticQueue =  ArrayDeque<IntArray>()
        for (i in 0 until numRows) {
            pacificQueue.add(intArrayOf(i, 0))
            atlanticQueue.add(intArrayOf(i, numCols - 1))
        }
        for (i in 0 until numCols) {
            pacificQueue.add(intArrayOf(0, i))
            atlanticQueue.add(intArrayOf(numRows - 1, i))
        }

        // Perform a BFS for each ocean to find all cells accessible by each ocean
        val pacificReachable = bfs(pacificQueue, matrix)
        val atlanticReachable = bfs(atlanticQueue, matrix)

        // Find all cells that can reach both oceans
        val commonCells = mutableListOf<List<Int>>()
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    commonCells.add(listOf(i, j))
                }
            }
        }
        return commonCells
    }

    private fun bfs(queue: ArrayDeque<IntArray>, landHeights: Array<IntArray>): Array<BooleanArray> {
        // Save initial values to parameters
        val numRows = landHeights.size
        val numCols = landHeights[0].size

        val reachable = Array(numRows) { BooleanArray(numCols) }
        while (!queue.isEmpty()) {
            val cell: IntArray = queue.removeFirst()
            // This cell is reachable, so mark it
            reachable[cell[0]][cell[1]] = true
            dir.forEach { (r1, c1) -> // Check all 4 directions
                val newRow = cell[0] + r1
                val newCol = cell[1] + c1
                // Check if new cell is within bounds
                if (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
                    return@forEach
                }
                // Check that the new cell hasn't already been visited
                if (reachable[newRow][newCol]) {
                    return@forEach
                }
                // Check that the new cell has a higher or equal height,
                // So that water can flow from the new cell to the old cell
                if (landHeights[newRow][newCol] < landHeights[cell[0]][cell[1]]) {
                    return@forEach
                }
                // If we've gotten this far, that means the new cell is reachable
                queue.addLast(intArrayOf(newRow, newCol))
            }
        }
        return reachable
    }


    fun pacificAtlantic1(heights: Array<IntArray>): List<List<Int>> {
        val pacific = ArrayDeque<Pair<Int, Int>>()
        val atlantic = ArrayDeque<Pair<Int, Int>>()

        val m = heights.size
        val n = heights[0].size

        val pacificSet = hashSetOf<Pair<Int, Int>>()
        val atlanticSet = hashSetOf<Pair<Int, Int>>()

        for (i in 0 until m) {
            var p =Pair(0, i)
            pacific.add(p)
            pacificSet.add(p)
            p = Pair(n - 1, i)
            atlantic.add(p)
            atlanticSet.add(p)
        }

        for (i in 0 until n) {
            var p = Pair(i, 0)
            pacific.add(p)
            pacificSet.add(p)

            p = Pair(i, m - 1)
            atlantic.add(p)
            atlanticSet.add(p)
        }

        while (pacific.isNotEmpty()) {
            val (r, c) = pacific.removeFirst()
            val h = heights[r][c]
            dir.forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                val p = Pair(nr, nc)
                if (valid(nr, nc, heights, h) && !pacificSet.contains(p)) {
                    pacificSet.add(p)
                    pacific.addLast(p)
                }
            }
        }

        val result = mutableSetOf<List<Int>>()
        while (atlantic.isNotEmpty()) {
            val (r, c) = atlantic.removeFirst()
            val h = heights[r][c]
            dir.forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                val p = Pair(nr, nc)

                if (valid(nr, nc, heights, h) ) {
                    if (pacificSet.contains(p)) {
                        result.add(listOf(p.first, p.second))
                    }
                    if (!atlanticSet.contains(p)) {
                        atlanticSet.add(p)
                        atlantic.addLast(p)
                    }
                }
            }
        }
        if (result.isEmpty()) return listOf(listOf(0,0))
        return result.toList()
    }

    fun valid(r: Int, c: Int, heights: Array<IntArray>, h: Int): Boolean
    = (r in heights.indices && c in heights[r].indices && heights[r][c] >= h)
}

fun main() {
    println(PacificAtlanticWaterFlow().pacificAtlantic(arrayOf(intArrayOf(1, 2, 2, 3, 5), intArrayOf(3, 2, 3, 4, 4), intArrayOf(2, 4, 5, 3, 1), intArrayOf(6, 7, 1, 4, 5), intArrayOf(5, 1, 1, 2, 4))))
    println(PacificAtlanticWaterFlow().pacificAtlantic(arrayOf(intArrayOf(1))))
}
