package com.dynamic.program.dp.knapsack

class CountOfSubArraySum {
    fun subarraySum(nums: IntArray, k: Int): Int {
        val mem = Array(nums.size+1){IntArray(k+1){-1}}
        return dp(k, nums.size, nums, mem)
    }

    fun dp(sum: Int, i: Int, nums:IntArray, mem: Array<IntArray>): Int {
        if(sum == 0) {
            return 1
        } else if (sum < 0 || i == 0) {
            return 0
        }
        if (mem[i][sum] != -1) {
            return mem[i][sum]
        }
        val v = if (nums[i-1] <= sum) {
            dp(sum - nums[i-1], i-1, nums, mem) + dp(sum, i-1, nums, mem)
        } else {
            dp(sum, i-1, nums, mem)
        }
        mem[i][sum] = v
        return v
    }
}

fun main() {
    println(CountOfSubArraySum().subarraySum(intArrayOf(2,3,5,6,8,10), 10))
}