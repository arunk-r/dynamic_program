package com.dynamic.program.union.find.medium

/**
 * 261. Graph Valid Tree
Medium
company
LinkedIn
company
Google
company
Amazon
You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.



Example 1:


Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true
Example 2:


Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false


Constraints:

1 <= n <= 2000
0 <= edges.length <= 5000
edges[i].length == 2
0 <= ai, bi < n
ai != bi
There are no self-loops or repeated edges.
 */
class GraphValidTree {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        if (edges.size != n - 1) return false
        val arr = IntArray(n)

        for(i in 0 until n) {
            arr[i] = i
        }

        for(edge in edges) {
            val v1 = edge[0]
            val v2 = edge[1]
            if (!union(arr, v1, v2)) return false
        }

        return true
    }

    private fun union(arr: IntArray, v1: Int, v2: Int): Boolean {
        val v1Parent = find(arr, v1)
        val v2Parent = find(arr, v2)

        if (v1Parent == v2Parent) return false
        arr[v1Parent] = v2Parent
        return true
    }

    private fun find(arr: IntArray, v: Int): Int {
        var c = v
        while(c != arr[c]) {
            arr[c] = arr[arr[c]]
            c = arr[c]
        }

        return c
    }
}
