package com.dynamic.program.top.hundred.medium

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/campus-bikes/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 1057. Campus Bikes
 * Medium
 * Topics
 * Companies
 * Hint
 * On a campus represented on the X-Y plane, there are n workers and m bikes, with n <= m.
 *
 * You are given an array workers of length n where workers[i] = [xi, yi] is the position of the ith worker. You are also given an array bikes of length m where bikes[j] = [xj, yj] is the position of the jth bike. All the given positions are unique.
 *
 * Assign a bike to each worker. Among the available bikes and workers, we choose the (workeri, bikej) pair with the shortest Manhattan distance between each other and assign the bike to that worker.
 *
 * If there are multiple (workeri, bikej) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index. If there are multiple ways to do that, we choose the pair with the smallest bike index. Repeat this process until there are no available workers.
 *
 * Return an array answer of length n, where answer[i] is the index (0-indexed) of the bike that the ith worker is assigned to.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation: Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation: Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Constraints:
 *
 * n == workers.length
 * m == bikes.length
 * 1 <= n <= m <= 1000
 * workers[i].length == bikes[j].length == 2
 * 0 <= xi, yi < 1000
 * 0 <= xj, yj < 1000
 * All worker and bike locations are unique.
 */
class CampusBikes {
    data class Edge(val worker: Int, val bike: Int, val distance: Int)

    fun assignBikes(workers: Array<IntArray>, bikes: Array<IntArray>): IntArray {
        val edges = PriorityQueue<Edge> { x, y -> if (x.distance != y.distance) x.distance - y.distance else if (x.worker != y.worker) x.worker - y.worker else x.bike - y.bike }
        for (i in workers.indices) {
            val (s1, e1) = workers[i]
            for (j in bikes.indices) {
                val (s2, e2) = bikes[j]
                edges.add(Edge(i, j, Math.abs(s1 - s2) + Math.abs(e1 - e2)))
            }
        }
        val nodes = workers.size
        val result = IntArray(nodes)
        val usedBikes = HashSet<Int>()
        val bikedAllocatedWorker = HashSet<Int>()
        println(edges)
        while (edges.isNotEmpty()) {
            val (worker, bike, _) = edges.remove()
            if (!usedBikes.contains(bike) && !bikedAllocatedWorker.contains(worker)) {
                result[worker] = bike
                bikedAllocatedWorker.add(worker)
                usedBikes.add(bike)
            }
        }
        return result
    }
}

fun main() {
    println(CampusBikes().assignBikes(arrayOf(intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(2, 0)), arrayOf(intArrayOf(1, 0), intArrayOf(2, 2), intArrayOf(2, 1))).toList())
}
