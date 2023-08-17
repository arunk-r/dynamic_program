package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/group-shifted-strings/
 *
 * 249. Group Shifted Strings
 * Medium
 * 1.5K
 * 306
 * company
 * Facebook
 * company
 * Amazon
 * company
 * Google
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 */
class GroupShiftedStrings {
    private fun hashKey(s: String): String {
        val buf = StringBuffer()
        for(i in 1 until s.length) {
            val mod: Int = (s[i] - s[i-1] + 26) % 26 + 'a'.toInt()
            buf.append(mod.toChar())
        }
        return buf.toString()
    }
    fun groupStrings(strings: Array<String>): List<List<String>> {
        val map = hashMapOf<String, MutableList<String>>()
        for(s in strings) {
            val key = hashKey(s)
            map.putIfAbsent(key, mutableListOf())
            map[key]?.add(s)
        }
        val result = mutableListOf<MutableList<String>>()
        map.values.forEach{ v ->
            result.add(v)
        }

        return result
    }
}
