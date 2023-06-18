package com.dynamic.program.bit.easy

import java.math.BigInteger

/**
 * https://leetcode.com/problems/add-binary/description/
 * I did not like bit manipulation problems :-(
 *
 * 67. Add Binary
 * Easy
 * 8.1K
 * 803
 * company
 * Microsoft
 * company
 * Adobe
 * company
 * Bloomberg
 * Given two binary strings a and b, return their sum as a binary string.
 *
 *
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
class AddBinary {
    fun addBinary(a: String?, b: String?): String? {
        var x = BigInteger(a, 2)
        var y = BigInteger(b, 2)
        val zero = BigInteger("0", 2)
        var carry: BigInteger
        var answer: BigInteger
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y)
            carry = x.and(y).shiftLeft(1)
            x = answer
            y = carry
        }
        return x.toString(2)
    }
}
