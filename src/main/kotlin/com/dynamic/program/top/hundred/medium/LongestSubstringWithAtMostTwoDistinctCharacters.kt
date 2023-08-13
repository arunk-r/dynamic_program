package com.dynamic.program.top.hundred.medium

/**
 *https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/description/
 *
 * 159. Longest Substring with At Most Two Distinct Characters
 * Medium
 * 2.1K
 * 32
 * company
 * Bloomberg
 * company
 * Amazon
 * company
 * Microsoft
 * Given a string s, return the length of the longest
 * substring
 *  that contains at most two distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of English letters.
 */
class LongestSubstringWithAtMostTwoDistinctCharacters {
    fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
        var l = 0
        var r = 0
        var strLen = 0
        val map = hashMapOf<Char, Int>()
        while(r < s.length) {
            val c = s[r]
            map[c] = map.getOrDefault(c, 0) + 1

            if(map.size > 2) {
                strLen = maxOf(strLen, r - l)
            }

            while(map.size > 2) {
                val lc = s[l]
                map[lc] = map[lc]!!-1
                if(map[lc]!! == 0) {
                    map.remove(lc)
                }
                l++
            }
            r++
        }
        strLen = maxOf(strLen, map.values.sum())
        return strLen
    }
}
