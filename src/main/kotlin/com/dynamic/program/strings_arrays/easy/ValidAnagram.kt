package com.dynamic.program.strings_arrays.easy

/**
 * https://leetcode.com/problems/valid-anagram/description/
 * 242. Valid Anagram
 * Easy
 * 9.3K
 * 292
 * company
 * Amazon
 * company
 * Bloomberg
 * company
 * Apple
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 *
 *
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */
class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        val sMap = hashMapOf<Char, Int>()
        val tMap = hashMapOf<Char, Int>()
        for(c in s) {
            sMap.putIfAbsent(c, 0)
            sMap[c] = sMap[c]!! + 1
        }
        for(c in t) {
            tMap.putIfAbsent(c, 0)
            tMap[c] = tMap[c]!! + 1
        }
        sMap.forEach{ (k, v) ->
            if(tMap.isNotEmpty() && tMap.containsKey(k)) {
                if (tMap[k] != v) {
                    return false
                } else {
                    tMap.remove(k)
                }
            } else {
                return false
            }
        }
        return tMap.isEmpty()
    }

    fun isAnagram1(s: String, t: String): Boolean {
        return key(s) == key(t)
    }

    fun key(s: String): String {
        val count = IntArray(26)
        for(c in s) {
            count[c-'a']++
        }
        val buf = StringBuffer()

        for (c in count) {
            buf.append('#')
            buf.append(c)
        }
        return buf.toString()
    }
}
