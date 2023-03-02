package com.dynamic.program.two.pointers.medium

/**
 * 159. Longest Substring with At Most Two Distinct Characters
Medium


Bloomberg

Amazon

Microsoft
Given a string s, return the length of the longest
substring
that contains at most two distinct characters.



Example 1:

Input: s = "eceba"
Output: 3
Explanation: The substring is "ece" which its length is 3.
Example 2:

Input: s = "ccaabbb"
Output: 5
Explanation: The substring is "aabbb" which its length is 5.


Constraints:

1 <= s.length <= 105
s consists of English letters.
 */
class LongestSubstringWithAtMostTwoDistinctCharacters {
    fun lengthOfLongestSubstringTwoDistinct(s: String): Int {
        var left = 0
        var right = 0
        var max = 0
        while(right < s.length) {
            val substr = s.substring(left, right+1)
            if(chars(substr) <= 2) {
                max = maxOf(max, substr.length)
                right++
            } else {
                left++
            }
        }

        return max
    }

    private fun chars(s: String): Int {
        val set = hashSetOf<Char>()
        for(c in s) {
            set.add(c)
        }
        return set.size
    }
}
