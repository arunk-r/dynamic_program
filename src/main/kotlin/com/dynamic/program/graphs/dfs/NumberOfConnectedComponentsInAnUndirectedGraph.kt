package com.dynamic.program.graphs.dfs

/**
 * Number of Connected Components in an Undirected Graph
 *
 * You have a graph of n nodes. You are given an integer n and an array edges where edges\[i] = [ai, bi]
 * indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * Example 1:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 * Constraints:
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges\[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
class NumberOfConnectedComponentsInAnUndirectedGraph {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val graph = hashMapOf<Int, MutableList<Int>>()
        edges.forEach{ edge ->
            val x = edge[0]
            val y = edge[1]
            graph.putIfAbsent(x, mutableListOf())
            graph.putIfAbsent(y, mutableListOf())

            graph[x]?.add(y)
            graph[y]?.add(x)
        }

        var ans = n - graph.size
        val visited = BooleanArray(n)
        graph.forEach{ (k, _) ->
            if (!visited[k]) {
                bfs(k, graph, visited)
                ans++
            }
        }
        return ans
    }

    private fun bfs(curNode: Int, graph: Map<Int, List<Int>>, visited: BooleanArray) {
        if (!visited[curNode]) {
            visited[curNode] = true
            graph[curNode]?.forEach{ neighbor ->
                if (!visited[neighbor]) {
                    bfs(neighbor, graph, visited)
                }
            }
        }
    }
}

fun main() {
    println(NumberOfConnectedComponentsInAnUndirectedGraph().countComponents(5, arrayOf(intArrayOf(0,1), intArrayOf(1,2), intArrayOf(3,4))))
}