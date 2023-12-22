package com.dynamic.program.graphs.hard

import java.util.PriorityQueue

/**
 * 1168. Optimize Water Distribution in a Village
 * Hard
 * 1.1K
 * 39
 * company
 * Google
 * company
 * Amazon
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the same two houses with different costs.
 *
 * Return the minimum total cost to supply water to all houses.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation: The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 * Example 2:
 *
 * Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
 * Output: 2
 * Explanation: We can supply water with cost two using one of the three options:
 * Option 1:
 *   - Build a well inside house 1 with cost 1.
 *   - Build a well inside house 2 with cost 1.
 * The total cost will be 2.
 * Option 2:
 *   - Build a well inside house 1 with cost 1.
 *   - Connect house 2 with house 1 with cost 1.
 * The total cost will be 2.
 * Option 3:
 *   - Build a well inside house 2 with cost 1.
 *   - Connect house 1 with house 2 with cost 1.
 * The total cost will be 2.
 * Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 104
 * wells.length == n
 * 0 <= wells[i] <= 105
 * 1 <= pipes.length <= 104
 * pipes[j].length == 3
 * 1 <= house1j, house2j <= n
 * 0 <= costj <= 105
 * house1j != house2j
 */
class OptimizeWaterDistributionInAVillage {
    fun minCostToSupplyWater(n: Int, wells: IntArray, pipes: Array<IntArray>): Int {
        val graph = HashMap<Int, MutableList<Pair<Int, Int>>>()

        for((s, e, c) in pipes) {
            graph.putIfAbsent(s, mutableListOf())
            graph.putIfAbsent(e, mutableListOf())
            graph[s]?.add(Pair(e, c))
            graph[e]?.add(Pair(s, c))
        }

        val pq = PriorityQueue<Pair<Int, Int>>{x,y -> x.second - y.second}
        for((i, c) in wells.withIndex()) {
            pq.add(Pair(i+1, c))
        }
        println(graph)
        println(pq)
        val seen = HashSet<Int>()
        var cost = 0
        while(pq.isNotEmpty()) {
            val (n, c) = pq.remove()
            if(seen.contains(n)) continue
            seen.add(n)
            cost += c
            graph[n]?.forEach{ (next, cst) ->
                if(!seen.contains(next)) {
                    pq.add(Pair(next, cst))
                }
            }
        }
        if(seen.size < n) return -1
        else return cost
    }

    fun largestNumber(nums: IntArray): String {
        val str = Array(nums.size){""}
        for((i, n) in nums.withIndex()) {
            str[i] = "$n"
        }
        str.sortWith(kotlin.Comparator{x, y -> val o1 = x+y; val o2 = y + x; o2.compareTo(o1)})
        val buf = StringBuffer()
        for(s in str) {
            buf.append(s)
        }
        return buf.toString()
    }
}

fun main() {
    println(OptimizeWaterDistributionInAVillage().minCostToSupplyWater(3, intArrayOf(1,2,2), arrayOf(intArrayOf(1,2,1), intArrayOf(2,3,1))))
}
