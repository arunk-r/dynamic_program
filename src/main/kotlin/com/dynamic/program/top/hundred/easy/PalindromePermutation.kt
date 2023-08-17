package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/palindrome-permutation/
 * 266. Palindrome Permutation
 * Easy
 * 1K
 * 68
 * company
 * Facebook
 * company
 * Microsoft
 * company
 * Google
 * Given a string s, return true if a permutation of the string could form a
 * palindrome
 *  and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "code"
 * Output: false
 * Example 2:
 *
 * Input: s = "aab"
 * Output: true
 * Example 3:
 *
 * Input: s = "carerac"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5000
 * s consists of only lowercase English letters.
 */
class PalindromePermutation {
    fun canPermutePalindrome2(s: String): Boolean {
        val set = hashSetOf<Char>()
        for(c in s) {
            if(set.contains(c)) {
                set.remove(c)
            } else {
                set.add(c)
            }
        }
        return set.size <= 1
    }
    fun canPermutePalindrome(s: String): Boolean {
        val map = hashMapOf<Char, Int>()
        for(c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }
        var oddCnt = 0
        map.forEach{ (c, v) ->
            oddCnt += (v % 2)
        }
        return oddCnt <= 1
    }

    fun canPermutePalindrome1(s: String): Boolean {
        val map = hashMapOf<Char, Int>()
        for(c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }
        var oddCnt = 0
        map.forEach{ (c, v) ->
            if((v % 2) == 1) {
                oddCnt++
            }
            if(oddCnt > 1) return false
        }
        return true
    }
}
