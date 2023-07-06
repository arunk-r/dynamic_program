package com.dynamic.program.strings_arrays.medium

import java.util.Arrays

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 *
 * 438. Find All Anagrams in a String
 * Medium
 * 11.4K
 * 316
 * company
 * Yandex
 * company
 * Amazon
 * company
 * Adobe
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 *
 * Constraints:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s and p consist of lowercase English letters.
 */
class FindAllAnagramsInAString {
    fun findAnagrams(s: String, p: String): List<Int> {
        val pArr = IntArray(26)
        for(c in p) {
            pArr[c - 'a']++
        }
        val len = p.length
        val sArr = IntArray(26)
        val result = mutableListOf<Int>()
        for(i in s.indices) {
            sArr[s[i] - 'a'] ++
            if(i >= len) {
                sArr[s[i - len] - 'a'] --
            }

            if(sArr contentEquals pArr) {
                result.add(i-len + 1)
            }
        }

        return result
    }

    fun findAnagrams1(s: String, p: String): List<Int> {
        val map = hashMapOf<Char, Int>()
        for(c in p) {
            map.putIfAbsent(c, 0)
            map[c] = map[c]!!+1
        }
        val len = p.length
        val result = mutableListOf<Int>()
        for(i in 0 until s.length - len + 1) {
            val str = s.substring(i, i+len)
            if(isAnagram(str, map)) {
                result.add(i)
            }
        }
        return result
    }

    private fun isAnagram(s: String, map: HashMap<Char, Int>): Boolean {
        val map1 = hashMapOf<Char, Int>()
        for(c in s) {
            map1.putIfAbsent(c, 0)
            map1[c] = map1[c]!!+1
        }

        map.forEach{ (k, v) ->
            if(v != map1[k]) {
                return false
            }
        }

        return true
    }
}
