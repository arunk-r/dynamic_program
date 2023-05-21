package com.dynamic.program.graphs


/**
 * Evaluate Division
 * You are given an array of variable pairs equations and an array of real numbers values, where equations\[i] = [Ai, Bi]
 * and values\[i] represent the equation Ai / Bi = values\[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries\[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * Constraints:
 * 1 <= equations.length <= 20
 * equations\[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values\[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries\[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 *
 */

internal class EvaluateDivision {
    fun calcEquation(
        equations: List<List<String>>, values: DoubleArray,
        queries: List<List<String>>
    ): DoubleArray {
        val graph = HashMap<String, HashMap<String, Double>>()

        // Step 1). build the graph from the equations
        for (i in equations.indices) {
            val dividend = equations[i][0]
            val divisor = equations[i][1]

            val quotient = values[i]

            graph.putIfAbsent(dividend, hashMapOf())
            graph.putIfAbsent(divisor, hashMapOf())
            graph[dividend]!![divisor] = quotient
            graph[divisor]!![dividend] = 1 / quotient
        }

        // Step 2). Evaluate each query via bactracking (DFS)
        // by verifying if there exists a path from dividend to divisor
        val results = DoubleArray(queries.size)
        for (i in queries.indices) {
            val query = queries[i]
            val dividend = query[0]
            val divisor = query[1]
            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0
            } else if (dividend === divisor) {
                results[i] = 1.0
            } else {
                val visited = HashSet<String>()
                results[i] = backtrackEvaluate(graph, dividend, divisor, 1.0, visited)
            }
        }
        return results
    }

    private fun backtrackEvaluate(
        graph: HashMap<String, HashMap<String, Double>>,
        currNode: String,
        targetNode: String,
        accProduct: Double,
        visited: MutableSet<String>
    ): Double {

        // mark the visit
        visited.add(currNode)
        var ret = -1.0
        val neighbors: Map<String, Double> = graph[currNode]!!
        if (neighbors.containsKey(targetNode)) ret = accProduct * neighbors[targetNode]!! else {
            for ((nextNode, value) in neighbors) {
                if (visited.contains(nextNode)) continue
                ret = backtrackEvaluate(
                    graph, nextNode, targetNode,
                    accProduct * value, visited
                )
                if (ret != -1.0) break
            }
        }

        // unmark the visit, for the next backtracking
        visited.remove(currNode)
        return ret
    }
}

fun main() {
    /*println(
        EvaluateDivision().calcEquation(
            listOf(listOf("a", "b"), listOf("b", "c")),
            doubleArrayOf(2.0, 3.0),
            listOf(listOf("a", "c"), listOf("b", "a"), listOf("a", "e"), listOf("a", "a"), listOf("x", "x"))
        ).toList()
    )*/

    println(
        EvaluateDivision().calcEquation(
            listOf(listOf("a", "e"), listOf("b", "e")),
            doubleArrayOf(4.0, 3.0),
            listOf(listOf("a", "b"), listOf("e", "e"), listOf("x", "x"))
        ).toList()
    )
}
