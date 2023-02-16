package com.dynamic.program.dp

class SubSetSum {
    val t = Array(100) { IntArray(100) { -1 } }
    fun subsetSum(nums: IntArray, k: Int): Boolean {
        val dp = Array(nums.size+1){BooleanArray(k+1)}
        for (i in 0 .. nums.size) {
            for (j in 0 .. k) {
                if (j == 0) {
                    dp[i][j] = true
                } else if (i == 0) {
                    dp[i][j] = false
                } else if (nums[i-1] <= j) {
                    dp[i][j] = dp[i][j - nums[i-1]] || dp[i-1][j]
                } else {
                    dp[i][j] = dp[i-1][j]
                }
            }
        }
        return dp[nums.size][k]
    }

    fun subsetSum1(nums: IntArray, k: Int): Boolean {
        val d = dp(k, nums.size, nums)
        return d
    }

    private fun dp(k: Int, i: Int, nums: IntArray): Boolean {
        if (k == 0) return true
        else if (k < 0 || i == 0) return false
        if (t[i][k] != -1) {
            return t[i][k] == 1
        }
        val d = if (nums[i - 1] <= k && dp(k - nums[i - 1], i - 1, nums)) {
            true
        } else {
            dp(k, i - 1, nums)
        }
        t[i][k] = if (d) 1 else 0
        return d
    }
}

fun main() {
    println(SubSetSum().subsetSum(intArrayOf(2, 3, 7, 8, 10), 11))
    println(SubSetSum().subsetSum(intArrayOf(1,2,5), 4))
}