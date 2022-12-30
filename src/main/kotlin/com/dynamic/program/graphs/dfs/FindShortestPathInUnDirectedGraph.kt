package com.dynamic.program.graphs.dfs

class FindShortestPathInUnDirectedGraph {
    fun shortestPath(graph: HashMap<Int, MutableList<Int>>, s: Int, e: Int): List<Int> {
        return reconstructPath(s, e, path(graph, s))
    }
    private fun path(graph: HashMap<Int, MutableList<Int>>, s: Int): List<Int?> {
        val q = ArrayDeque<Int>()
        val visited = BooleanArray(graph.size)

        q.addLast(s)
        visited[s] = true
        val prev = MutableList<Int?>(graph.size) {null}
        while (q.isNotEmpty()) {
            val node = q.removeFirst()
            graph[node]?.forEach { neighbor ->
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    prev[neighbor] = node
                    q.addLast(neighbor)
                }
            }
            //println(prev)
        }
        return prev
    }

    private fun reconstructPath(s: Int, e: Int, paths: List<Int?>): List<Int> {
        val path = mutableListOf<Int>()
        var curr: Int? = e
        while (curr != null) {
            path.add(curr)
            curr = paths[curr]
        }
        path.reverse()
        return if (path[0] == s) {
            path
        } else {
            emptyList()
        }
    }
}

fun main() {
    val graph = hashMapOf<Int, MutableList<Int>>()
    graph[0] = mutableListOf(9,7,11)
    graph[1] = mutableListOf(8,10)
    graph[2] = mutableListOf(3, 12)
    graph[3] = mutableListOf(2, 4, 7)
    graph[4] = mutableListOf(3)
    graph[5] = mutableListOf(6)
    graph[6] = mutableListOf(7, 5)
    graph[7] = mutableListOf(0, 3, 6, 11)
    graph[8] = mutableListOf(1, 9, 12)
    graph[9] = mutableListOf(0, 8, 10)
    graph[10] = mutableListOf(1, 9)
    graph[11] = mutableListOf(0, 7)
    graph[12] = mutableListOf(2, 8)

    println(FindShortestPathInUnDirectedGraph().shortestPath(graph, 0, 5))

}