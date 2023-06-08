package com.dynamic.program.graphs.bfs.hard

import java.util.PriorityQueue

/**
 * 675. Cut Off Trees for Golf Event - https://leetcode.com/problems/cut-off-trees-for-golf-event/description/
 * Hard
 * 1.1K
 * 640
 * company
 * Amazon
 * company
 * Apple
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
 *
 * 0 means the cell cannot be walked through.
 * 1 represents an empty cell that can be walked through.
 * A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
 *
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 *
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 *
 * Note: The input is generated such that no two trees have the same height, and there is at least one tree needs to be cut off.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 * Output: 6
 * Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
 * Example 2:
 *
 *
 * Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
 * Output: -1
 * Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
 * Example 3:
 *
 * Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
 * Output: 6
 * Explanation: You can follow the same path as Example 1 to cut off all the trees.
 * Note that you can cut off the first tree at (0, 0) before making any steps.
 *
 *
 * Constraints:
 *
 * m == forest.length
 * n == forest[i].length
 * 1 <= m, n <= 50
 * 0 <= forest[i][j] <= 109
 * Heights of all trees are distinct.
 */
class CutOffTreesForGolfEvent {
    data class Data(val r: Int, val c: Int, val h: Int, val d: Int)
    fun cutOffTree(forest: List<List<Int>>): Int {
        if (forest[0][0] == 0) return -1

        val q = PriorityQueue<Data>{x,y -> x.h - y.h}
        for(r in forest.indices) {
            for(c in forest[r].indices) {
                if (forest[r][c] > 1) {
                    q.add(Data(r,c,forest[r][c], 0))
                }
            }
        }
        var path = 0
        var sx = 0
        var sy = 0
        while(q.isNotEmpty()) {
            val (r,c,_,_) = q.remove()
            val d = bfs(sx, sy, r, c, forest)
            if (d == -1) return -1
            path += d
            sx = r
            sy = c
        }
        return path
    }

    fun bfs(x1: Int, y1: Int, tx: Int, ty: Int, forest: List<List<Int>>): Int {
        val q = PriorityQueue<Data>{x,y -> x.h - y.h}
        val seen = hashSetOf<Pair<Int, Int>>()
        q.add(Data(x1, y1, forest[0][0], 0))
        seen.add(Pair(x1, y1))


        val dir = listOf(Pair(1,0), Pair(0, 1), Pair(-1,0), Pair(0, -1))
        while(q.isNotEmpty()) {
            val (r, c, h, d) = q.remove()
            if (r == tx && c == ty) return d
            dir.forEach{ (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                if (nr in forest.indices && nc in forest[nr].indices && forest[nr][nc] > 1 && !seen.contains(Pair(nr, nc))) {
                    seen.add(Pair(nr, nc))
                    q.add(Data(nr, nc, forest[nr][nc], d+1))
                }
            }
        }
        return -1
    }
}
