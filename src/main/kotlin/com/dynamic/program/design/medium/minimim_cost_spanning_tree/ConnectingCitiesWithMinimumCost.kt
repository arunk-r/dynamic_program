package com.dynamic.program.design.medium.minimim_cost_spanning_tree

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/connecting-cities-with-minimum-cost/description/
 *
 * 1135. Connecting Cities With Minimum Cost
 * Medium
 * 1.1K
 * 59
 * company
 * Amazon
 * There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.
 *
 * Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities. If it is impossible to connect all the n cities, return -1,
 *
 * The cost is the sum of the connections' costs used.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation: Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * Example 2:
 *
 *
 * Input: n = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation: There is no way to connect all cities even if all edges are used.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 * 1 <= connections.length <= 104
 * connections[i].length == 3
 * 1 <= xi, yi <= n
 * xi != yi
 * 0 <= costi <= 105
 */
class ConnectingCitiesWithMinimumCost {
    data class Edge(val s: Int, val e: Int, val w: Int)
    fun minimumCost(n: Int, connections: Array<IntArray>): Int {
        val q = PriorityQueue<Edge>{x,y -> x.w - y.w}
        for((s, e, w) in connections) {
            q.add(Edge(s-1,e-1,w))
        }
        val uf = IntArray(n){i ->i}
        var cnt = n
        var cost = 0
        while(q.isNotEmpty()) {
            val (s, e, w) = q.remove()
            if(union(uf, s, e)) {
                cost += w
                cnt--
            }
        }
        if(cnt == 1) return cost
        return -1
    }

    fun find(uf:IntArray, i: Int): Int {
        var c = i
        while(c != uf[c]) {
            uf[c] = uf[uf[c]]
            c = uf[c]
        }
        return c
    }

    fun union(uf: IntArray, i: Int, j: Int): Boolean {
        val ip = find(uf, i)
        val jp = find(uf, j)

        if(ip == jp) return false
        else if(ip < jp) {
            uf[ip] = jp
        } else {
            uf[jp] = ip
        }
        return true
    }
}
