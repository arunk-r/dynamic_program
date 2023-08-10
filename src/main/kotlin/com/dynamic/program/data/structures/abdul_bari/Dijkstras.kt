package com.dynamic.program.data.structures.abdul_bari

import java.util.HashSet
import java.util.PriorityQueue

class Dijkstras {
    data class WeightedEdge(val s: Int, val e: Int, val w: Int)

    fun findShortestPath(n: Int, graph: Array<IntArray>, source: Int, destination: Int): Pair<Int, List<Int>> {
        val distance = IntArray(n) { Int.MAX_VALUE }
        val previous = IntArray(n) {-1}
        distance[source] = 0
        val weights = hashMapOf<Pair<Int, Int>, Int>()
        val map = hashMapOf<Int, MutableList<Int>>()
        val q = PriorityQueue<WeightedEdge> { x, y -> x.w - y.w }

        for ((s, e, w) in graph) {
            map.putIfAbsent(s, mutableListOf())
            map[s]?.add(e)
            weights[Pair(s, e)] = w
            if (s == source) {
                q.add(WeightedEdge(s, e, w))
                if (distance[e] > w){
                    distance[e] = w
                    previous[e] = s
                }
            }
        }
        val visited = HashSet<Int>()
        visited.add(source)
        while (q.isNotEmpty()) {
            val (_, e, w) = q.remove()
            map[e]?.forEach { nei ->
                if (!visited.contains(nei)) {
                    val tempWeight = weights[Pair(e, nei)]?.let { it + w } ?: Int.MAX_VALUE
                    if (tempWeight < distance[nei]) {
                        distance[nei] = tempWeight
                        previous[nei] = e
                        q.add(WeightedEdge(e, nei, tempWeight))
                    }
                    visited.add(nei)
                }
            }
        }
        val path = mutableListOf<Int>()
        path.add(destination)
        var nextIdx = previous[destination]
        while (nextIdx != -1) {
            path.add(nextIdx)
            nextIdx = previous[nextIdx]
        }
        return Pair(distance[destination], path.reversed())
    }
}

fun main() {
    println(Dijkstras().findShortestPath(
            6,
            arrayOf(
                    intArrayOf(0, 1, 50),
                    intArrayOf(0, 2, 45),
                    intArrayOf(0, 3, 10),
                    intArrayOf(3, 4, 15),
                    intArrayOf(3, 0, 10),
                    intArrayOf(1, 2, 10),
                    intArrayOf(1, 3, 15),
                    intArrayOf(4, 1, 20),
                    intArrayOf(4, 2, 35),
                    intArrayOf(2, 4, 30),
                    intArrayOf(5, 4, 3),
            ),
            0, 4
    ))
}
