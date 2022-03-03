package com.dynamic.program.hard.subarray_sort

import kotlin.math.max
import kotlin.math.min

/**
 * Write a function that takes in an array of at least two integers and that
 * returns an array of the starting and ending indices of the smallest subarray
 * in the input array that needs to be sorted in place in order for the entire
 * input array to be sorted (in ascending order).
 */
fun subarraySort(array: List<Int>): List<Int> {
    // Write your code here.
    var minOutOfOrder = Int.MAX_VALUE
    var maxOutOfOrder = Int.MIN_VALUE
    for (i in array.indices) {
        val num = array[i]
        if (isCurrentNumberOutOfOrder(i, num, array)) {
            minOutOfOrder = min(minOutOfOrder, num)
            maxOutOfOrder = max(maxOutOfOrder, num)
        }
    }

    if (minOutOfOrder == Int.MAX_VALUE) {
        return listOf(-1, -1)
    }

    var subArrayLeftIdx = 0
    while (minOutOfOrder >= array[subArrayLeftIdx]) subArrayLeftIdx++
    var subArrayRightIdx = array.size -1
    while (maxOutOfOrder <= array[subArrayRightIdx]) subArrayRightIdx--

    return listOf(subArrayLeftIdx, subArrayRightIdx)
}

fun isCurrentNumberOutOfOrder(i: Int, number: Int, array: List<Int>): Boolean {
    if (i == 0) {
        return number > array[i+1]
    }

    if (i == array.size -1) {
        return number < array[i -1]
    }

    return number > array[i+1] || number < array[i -1]
}

fun main() {
    println(subarraySort(listOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19)))
}