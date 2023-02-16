package com.dynamic.program.dp

import com.dynamic.program.google.permutations

/**
 * Largest Divisible Subset
 * Given a set of distinct positive integers nums, return the largest subset answer such that every
 * pair (answer[i], answer[j]) of elements in this subset satisfies:
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 *
 *
 * Example 2:
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 *
 *
 * Constraints:
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * All the integers in nums are unique.
 */
class LargestDivisibleSubset {
    fun largestDivisibleSubset(nums: IntArray): List<Int> {
        var totalPairs = nums.size/2
        val pairs = mutableListOf<Int>()
        val seen = BooleanArray(nums.size)
        if (nums.size == 1) return nums.toList()
        for (i in nums.indices) {
            for (j in i until nums.size) {
                if (!seen[i] && !seen[j] && (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0)) {
                    seen[i] = true
                    seen[j] = true
                    pairs.add(nums[i])
                    pairs.add(nums[j])
                    totalPairs--
                    break
                }
            }
            if (totalPairs == 0) {
                break
            }
        }
        return pairs
    }
}

//TODO - need to work
fun main() {
    println(LargestDivisibleSubset().largestDivisibleSubset(intArrayOf(1,2,3)))
    println(LargestDivisibleSubset().largestDivisibleSubset(intArrayOf(1,2,4,8)))
}