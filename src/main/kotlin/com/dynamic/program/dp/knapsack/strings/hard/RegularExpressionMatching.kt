package com.dynamic.program.dp.knapsack.strings.hard

/**
 * 10. Regular Expression Matching
Hard
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:

'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).



Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".


Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
class RegularExpressionMatching {
    fun isMatch(s: String, p: String): Boolean {
        var i = 0
        var j = 0
        var starIdx = -1
        var lastMatch = -1

        while (i < s.length) {
            when {
                j < p.length && (s[i] == p[j] || p[j] == '?') -> {
                    i++
                    j++
                }
                j < p.length && p[j] == '*' -> {
                    starIdx = j
                    lastMatch = i
                    j++
                }
                starIdx != -1 -> {
                    j = starIdx + 1
                    lastMatch++
                    i = lastMatch
                }
                else -> return false
            }
        }

        while (j < p.length && p[j] == '*') j++

        return i == s.length && j == p.length
    }

    fun isMatch2(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length
        val dp = Array(m+1){BooleanArray(n+1)}

        for (j in 1 .. n) {
            if (p[j-1] == '*' || p[j-1] == '?') {
                dp[0][j] = true
            }
        }

        dp[0][0] = true
        for(i in 1 .. m) {
            for (j in 1 .. n) {
                if(s[i-1] == p[j-1] || p[j-1] == '?') {
                    dp[i][j] = dp[i-1][j-1]
                } else if(p[j - 1] == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
                else {
                    dp[i][j]=false;
                }
            }
        }

        return dp[m][n]
    }

    fun isMatch1(s: String, p: String): Boolean {
        val n = s.length
        val m = p.length
        val dp = Array(n + 1) { BooleanArray(m + 1) }
        dp[0][0] = true

        for (i in 0..n) {
            for (j in 1..m) {
                if (p[j - 1] != '*') {
                    if (i >= 1 && (s[i - 1] == p[j - 1] || p[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1]
                    }
                } else {
                    if (p[j - 2] == '.') {
                        dp[i][j] = (i >= 1 && dp[i - 1][j]) || dp[i][j - 2]
                    } else {
                        dp[i][j] = (i >= 1 && s[i - 1] == p[j - 2] && dp[i - 1][j]) || dp[i][j - 2]
                    }
                }
            }
        }

        return dp[n][m]
    }
}

fun main() {
    println(RegularExpressionMatching().isMatch("aab", "a*a*b"))
}
