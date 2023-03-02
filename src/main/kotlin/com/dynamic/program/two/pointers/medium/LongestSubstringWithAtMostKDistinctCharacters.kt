package com.dynamic.program.two.pointers.medium

/**
 * 340. Longest Substring with At Most K Distinct Characters
Medium

Amazon

Google

Microsoft
Given a string s and an integer k, return the length of the longest
substring
of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.


Constraints:

1 <= s.length <= 5 * 104
0 <= k <= 50
 */
class LongestSubstringWithAtMostKDistinctCharacters {
    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
        val map = hashMapOf<Char, Int>()
        var left = 0
        var max = 0
        for(right in s.indices) {
            val c = s[right]

            if(!map.containsKey(c)) {
                map[c] = 0
            }
            map[c] = map[c]!!+1

            if(map.size <= k) {
                max = maxOf(max, right - left + 1)
            } else {
                while (map.size > k) {
                    val c1 = s[left]
                    if(map[c1]!! > 1) {
                        map[c1] = map[c1]!!-1
                    } else {
                        map.remove(c1)
                    }
                    left++
                }
            }

        }
        return max
    }
}

fun main() {
    println(LongestSubstringWithAtMostKDistinctCharacters().lengthOfLongestSubstringKDistinct("eceba", 2))
}
