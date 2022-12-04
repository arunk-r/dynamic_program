package com.dynamic.program.strings_arrays

import kotlin.math.max

/**
 *  Given an integer array nums and an integer k, find the sum of the subarray with the largest sum whose length is k.
 */

fun largestSum(a: IntArray, k: Int): Int {
    var cur = 0
    var ans = 0

    for (right in a.indices) {
        cur += if (right < k) {
            a[right]
        } else {
            a[right] - a[right - k]
        }
        ans = max(ans, cur)
    }
    return ans
}

fun main() {
    println(largestSum(intArrayOf(3, -1, 4, 12, -8, 5, 6), 4))
}
