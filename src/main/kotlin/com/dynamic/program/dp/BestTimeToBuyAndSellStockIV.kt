package com.dynamic.program.dp

/**
 * 188. Best Time to Buy and Sell Stock IV
Hard
You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
 */
class BestTimeToBuyAndSellStockIV {
    fun maxProfit(k: Int, prices: IntArray): Int {
        val dp = IntArray(k * 2) { 0 }
        for (i in 0 until k * 2) {
            if (i % 2 == 0) dp[i] = Int.MIN_VALUE
        }
        for (i in prices.indices) {
            for (j in 0 until k*2) {
                if (j == 0) {
                    dp[j] = Math.max(dp[j], -prices[i])
                } else if (j % 2 == 0) {
                    dp[j] = Math.max(dp[j], dp[j - 1] - prices[i])
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1] + prices[i])
                }
            }
        }
        return dp[k * 2 - 1]
    }
}

fun main() {
    println(BestTimeToBuyAndSellStockIV().maxProfit(2, intArrayOf(3, 2, 6, 5, 0, 3)))
    println(BestTimeToBuyAndSellStockIV().maxProfit(2, intArrayOf(2, 4, 1)))
}