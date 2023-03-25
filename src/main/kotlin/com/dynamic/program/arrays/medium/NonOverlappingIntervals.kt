package com.dynamic.program.arrays.medium

/**
 * 435. Non-overlapping Intervals
Medium

Amazon
Facebook
JPMorgan
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
class NonOverlappingIntervals {
    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {
        intervals.sortWith(kotlin.Comparator{x,y -> x[1]-y[1]})
        var ans = 0
        var prev = 0
        for(i in 1 until intervals.size) {
            val p = intervals[prev]
            val c = intervals[i]
            if(c[0] < p[1]) {
                ans++
            } else {
                prev = i
            }
        }
        return ans
    }

    fun eraseOverlapIntervals1(intervals: Array<IntArray>): Int {
        intervals.sortWith(kotlin.Comparator{x,y -> x[1]-y[1]})
        var ans = 1
        var prev = intervals[0][1]
        for(i in 1 until intervals.size) {
            val c = intervals[i]
            if(c[0] >= prev) {
                ans++
                prev = c[1]
            }
        }
        return intervals.size - ans
    }
}
