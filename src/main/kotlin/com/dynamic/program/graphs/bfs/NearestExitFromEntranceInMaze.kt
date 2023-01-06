package com.dynamic.program.graphs.bfs

/**
 * Nearest Exit from Entrance in Maze
 */
data class State2(val r: Int, val c: Int, val dst: Int)

class NearestExitFromEntranceInMaze {
    private val dir = listOf(Pair(0,1),Pair(1,0),Pair(0,-1),Pair(-1,0))
    fun nearestExit(maze: Array<CharArray>, entrance: IntArray): Int {
        val m = maze.size
        val n = maze[0].size
        val q = ArrayDeque<State2>()
        q.addLast(State2(entrance[0],entrance[1], 0))
        val seen = MutableList(m){BooleanArray(n)}
        seen[entrance[0]][entrance[1]] = true

        while(q.isNotEmpty()) {
            val c = q.removeFirst()
            dir.forEach {ne ->
                val nr = c.r + ne.first
                val nc = c.c + ne.second
                if (valid(nr, nc, maze) && !seen[nr][nc]) {
                    if ((c.r == 0 || c.c == 0 || c.r == m - 1 || c.c == n - 1)) {
                        return c.dst
                    }
                    seen[nr][nc] = true
                    q.addLast(State2(nr, nc, c.dst+1))
                }
            }
        }
        return -1
    }

    fun valid(r: Int, c: Int, maze: Array<CharArray>) = r >= 0 && c >= 0 && r < maze.size && c < maze[r].size && maze[r][c] == '.'
}

fun main() {
    println(NearestExitFromEntranceInMaze().nearestExit(arrayOf(charArrayOf('+','+','.','+'),charArrayOf('.','.','.','+'),charArrayOf('+','+','+','.')), intArrayOf(1,2)))
}