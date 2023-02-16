package com.dynamic.program.dp

/**
 * Longest Common Subsequence
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
 * without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 * Example 1:
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 *
 *
 * Example 2:
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 *
 *
 * Example 3:
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * Constraints:
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 */
class LongestCommonSubsequence {
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val dp = Array(text1.length+1){IntArray(text2.length+1){0}}
        for(i in text1.length - 1 downTo 0) {
            for(j in text2.length - 1 downTo 0) {
                if (text1[i] == text2[j]) {
                    dp[i][j] = 1 + dp[i+1][j+1]
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1])
                }
            }
        }

        return dp[0][0]
    }

    fun longestCommonSubsequence1(text1: String, text2: String): Int {
        return dp(0,0, text1, text2)
    }

    fun dp(i: Int, j: Int, text1: String, text2: String): Int {
        if (i==text1.length || j==text2.length) return 0
        return if (text1[i] == text2[j]) {
            1 + dp(i+1, j+1, text1, text2)
        } else {
            Math.max(dp(i+1, j, text1, text2), dp(i, j+1, text1, text2))
        }
    }
}

fun main() {
    println(LongestCommonSubsequence().longestCommonSubsequence("abcde", "abc"))
}