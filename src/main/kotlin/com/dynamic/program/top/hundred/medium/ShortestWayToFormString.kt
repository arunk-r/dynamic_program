package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/shortest-way-to-form-string/
 *
 * 1055. Shortest Way to Form String
 * Medium
 * 1.2K
 * 66
 * company
 * Google
 * company
 * Facebook
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
 * Example 3:
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 *
 * Constraints:
 *
 * 1 <= source.length, target.length <= 1000
 * source and target consist of lowercase English letters.
 */
class ShortestWayToFormString {
    fun shortestWay(source: String, target: String): Int {
        val check = BooleanArray(26)
        for (c in source) {
            check[c - 'a'] = true
        }

        for (c in target) {
            if (check[c - 'a'] == false) return -1
        }

        var count = 0
        var sCnt = 0
        for (c in target) {
            if (sCnt == 0) {
                count++
            }
            while (c != source[sCnt]) {
                sCnt++
                if (sCnt == source.length) {
                    sCnt = 0
                }
                if (sCnt == 0) {
                    count++
                }
            }
            sCnt++
            if (sCnt == source.length) {
                sCnt = 0
            }
        }
        return count
    }
}
