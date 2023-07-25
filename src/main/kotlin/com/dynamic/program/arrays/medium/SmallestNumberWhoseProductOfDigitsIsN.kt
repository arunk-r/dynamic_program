package com.dynamic.program.arrays.medium

/**
 * Smallest number whose product of digits is N.
 * Given a positive number N, you need to find the smallest number S such that product of digits of S is equal to N.
 *
 * Sample input
 * N = 1000
 * Output
 * 5558 => 5*5*5*8 = 1000
 */
class SmallestNumberWhoseProductOfDigitsIsN {
    fun productDigits(n: Int): String {
        val digits = IntArray(10)
        var number = n
        for (k in 9 downTo 2) {
            while (number % k == 0) {
                number /= k
                digits[k]++
            }
        }
        if (number > 1) return "-1"
        val buf = StringBuffer()
        for (i in 2 until 10) {
            for (k in 0 until digits[i]) {
                buf.append(i)
            }
        }
        if (buf.isEmpty()) return "-1"
        return buf.toString()
    }
}

fun main() {
    println(SmallestNumberWhoseProductOfDigitsIsN().productDigits(1000))
    println(SmallestNumberWhoseProductOfDigitsIsN().productDigits(13))
    println(SmallestNumberWhoseProductOfDigitsIsN().productDigits(77))
    println(SmallestNumberWhoseProductOfDigitsIsN().productDigits(81000))
}
