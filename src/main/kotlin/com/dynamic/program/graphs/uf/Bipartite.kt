package com.dynamic.program.graphs.uf

class Bipartite {
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val uf = IntArray(graph.size){i -> i}
        val rank = IntArray(graph.size) {1}
        for (i in graph.indices) {
            for (j in graph[i].indices) {
                // If the node is in the same component as its neighbors, then they are in the same set and the graph is not bipartite.
                // Bipartite means each node can only be connected to a node in another set/component
                if (isConnected(uf, i, graph[i][j])) return false

                // We union all the neighbors together
                union(uf, rank, graph[i][0], graph[i][j])
            }
        }
        return true
    }


    fun find(uf: IntArray,x: Int): Int {
        if (uf[x] == x) return x
        val root = find(uf, uf[x])
        uf[x] = root // path compression
        return root
    }

    fun union(uf: IntArray, rank: IntArray, x: Int, y: Int) {
        val rootx = find(uf, x)
        val rooty = find(uf, y)
        if (rootx == rooty) return
        if (rank[rootx] > rank[rooty]) {
            uf[rooty] = rootx
            rank[rootx]++
        } else {
            uf[rootx] = rooty
            rank[rooty]++
        }
    }

    fun isConnected(uf: IntArray, p: Int, q: Int): Boolean {
        return find(uf, p) == find(uf, q)
    }

}
