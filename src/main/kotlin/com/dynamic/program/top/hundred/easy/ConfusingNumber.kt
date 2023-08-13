package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/confusing-number/
 * 1056. Confusing Number
 * Easy
 * 288
 * 161
 * company
 * Google
 * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 * We can rotate digits of a number by 180 degrees to form new digits.
 *
 * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
 * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
 * Note that after rotating a number, we can ignore leading zeros.
 *
 * For example, after rotating 8000, we have 0008 which is considered as just 8.
 * Given an integer n, return true if it is a confusing number, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6
 * Output: true
 * Explanation: We get 9 after rotating 6, 9 is a valid number, and 9 != 6.
 * Example 2:
 *
 *
 * Input: n = 89
 * Output: true
 * Explanation: We get 68 after rotating 89, 68 is a valid number and 68 != 89.
 * Example 3:
 *
 *
 * Input: n = 11
 * Output: false
 * Explanation: We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number
 *
 *
 * Constraints:
 *
 * 0 <= n <= 109
 */
class ConfusingNumber {
    val map = hashMapOf<Int, Int>()
    init {
        map[0] = 0
        map[1] = 1
        map[6] = 9
        map[8] = 8
        map[9] = 6
    }
    fun confusingNumber(n: Int): Boolean {
        var newN = n
        var rotate = 0
        while (newN > 0) {
            val rem = getRotationNumber(newN % 10)
            if (rem == -1) {
                return false
            }
            rotate = rotate * 10 + rem
            newN /= 10
        }
        return n != rotate
    }

    private fun getRotationNumber(num: Int): Int {
        return when(num) {
            0 -> 0
            1 -> 1
            6 -> 9
            8 -> 8
            9 -> 6
            else -> -1
        }
    }
}
