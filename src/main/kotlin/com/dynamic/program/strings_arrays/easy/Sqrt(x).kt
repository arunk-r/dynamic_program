package com.dynamic.program.strings_arrays.easy

/**
 * 69. Sqrt(x)
Easy
Bloomberg
Amazon
Microsoft
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.


Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.


Constraints:

0 <= x <= 231 - 1
 */
class `Sqrt(x)` {
    fun mySqrt(x: Int): Int {
        if (x == 0 || x == 1) return x
        var left = 0
        var right = x
        while(left < right) {
            val mid = left + (right - left) / 2
            if(mid > x / mid) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left - 1
    }

    fun mySqrt1(x: Int): Int {
        var l = 1
        var r = x
        while(l<=r) {
            val mid = (l+r) / 2
            if(x / mid == mid) return mid
            else if( mid > x /mid) r = mid -1
            else l = mid + 1
        }

        return r
    }
}

fun main() {
    println(`Sqrt(x)`().mySqrt(2147395599))
    println(`Sqrt(x)`().mySqrt1(2147395599))
}
