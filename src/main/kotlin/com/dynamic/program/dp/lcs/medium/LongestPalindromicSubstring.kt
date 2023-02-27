package com.dynamic.program.dp.lcs.medium

/**
 * 5. Longest Palindromic Substring
Medium
Given a string s, return the longest
palindromic

substring
in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
class LongestPalindromicSubstring {
    fun longestPalindrome(s: String): String {
        val n = s.length
        var longest = 0
        var ans = ""
        for (i in s.indices) {
            // odd length
            val odd = getSubstring(i, i, s, n , longest, ans)
            longest = odd.first
            ans = odd.second
            // even length
            val even = getSubstring(i, i+1, s, n , longest, ans)
            longest = even.first
            ans = even.second
        }
        return  ans
    }

    private fun getSubstring(left: Int, right: Int, s: String, n: Int, longest: Int, ans: String): Pair<Int, String> {
        var lg = longest
        var m = ans
        var l = left
        var r = right
        while (l >= 0 && r < n && s[l] == s[r]) {
            if (r-l+1 > longest) {
                lg = r-l+1
                m = s.substring(l, l+(r-l+1))
            }
            l--
            r++
        }

        return Pair(lg, m)
    }
}

fun main() {
    println(LongestPalindromicSubstring().longestPalindrome("babad"))
    println(LongestPalindromicSubstring().longestPalindrome("c"))
}
