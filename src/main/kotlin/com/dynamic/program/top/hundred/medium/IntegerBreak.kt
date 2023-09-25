package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/integer-break/description/
 * 343. Integer Break
 * Medium
 * 3.9K
 * 371
 * company
 * Facebook
 * company
 * Apple
 * company
 * Google
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 *
 * Return the maximum product you can get.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 *
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 58
 *
 */
class IntegerBreak {
    fun integerBreak(n: Int): Int {
        if (n <= 3) return n -1
        var ans = 1
        var newN = n
        while(newN > 4) {
            ans *= 3
            newN -= 3
        }

        return ans * newN
    }
}
