package com.dynamic.program.strings_arrays.medium

/**
 * 56. Merge Intervals
Medium
Bloomberg
Facebook
Amazon
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
class MergeIntervals {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(kotlin.Comparator{x, y -> x[0]-y[0]})
        val result = mutableListOf<IntArray>()
        var rIdx = 0
        var idx = 1
        result.add(intervals[rIdx])
        while(idx < intervals.size) {
            if(result[rIdx][1] >= intervals[idx][0]) {
                result[rIdx][1] = maxOf(result[rIdx][1],intervals[idx][1])
            } else {
                result.add(intervals[idx])
                rIdx++
            }
            idx++
        }
        return result.toTypedArray()
    }
}
