package com.dynamic.program.two.pointers.medium

import java.util.PriorityQueue

/**
 * 1229. Meeting Scheduler
Medium

Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []


Constraints:

1 <= slots1.length, slots2.length <= 104
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 109
1 <= duration <= 106
 */
class MeetingScheduler {
    fun minAvailableDuration(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        val p = PriorityQueue<IntArray>{x, y -> x[0] - y[0]}
        for (s in slots1) {
            if (s[1] - s[0] >= duration)
            p.add(s)
        }

        for (s in slots2) {
            if (s[1] - s[0] >= duration)
                p.add(s)
        }

        while (p.isNotEmpty()) {
            val s1 = p.remove()
            val s2 = p.peek()
            if (s1[1] >= s2[0]+duration) {
                return listOf(s2[0], s2[0]+duration)
            }
        }
        return emptyList()
    }
    fun minAvailableDuration1(slots1: Array<IntArray>, slots2: Array<IntArray>, duration: Int): List<Int> {
        slots1.sortWith(kotlin.Comparator{x, y -> x[0] - y[0]})
        slots2.sortWith(kotlin.Comparator{x, y -> x[0] - y[0]})
        val result = mutableListOf<Int>()
        val m = slots1.size
        val n = slots2.size
        var s1Idx = 0
        var s2Idx = 0

        while (s1Idx < m && s2Idx < n) {
            val startTime = maxOf(slots1[s1Idx][0], slots2[s2Idx][0])
            val endTime = minOf(slots1[s1Idx][1], slots2[s2Idx][1])

            val diff = endTime - startTime

            println("$startTime, $endTime, $diff")

            if(diff >= duration) {
                result.add(startTime)
                result.add(startTime+duration)
                break
            }

            if (slots1[s1Idx][1] < slots2[s2Idx][1]) {
                s1Idx++
            } else {
                s2Idx++
            }
        }

        return result
    }
}

fun main() {
    println(MeetingScheduler().minAvailableDuration(arrayOf(intArrayOf(10,50),intArrayOf(60,120),intArrayOf(140,210)), arrayOf(intArrayOf(0,15), intArrayOf(60,70)), 8))
}
