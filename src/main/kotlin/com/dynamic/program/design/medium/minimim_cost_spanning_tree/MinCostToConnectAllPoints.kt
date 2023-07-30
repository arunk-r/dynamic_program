package com.dynamic.program.design.medium.minimim_cost_spanning_tree

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 *
 * 1584. Min Cost to Connect All Points
 * Medium
 * 3.8K
 * 87
 * company
 * Microsoft
 * company
 * Amazon
 * Directi
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 */
class MinCostToConnectAllPoints {
    data class Edge(val s: Int, val e: Int, val w: Int)

    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val nodes = points.size
        val edges = PriorityQueue<Edge> { x, y -> x.w - y.w }
        for (i in points.indices) {
            val (s1, e1) = points[i]
            for (j in i + 1 until points.size) {
                val (s2, e2) = points[j]
                edges.add(Edge(i, j, Math.abs(s1 - s2) + Math.abs(e1 - e2)))
            }
        }
        val uf = IntArray(nodes){i -> i}
        var totalEdges = nodes
        var weight = 0
        while (totalEdges > 0 && edges.isNotEmpty()) {
            val (s, e, w) = edges.remove()
            if (union(uf, s, e)) {
                weight += w
                totalEdges--
            }
        }
        return weight
    }

    private fun find(uf: IntArray, n: Int): Int {
        var c = n
        while (c != uf[c]) {
            uf[c] = uf[uf[c]]
            c = uf[c]
        }
        return c
    }

    private fun union(uf: IntArray, s: Int, e: Int): Boolean {
        val sp = find(uf, s)
        val ep = find(uf, e)
        if (sp == ep) return false
        if (sp < ep) {
            uf[sp] = ep
        } else {
            uf[ep] = sp
        }
        return true
    }
}
