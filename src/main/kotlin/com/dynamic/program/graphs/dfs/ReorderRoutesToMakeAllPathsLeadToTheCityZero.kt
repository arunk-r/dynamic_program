package com.dynamic.program.graphs.dfs

/**
 * Reorder Routes to Make All Paths Lead to the City Zero
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities.
 * Roads are represented by connections where connections[i] = [x, y] represents a road from city x to city y. The edges are directed.
 * You need to swap the direction of edges so that every city can reach city 0. Return the minimum number of swaps needed.
 */
class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    val visited = mutableSetOf<Int>()
    val edges = mutableSetOf<String>()
    val graph = hashMapOf<Int, MutableList<Int>>()
    fun minReorder(n: Int, connections: List<List<Int>>): Int {
        connections.forEach { edge ->
            val x = edge[0]
            val y = edge[1]

            graph.putIfAbsent(x, mutableListOf<Int>())
            graph.putIfAbsent(y, mutableListOf<Int>())

            graph[x]?.add(y)
            graph[y]?.add(x)
            edges.add("$x,$y")
        }
        return dfs(0)
    }

    private fun dfs(start: Int): Int {
        var ans = 0
        if (!visited.contains(start)) {
            visited.add(start)
            graph[start]?.forEach { neighbor ->
                if (!visited.contains(neighbor) && edges.contains("$start,$neighbor")) {
                    ans++
                }
                ans += dfs(neighbor)
            }
        }
        return ans
    }
}

fun main() {
    println(ReorderRoutesToMakeAllPathsLeadToTheCityZero().minReorder(0, listOf(listOf(0, 1), listOf(1, 3), listOf(2, 3), listOf(4, 0), listOf(4, 5))))
    println(ReorderRoutesToMakeAllPathsLeadToTheCityZero().minReorder(0, listOf(listOf(1, 0), listOf(1, 2), listOf(3, 2), listOf(3, 4))))
    println(ReorderRoutesToMakeAllPathsLeadToTheCityZero().minReorder(0, listOf(listOf(1, 0), listOf(2, 0))))
}