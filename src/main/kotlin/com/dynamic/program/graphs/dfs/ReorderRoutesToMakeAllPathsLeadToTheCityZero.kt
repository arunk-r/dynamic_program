package com.dynamic.program.graphs.dfs

/**
 * Reorder Routes to Make All Paths Lead to the City Zero
 * There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities.
 * Roads are represented by connections where connections[i] = [x, y] represents a road from city x to city y. The edges are directed.
 * You need to swap the direction of edges so that every city can reach city 0. Return the minimum number of swaps needed.
 */
class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    private val seen = HashSet<Int>()
    private val edges = HashSet<String>()
    private val graph = hashMapOf<Int, MutableList<Int>>()

    fun minReorder(n: Int, connections: List<List<Int>>): Int {
        for (i in connections.indices) {
            graph.putIfAbsent(i, mutableListOf())
        }
        for (i in connections.indices) {
            val x = connections[i][0]
            val y = connections[i][1]
            graph[x]?.add(y)
            graph[y]?.add(x)
            edges.add(getUniqueGridLoc(x, y))
        }
        seen.add(n)
        return dfs(n)
    }

    private fun getUniqueGridLoc(row: Int, col: Int) = "$row,$col"


    private fun dfs(node: Int): Int {
        var ans = 0
        graph[node]?.forEach { neighbor ->
            if (!seen.contains(neighbor)) {
                if (edges.contains(getUniqueGridLoc(node, neighbor))) {
                    ans++
                }
                seen.add(neighbor)
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