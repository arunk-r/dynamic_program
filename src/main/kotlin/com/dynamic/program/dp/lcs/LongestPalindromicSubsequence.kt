package com.dynamic.program.dp.lcs

/**
 * Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.



Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".


Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */
class LongestPalindromicSubsequence {
    fun longestPalindromeSubseq(s: String): Int {
        val s2 = s.reversed()
        val n = s.length
        val dp = Array(n+1){IntArray(n+1)}
        for(i in 1 .. n) {
            for(j in 1..n) {
                dp[i][j] = if (s[i-1] == s2[j-1]) {
                    dp[i-1][j-1]+1
                } else {
                    maxOf(dp[i-1][j],dp[i][j-1])
                }
            }
        }
        return dp[n][n]
    }
}