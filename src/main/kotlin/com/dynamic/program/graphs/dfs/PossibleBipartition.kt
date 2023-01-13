package com.dynamic.program.graphs.dfs

/**
 * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike
 * some other people, and they should not go into the same group.
 *
 * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not
 * like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
 *
 *
 * Example 1:
 * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: The first group has [1,4], and the second group has [2,3].
 *
 * Example 2:
 * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
 *
 *
 * Constraints:
 * 1 <= n <= 2000
 * 0 <= dislikes.length <= 10^4
 * dislikes\[i].length == 2
 * 1 <= ai < bi <= n
 * All the pairs of dislikes are unique.
 *
 */
class PossibleBipartition {
    fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        val graph = hashMapOf<Int, MutableList<Int>>()
        for (i in 1 .. n) {
            graph[i] = mutableListOf()
        }
        dislikes.forEach{ p ->
            graph[p[0]]?.add(p[1])
            graph[p[1]]?.add(p[0])
        }
        val color = MutableList(n+1){-1}
        for(i in 1 .. n) {
            if (color[i] == -1) {
                if (!dfs(i, 0, color, graph)) {
                    return false
                }
            }
        }
        return true
    }

    fun dfs(node: Int, clr: Int, color: MutableList<Int>, graph: Map<Int, List<Int>>): Boolean{
        color[node] = clr
        graph[node]?.forEach{ nei ->
            if (color[nei] == color[node]) {
                return false
            }
            if (color[nei] == -1) {
                if(!dfs(nei, 1 - clr, color, graph)) {
                    return false
                }
            }
        }
        return true
    }
}

fun main() {
    println(PossibleBipartition().possibleBipartition(4, arrayOf(intArrayOf(1,2), intArrayOf(1,3), intArrayOf(2,4))))
    println(PossibleBipartition().possibleBipartition(5, arrayOf(intArrayOf(1,3), intArrayOf(1,5), intArrayOf(2,4),
        intArrayOf(4,5)
    )))
}