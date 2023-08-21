package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/remove-interval/
 *
 * 1272. Remove Interval
 * Medium
 * 410
 * 28
 * company
 * Google
 * A set of real numbers can be represented as the union of several disjoint intervals, where each interval is in the form [a, b). A real number x is in the set if one of its intervals [a, b) contains x (i.e. a <= x < b).
 *
 * You are given a sorted list of disjoint intervals intervals representing a set of real numbers as described above, where intervals[i] = [ai, bi] represents the interval [ai, bi). You are also given another interval toBeRemoved.
 *
 * Return the set of real numbers with the interval toBeRemoved removed from intervals. In other words, return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved. Your answer should be a sorted list of disjoint intervals as described above.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 *
 *
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 * Example 3:
 *
 * Input: intervals = [[-5,-4],[-3,-2],[1,2],[3,5],[8,9]], toBeRemoved = [-1,4]
 * Output: [[-5,-4],[-3,-2],[4,5],[8,9]]
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * -109 <= ai < bi <= 109
 */
class RemoveInterval {
    fun removeInterval(intervals: Array<IntArray>, toBeRemoved: IntArray): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        val (rs, re) = toBeRemoved
        for((s, e) in intervals) {
            var t = minOf(e, rs)
            if(t > s) {
                result.add(mutableListOf(s, t))
            }
            t = maxOf(s, re)
            if(t < e) {
                result.add(mutableListOf(t, e))
            }
        }
        return result
    }
    fun removeInterval1(intervals: Array<IntArray>, toBeRemoved: IntArray): List<List<Int>> {
        val result = mutableListOf<MutableList<Int>>()
        val (rs, re) = toBeRemoved
        for((s, e) in intervals) {
            if( s > re || e < rs) {
                result.add(mutableListOf(s, e))
            } else {
                if(s < rs) {
                    result.add(mutableListOf(s, rs))
                }

                if(re < e) {
                    result.add(mutableListOf(re, e))
                }
            }
        }

        return result
    }
}
