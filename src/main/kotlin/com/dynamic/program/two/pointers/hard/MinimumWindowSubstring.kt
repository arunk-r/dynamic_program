package com.dynamic.program.two.pointers.hard


/**
 * 76. Minimum Window Substring
Hard

Amazon

Airbnb

Lyft
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String? {
        if (s.isEmpty() || t.isEmpty()) {
            return ""
        }
        val dictT: MutableMap<Char, Int> = HashMap()
        for (i in t.indices) {
            val count = dictT.getOrDefault(t[i], 0)
            dictT[t[i]] = count + 1
        }
        val required = dictT.size

        // Filter all the characters from s into a new list along with their index.
        // The filtering criteria is that the character should be present in t.
        val filteredS: MutableList<Pair<Int, Char>> = ArrayList()
        for (i in s.indices) {
            val c = s[i]
            if (dictT.containsKey(c)) {
                filteredS.add(Pair(i, c))
            }
        }
        var l = 0
        var r = 0
        var formed = 0
        val windowCounts: MutableMap<Char, Int> = HashMap()
        val ans = intArrayOf(-1, 0, 0)

        // Look for the characters only in the filtered list instead of entire s.
        // This helps to reduce our search.
        // Hence, we follow the sliding window approach on as small list.
        while (r < filteredS.size) {
            var c: Char = filteredS[r].second
            val count = windowCounts.getOrDefault(c, 0)
            windowCounts[c] = count + 1
            if (dictT.containsKey(c) && windowCounts[c]!!.toInt() == dictT[c]!!.toInt()) {
                formed++
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (l <= r && formed == required) {
                c = filteredS[l].second

                // Save the smallest window until now.
                val end: Int = filteredS[r].first
                val start: Int = filteredS[l].first
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1
                    ans[1] = start
                    ans[2] = end
                }
                windowCounts[c] = windowCounts[c]!! - 1
                if (dictT.containsKey(c) && windowCounts[c]!!.toInt() < dictT[c]!!.toInt()) {
                    formed--
                }
                l++
            }
            r++
        }
        return if (ans[0] == -1) "" else s.substring(ans[1], ans[2] + 1)
    }
    
    fun minWindow1(s: String, t: String): String {
        if (s == t) return s
        else if (s.length < t.length) return ""

        val map: Map<Char, Int> = t.groupingBy { it }.eachCount()
        var left = 0
        var right = 0
        var min = Int.MAX_VALUE
        var result = ""
        while (right < s.length) {
            val substr = s.substring(left, right + 1)
            val valid = valid(substr, map)
            if (!valid) {
                right++
            } else {
                if (right - left + 1 < min) {
                    min = right - left + 1
                    result = substr
                }
                left++
            }
        }
        return result
    }

    fun valid(s: String, map: Map<Char, Int>): Boolean {
        val m: Map<Char, Int> = s.groupingBy { it }.eachCount()
        var cnt = 0
        m.forEach { (k, v) ->
            if (map.containsKey(k) && v >= map[k]!!) {
                cnt++
            }
        }
        return cnt == map.size
    }
}

fun main() {
    println(MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"))
}
