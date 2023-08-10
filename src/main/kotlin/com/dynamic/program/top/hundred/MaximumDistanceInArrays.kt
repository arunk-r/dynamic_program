package com.dynamic.program.top.hundred

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/maximum-distance-in-arrays/
 * 624. Maximum Distance in Arrays
 * Medium
 * 676
 * 68
 * company
 * Yahoo
 * You are given m arrays, where each array is sorted in ascending order.
 *
 * You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.
 *
 * Return the maximum distance.
 *
 *
 *
 * Example 1:
 *
 * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
 * Output: 4
 * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
 * Example 2:
 *
 * Input: arrays = [[1],[1]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == arrays.length
 * 2 <= m <= 105
 * 1 <= arrays[i].length <= 500
 * -104 <= arrays[i][j] <= 104
 * arrays[i] is sorted in ascending order.
 * There will be at most 105 integers in all the arrays.
 */
class MaximumDistanceInArrays {
    fun maxDistance(arrays: List<List<Int>>): Int {
        var min = arrays[0].first()
        var max = arrays[0].last()

        var res = Int.MIN_VALUE
        for(i in 1 until arrays.size) {
            val n = arrays[i]
            res = maxOf(res,
                    maxOf(Math.abs(n.last() - min), Math.abs(max - n.first()))
            )
            min = minOf(min, n.first())
            max = maxOf(max, n.last())
        }

        return res
    }

    /**
     * works only for +ve values
     */
    fun maxDistance1(arrays: List<List<Int>>): Int {
        val q1 = PriorityQueue<Pair<Int, Int>>{x,y -> x.first - y.first}
        val q2 = PriorityQueue<Pair<Int, Int>>{x,y -> y.first - x.first}
        for(i in arrays.indices) {
            for(v in arrays[i]) {
                q1.add(Pair(v, i))
                q2.add(Pair(v, i))
            }
        }
        while(q1.peek().second == q2.peek().second) {
            q2.remove()
        }
        println("${q2.peek().first} - ${q1.peek().first}")
        return q2.peek().first - q1.peek().first
    }
}
