package com.dynamic.program.facebook.easy

import java.time.Instant
import kotlin.math.sqrt

/**
 * Given a number N, find all factors of N.

Example:

N = 6
factors = {1, 2, 3, 6}
Make sure the returned array is sorted.

Problem Approach:

VIDEO : https://www.youtube.com/watch?v=dolcMgiJ7I0

Complete code in the hint.
 */

fun allFactorsBruteForce(input: Int): List<Int> {
    val output = mutableListOf<Int>()
    for (i in 1 .. input) {
        if (input%i == 0) {
            output.add(i)
        }
    }
    return output
}

/**
 * Co-factors
 * a,b = n/a
 * a*b = n
 * where n=36
 * {1,2,3,6,9,12,18,36}
 *  a=1, b=36 = 1*36 = 36
 *  a=2, b=18 = 36
 *
 *  so I can divide n/2
 */
fun allFactorsBestCase(input: Int): List<Int> {
    val output = mutableListOf<Int>()
    output.add(1)
    for (i in 2 .. input/2) {
        if (input%i == 0) {
            output.add(i)
        }
    }
    output.add(input)
    return output
}

/**
 * math factor
 * if a<b then a < sqrt(n), b > sqrt(n)
 * Assume n =36
 * 1 * 36 -> we have 2 factors 1, 36
 * 2 * 18 -> we have 2 factors 2, 18
 * 3 * 12 -> we have 2 factors 3, 9
 * 4 * 9  -> we have 2 factors 4, 9
 * if a = b = sqrt(n)
 * 6 * 6 -> here we consider only 1 factor
 */
fun allFactorsBestCase2(input: Int): List<Int> {
    val output = mutableListOf<Int>()
    for (i in 1 ..sqrt(input.toDouble()).toInt()) {
        if (input%i == 0) {
            output.add(i) // add factor 1
            if (i != sqrt(input.toDouble()).toInt()) { // skip if a==b
                output.add(input/i) // add factor 2
            }
        }
    }
    return output
}

fun main() {
    println(Instant.now())
    println(allFactorsBruteForce(1000000001))
    println(Instant.now())
    println(allFactorsBestCase(1000000001))
    println(Instant.now())
    println(allFactorsBestCase2(1000000001))
    println(Instant.now())
}
