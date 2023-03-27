package com.dynamic.program.arrays.easy

/**
 * 1588. Sum of All Odd Length Subarrays
Easy

company
Adobe
company
Apple
company
Google
Given an array of positive integers arr, return the sum of all possible odd-length subarrays of arr.

A subarray is a contiguous subsequence of the array.



Example 1:

Input: arr = [1,4,2,5,3]
Output: 58
Explanation: The odd-length subarrays of arr and their sums are:
[1] = 1
[4] = 4
[2] = 2
[5] = 5
[3] = 3
[1,4,2] = 7
[4,2,5] = 11
[2,5,3] = 10
[1,4,2,5,3] = 15
If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
Example 2:

Input: arr = [1,2]
Output: 3
Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
Example 3:

Input: arr = [10,11,12]
Output: 66


Constraints:

1 <= arr.length <= 100
1 <= arr[i] <= 1000


Follow up:

Could you solve this problem in O(n) time complexity?
 */
class SumOfAllOddLengthSubArrays {
    fun sumOddLengthSubarrays(arr: IntArray): Int {
        val s = arr.size
        val op = IntArray(s)
        op[0] = arr[0]
        for(i in 1 until arr.size) {
            op[i] = op[i-1] + arr[i]
        }

        var ans = op[s-1]

        var i = 2
        while(i < op.size) {
            ans += getSum(i, op)
            i += 2
        }

        return ans

    }

    private fun getSum(jmp: Int, op: IntArray): Int {
        var i = jmp
        var ans = 0
        while(i < op.size) {
            ans += op[i]
            val prev = i - (jmp+1)
            if (prev >= 0) {
                ans -= op[prev]
            }
            i++
        }

        return ans
    }
}

fun main() {
    println(SumOfAllOddLengthSubArrays().sumOddLengthSubarrays(intArrayOf(1,4,2,5,3)))
}
