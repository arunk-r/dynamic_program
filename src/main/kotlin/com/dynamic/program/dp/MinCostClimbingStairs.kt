package com.dynamic.program.dp

import kotlin.math.cos

/**
 * Min Cost Climbing Stairs
 *
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor.
 *
 *
 * Example 1:
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 *
 *
 * Example 2:
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 */
class MinCostClimbingStairs {
    fun minCostClimbingStairs1(cost: IntArray): Int {
        val mem = IntArray(1001){0}
        for (i in 2 .. cost.size) {
            mem[i] = Math.min(mem[i-1] + cost[i-1], mem[i-2] + cost[i-2])
        }
        return mem[cost.size]
    }
    fun minCostClimbingStairs(cost: IntArray): Int {
        val mem = IntArray(1001){-1}
        return dp(cost.size, cost, mem)
    }

    fun dp(n: Int, cost: IntArray, mem: IntArray): Int {
        if (n <= 1) {
            return 0
        }
        if(mem[n] > -1) {
            return mem[n]!!
        }

        val ans = Math.min(cost[n-1] + dp(n-1, cost, mem), cost[n-2] + dp(n-2, cost, mem))
        mem[n] = ans
        return ans
    }
}