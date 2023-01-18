package com.dynamic.program.greedy

import java.util.PriorityQueue

/**
 * Least Number of Unique Integers after K Removals
 */
class LeastNumberOfUniqueIntegersAfterKRemovals {
    fun findLeastNumOfUniqueInts(arr: IntArray, k: Int): Int {
        val map = hashMapOf<Int, Int>()
        arr.forEach{ a ->
            map[a] = map[a]?.let{ it +1 } ?: 1
        }
        val queue = PriorityQueue(map.values)

        var idx = k
        while(idx > 0 && queue.isNotEmpty()) {
            if (queue.peek() <= idx) {
                idx -= queue.remove()
            } else {
                break
            }
        }
        return queue.size
    }
}

fun main() {
    println(LeastNumberOfUniqueIntegersAfterKRemovals().findLeastNumOfUniqueInts(intArrayOf(4,3,1,1,3,3,2), 3))
}