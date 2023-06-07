package com.dynamic.program.graphs.bfs.hard

/**
 * 490. The Maze https://leetcode.com/problems/the-maze/description/
 * Medium
 * 1.7K
 * 169
 * company
 * Square
 * company
 * TikTok
 * company
 * Amazon
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * Example 2:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 */
class TheMaze {
    val dir = listOf(Pair(1,0), Pair(0,1),Pair(-1,0), Pair(0,-1))
    fun hasPath(maze: Array<IntArray>, start: IntArray, destination: IntArray): Boolean {
        val q = ArrayDeque<Pair<Int, Int>>()
        val seen = hashSetOf<Pair<Int, Int>>()

        var p = Pair(start[0], start[1])
        q.add(p)
        seen.add(p)
        val (dr, dc) = destination
        while(q.isNotEmpty()) {
            for(i in q.size downTo 1) {
                val (r, c) = q.removeFirst()
                if (r == dr && c == dc) {
                    return true
                }
                dir.forEach{ (r1, c1) ->
                    var nr = r + r1
                    var nc = c + c1

                    while (nr in maze.indices && nc in maze[nr].indices && maze[nr][nc] == 0) {
                        nr += r1
                        nc += c1
                    }

                    nr -= r1
                    nc -= c1
                    p = Pair(nr, nc)
                    if(!seen.contains(p)) {
                        q.addLast(p)
                        seen.add(p)
                    }
                }
            }
        }
        return false
    }
}
