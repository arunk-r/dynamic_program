package com.dynamic.program.graphs.dfs

/**
 * Number of Provinces
 * There are n cities. A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise. Return the total number of provinces.
 */
class NumberOfProvinces {
    private val gMap = mutableMapOf<Int, MutableList<Int>>()
    private var visited = BooleanArray(0)
    fun findProvinces(graph: List<List<Int>>): Int {
        visited = BooleanArray(graph.size)
        var ans = 0
        for (i in graph.indices) {
            if (!gMap.containsKey(i)) {
                gMap[i] = mutableListOf()
            }
            for (j in i + 1 until graph.size) {
                if (!gMap.containsKey(j)) {
                    gMap[j] = mutableListOf()
                }
                if (graph[i][j] == 1) {
                    gMap[i]?.add(j)
                    gMap[j]?.add(i)
                }
            }
        }

        for (i in visited.indices) {
            if (!visited[i]) {
                ans++
                visited[i] = true
                dfs(i)
            }
        }
        return ans
    }

    private fun dfs(node: Int) {
        gMap[node]?.forEach { i ->
            if (!visited[i]) {
                visited[i] = true
                dfs(i)
            }
        }
    }
}

fun main() {
    println(NumberOfProvinces().findProvinces(listOf(listOf(1,1,0),listOf(1,1,0),listOf(0,0,1))))
    println(NumberOfProvinces().findProvinces(listOf(listOf(1,0,0),listOf(0,1,0),listOf(0,0,1))))
}