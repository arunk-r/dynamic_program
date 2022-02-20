package com.dynamic.program.maximize_expression

import kotlin.math.max

/**
 * Write a function that takes in an array of integers and returns the largest
 * possible value for the expression
 * array[a] - array[b] + array[c] - array[d], where a,
 * b, c, and d are indices of the array
 * and a < b < c < d.
 *
 *
 * If the input array has fewer than 4 elements, your function should return 0.
 *
 * Sample Input
 * array = [3, 6, 1, -3, 2, 7]
 *
 * Sample Output
 * 4
 * // Choose a = 1, b = 3, c = 4, and d = 5
 * // -> 6 - (-3) + 2 - 7 = 4
 *
 *
 */

fun maximizeExpression(array: List<Int>): Int {
    if (array.size < 4) return 0
    // Write your code here.
    val aList = mutableListOf<Int>()
    val abList = mutableListOf<Int>()
    val abcList = mutableListOf<Int>()
    val abcdList = mutableListOf<Int>()

    aList.add(array[0])
    for (idx in 1 until array.size) {
        aList.add(max(aList[idx - 1], array[idx]))
    }

    abList.add(Int.MIN_VALUE)
    for (idx in 1 until array.size) {
        abList.add(max(abList[idx - 1], (aList[idx -1] - array[idx])))
    }

    abcList.add(Int.MIN_VALUE)
    abcList.add(Int.MIN_VALUE)
    for (idx in 2 until array.size) {
        abcList.add(max(abcList[idx - 1], (abList[idx -1] + array[idx])))
    }

    abcdList.add(Int.MIN_VALUE)
    abcdList.add(Int.MIN_VALUE)
    abcdList.add(Int.MIN_VALUE)
    for (idx in 3 until array.size) {
        abcdList.add(max(abcdList[idx - 1], (abcList[idx -1] - array[idx])))
    }

    return abcdList[abcdList.size -1]
}

fun main() {
    println(maximizeExpression(listOf(3, 6, 1, -3, 2, 7)))
}