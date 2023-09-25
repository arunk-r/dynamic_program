package com.dynamic.program.graphs.bfs.hard

import java.util.PriorityQueue

/**
 * 505. The Maze II - https://leetcode.com/problems/the-maze-ii/description/
 * Medium
 * 1.2K
 * 52
 * company
 * Amazon
 * company
 * Google
 * company
 * ByteDance
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: 12
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * Example 2:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: -1
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
 * 0 <= startrow, destinationrow < m
 * 0 <= startcol, destinationcol < n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 */
class TheMazeII {
    data class Data(val r: Int, val c: Int)
    data class Distance(val pos: Data, val cnt: Int = 0)

    val dir = listOf(Data(1, 0), Data(0, 1), Data(-1, 0), Data(0, -1))

    fun shortestDistance(maze: Array<IntArray>, start: IntArray, destination: IntArray): Int {
        val distance = Array(maze.size) { IntArray(maze[0].size) { Int.MAX_VALUE } }
        distance[start[0]][start[1]] = 0
        dijkstra(maze, Data(start[0], start[1]), distance)
        val result = distance[destination[0]][destination[1]]
        return if (result == Int.MAX_VALUE) -1 else result
    }

    private fun dijkstra(maze: Array<IntArray>, start: Data, distance: Array<IntArray>) {
        val q = PriorityQueue<Distance> { x, y -> x.cnt - y.cnt }
        q.add(Distance(start))

        while (q.isNotEmpty()) {
            val (d, cnt) = q.remove()
            if (distance[d.r][d.c] < cnt) {
                continue
            } else {
                dir.forEach { (r1, c1) ->
                    var nr = d.r + r1
                    var nc = d.c + c1
                    var count = 0
                    while (nr in maze.indices && nc in maze[nr].indices && maze[nr][nc] == 0) {
                        nr += r1
                        nc += c1
                        count++
                    }
                    nr -= r1
                    nc -= c1
                    if (distance[d.r][d.c] + count < distance[nr][nc]) {
                        distance[nr][nc] = distance[d.r][d.c] + count
                        q.add(Distance(Data(nr, nc), distance[nr][nc]))
                    }
                }
            }
        }
    }

    fun shortestDistance1(maze: Array<IntArray>, start: IntArray, destination: IntArray): Int {
        val (sr, sc) = start
        val (dr, dc) = destination
        val q = ArrayDeque<Triple<Int, Int, Int>>()
        val seen = hashSetOf<Pair<Int, Int>>()

        q.addLast(Triple(sr, sc, 0))
        seen.add(Pair(sr, sc))
        var min = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            val (r, c, cnt) = q.removeFirst()
            if (r == dr && c == dc) {
                min = minOf(cnt, min)
            } else {
                dir.forEach { (r1, c1) ->
                    var nr = r + r1
                    var nc = c + c1
                    var nCnt = 0
                    while (nr in maze.indices && nc in maze[nr].indices && maze[nr][nc] == 0) {
                        nr += r1
                        nc += c1
                        nCnt++
                    }

                    nr -= r1
                    nc -= c1
                    if (!seen.contains(Pair(nr, nc))) {
                        seen.add(Pair(nr, nc))
                        q.addLast(Triple(nr, nc, cnt + nCnt))
                    }
                }
            }
        }
        return if (min == Int.MAX_VALUE)
            -1
        else min
    }
}

fun main() {
    println(TheMazeII().shortestDistance(arrayOf(
            intArrayOf(0, 0, 1, 0, 0),
            intArrayOf(0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(1, 1, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 0)
    ), intArrayOf(0, 4), intArrayOf(4, 4)))
}
