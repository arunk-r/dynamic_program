package com.dynamic.program.graphs.medium

/**
 * 310. Minimum Height Trees
Medium

Amazon
Google
Uber
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.



Example 1:


Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:


Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]


Constraints:

1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
 */
class MinimumHeightTrees {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {
        val inDegree = IntArray(n)
        val graph = hashMapOf<Int, MutableList<Int>>()

        for(e in edges) {
            graph.putIfAbsent(e[1], mutableListOf())
            graph[e[1]]?.add(e[0])

            graph.putIfAbsent(e[0], mutableListOf())
            graph[e[0]]?.add(e[1])

            inDegree[e[1]]++
            inDegree[e[0]]++
        }

        val q = ArrayDeque<Int>()
        var lst = mutableListOf<Int>()
        for((i, v) in inDegree.withIndex()) {
            if(v == 1) {
                q.addLast(i)
                lst.add(i)
            }
        }
        while(q.isNotEmpty()) {
            val s = q.size
            val nl = mutableListOf<Int>()
            for( i in 0 until s) {
                val cur = q.removeFirst()
                nl.add(cur)
                graph[cur]?.forEach{ cld ->
                    inDegree[cld]--
                    if(inDegree[cld] == 1) {
                        q.addLast(cld)
                    }
                }
            }
            lst = nl
        }

        return lst
    }

    fun findMinHeightTrees1(n: Int, edges: Array<IntArray>): List<Int> {

        if(n < 2) {
            val lst = mutableListOf<Int>()
            for(i in 0 until n) {
                lst.add(i)
            }
            return lst
        }
        val graph = hashMapOf<Int, MutableList<Int>>()

        for(e in edges) {
            val frm = e[1]
            val to = e[0]

            graph.putIfAbsent(to, mutableListOf())
            graph[to]?.add(frm)

            graph.putIfAbsent(frm, mutableListOf())
            graph[frm]?.add(to)
        }

        var leafNodes = mutableSetOf<Pair<Int, Int>>()

        graph.forEach{ (k, v) ->
            if (v.size == 1) {
                leafNodes.add(Pair(k, v[0]))
            }
        }
        var totalNodes = n

        while( totalNodes > 2) {
            totalNodes = totalNodes - leafNodes.size

            val nl = mutableSetOf<Pair<Int, Int>>()
            leafNodes.forEach{ (k, v) ->
                graph[v]?.remove(k)
                if(graph[v]?.size == 1) {
                    graph[v]?.let{nl.add(Pair(v, it[0]))}
                }
            }
            leafNodes = nl
        }
        val lst = mutableListOf<Int>()
        leafNodes.forEach{ (k, v) -> lst.add(k)}
        return lst
    }
}

fun main() {
    println(MinimumHeightTrees().findMinHeightTrees(4, arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(1,3))))
}
