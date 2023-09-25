package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/paint-house/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 256. Paint House
 * Medium
 * 2.2K
 * 128
 * company
 * LinkedIn
 * company
 * Twitter
 * company
 * Amazon
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Return the minimum cost to paint all houses.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 * Example 2:
 *
 * Input: costs = [[7,6,2]]
 * Output: 2
 *
 *
 * Constraints:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 */
class PaintHouse {
    fun minCost(costs: Array<IntArray>): Int {
        for(i in costs.size-2 downTo 0) {
            costs[i][0] += minOf(costs[i+1][1], costs[i+1][2])
            costs[i][1] += minOf(costs[i+1][0], costs[i+1][2])
            costs[i][2] += minOf(costs[i+1][0], costs[i+1][1])
        }
        return minOf(costs[0][0], minOf(costs[0][1], costs[0][2]))
    }
}
