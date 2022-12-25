package com.dynamic.program.stacks_queues

import java.util.*

/**
 * Maximum Number of Robots Within Budget
 *
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n.
 * The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts),
 * where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 *
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 *
 * Example 1:
 * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * Output: 3
 *
 * Explanation:
 * It is possible to run all individual and consecutive pairs of robots within budget.
 * To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
 * It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
 *
 * Example 2:
 * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * Output: 0
 * Explanation: No robot can be run that does not exceed the budget, so we return 0.
 *
 * Constraints:
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 10^4
 * 1 <= chargeTimes[i], runningCosts[i] <= 10^5
 * 1 <= budget <= 10^15
 */

fun maximumRobots(chargeTimes: IntArray, runningCosts: IntArray, budget: Long): Int {
    var rSum = 0
    var cMax = Int.MIN_VALUE
    var left = 0
    var ans = 0
    for (right in chargeTimes.indices) {
        cMax = if (left == right) {
            chargeTimes[right]
        } else {
            Math.max(cMax, chargeTimes[right])
        }
        rSum += runningCosts[right]

        while ((cMax + (right - left + 1) * rSum) > budget) {
            rSum -= runningCosts[left]
            left++
            if (left >= chargeTimes.size) {
                return 0
            }
            cMax = Math.max(cMax, chargeTimes[left])
        }
        ans = Math.max(ans, right - left + 1)
    }
    return ans
}

fun maximumRobots_Best(chargeTimes: IntArray, runningCosts: IntArray, budget: Long): Int {
    var ans = 0
    var left = -1
    var rSum: Long = 0
    val pq: PriorityQueue<Pair<Int, Int>> = PriorityQueue { o1, o2 -> o2.first.compareTo(o1.first) }
    for (right in runningCosts.indices) {
        rSum += runningCosts[right]
        pq.offer(Pair(chargeTimes[right], right))
        while (rSum * (right - left) + removeStale(pq, left) > budget) {
            rSum -= runningCosts[++left]
        }
        ans = Math.max(ans, right - left)
    }
    return ans
}

private fun removeStale(pq: PriorityQueue<Pair<Int, Int>>, j: Int): Int {
    while (!pq.isEmpty() && pq.peek().second <= j)
        pq.poll()
    return if (pq.isEmpty()) 0 else pq.peek().first
}

fun main() {
    println(
        maximumRobots_Best(
            intArrayOf(19, 63, 21, 8, 5, 46, 56, 45, 54, 30, 92, 63, 31, 71, 87, 94, 67, 8, 19, 89, 79, 25),
            intArrayOf(91, 92, 39, 89, 62, 81, 33, 99, 28, 99, 86, 19, 5, 6, 19, 94, 65, 86, 17, 10, 8, 42),
            85
        )
    )
}
