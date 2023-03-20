package com.dynamic.program.strings_arrays.medium

/**
 * 91. Decode Ways
Medium
company
Amazon
company
Cisco
company
Facebook
A message containing letters from A-Z can be encoded into numbers using the following mapping:

'A' -> "1"
'B' -> "2"
...
'Z' -> "26"
To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

"AAJF" with the grouping (1 1 10 6)
"KJF" with the grouping (11 10 6)
Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

Given a string s containing only digits, return the number of ways to decode it.

The test cases are generated so that the answer fits in a 32-bit integer.



Example 1:

Input: s = "12"
Output: 2
Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: s = "226"
Output: 3
Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
Example 3:

Input: s = "06"
Output: 0
Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").


Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */
class DecodeWays {
    val map = hashMapOf<Int, Int>()
    fun numDecodings(s: String): Int {
        if (s[0] == '0') return 0
        val dp = IntArray(s.length + 1)
        dp[0] = 1
        dp[1] = if (s[0] == '0') 0 else 1
        for (i in 1 until s.length) {
            if (s[i] != '0') {
                dp[i+1] = dp[i]
            }
            if (s[i - 1] - '0' <= 2) {
                dp[i+1] = dp[i] + dp[i-1]
            }
        }
        return dp[s.length]
    }

    fun numDecodings1(s: String): Int {
        return dfs(s, 0)
    }

    private fun dfs(s: String, i: Int): Int {
        if (map[i] != null) return map[i]!!
        if (i == s.length) {
            return 1
        }
        if (s[i] == '0') return 0
        if (i == s.length - 1) return 1
        var ans = dfs(s, i + 1)
        map[i + 1] = ans
        if (s.substring(i, i + 2).toInt() <= 26) {
            val v = dfs(s, i + 2)
            map[i + 2] = ans
            ans += v
        }
        map[i] = ans
        return ans
    }
}

fun main() {
    println(DecodeWays().numDecodings("2246"))
}
