package com.dynamic.program.dp

/**
 * 2218. Maximum Value of K Coins From Piles
Hard
 * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.

In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.

Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.



Example 1:


Input: piles = [[1,100,3],[7,8,9]], k = 2
Output: 101
Explanation:
The above diagram shows the different ways we can choose k coins.
The maximum total we can obtain is 101.
Example 2:

Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
Output: 706
Explanation:
The maximum total can be obtained if we choose all coins from the last pile.


Constraints:

n == piles.length
1 <= n <= 1000
1 <= piles[i][j] <= 105
1 <= k <= sum(piles[i].length) <= 2000
 */
class MaximumValueOfKCoinsFromPiles {
    fun maxValueOfCoins1(piles: List<List<Int>>, k: Int): Int {
        val n = piles.size
        val dp = Array(n+1){IntArray(k+1){0} }
        for (i in n-1 downTo 0) {
            for (remain in i .. k) {
                dp[i][remain] = dp[i+1][remain]
                var cur = 0
                for (j in 0 until Math.min(remain, piles[i].size)) {
                    cur += piles[i][j]
                    println("dp[$i][$remain], $cur + dp[$i+1][$remain - $j - 1], ${dp[i][remain]}, $cur + dp[${i+1}][${remain - j - 1}], ${dp[i][remain]}, $cur + ${dp[i+1][remain - j - 1]}")
                    dp[i][remain] = Math.max(dp[i][remain], cur + dp[i+1][remain - j - 1])
                }
            }
        }
        return dp[0][k]
    }
    fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        val dp = Array(piles.size+1){IntArray(k+1){-1} }
        return dp(0,k, piles, dp)
    }

    fun dp(i: Int, remain: Int, piles: List<List<Int>>, memo: Array<IntArray>): Int {
        if (i == piles.size || remain == 0) {
            return 0
        }
        if (memo[i][remain] !== -1) {
            return memo[i][remain]
        }
        var ans = dp(i + 1, remain, piles, memo)
        var curr = 0
        for (j in 0 until Math.min(remain, piles[i].size)) {
            curr += piles[i][j]
            ans = Math.max(ans, curr + dp(i + 1, remain - j - 1, piles, memo))
        }
        memo[i][remain] = ans
        return ans
    }
}

fun main(){
    println(MaximumValueOfKCoinsFromPiles().maxValueOfCoins(listOf(listOf(1,100,3), listOf(7,8,9)), 2))
}