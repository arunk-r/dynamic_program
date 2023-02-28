package com.dynamic.program.two.pointers.medium

/**
 * 28. Find the Index of the First Occurrence in a String
Medium
company
Bloomberg
company
Amazon
company
Microsoft
Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.



Example 1:

Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
Example 2:

Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.


Constraints:

1 <= haystack.length, needle.length <= 104
haystack and needle consist of only lowercase English characters.
 */
class FindTheIndexOfTheFirstOccurrenceInAString {
    fun strStr(haystack: String, needle: String): Int {
        val m = haystack.length
        val n = needle.length
        if (haystack == needle) return 0
        if(n> m) return -1

        var left = 0
        var right = n
        while(right <= m) {
            val s = haystack.substring(left, right)
            if(s == needle) return left
            left++
            right++
        }

        return -1
    }
}
