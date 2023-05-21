package com.dynamic.program.heaps

import java.util.*

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 *
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 *
 * Constraints:
 * 1 <= k <= points.length <= 10^4
 * -10^4 < xi, yi < 10^4
 *
 */
class KClosestPointsOrigin {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val p = PriorityQueue<IntArray>{x,y -> dist(x)-dist(y)}
        p.addAll(points)
        val result = Array(k){IntArray(2)}
        for(i in 0 until k) {
            if(p.isNotEmpty()) {
                result[i] = p.remove()
            }
        }
        return result
    }
    fun kClosest1(points: Array<IntArray>, k: Int): Array<IntArray> {
        val N = points.size
        val dists = IntArray(N)
        for (i in 0 until N) dists[i] = dist(points[i])
        Arrays.sort(dists)
        val distK = dists[k - 1]
        val ans = Array(k) { IntArray(2) }
        var t = 0
        for (i in 0 until N) if (dist(points[i]) <= distK) ans[t++] = points[i]
        return ans
    }

    private fun dist(point: IntArray): Int {
        return point[0] * point[0] + point[1] * point[1]
    }
}

fun main() {
    println(KClosestPointsOrigin().kClosest(arrayOf(intArrayOf(3,3), intArrayOf(5,-1), intArrayOf(-2,4)), 2).toList())
}
