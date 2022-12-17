package com.dynamic.program.hashing

/**
 *  Max Sum of a Pair With Equal Sum of Digits
 *
 * Given an array of integers nums, find the maximum value of nums[i] + nums[j],
 * where nums[i] and nums[j] have the same digit sum (the sum of their individual digits).
 * Return -1 if there is no pair of numbers with the same digit sum.
 *
 * Example1:
 * Input: nums = [18,43,36,13,7]
 * Output: 54
 * Explanation:
 * Sum of digits
 * 18 = 9
 * 43 = 7
 * 36 = 9
 * 13 = 4
 *  7 = 7
 * The pairs (i, j) that satisfy the conditions are:
 * - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
 * - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
 * So the maximum sum that we can obtain is 54.
 *
 * Example 2:
 * Input: nums = [10,12,19,14]
 * Output: -1
 * Explanation: There are no two numbers that satisfy the conditions, so we return -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 *  complexity is O(n)
 */

fun maxSumOfAPairWithEqualSumOfDigits(nums: IntArray): Int {
    var ans = -1
    val pariSum = hashMapOf<Int, Pair<Int?, Int?>>()
    nums.forEach { num ->
        val sumDigit = getSum(num.toString())
        if (pariSum.containsKey(sumDigit) && pariSum[sumDigit]?.second == -1) {
            pariSum[sumDigit] = Pair(pariSum[sumDigit]?.first, num)
            val f = pariSum[sumDigit]?.first ?: -1
            ans = Math.max(ans, f + num)
        } else {
            pariSum[sumDigit] = Pair(num, -1)
        }
    }
    pariSum.values.forEach { p ->

    }
    return ans
}

fun getSum(num: String): Int {
    var sum = 0
    for (c in num) {
        sum += c.digitToInt()
    }
    return sum
}

fun main() {
    println(maxSumOfAPairWithEqualSumOfDigits(intArrayOf(18, 43, 36, 13, 7)))
    println(maxSumOfAPairWithEqualSumOfDigits(intArrayOf(10, 12, 19, 14)))
}
