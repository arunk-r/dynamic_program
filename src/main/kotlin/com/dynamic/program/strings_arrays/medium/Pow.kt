package com.dynamic.program.strings_arrays.medium

/**
 * 50. Pow(x, n)
Medium
Facebook
Bloomberg
Microsoft
Implement pow(x, n), which calculates x raised to the power n (i.e., xn).



Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25


Constraints:

-100.0 < x < 100.0
-231 <= n <= 231-1
n is an integer.
-104 <= xn <= 104
 */
class Pow {
    fun myPow(x: Double, n: Int): Double {
        var X = x
        var N = n.toLong()
        if (N < 0) {
            X = 1 / x
            N = -N
        }
        var ans = 1.0
        var current_product = X
        var i = N
        while (i > 0) {
            if (i % 2 == 1L) {
                ans *= current_product
            }
            current_product *= current_product
            i /= 2
        }
        return ans
    }
}

fun main() {
    println(Pow().myPow(0.00001, 2147483647))
}
