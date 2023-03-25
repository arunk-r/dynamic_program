package com.dynamic.program.hashing

/**
 * Longest Substring Without Repeating Characters
 *Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 10^4
 * s consists of English letters, digits, symbols and spaces.
 */

class LongestSubstringWithoutRepeatingCharacters {

    fun lengthOfLongestSubstring(s: String): Int {
        val map = hashMapOf<Char, Int>()

        for (c in s) map[c] = 0
        var d = Int.MIN_VALUE

        var end = 0
        var begin = 0

        while (end < s.length) {
            map[s[end]] = map[s[end]]!! + 1

            while (map[s[end]]!! > 1) {
                map[s[begin]] = map[s[begin]]!! - 1
                begin++
            }
            end++

            d = maxOf(d, end - begin)
        }
        return d
    }

    fun lengthOfLongestSubstring1(s: String): Int {
        var ans = 0
        val set = hashSetOf<String>()
        var substr = StringBuilder()
        s.forEach { c ->
            if (set.contains(c.toString())) {
                substr = StringBuilder()
                substr.append(c)
            } else {
                set.add(c.toString())
                substr.append(c)
                val str = substr.toString()
                set.add(str)
                ans = Math.max(ans, str.length)
            }
        }
        return ans
    }

    // sliding window
    fun lengthOfLongestSubstring_SlidingWindow(s: String): Int {
        var ans = 0
        var left = 0
        val charArray = IntArray(128)
        for (right in s.indices) {
            val c = s[right].toInt()
            charArray[c]++
            while (charArray[c] > 1) {
                val leftChar = s[left].code
                charArray[leftChar]--
                left++
            }
            ans = Math.max(ans, right - left + 1)
        }
        return ans
    }
}

fun main() {
    val l = LongestSubstringWithoutRepeatingCharacters()
    println(l.lengthOfLongestSubstring("abcabcbb"))
    println(l.lengthOfLongestSubstring("dvdf"))
    println(l.lengthOfLongestSubstring("bbbbb"))
    println(l.lengthOfLongestSubstring("pwwkew"))

    println(l.lengthOfLongestSubstring_SlidingWindow("dvdf"))
    println(l.lengthOfLongestSubstring_SlidingWindow("bbbbb"))
    println(l.lengthOfLongestSubstring_SlidingWindow("pwwkew"))
}
