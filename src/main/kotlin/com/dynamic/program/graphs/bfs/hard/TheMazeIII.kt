package com.dynamic.program.graphs.bfs.hard

import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.forEach
import kotlin.collections.hashSetOf
import kotlin.collections.indices
import kotlin.collections.isNotEmpty
import kotlin.collections.listOf


/**
 * 499. The Maze III - https://leetcode.com/problems/the-maze-iii/
 * Hard
 * 424
 * 66
 * company
 * Google
 * company
 * Microsoft
 * company
 * Amazon
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls onto the hole.
 *
 * Given the m x n maze, the ball's position ball and the hole's position hole, where ball = [ballrow, ballcol] and hole = [holerow, holecol], return a string instructions of all the instructions that the ball should follow to drop in the hole with the shortest distance possible. If there are multiple valid instructions, return the lexicographically minimum one. If the ball can't drop in the hole, return "impossible".
 *
 * If there is a way for the ball to drop in the hole, the answer instructions should contain the characters 'u' (i.e., up), 'd' (i.e., down), 'l' (i.e., left), and 'r' (i.e., right).
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
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [0,1]
 * Output: "lul"
 * Explanation: There are two shortest ways for the ball to drop into the hole.
 * The first way is left -> up -> left, represented by "lul".
 * The second way is up -> left, represented by 'ul'.
 * Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".
 * Example 2:
 *
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], ball = [4,3], hole = [3,0]
 * Output: "impossible"
 * Explanation: The ball cannot reach the hole.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0,0,0],[0,0,1,0,0,1,0],[0,0,0,0,1,0,0],[0,0,0,0,0,0,1]], ball = [0,4], hole = [3,5]
 * Output: "dldr"
 *
 *
 * Constraints:
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * ball.length == 2
 * hole.length == 2
 * 0 <= ballrow, holerow <= m
 * 0 <= ballcol, holecol <= n
 * Both the ball and the hole exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 */
class TheMazeIII {
    val dir = listOf(Triple(0, -1, 'l'), Triple(0, 1, 'r'), Triple(1, 0, 'd'), Triple(-1, 0, 'u'))

    data class Data(val r: Int, val c: Int)
    data class Distance(val pos: Data, val cnt: Int = 0, val dir: String = "")

    fun findShortestWay(maze: Array<IntArray>, ball: IntArray, hole: IntArray): String? {
        val heap: PriorityQueue<Distance> = PriorityQueue<Distance> { x, y -> if (x.cnt == y.cnt) x.dir.compareTo(y.dir) else x.cnt - y.cnt }
        val seen = HashSet<Data>()
        heap.add(Distance(Data(ball[0], ball[1]), 0, ""))
        while (!heap.isEmpty()) {
            val (pos, cnt, direction) = heap.remove()
            if (seen.contains(pos)) {
                continue
            }
            if (pos.r == hole[0] && pos.c == hole[1]) {
                return direction
            }
            seen.add(pos)
            getNeighbors(pos, maze, hole).forEach { (newPos, newCnt, newDir) ->
                heap.add(Distance(newPos, cnt + newCnt, "$direction$newDir"))
            }
        }
        return "impossible"
    }

    private fun valid(row: Int, col: Int, maze: Array<IntArray>): Boolean = row in maze.indices && col in maze[row].indices && maze[row][col] == 0

    private fun getNeighbors(d: Data, maze: Array<IntArray>, hole: IntArray): List<Distance> {
        val neighbors = mutableListOf<Distance>()
        dir.forEach { (r1, c1, c) ->
            var count = 0
            var nr = d.r
            var nc = d.c
            while (valid(nr+r1, nc+c1, maze)) {
                nr += r1
                nc += c1
                count++
                if (nr == hole[0] && nc == hole[1]) {
                    break
                }
            }
            neighbors.add(Distance(Data(nr, nc), count, "$c"))
        }
        return neighbors
    }

    fun findShortestWay1(maze: Array<IntArray>, ball: IntArray, hole: IntArray): String {
        val q = ArrayDeque<Triple<Int, Int, String>>()
        val seen = hashSetOf<Pair<Int, Int>>()

        val (sr, sc) = ball
        val (dr, dc) = hole
        q.addLast(Triple(sr, sc, ""))
        seen.add(Pair(sr, sc))

        val set = TreeSet<String>()

        while (q.isNotEmpty()) {
            val (r, c, str) = q.removeFirst()
            dir.forEach { (r1, c1, d) ->
                var nr = r + r1
                var nc = c + c1
                while (nr in maze.indices && nc in maze[nr].indices && maze[nr][nc] == 0) {
                    if (nr == dr && nc == dc) {
                        set.add("$str$d")
                    }
                    nr += r1
                    nc += c1
                }

                nr -= r1
                nc -= c1

                if (!seen.contains(Pair(nr, nc))) {
                    seen.add(Pair(nr, nc))
                    q.addLast(Triple(nr, nc, "$str$d"))
                }
            }
        }
        return if (set.isEmpty()) {
            "impossible"
        } else {
            //println(set)
            set.first()
        }
    }
}

fun main() {
    println(TheMazeIII().findShortestWay(arrayOf(
            intArrayOf(0, 0, 0, 0, 0), intArrayOf(1, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 1, 0, 0, 1), intArrayOf(0, 1, 0, 0, 0)
    ), intArrayOf(4, 3), intArrayOf(0, 1)))
}
