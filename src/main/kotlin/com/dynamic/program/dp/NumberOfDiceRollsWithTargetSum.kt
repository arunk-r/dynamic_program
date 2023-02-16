package com.dynamic.program.dp

/**
 * Number of Dice Rolls With Target Sum
 *
 * You have n dice, and each die has k faces numbered from 1 to k.
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways)
 * to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 *
 *
 * Example 2:
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 *
 * Example 3:
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 *
 *
 * Constraints:
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
class NumberOfDiceRollsWithTargetSum {
    /*val MOD = 1000000007

    private fun waysToTarget(memo: Array<IntArray>, diceIndex: Int, n: Int, currSum: Int, target: Int, k: Int): Int {
        // All the n dice are traversed, the sum must be equal to target for valid combination
        if (diceIndex == n) {
            return if (currSum == target) 1 else 0
        }

        // We have already calculated the answer so no need to go into recursion
        if (memo[diceIndex][currSum] != -1) {
            return memo[diceIndex][currSum]
        }
        var ways = 0
        // Iterate over the possible face value for current dice
        for (i in 1..minOf(k, target - currSum)) {
            ways = (ways + waysToTarget(memo, diceIndex + 1, n, currSum + i, target, k)) % MOD
        }
        return ways.also { memo[diceIndex][currSum] = it }
    }

    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        val memo = Array(n + 1) { IntArray(target + 1) { -1 } }
        return waysToTarget(memo, 0, n, 0, target, k)
    }*/
    val mod = 1000000007
    fun numRollsToTarget(n: Int, k: Int, target: Int): Int {
        val mem = Array(n+1){IntArray(k+1) {-1}}
        return dp(0, n, 0, k, target, mem)
    }

    private fun dp(dice: Int, n: Int, sum: Int, k: Int, target: Int, mem: Array<IntArray>): Int {
        if (dice == n) {
            return if (sum == target) 1 else 0
        }

        if(mem[dice][sum] != -1) return mem[dice][sum]

        var ways = 0
        for(i in 1 .. minOf(k, target - sum)) {
            ways = (ways + dp(dice+1, n, sum+i, k, target, mem)) % mod
        }
        mem[dice][sum] = ways
        return ways
    }
}

fun main() {
    println(NumberOfDiceRollsWithTargetSum().numRollsToTarget(30, 30, 500))
}