package com.dynamic.program.greedy

import java.util.TreeSet

/**
 * Longest Palindrome
 *
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 *
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 *
 * Example 2:
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 *
 *
 * Constraints:
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 *
 */
class LongestPalindrome {
    fun longestPalindrome(s: String): Int {
        val set = TreeSet<Int>()
        set.addAll(intArrayOf(1,2).toList())
        val count = IntArray(128)
        s.forEach{ c ->
            count[c.toInt()]++
        }

        var ans = 0
        for (v in count) {
            ans += v / 2 * 2
            if (ans % 2 == 0 && v % 2 == 1) ans++
        }
        return ans
    }
}