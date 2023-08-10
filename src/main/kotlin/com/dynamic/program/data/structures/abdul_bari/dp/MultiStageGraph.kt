package com.dynamic.program.data.structures.abdul_bari.dp

class MultiStageGraph {
    fun findShortestPath(graph: Array<IntArray>, source: Int, dest: Int): Pair<Int, MutableList<Int>> {
        val m = graph[0].size
        val cost = IntArray(m) {Int.MAX_VALUE}
        val path = IntArray(m) { -1 }
        cost[m-1] = 0
        for (i in m - 2 downTo 0) {
            for (k in i  until m) {
                if (graph[i][k] != 0 && cost[k] != Int.MAX_VALUE) {
                    val cst = graph[i][k] + cost[k]
                    if (cst < cost[i]) {
                        cost[i] = cst
                        path[i] = k
                    }
                }
            }
        }
        val result = mutableListOf<Int>()
        result.add(source)
        var idx = path[source]
        while (idx != -1) {
            result.add(idx)
            idx = path[idx]
        }
        return Pair(cost[source], result)
    }
}

fun main() {
    println(MultiStageGraph().findShortestPath(
            arrayOf(
                    intArrayOf(0, 1, 2, 5, 0, 0, 0, 0),
                    intArrayOf(0, 0, 0, 0, 4, 11, 0, 0),
                    intArrayOf(0, 0, 0, 0, 9, 5, 16, 0),
                    intArrayOf(0, 0, 0, 0, 0, 0, 2, 0),
                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 18),
                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 13),
                    intArrayOf(0, 0, 0, 0, 0, 0, 0, 2)
            ), 0, 6
    )
    )
}
