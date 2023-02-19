package com.dynamic.program.dp.mcm

/**
 * 131. Palindrome Partitioning
Medium

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]


Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
class PalindromePartitioning {
    fun minCut(s: String): Int {
        val end = s.length
        val dp = IntArray(end + 1) { i -> i - 1 }

        for (i in 0 until 2 * end) {
            var left = i / 2; var right = i - left
            while (right < end && left >= 0 && s[right] == s[left]) {
                dp[right + 1] = minOf(dp[right + 1], dp[left] + 1)
                right++; left--
            }
            println("l = $left, r = $right, ${dp.toList()}")
        }
        return dp[end]
    }

    fun minCut1(s: String): Int {
        val cutsDp = IntArray(s.length)
        for (i in 1 until s.length) {
            cutsDp[i] = i
        }
        for (mid in s.indices) {
            // check for odd length palindrome around mid index
            findMinimumCuts(mid, mid, cutsDp, s)
            // check for even length palindrome around mid index
            findMinimumCuts(mid - 1, mid, cutsDp, s)
        }

        return cutsDp[s.length - 1]
    }

    private fun findMinimumCuts(startIndex: Int, endIndex: Int, cutsDp: IntArray, s: String) {
        var start = startIndex
        var end = endIndex
        while (start >= 0 && end < s.length && s[start] == s[end]) {
            val newCut = if (start == 0) 0 else cutsDp[start - 1] + 1
            cutsDp[end] = Math.min(cutsDp[end], newCut)
            start--
            end++
        }
    }

    fun partition(s: String): Int {
        val n = s.length
        val mem = Array(n){IntArray(n+1) { -1 }}
        return mcm(0, n-1, s, mem)
    }

    private fun mcm(i: Int, j: Int, s: String, mem: Array<IntArray>): Int {
        if (i >= j) {
            return 0
        }
        if (mem[i][j] != -1) return mem[i][j]
        if (palindrome(s, i, j)) return 0
        var min = Int.MAX_VALUE
        for (k in i until j) {
            val left = if (mem[i][k] != -1) mem[i][k]  else mcm(i, k, s, mem)
            mem[i][k] = left
            val right = if (mem[k+1][j] != -1) mem[k+1][j] else mcm(k + 1, j, s, mem)
            mem[k+1][j] = right
            val tmp = left + right  + 1
            if (tmp < min) {
                min = tmp
            }
        }
        mem[i][j] = min
        return min
    }

    private fun palindrome(s: String, i: Int, j: Int): Boolean {
        var i1 = i
        var j1 = j
        while (i1 < j1) {
            if (s[i1] != s[j1]) return false
            j1--
            i1++
        }
        return true
    }
}

fun main() {
    println(PalindromePartitioning().minCut("aab"))
   /* println(
        PalindromePartitioning().minCut(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        )
    )*/
}
