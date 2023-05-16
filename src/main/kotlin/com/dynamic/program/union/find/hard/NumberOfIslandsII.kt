package com.dynamic.program.union.find.hard

/**
 * 305. Number of Islands II
 * Hard
 *
 * company
 * Uber
 * company
 * Amazon
 * company
 * Google
 * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 *
 * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 * Example 2:
 *
 * Input: m = 1, n = 1, positions = [[0,0]]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= m, n, positions.length <= 104
 * 1 <= m * n <= 104
 * positions[i].length == 2
 * 0 <= ri < m
 * 0 <= ci < n
 *
 *
 * Follow up: Could you solve it in time complexity O(k log(mn)), where k == positions.length?
 */
class NumberOfIslandsII {

    class UnionFind(val size: Int) {
        private var parents = mutableListOf<Int>()
        private var rank = 0
        private var count = 0

        init {
            parents = MutableList(size) { -1 }
        }

        fun numberOfNodes(): Int = count

        fun addNode(n: Int) {
            if (parents[n] >= 0) return
            parents[n] = n
            count++
        }

        fun isLand(n: Int): Boolean {
            return parents[n] >= 0
        }

        fun find(n: Int): Int {
            var c = n
            while (c != parents[c]) {
                parents[c] = parents[parents[c]]
                c = parents[c]
            }

            return c
        }

        fun union(x: Int, y: Int) {
            val xp = find(x)
            val yp = find(y)
            if (xp == yp) return
            if (parents[xp] < 0) {
                parents[xp] = yp
            } else {
                parents[yp] = xp
            }
            count--
        }
    }

    val dir = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, 0), Pair(0, -1))
    fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        val union = UnionFind(m * n)
        for (p in positions) {
            val r = p[0]
            val c = p[1]
            val landPosition = r * n + c
            union.addNode(landPosition)

            dir.forEach { (r1, c1) ->
                val nr = r + r1
                val nc = c + c1
                val neighbor = nr * n + nc
                if (valid(nr, nc, m, n) && union.isLand(neighbor)) {
                    union.union(landPosition, neighbor)
                }
            }
            result.add(union.numberOfNodes())
        }
        return result
    }

    fun valid(r: Int, c: Int, m: Int, n: Int): Boolean =
        (r in 0 until m && c in 0 until n)
}

fun main() {
    println(NumberOfIslandsII().numIslands2(2,2, arrayOf(intArrayOf(0,0), intArrayOf(1,1), intArrayOf(0,1))))
}
