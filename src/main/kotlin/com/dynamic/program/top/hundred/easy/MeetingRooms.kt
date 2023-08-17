package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/meeting-rooms/
 *
 * 252. Meeting Rooms
 * Easy
 * 1.9K
 * 90
 * company
 * eBay
 * company
 * Google
 * company
 * Amazon
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 */
class MeetingRooms {
    fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
        if(intervals.isEmpty()) return true
        intervals.sortWith(kotlin.Comparator{x, y -> x[0] - y[0]})

        for(i in 1 until intervals.size) {
            val prev = intervals[i-1]
            val cur = intervals[i]
            if(cur[0] in prev[0] until prev[1]) return false
        }
        return true
    }
}
