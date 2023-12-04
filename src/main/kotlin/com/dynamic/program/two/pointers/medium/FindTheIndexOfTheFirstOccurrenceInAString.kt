package com.dynamic.program.two.pointers.medium

/**
 * 28. Find the Index of the First Occurrence in a String
Medium

Bloomberg

Amazon

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
    fun strStr1(haystack: String, needle: String): Int {
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

    fun strStr(haystack: String, needle: String): Int {
        if(needle.length > haystack.length) return -1
        else if(needle.length == haystack.length) {
            if(needle == haystack) return 0
            else return -1
        }

        var l = 0
        var reset = true
        var idx = 0
        var r = 0
        val set = HashSet<Int>()
        while(r < haystack.length && idx < needle.length) {
            if(reset && haystack[r] == needle[idx]) {
                l = r
                idx++
                reset = false
            } else if(haystack[r] == needle[idx]) {
                idx++
            } else if(!reset){
                var tmpR = r-1
                while(tmpR in 0 until l && haystack[tmpR] != needle[0]) {
                    tmpR--
                }
                if(!set.contains(tmpR) && tmpR >= 0 && haystack[tmpR] == needle[0]) {
                    r = tmpR
                    set.add(tmpR)
                    reset = true
                    idx = 0
                    continue
                }
                reset = true
                idx = 0
            }
            r++
        }
        if(idx == needle.length) return l
        return -1
    }
}

fun main() {
    println(FindTheIndexOfTheFirstOccurrenceInAString().strStr("mississippi", "issip"))
}
