package com.dynamic.program.stacks_queues

import java.util.*

/**
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ,
 * subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5]
 * because the first position they differ is at the final number, and 4 is less than 5.
 *
 * Example 1:
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 *
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 *
 * Example 2:
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */

fun mostCompetitive(nums: IntArray, k: Int): IntArray {
    val s = Stack<Int>()
    var smallestIdx = 0
    for (i in 0 until nums.size - k + 1) {
        if (nums[smallestIdx] > nums[i]) {
            smallestIdx = i
        }
    }
    for (i in smallestIdx until nums.size) {
        while (s.isNotEmpty() && (nums.size - i) > (k - s.size)  && s.peek() > nums[i]) {
            s.pop()
        }
        if (s.size < k) {
            s.push(nums[i])
        }
    }
    val l = MutableList(k) { 0 }
    var idx = k - 1
    while (s.isNotEmpty()) {
        l[idx--] = s.pop()
    }
    return l.toIntArray()
}

fun main() {
    println(mostCompetitive(intArrayOf(84, 10, 71, 23, 66, 61, 62, 64, 34, 41, 80, 25, 91, 43, 4, 75, 65, 13, 37, 41, 46, 90, 55, 8, 85, 61, 95, 71), 24).toList())
    println(mostCompetitive(intArrayOf(71,18,52,29,55,73,24,42,66,8,80,2), 3).toList())
    println(mostCompetitive(intArrayOf(2,4,3,3,5,4,9,6), 4).toList())
}
