package com.dynamic.program.interval


/**
 * https://leetcode.com/problems/insert-interval/description/
 *
 * 57. Insert Interval
 * Medium
 * 8.4K
 * 597
 * company
 * Google
 * company
 * Adobe
 * company
 * Amazon
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */
class InsertInterval {
    fun insert(intervals: Array<IntArray>, ni: IntArray): Array<IntArray> {
        val result = mutableListOf<IntArray>()
        var idx = 0
        // identify first part of merge
        while (idx < intervals.size && intervals[idx][0] < ni[0]) {
            result.add(intervals[idx++])
        }
        // merge the new interval
        if (intervals.isEmpty() || result.isEmpty() || ni[0] > result[result.size-1][1]) {
            result.add(ni)
        } else {
            val d = result[result.size - 1]
            d[0] = minOf(d[0], ni[0])
            d[1] = maxOf(d[1], ni[1])
        }
        // merge remaining
        while (idx < intervals.size) {
            val d = result[result.size - 1]
            val n = intervals[idx++]

            if (n[0] in d[0]..d[1]) {
                d[0] = minOf(d[0], n[0])
                d[1] = maxOf(d[1], n[1])
            } else {
                result.add(n)
            }
        }
        return result.toTypedArray()
    }
}

fun main() {
    /*println(
        InsertInterval().insert(
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 5),
                intArrayOf(6, 7),
                intArrayOf(8, 10),
                intArrayOf(12, 16)
            ), intArrayOf(4, 8)
        )
    )*/

    println(
        InsertInterval().insert(
            arrayOf(
                intArrayOf(1, 5)
            ), intArrayOf(6, 8)
        )
    )
}
