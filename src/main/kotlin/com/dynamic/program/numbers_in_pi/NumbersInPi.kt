package com.dynamic.program.numbers_in_pi

import kotlin.math.min

/**
 *
 * Given a string representation of the first n digits of Pi and a list of
 * positive integers (all in string format), write a function that returns the
 * smallest number of spaces that can be added to the n digits of Pi such that
 * all resulting numbers are found in the list of integers.
 *
 * Note that a single number can appear multiple times in the resulting numbers.
 * For example, if Pi is "3141" and the numbers are
 * ["1", "3", "4"], the number "1" is allowed to appear
 * twice in the list of resulting numbers after three spaces are added:
 * "3 | 1 | 4 | 1".
 *
 * If no number of spaces to be added exists such that all resulting numbers are
 * found in the list of integers, the function should return
 * -1.
 *
 * Sample Input
 * pi = "3141592653589793238462643383279",
 * numbers = ["314159265358979323846", "26433", "8", "3279", "314159265", "35897932384626433832", "79"]
 *
 * Sample Output
 * 2 // "314159265 | 35897932384626433832 | 79"
 *
 */

fun numbersInPi(pi: String, numbers: List<String>): Int {
    // Write your code here.
    if (pi.isEmpty() || numbers.isEmpty()) return -1
    val memo = MutableList(pi.length) {MutableList(pi.length) {Int.MAX_VALUE} }
    for (i in pi.indices) {
        if (numbers.contains(pi[i].toString())) {
            memo[i][i] = 0
        }
    }
    println(memo)
    for (len in 1 until pi.length) {
        for (i in 0 until (pi.length - len)) {
            val subStr = pi.substring(i, i+len+1)
            println(subStr)
            if (numbers.contains(subStr)) {
                memo[i][i+len] = 0
            } else {
                for(k in i until i+len) {
                    if(memo[i][k] != Int.MAX_VALUE && memo[k+1][i+len] != Int.MAX_VALUE)
                        memo[i][len] = min(memo[i][len],1+ memo[i][k] + memo[k+1][len])
                }
            }
            //println(memo)
        }
    }
    println(memo)
    return if (memo[0][pi.length-1] == Int.MAX_VALUE) -1 else memo[0][pi.length-1]
}

fun main() {
    println(numbersInPi("3141", listOf("3", "1", "4", "14", "141")))
}