package com.dynamic.program.strings_arrays

/**
 * Example 3: 713. Subarray Product Less Than K.
 *
 * Given an array of positive integers nums and an integer k, return the number of contiguous subarrays
 * where the product of all the elements in the subarray is strictly less than k.
 *
 * For example, given the input nums = [10, 5, 2, 6], k = 100, the answer is 8. The subarrays with products less than k are:
 *
 * Key idea: Whenever you see a problem asking for the number of subarrays, think of this: at each index,
 * how many valid subarrays end at this index? Let's split the 8 subarrays by their ending indices:
 *
 * [10]
 * [5], [10, 5]
 * [2], [5, 2] here [10, 5, 2] == 100 violates problem statement
 * [6], [5, 2, 6], [2, 6]
 *
 * total = 8 subarrays
 */

fun subarrayProduct(a: IntArray, k: Int): Int {
    if (k < 1) return 0
    var left = 0
    var ans = 0
    var cur = 1

    for (right in a.indices) {
        cur *= a[right]

        while (cur >= k) {
            cur /= a[left]
            left ++
        }
        println((right - left) + 1)
        ans += (right - left) + 1
    }
    return ans
}

fun main() {
    println(subarrayProduct(intArrayOf(10, 5, 2, 6), 100))
}
