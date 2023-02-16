package com.dynamic.program.dp.knapsack

import java.util.*


/**
 * Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 *
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 10^4
 */
class CoinChange {
    fun coinChange1(coins: IntArray, amount: Int): Int {
        val max = amount + 1
        val dp = IntArray(amount + 1) { max }
        dp[0] = 0
        for (coin in coins){
            for (amt in coin..amount){
                dp[amt] = minOf(dp[amt], dp[amt - coin] +1)
            }
        }
        return if (dp[amount] > amount) -1 else dp[amount]
    }
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = Array(coins.size+1){IntArray(amount+1)}
        for (i in 0 .. coins.size) {
            dp[i][0] = 0
        }
        for (j in 0 .. amount) {
            dp[0][j] = Int.MAX_VALUE
        }
        for (j in 1 .. amount) {
            dp[1][j] = if (j % coins[0] == 0) {
               j / coins[0]
            } else Int.MAX_VALUE
        }
        for (i in 2 .. coins.size) {
            for (j in 0 .. amount) {
                dp[i][j] = if (coins[i-1] <= j) {
                    minOf(dp[i][j- coins[i-1]]+1, dp[i-1][j])
                } else dp[i-1][j]
            }
        }
        return dp[coins.size][amount]
    }
}

fun main() {
    //println(CoinChange().coinChange(intArrayOf(186, 419, 83, 408), 6249))
    //println(CoinChange().coinChange1(intArrayOf(1, 2, 5), 5))
    //println(CoinChange().coinChange(intArrayOf(1, 2, 5), 5))
    println(CoinChange().coinChange(intArrayOf(1), 2))
}