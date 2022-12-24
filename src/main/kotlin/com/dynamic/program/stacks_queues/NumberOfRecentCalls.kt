package com.dynamic.program.stacks_queues

import java.util.LinkedList

/**
 * Number of Recent Calls
 *
 * Implement the RecentCounter class. It should support ping(int t), which records a call at time t,
 * and then returns an integer representing the number of calls that have happened in the range [t - 3000, t].
 * Calls to ping will have increasing t.
 */


fun recentCalls(pings: IntArray): List<Int> {
    val list = mutableListOf<Int>()
    val queue = LinkedList<Int>()
    pings.forEach { ping ->
        queue.offer(ping)
        while (queue.isNotEmpty() && queue.peek() < (ping - 3000)) {
            queue.poll()
        }
        list.add(queue.size)
    }
    return list
}

fun main() {
    println(recentCalls(intArrayOf(1, 100, 3001, 4000)))
}
