package com.dynamic.program.bit.medium

/**
 * 29. Divide Two Integers
Medium
company
Facebook
company
Amazon
company
Google
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.



Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.


Constraints:

-231 <= dividend, divisor <= 231 - 1
divisor != 0
 */
class DivideTwoIntegers {
    fun divide(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE || divisor == -1) {
            return Int.MAX_VALUE
        } else if (Math.abs(divisor) == 1) return dividend * divisor
        else if(divisor > dividend) return 0

        var negative = 1
        var div = dividend
        var d = divisor

        if (dividend < 0) {
            div *= -1
            negative *=-1
        }
        if (divisor < 0) {
            d *= -1
            negative *= -1
        }

        var final = 0
        var remainder = dividend

        while (remainder >= d) {
            var num = d
            var res = 1

            while (num.shl(1) <= remainder) {
                if (num.shl(1) < 0) {
                    break
                }
                num = num.shl(1)
                res = res.shl(1)
            }
            remainder -= num
            final += res
        }

        return final * negative
    }
    fun divide1(dividend: Int, divisor: Int): Int {
        if (dividend == Int.MIN_VALUE || divisor == -1) {
            return Int.MAX_VALUE
        }
        var negative = false
        var div = dividend
        var d = divisor

        if (div < 0) {
            div *= -1
            negative = true
        }

        if (d < 0) {
            d *= -1
            negative = true
        }

        var quotient = 0
        while ((div - d) > 0) {
            div = (div - d)
            quotient++
        }

        if (negative) {
            quotient *= -1
        }
        return quotient
    }
}

fun main() {
    println(DivideTwoIntegers().divide(10, 3))
    println(DivideTwoIntegers().divide(7, -3))
}
