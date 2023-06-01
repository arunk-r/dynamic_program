package com.dynamic.program.graphs.bfs.hard

/**
 * 815. Bus Routes
 * Hard
 *
 * company
 * Uber
 * company
 * Amazon
 * company
 * Google
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 *
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 105
 * All the values of routes[i] are unique.
 * sum(routes[i].length) <= 105
 * 0 <= routes[i][j] < 106
 * 0 <= source, target < 106
 */
class BusRoutes {
    data class Route(val stop: Int, val busNo: Int, val noBuses: Int)

    fun numBusesToDestination(routes: Array<IntArray>, source: Int, target: Int): Int {
        val graph = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
        var sourceBusNumber = 0
        // graph construction
        for (r in routes.indices) {
            val route = routes[r]
            for (i in 1 until route.size) {
                val s1 = route[i - 1]
                val s2 = route[i]
                graph.putIfAbsent(s1, mutableListOf())
                graph.putIfAbsent(s2, mutableListOf())
                graph[s1]!!.add(Pair(s2, r))
                graph[s2]!!.add(Pair(s1, r))
                if (source == s1 || source == s2) {
                    sourceBusNumber = r
                }
            }
        }

        val q = ArrayDeque<Route>()
        q.addLast(Route(source, sourceBusNumber, 1))
        val seen = hashSetOf<Int>()
        seen.add(source)
        var minBuses = Int.MAX_VALUE
        while (q.isNotEmpty()) {
            val size = q.size
            for (i in 0 until size) {
                val r = q.removeFirst()
                if (r.stop == target) {
                    minBuses = minOf(minBuses, r.noBuses)
                } else {
                    graph[r.stop]?.forEach { (stop, busNo) ->
                        if (!seen.contains(stop)) {
                            if (busNo != r.busNo) {
                                q.addLast(Route(stop, r.busNo, r.noBuses + 1))
                            } else {
                                q.addLast(Route(stop, r.busNo, r.noBuses))
                            }
                            seen.add(stop)
                        }
                    }
                }
            }
        }

        return if (minBuses == Int.MAX_VALUE) -1
        else minBuses
    }
}

fun main() {
    //println(BusRoutes().numBusesToDestination(arrayOf(intArrayOf(1, 2, 7), intArrayOf(3, 6, 7)), 1, 6))
    println(
        BusRoutes().numBusesToDestination(
            arrayOf(
                intArrayOf(7, 12),
                intArrayOf(4, 5, 15),
                intArrayOf(6),
                intArrayOf(15, 19),
                intArrayOf(9, 12, 13)
            ), 15, 12
        )
    )
}
