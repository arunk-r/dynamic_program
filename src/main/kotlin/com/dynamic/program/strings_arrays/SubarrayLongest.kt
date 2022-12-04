package com.dynamic.program.strings_arrays

import kotlin.math.max

/**
 * Given an array of positive integers nums and an integer k, find the length of the longest subarray whose sum is less than or equal to k.
 *
 * example where nums = [3, 1, 2, 7, 4, 2, 1, 1, 5] and k = 8.
 *
 * You may be thinking: there is a while loop inside of the for loop, isn't the time complexity O(n^2)?
 * The reason it is still O(n) is that the while loop can only iterate nn times in total for the entire algorithm
 * (left starts at 0, only increases, and never exceeds n).
 * Even though the while loop can run n times on one iteration of the for loop, that would mean it wouldn't run at all for all the
 * other iterations of the for loop. This is what we refer to as amortized analysis - even though the worst case for an iteration inside
 * the for loop is O(n), it averages out to O(1) when you consider the entire runtime of the algorithm.
 */

fun longestSubarray(a: IntArray, k: Int): Int {
    var left = 0
    var cur = 0
    var ans = 0
    for (right in a.indices) {
        cur += a[right]
        while (cur > k) {
            cur -=a[left]
            left++
        }
        ans = max(ans, right - left + 1)

    }
    return ans
}

/**
 * You are given a binary substring s (a string containing only "0" and "1"). An operation involves flipping a "0" into a "1".
 * What is the length of the longest substring containing only "1" after performing at most one operation?
 *
 * For example, given s = "1101100111", the answer is 5. If you perform the operation at index 2, the string becomes 1111100111.
 */
fun longestSubarray(s: String): Int {
    var left = 0
    var cur = 0
    var ans = 0
    for (right in s.indices) {
        if (s[right] == '0') {
            cur++
        }
        while (cur > 1) {
            if (s[left] == '0') {
                cur--
            }
            left++
        }
        ans = max(ans, right - left + 1)

    }
    return ans
}

fun main(){
    println(longestSubarray(intArrayOf(3, 1, 2, 7, 4, 2, 1, 1, 5), 8))
    println(longestSubarray("1101100111"))
}
