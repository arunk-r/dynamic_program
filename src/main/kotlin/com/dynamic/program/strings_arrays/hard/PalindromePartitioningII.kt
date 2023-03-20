package com.dynamic.program.strings_arrays.hard

/**
 * 132. Palindrome Partitioning II
Hard

Amazon
Yahoo
Bloomberg
Given a string s, partition s such that every
substring
of the partition is a
palindrome
.

Return the minimum cuts needed for a palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1


Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.
 */
class PalindromePartitioningII {
    fun minCut(s: String): Int {
        val len = s.length
        val dp = IntArray(len + 1) { Int.MAX_VALUE }
        dp[0] = -1
        var i = 0
        while (i < len) {
            validPalindrome(s, i, i, dp)
            validPalindrome(s, i, i + 1, dp)
            i++
        }
        return dp[len]
    }

    private fun validPalindrome(s: String, i: Int, j: Int, dp: IntArray) {
        var l = i
        var r = j
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            dp[r + 1] = minOf(dp[r + 1], dp[l] + 1)
            l--
            r++
        }
    }

    fun minCut1(s: String): Int {
        val len = s.length
        val mem = Array(len) { IntArray(len) { -1 } }
        val palMem = Array(len) { Array<Boolean?>(len) { null } }
        return dp(s, 0, s.length - 1, mem, palMem)
    }

    private fun dp(s: String, i: Int, j: Int, mem: Array<IntArray>, palMem: Array<Array<Boolean?>>): Int {
        if (i >= j) return 0
        else if (palindrome(s, i, j, palMem)) return 0
        else if (mem[i][j] != -1) return mem[i][j]
        else {
            var min = Int.MAX_VALUE
            for (k in i until j) {
                val l = dp(s, i, k, mem, palMem)
                mem[i][k] = l
                val r = dp(s, k + 1, j, mem, palMem)
                mem[k + 1][j] = r
                val tmp = 1 + l + r
                min = minOf(min, tmp)
            }
            mem[i][j] = min
            return min
        }
    }

    private fun palindrome(s: String, i: Int, j: Int, mem: Array<Array<Boolean?>>): Boolean {
        if (mem[i][j] != null) return mem[i][j]!!
        var l = i
        var r = j
        while (l < r) {
            if (s[l] != s[r]) {
                mem[i][j] = false
                return false
            }
            l++
            r--
        }
        mem[i][j] = true
        return true
    }
}

fun main() {
    println(
        PalindromePartitioningII().minCut(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        )
    )
}
