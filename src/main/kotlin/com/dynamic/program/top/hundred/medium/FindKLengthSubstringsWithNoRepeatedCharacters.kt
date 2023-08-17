package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
 *
 * 1100. Find K-Length Substrings With No Repeated Characters
 * Medium
 * 527
 * 11
 * company
 * Amazon
 * Given a string s and an integer k, return the number of substrings in s of length k with no repeated characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "havefunonleetcode", k = 5
 * Output: 6
 * Explanation: There are 6 substrings they are: 'havef','avefu','vefun','efuno','etcod','tcode'.
 * Example 2:
 *
 * Input: s = "home", k = 5
 * Output: 0
 * Explanation: Notice k can be larger than the length of s. In this case, it is not possible to find any substring.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 * 1 <= k <= 104
 */
class FindKLengthSubstringsWithNoRepeatedCharacters {
    fun numKLenSubstrNoRepeats(s: String, k: Int): Int {
        for((c, i) in s.withIndex()) {
            println("$c $i")
        }
        var cnt = 0
        var l = 0
        var r = 0
        val set = hashSetOf<Char>()
        while(r < s.length) {
            val c = s[r++]
            if(set.size == k) {
                cnt++
                set.remove(s[l++])
            }
            while(set.contains(c)) {
                set.remove(s[l++])
            }
            set.add(c)
        }
        if(set.size == k) {
            cnt++
        }
        return cnt
    }
}

fun main() {
    println(FindKLengthSubstringsWithNoRepeatedCharacters().numKLenSubstrNoRepeats("abs", 1))
}
