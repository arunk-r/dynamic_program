package com.dynamic.program.greedy


/**
 * Subarray Sums Divisible by K
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 *
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 *
 *
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * 2 <= k <= 10^4
 */
class SubarraySumsDivisibleByK {
    fun subarraysDivByK(nums: IntArray, k: Int): Int {
        val ps = hashMapOf<Int, Int>()
        var sum = 0
        for(i in nums.indices) {
            sum += nums[i]

            var mod = sum % k
            if (mod < 0) {
                mod += k
            }
            ps.putIfAbsent(mod, 0)
            ps[mod] = ps[mod]!! + 1
        }

        var ans = 0
        ps.forEach { (k, v) ->
            ans += (v * (v - 1)) / 2
            if (k == 0) {
                ans += v
            }
        }

        return ans
    }

    fun subarraysDivByK_1(nums: IntArray, k: Int): Int {
        var cnt = 0
        val lst = nums.toMutableList()
        for (i in nums.indices) {
            var right = nums.size
            while (right > i) {
                val subLst = lst.subList(i, right)
                if (Math.abs(subLst.sum()) % k == 0) {
                    cnt++
                }
                right--
            }
        }
        return cnt
    }

    fun subarraysDivByK2(nums: IntArray, k: Int): Int {
// Use the HashMap to record the frequency of all the prefix sum remainders.
        // Use the HashMap to record the frequency of all the prefix sum remainders.
        val map = HashMap<Int, Int>()
        var remainder = 0
        nums.forEach { num ->

            // Note that the integer in 'nums' can be negative.
            // Thus, we need to adjust the negative remainder to positive remainder.
            // Below accounts for both negative and positive remainders.
            // We can also check if the remainder is negative, then add a 'k' to make the remainder positive.
            // For Example, nums = [-2,3,2], k = 5,
            // remainder for the prefix sum of [-2,1,3] are -2, 1 and 3 respectively.
            // We know that [3,2] sum to 5, which is divisible by 5.
            // After converting -2 to 3, by adding 5, it has the same remainder with prefix sum 3.
            remainder = ((remainder + num) % k + k) % k
            map[remainder] = map.getOrDefault(remainder, 0) + 1
        }
        // The result contains all the prefix sum with remainder 0,
        // as all the prefix sum with remainder of 0 is itself divisible by 'k'.
        // However, do note that the prefix sum with remainder 0 also able to form subarray sums that is divisible by 'k'
        // with one another, which will be calculated next.
        // For Example: nums = [5,5,5,5], k = 5,
        // The prefix sum of [5,10,15,20] are themselves divisible by 5, while also forming subarray sums divisible by 5
        // with 10 [5,5] - 5 [5] == 5, 15 [5,5,5] - 5 [5] == 10, etc.
        // The result contains all the prefix sum with remainder 0,
        // as all the prefix sum with remainder of 0 is itself divisible by 'k'.
        // However, do note that the prefix sum with remainder 0 also able to form subarray sums that is divisible by 'k'
        // with one another, which will be calculated next.
        // For Example: nums = [5,5,5,5], k = 5,
        // The prefix sum of [5,10,15,20] are themselves divisible by 5, while also forming subarray sums divisible by 5
        // with 10 [5,5] - 5 [5] == 5, 15 [5,5,5] - 5 [5] == 10, etc.
        var result = map[0] ?: 0

        // The prefix sums with the same remainder can form subarray sums that is divisible by 'k' with each other.
        // For each remainder, the number of subarray that is divisible by 'k' is the number of combinations from the frequency.
        // Equation for the number of combinations of n items is n * "(n - 1) / 2".

        // The prefix sums with the same remainder can form subarray sums that is divisible by 'k' with each other.
        // For each remainder, the number of subarray that is divisible by 'k' is the number of combinations from the frequency.
        // Equation for the number of combinations of n items is n * "(n - 1) / 2".
        for (frequency in map.values)
            result += frequency * (frequency - 1) / 2

        return result
    }
}

fun main() {
    println(SubarraySumsDivisibleByK().subarraysDivByK(intArrayOf(4,5,0,-2,-3,1), 5))
}
