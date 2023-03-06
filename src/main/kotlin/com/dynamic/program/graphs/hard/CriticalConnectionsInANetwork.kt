package com.dynamic.program.graphs.hard

import java.util.*


/**
 * 1192. Critical Connections in a Network
Hard

Facebook
Amazon
Microsoft
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.



Example 1:


Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
Example 2:

Input: n = 2, connections = [[0,1]]
Output: [[0,1]]


Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
 */
class CriticalConnectionsInANetwork {
    fun criticalConnections(n: Int, connections: List<List<Int>>): List<List<Int>> {
        val graph = Array(n) { mutableListOf<Int>() }
        for (connection in connections) {
            graph[connection[0]].add(connection[1])
            graph[connection[1]].add(connection[0])
        }

        val visited = IntArray(n) { -1 }
        val low = IntArray(n) { -1 }
        val result = mutableListOf<List<Int>>()

        fun dfs(node: Int, parent: Int, time: Int): Int {
            visited[node] = time
            low[node] = time

            for (neighbor in graph[node]) {
                if (neighbor == parent) continue
                if (visited[neighbor] == -1) {
                    low[node] = minOf(low[node], dfs(neighbor, node, time + 1))
                } else {
                    low[node] = minOf(low[node], visited[neighbor])
                }
            }

            if (low[node] == visited[node] && parent != -1) {
                result.add(listOf(parent, node))
            }

            return low[node]
        }

        dfs(0, -1, 0)

        return result
    }
}

fun main() {
    println(CriticalConnectionsInANetwork().criticalConnections(4, listOf(listOf(0, 1), listOf(1, 2), listOf(2, 0), listOf(1, 3))))
}
