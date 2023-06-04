package com.dynamic.program.graphs.bfs.medium

/**
 * 365. Water and Jug Problem
 * Medium
 *
 * company
 * Lyft
 * company
 * Google
 * company
 * Microsoft
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
 *
 *
 * Example 1:
 *
 * Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * Output: true
 * Explanation: The famous Die Hard example
 * Example 2:
 *
 * Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
 * Output: false
 * Example 3:
 *
 * Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106
 */

// refer this web page for reference - https://www.math.tamu.edu/~dallen/hollywood/diehard/diehard.htm
class WaterAndJugProblem {
    fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {
        if (targetCapacity == 0) return true
        if (targetCapacity > jug1Capacity + jug2Capacity) return false
        val fillCapacity = listOf(jug1Capacity, -jug1Capacity, jug2Capacity, -jug2Capacity)
        val q = ArrayDeque<Int>()
        val seen = hashSetOf<Int>()
        q.add(0)
        seen.add(0)

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            fillCapacity.forEach{ water ->
                val total = cur + water
                if(total == targetCapacity) return true
                if (total > 0 && total <= jug1Capacity + jug1Capacity && !seen.contains(total)) {
                    q.addLast(total)
                    seen.add(total)
                }
            }
        }

        return false
    }
}
