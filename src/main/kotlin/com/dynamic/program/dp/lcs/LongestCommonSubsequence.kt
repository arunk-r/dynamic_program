package com.dynamic.program.dp.lcs

/**
 * Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.



Example 1:

Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.


Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
class LongestCommonSubsequence {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array(text1.length+1){IntArray(text2.length+1)}
        for (i in 0 .. text1.length) {
            for (j in 0 .. text2.length){
                dp[i][j] = if (i == 0 || j == 0) 0
                else if (text1[i-1] == text2[j-1]) 1+ dp[i-1][j-1]
                else
                    maxOf(dp[i-1][j], dp[i][j-1])
            }
        }
        return dp[text1.length][text2.length]
    }
    fun longestCommonSubsequence1(text1: String, text2: String): Int {
        val mem = Array(text1.length+1){IntArray(text2.length+1){-1} }
        val v = dp(text1, text2, text1.length, text2.length, mem)
        return v
    }

    fun dp(text1: String, text2: String, n: Int, m: Int, mem: Array<IntArray>): Int {
        return if (n == 0 || m == 0) 0
        else if (mem[n][m] != -1) mem[n][m]
        else if (text1[n-1] == text2[m-1]) {
            val v = dp(text1, text2, n - 1, m - 1, mem) + 1
            mem[n][m] = v
            v
        }
        else {
            val v = maxOf(dp(text1, text2, n, m - 1, mem), dp(text1, text2, n - 1, m, mem))
            mem[n][m] = v
            v
        }
    }
}

fun main() {
    println(LongestCommonSubsequence().longestCommonSubsequence("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"))
    println(LongestCommonSubsequence().longestCommonSubsequence1("mhunuzqrkzsnidwbun", "szulspmhwpazoxijwbq"))
}