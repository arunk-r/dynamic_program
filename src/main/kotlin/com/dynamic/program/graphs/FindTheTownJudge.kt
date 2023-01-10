package com.dynamic.program.graphs

/**
 * Find the Town Judge
 *
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 *
 * If the town judge exists, then:
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 *
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 *
 *
 * Example 1:
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 *
 * Example 2:
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 *
 * Example 3:
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *
 * Constraints:
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * All the pairs of trust are unique.
 * ai != bi
 * 1 <= ai, bi <= n
 *
 */
class FindTheTownJudge {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        val graph = hashMapOf<Int, HashSet<Int>>()
        roads.forEach{ road ->
            val n1 = road[0]
            val n2 = road[1]

            graph.putIfAbsent(n1, hashSetOf())
            graph[n1]?.add(n2)

            graph.putIfAbsent(n2, hashSetOf())
            graph[n2]?.add(n1)
        }
        var res = 0
        for(i in 0 until n) {
            for (j in i+1 until n) {
                val city1 = graph[i]?.size ?: 0
                val city2 = graph[j]?.size ?: 0
                val city1InCity2 = graph[j]?.contains(i) ?: false
                val removeDuplicate = if (city1InCity2) 1 else 0
                res = Math.max(res, city1+city2-removeDuplicate)
            }
        }
        return res
    }
}

fun main() {
    println(FindTheTownJudge().maximalNetworkRank(5, arrayOf(intArrayOf(0,1),intArrayOf(0,3),intArrayOf(1,2),intArrayOf(1,3),intArrayOf(2,3),intArrayOf(2,4))))
    println(FindTheTownJudge().maximalNetworkRank(5, arrayOf(intArrayOf(0,1),intArrayOf(0,3),intArrayOf(1,2),intArrayOf(1,3))))
}