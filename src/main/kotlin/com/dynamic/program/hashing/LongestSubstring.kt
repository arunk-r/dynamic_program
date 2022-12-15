package com.dynamic.program.hashing

/**
 * You are given a string s and an integer k. Find the length of the longest substring that contains at most k distinct characters.
 *
 * For example, given s = "eceba" and k = 2, return 3. The longest substring with at most 2 distinct characters is "ece".
 */

fun longestSubstring(s: String, k: Int): Int {
    val counts = hashMapOf<Char, Int>()
    var left = 0
    var ans = 0
    var right = 0
    for (c in s) {
        counts[c] = counts[c]?.plus(1) ?: 1
        while (counts.size > k) {
            counts[s[left]] = counts[s[left]]?.minus(1) ?: 0
            if (counts[s[left]] == 0) {
                counts.remove(s[left])
            }
            left++
        }
        ans = Math.max(ans, right - left + 1)
        right++
    }
    return ans
}

fun main() {
    println(longestSubstring("eceba", 2))
}
