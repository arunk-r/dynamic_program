package com.dynamic.program.dp.lcs.medium

import java.util.PriorityQueue

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
        val r = s.reversed()
        val map = hashMapOf<String, String>()
        val q = PriorityQueue<String>{x, y -> y.length - x.length}
        val dp = Array(s.length){BooleanArray(s.length)}
        for(i in s.indices) {
            for(j in r.indices) {
                val key = "$i,$j"
                if(s[i] == r[j]) {
                    if(i > 0 && j > 0 && dp[i-1][j-1]) {
                        dp[i][j] = true
                        val key1 = "${i - 1},${j - 1}"
                        map[key] = "${map[key1]!!}${s[i]}"
                    } else {
                        dp[i][j] = true
                        map[key] = "${s[i]}"
                    }
                    q.add(map[key]!!)
                }
            }
        }
        return q.remove()
    }

    fun longestPalindrome1(s: String): String {
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
    //println(LongestPalindromicSubstring().longestPalindrome("babad"))
    //println(LongestPalindromicSubstring().longestPalindrome("c"))
    println(LongestPalindromicSubstring().longestPalindrome("aacabdkacaa"))
}
