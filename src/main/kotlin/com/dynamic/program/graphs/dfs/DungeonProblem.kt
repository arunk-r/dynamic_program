package com.dynamic.program.graphs.dfs

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room
 * and must fight his way through dungeon to rescue the princess.
 *
 * The dungeon has a size of R x C and you start at cell 'S' and there's an exit at cell 'E'.
 * A cell ful of rock is indicated by a '#' and empty cells are represented by a '.'
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 */
class DungeonProblem {
    private val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))

    fun dungeonProblem(dun: List<List<Char>>): List<Pair<Int, Int>> {
        var startCell = Pair(-1, -1)
        var endCell = Pair(-1, -1)
        val graph = hashMapOf<Pair<Int, Int>, List<Pair<Int, Int>>>()
        for (row in dun.indices) {
            for (col in dun[0].indices) {
                if (dun[row][col] != '#') {
                    graph[Pair(row, col)] = getNeighbors(row, col, dun)
                    if (dun[row][col] == 'S') {
                        startCell = Pair(row, col)
                    }
                    if (dun[row][col] == 'E') {
                        endCell = Pair(row, col)
                    }
                }
            }
        }
        return reconstructPath(startCell, endCell, path(startCell, graph))
    }

    private fun path(s: Pair<Int, Int>, graph: HashMap<Pair<Int, Int>, List<Pair<Int, Int>>>): Map<Pair<Int, Int>, Pair<Int, Int>> {
        val prev = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        val visited = HashSet<Pair<Int, Int>?>(graph.size)
        val q = ArrayDeque<Pair<Int, Int>>()

        q.addLast(s)
        visited.add(s)

        while (q.isNotEmpty()) {
            val node = q.removeFirst()
            graph[node]?.forEach { neighbor ->
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)
                    q.addLast(neighbor)
                    prev[neighbor] = node
                }
            }
        }
        return prev
    }

    private fun reconstructPath(s: Pair<Int, Int>, e: Pair<Int, Int>, paths: Map<Pair<Int, Int>, Pair<Int, Int>>): List<Pair<Int, Int>> {
        val fPaths = mutableListOf<Pair<Int, Int>>()

        var cur: Pair<Int, Int>? = e
        while (cur != null) {
            fPaths.add(cur)
            cur = paths[cur]
        }
        fPaths.reverse()
        return if (fPaths[0] == s) {
            fPaths
        } else {
            emptyList()
        }
    }

    private fun isValid(row: Int, col: Int, grid: List<List<Char>>): Boolean {
        return row >= 0 && col >= 0 && row < grid.size && col < grid[0].size && grid[row][col] != '#'
    }

    private fun getNeighbors(row: Int, col: Int, dun: List<List<Char>>): List<Pair<Int, Int>> {
        val neighbors = mutableListOf<Pair<Int, Int>>()
        directions.forEach { pair ->
            val nextRow = row + pair.first
            val nextCol = col + pair.second
            if (isValid(nextRow, nextCol, dun)) {
                neighbors.add(Pair(nextRow, nextCol))
            }
        }
        return neighbors
    }
}

fun main() {
    val dungeon = listOf(
            listOf('S', '.', '.', '#', '.', '.', '.'),
            listOf('.', '#', '.', '.', '.', '#', '.'),
            listOf('.', '#', '.', '.', '.', '.', '.'),
            listOf('.', '.', '#', '#', '.', '.', '.'),
            listOf('#', '.', '#', 'E', '.', '#', '.'),
    )

    println(DungeonProblem().dungeonProblem(dungeon))
}