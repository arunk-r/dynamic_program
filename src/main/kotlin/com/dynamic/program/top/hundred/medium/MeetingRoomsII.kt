package com.dynamic.program.top.hundred.medium

import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 * 253. Meeting Rooms II
 * Medium
 * 6.6K
 * 139
 * company
 * Google
 * company
 * TikTok
 * company
 * Amazon
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
class MeetingRoomsII {
    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        intervals.sortWith(kotlin.Comparator{x,y -> x[0] - y[0]})
        val p = PriorityQueue<IntArray>{x, y -> x[1] - y[1]}
        var cnt = 0
        for(interval in intervals) {
            if(p.isNotEmpty() && interval[0] !in p.peek()[0] until p.peek()[1]) {
                p.remove()
            } else {
                cnt++
            }
            p.add(interval)
        }

        return cnt
    }
}
