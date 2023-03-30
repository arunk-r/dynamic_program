package com.dynamic.program.arrays.medium

import java.util.Arrays
import java.util.PriorityQueue

/**
 * 253. Meeting Rooms II
Medium

company
Bloomberg
company
Amazon
company
Apple
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1


Constraints:

1 <= intervals.length <= 104
0 <= starti < endi <= 106
 */
class MeetingRoomsII {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        Arrays.sort(intervals, kotlin.Comparator{x, y -> x[0] - y[0]})
        val q = PriorityQueue<Int>()
        for(i in intervals) {
            val s = i[0]
            val e = i[1]

            if (q.isNotEmpty() && q.peek() <= s) {
                q.remove()
                q.add(e)
            } else {
                q.add(e)
            }
        }

        return q.size
    }
}
