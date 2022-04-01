package com.dynamic.program.facebook.easy

import kotlin.math.round
import kotlin.math.sqrt

/**
 * Given a number N, verify if N is prime or not.

Return 1 if N is prime, else return 0.

Example :

Input : 7
Output : True
Problem Approach:

VIDEO : https://www.youtube.com/watch?v=7VPA-HjjUmU

Complete code in the hint.
 */

fun verifyPrimeNumberBruteForce(v: Int): Boolean {
    if (v == 2 || v == 1) return true
    if (v%2 == 0) return false
    return checkPrime(v, sqrt(v.toFloat()).toInt())
}

fun checkPrime(v: Int, divisible: Int): Boolean {
    if (divisible == 1) return true
    if (v%divisible == 0) return false
    return checkPrime(v, divisible - 1)
}

fun findAllPrimeNumbers(v: Int): List<Int> {
    val output = mutableListOf<Int>()
    for (i in 1.. v) {
        if (verifyPrimeNumberBruteForce(i)) {
            output.add(i)
        }
    }
    return output
}

fun main() {
    println(sqrt(21.0))
    println(verifyPrimeNumberBruteForce(21))
    println(findAllPrimeNumbers(99))
}