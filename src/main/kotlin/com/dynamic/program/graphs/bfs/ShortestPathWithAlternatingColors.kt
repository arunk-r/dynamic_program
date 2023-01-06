package com.dynamic.program.graphs.bfs

/**
 * Shortest Path with Alternating Colors
 *
 * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
 * Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
 *
 * You are given two arrays redEdges and blueEdges where:
 * redEdges\[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
 * blueEdges\[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
 * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such
 * that the edge colors alternate along the path, or -1 if such a path does not exist.
 *
 * Example 1:
 * Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
 * Output: [0,1,-1]
 *
 * Example 2:
 * Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
 * Output: [0,1,-1]
 *
 *
 * Constraints:
 * 1 <= n <= 100
 * 0 <= redEdges.length, blueEdges.length <= 400
 * redEdges\[i].length == blueEdges\[j].length == 2
 * 0 <= ai, bi, uj, vj < n
 */
data class State1(val n: Int, val clr: Int, val dst: Int)
class ShortestPathWithAlternatingColors {
    fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
        val distance = IntArray(n) {Int.MAX_VALUE}
        val redG = hashMapOf<Int, MutableList<Int>>()
        val blueG = hashMapOf<Int, MutableList<Int>>()

        val RED = 0
        val BLUE = 1
        map(n, redEdges, redG)
        map(n, blueEdges, blueG)
        val q = ArrayDeque<State1>()
        redG[0]?.let{q.addLast(State1(0, RED, 0))}
        blueG[0]?.let{q.addLast(State1(0, BLUE, 0))}
        val seen = MutableList(n){BooleanArray(2)}
        seen[0][RED] = true
        seen[0][BLUE] = true

        while(q.isNotEmpty()) {
            val c = q.removeFirst()
            distance[c.n] = Math.min(distance[c.n], c.dst)
            val neib = if (c.clr == RED) redG[c.n] else blueG[c.n]
            val oppClr = 1 - c.clr

            neib?.forEach{ ne ->
                if (!seen[ne][oppClr]) {
                    seen[ne][oppClr] = true
                    q.addLast(State1(ne, oppClr, c.dst+1))
                }
            }
        }

        for(i in distance.indices) {
            if(distance[i] == Int.MAX_VALUE) {
                distance[i] = -1
            }
        }

        return distance
    }

    fun map(n: Int, edges: Array<IntArray>, graph: HashMap<Int, MutableList<Int>>) {
        for (i in 0 until n) {
            graph[i] = mutableListOf()
        }
        edges.forEach{ e ->
            val x = e[0]
            val y = e[1]
            graph[x]?.add(y)
        }
    }
}

fun main() {
    //println(ShortestPathWithAlternatingColors().shortestAlternatingPaths(3, arrayOf(intArrayOf(0,1), intArrayOf(1,2)), arrayOf()).toList())
    println(ShortestPathWithAlternatingColors().shortestAlternatingPaths(5,
            arrayOf(intArrayOf(3,2), intArrayOf(4,1), intArrayOf(1,4), intArrayOf(2,4)),
            arrayOf(intArrayOf(2,3), intArrayOf(0,4), intArrayOf(4,3), intArrayOf(4,4), intArrayOf(4,0), intArrayOf(1,0))
    ).toList())
}