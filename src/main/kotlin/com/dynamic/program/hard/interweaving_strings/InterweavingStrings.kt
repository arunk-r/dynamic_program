package com.dynamic.program.hard.interweaving_strings

/**
 * Write a function that takes in three strings and returns a boolean
 * representing whether the third string can be formed by interweaving the first
 * two strings.
 *
 * To interweave strings means to merge them by alternating their letters without
 * any specific pattern. For instance, the strings "abc" and "123" can be interwoven as "a1b2c3", as
 * "abc123", and as "ab1c23" (this list is nonexhaustive).
 *
 * Letters within a string must maintain their relative ordering in the interwoven string.
 *
 * Sample Input
 * one = "algoexpert"
 * two = "your-dream-job"
 * three = "your-algodream-expertjob"
 *
 * Sample Output
 * true
 */

fun interweavingStrings(one: String, two: String, three: String): Boolean {
    // Write your code here.
    val len = one.length + two.length
    if (len < three.length || three.length > len) return false
    return areInterweaving(one, two, three, 0, 0)
}

fun areInterweaving(one: String, two: String, three: String, i: Int, j: Int): Boolean {
    val k = i+j
    if (k == three.length) return true

    if (i < one.length && one[i] == three[k])
        if (areInterweaving(one, two, three, i+1, j)) return true
    if (j < two.length && two[j] == three[k])
        return areInterweaving(one, two, three, i, j+1)
    return false
}

fun main() {
    println(interweavingStrings("algoexpert", "your-dream-job", "your-algodream-expertjob"))
}