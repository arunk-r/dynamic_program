package com.dynamic.program.graphs.dfs

import java.util.*

/**
 * Find if Path Exists in Graph
 *
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges\[i] = [ui, vi] denotes a bi-directional edge between vertex ui
 * and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 *
 * Example 1:
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * Example 2:
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 * Constraints:
 * 1 <= n <= 2 * 10^5
 * 0 <= edges.length <= 2 * 10^5
 * edges\[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 */
class FindPathExistsInGraph {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        val graph = hashMapOf<Int, MutableList<Int>>()
        edges.forEach { edge ->
            val x = edge[0]
            val y = edge[1]
            graph.putIfAbsent(x, mutableListOf())
            graph.putIfAbsent(y, mutableListOf())
            graph[x]?.add(y)
            graph[y]?.add(x)
        }

        val visited = BooleanArray(n)
        val stack = Stack<Int>()
        stack.add(source)
        while (stack.isNotEmpty()) {
            val node = stack.pop()
            if (node == destination) return true
            if (!visited[node]) {
                visited[node] = true
                graph[node]?.forEach { neighbor ->
                    stack.push(neighbor)
                }
            }
        }
        return false

    }
}

fun main() {
    println(FindPathExistsInGraph().validPath(5, arrayOf(intArrayOf(0, 4)), 0, 4))
    println(FindPathExistsInGraph().validPath(3, arrayOf(intArrayOf(0, 1), intArrayOf(1,2), intArrayOf(2,0)), 0, 2))
}