package com.dynamic.program.data.structures.abdul_bari.dp

class AllPairsShortestPath {

    fun shortestPath(graph: Array<IntArray>): Unit {
        for (k in graph.indices) {
            for (r in graph.indices) {
                for (c in graph[r].indices) {
                    if (r != k && graph[r][c] != 0) {
                        if (graph[r][k] != Int.MAX_VALUE && graph[k][c] != Int.MAX_VALUE) {
                            graph[r][c] = minOf(graph[r][c], graph[r][k] + graph[k][c])
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    val map = hashMapOf<Char, Int>()


    val graph = arrayOf(
            intArrayOf(0, 3, Int.MAX_VALUE, 7),
            intArrayOf(8, 0, 2, Int.MAX_VALUE),
            intArrayOf(5, Int.MAX_VALUE, 0, 1),
            intArrayOf(2, Int.MAX_VALUE, Int.MAX_VALUE, 0)
    )
    AllPairsShortestPath().shortestPath(graph)
    for (i in graph.indices) {
        println(graph[i].contentToString())
    }
}
