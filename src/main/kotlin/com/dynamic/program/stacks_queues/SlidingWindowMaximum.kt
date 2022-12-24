package com.dynamic.program.stacks_queues

/**
 * Sliding Window Maximum
 *
 * Given an integer array nums and an integer k, there is a sliding window of size k that moves from the very left to the very right.
 * For each window, find the maximum element in the window.
 *
 * For example, given nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3, return [3, 3, 5, 5, 6, 7].
 * The first window is [1, 3, -1, -3, 5, 3, 6, 7] and the last window is [1, 3, -1, -3, 5, 3, 6, 7]
 */

fun slidingWindowMaximum(w: IntArray, k: Int): List<Int> {
    val wSize = mutableListOf<Int>()
    val queue = ArrayDeque<Int>()
    for (i in w.indices) {

        // maintain monotonic decreasing.
        // all elements in the deque smaller than the current one
        // have no chance of being the maximum, so get rid of them
        while (queue.isNotEmpty() && w[i] > w[queue.last()]) {
            queue.removeLast()
        }
        queue.addLast(i)

        // queue[0] is the index of the maximum element.
        // if queue[0] + k == i, then it is outside the window or
        // if i - queue[0] == k, then it is outside the window
        if (i - queue.first() == k) {
            queue.removeFirst()
        }

        // only add to the answer once our window has reached size k
        if (i >= k - 1) {
            wSize.add(w[queue.first()])
        }
    }
    return wSize
}

fun main() {
    println(slidingWindowMaximum(intArrayOf(1, 3, -1, -3, -2, 3, 6, 7), 3))
    println(slidingWindowMaximum(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3))
}
