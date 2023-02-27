package com.dynamic.program.dp.mcm

/**
 * 887. Super Egg Drop
Hard
You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.

You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break, and any egg dropped at or below floor f will not break.

Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it. However, if the egg does not break, you may reuse it in future moves.

Return the minimum number of moves that you need to determine with certainty what the value of f is.



Example 1:

Input: k = 1, n = 2
Output: 2
Explanation:
Drop the egg from floor 1. If it breaks, we know that f = 0.
Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
If it does not break, then we know f = 2.
Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
Example 2:

Input: k = 2, n = 6
Output: 3
Example 3:

Input: k = 3, n = 14
Output: 4


Constraints:

1 <= k <= 100
1 <= n <= 104
 */
class SuperEggDrop {
    fun superEggDrop(k: Int, n: Int): Int {
        val dp = Array(n+1) { IntArray(k+1) }
        var m = 0
        while (dp[m][k] < n) {
            m++
            for (i in 1 .. k) {
                dp[m][i] = dp[m-1][i-1] + dp[m-1][i] + 1
            }
        }
        return m
    }
    val dp = Array(101) { IntArray(105) { -1 } }
    fun superEggDrop1(k: Int, n: Int): Int {
        if (n in 0..1) return n
        else if (k == 1) return n
        else if (dp[k][n] != -1) return dp[k][n]

        var min = Int.MAX_VALUE
        for (i in 1..n) {
            val eggBreakFloor = if (dp[k - 1][i - 1] != -1) dp[k - 1][i - 1] else superEggDrop(k - 1, i - 1)
            dp[k - 1][i - 1] = eggBreakFloor
            val nextFloor = if (dp[k][n - i] != -1) dp[k][n - i] else superEggDrop(k, n - i)
            dp[k][n - i] = nextFloor
            val temp = 1 + maxOf(eggBreakFloor, nextFloor)
            min = minOf(temp, min)
        }
        dp[k][n] = min
        return min
    }
}

fun main() {
    //println(SuperEggDrop().superEggDrop(1,2))
    println(SuperEggDrop().superEggDrop(2, 6))
    //println(SuperEggDrop().superEggDrop(3,14))
}
