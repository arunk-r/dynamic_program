package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/perform-string-shifts/
 *
 * 1427. Perform String Shifts
 * Easy
 * 218
 * 5
 * company
 * Goldman Sachs
 * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [directioni, amounti]:
 *
 * directioni can be 0 (for left shift) or 1 (for right shift).
 * amounti is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s and append it to the end.
 * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 * Return the final string after all operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 * Example 2:
 *
 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * Output: "efgabcd"
 * Explanation:
 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s only contains lower case English letters.
 * 1 <= shift.length <= 100
 * shift[i].length == 2
 * directioni is either 0 or 1.
 * 0 <= amounti <= 100
 */
class PerformStringShifts {
    fun stringShift(s: String, shift: Array<IntArray>): String {
        val len = s.length
        var str = s
        for ((dir, n) in shift) {
            val idx = n % len
            str = if (dir == 0) {
                str.substring(idx) + str.substring(0, idx)
            } else {
                str.substring(len - idx) + str.substring(0, len - idx)
            }
        }
        return str
    }

    fun stringShift1(s: String, shift: Array<IntArray>): String {
        var leftShif = 0
        for ((dir, n) in shift) {
            leftShif += if (dir == 0) n else n * -1
        }
        leftShif = Math.floorMod(leftShif, s.length)
        return s.substring(leftShif) + s.substring(0, leftShif)
    }
}
