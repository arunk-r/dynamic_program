package com.dynamic.program.dp.knapsack


/**
 * Partition Array Into Two Arrays to Minimize Sum Difference
 *
 * You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to
 * minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.
 * Return the minimum possible absolute difference.
 *
 *
 * Example 1:
 * example-1
 * Input: nums = [3,9,7,3]
 * Output: 2
 * Explanation: One optimal partition is: [3,9] and [7,3].
 * The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
 *
 * Example 2:
 * Input: nums = [-36,36]
 * Output: 72
 * Explanation: One optimal partition is: [-36] and [36].
 * The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
 *
 * Example 3:
 * example-3
 * Input: nums = [2,-1,0,4,-2,-9]
 * Output: 0
 * Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
 * The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 *
 *
 * Constraints:
 * 1 <= n <= 15
 * nums.length == 2 * n
 * -10^7 <= nums[i] <= 10^7
 */
class PartitionArrayIntoTwoArraysToMinimizeSumDifference {
    fun minimumDifference1(nums: IntArray): Int {
        val sum = nums.sum()
        val dp = Array(nums.size + 1) { BooleanArray(sum + 1) }
        for (i in 0..nums.size) {
            for (j in 0..sum) {
                dp[i][j] = if (i == 0 && j == 0) true
                else if (i == 0) false
                else if (j == 0) true
                else if (nums[i - 1] <= j) {
                    dp[i - 1][j - nums[i - 1]] || dp[i - 1][j]
                } else {
                    dp[i - 1][j]
                }
            }
        }
        var min = Int.MAX_VALUE
        for (j in 0..sum / 2) {
            if (dp[nums.size][j]) {
                println("$j -> $sum - 2 * $j - ${sum - 2 * j}")
                min = minOf(min, sum - 2 * j)
            }
        }
        return min
    }


    fun minimumDifference3(nums: IntArray): Int {
        val sum = nums.sum()
        val list: MutableList<Int> = ArrayList()
        f(nums, 0, 0, 0, list)
        var ans = Int.MAX_VALUE
        for (i in 0..list.size / 2) {
            ans = Math.min(ans, Math.abs(sum - list[i] - list[i]))
        }
        return ans
    }

    fun f(nums: IntArray, i: Int, sum: Int, l: Int, list: MutableList<Int>) {
        if (l == nums.size / 2) {
            list.add(sum)
            return
        }
        if (i == nums.size) return
        f(nums, i + 1, sum + nums[i], l + 1, list)
        f(nums, i + 1, sum, l, list)
    }

    val set = hashSetOf<String>()
    fun minimumDifference(nums: IntArray): Int {
        val sum = nums.sum()
        val lst = mutableListOf<Int>()
        if (nums.size == 1) return 0
        if (nums.size == 2) {
            if (Math.abs(nums[0]) == Math.abs(nums[1]))
                return Math.abs(nums[0] - nums[1])
            else return 0
        }
        val subListCnt = nums.size / 2
        dp(lst, 0, subListCnt, nums.size, nums, mutableListOf())
        var min = Int.MAX_VALUE
        for (j in 0 until lst.size/2) {
            min = minOf(min, Math.abs(sum - lst[j] - lst[j]))
        }
        return min
    }

    fun dp(lst: MutableList<Int>, sum: Int, cnt: Int, i: Int, nums: IntArray, v: MutableList<Int>) {
        if (i == 0) {
            if (cnt == 0) {
                v.sort()
                if (!set.contains(v.toString())) {
                    set.add(v.toString())
                    lst.add(sum)
                }
            }
        } else {
            val d = v.toMutableList()
            d.add(nums[i - 1])
            dp(lst, sum + nums[i - 1], cnt - 1, i - 1, nums, d)
            dp(lst, sum, cnt, i - 1, nums, v)
        }
    }
}

fun main() {
    println(PartitionArrayIntoTwoArraysToMinimizeSumDifference().minimumDifference(intArrayOf(3, 9, 7, 3)))
    println(PartitionArrayIntoTwoArraysToMinimizeSumDifference().minimumDifference(intArrayOf(-36, 36)))
    println(PartitionArrayIntoTwoArraysToMinimizeSumDifference().minimumDifference(intArrayOf(2, -1, 0, 4, -2, -9)))
    println(
        PartitionArrayIntoTwoArraysToMinimizeSumDifference().minimumDifference(
            intArrayOf(
                276,
                8,
                45,
                20,
                74,
                84,
                28,
                1
            )
        )
    )
}