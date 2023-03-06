package com.dynamic.program.trees.dfs.medium

/**
 * 1319. Number of Operations to Make Network Connected
Medium
Akuna Capital
Microsoft
Activision
There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.

You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.

Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.



Example 1:


Input: n = 4, connections = [[0,1],[0,2],[1,2]]
Output: 1
Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
Example 2:


Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
Output: 2
Example 3:

Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
Output: -1
Explanation: There are not enough cables.


Constraints:

1 <= n <= 105
1 <= connections.length <= min(n * (n - 1) / 2, 105)
connections[i].length == 2
0 <= ai, bi < n
ai != bi
There are no repeated connections.
No two computers are connected by more than one cable.
 */
class NumberOfOperationsToMakeNetworkConnected {
    fun makeConnected(n: Int, connections: Array<IntArray>): Int {
        if (connections.size < n-1) return -1
        val map = hashMapOf<Int, MutableList<Int>>()

        for(i in connections) {
            if (map[i[0]] == null) {
                map[i[0]] = mutableListOf()
            }
            if (map[i[1]] == null) {
                map[i[1]] = mutableListOf()
            }
            map[i[0]]!!.add(i[1])
            map[i[1]]!!.add(i[0])
        }
        val seen = BooleanArray(n)
        var cnt = 0
        for(i in 0 until n) {
            if(!seen[i]) {
                cnt++
                dfs(i, map, seen)
            }
        }
        return cnt-1
    }

    fun dfs(i: Int, map: HashMap<Int, MutableList<Int>>, seen: BooleanArray) {
        seen[i] = true
        if(map[i] == null) {
            return
        }
        for(n in map[i]!!) {
            if(!seen[n]) {
                seen[n] = true
                dfs(n, map, seen)
            }
        }
    }
}
