package com.dynamic.program.strings_arrays.medium

/**
 * 424. Longest Repeating Character Replacement
Medium
company
Google
company
Amazon
company
Uber
You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length

 */
class LongestRepeatingCharacterReplacement {
    fun characterReplacement(s: String, k: Int): Int {
        var left = 0
        var right = 0
        val frequency = IntArray(26)

        var maxFreq = 0

        var ans = 0
        while (right < s.length) {

            var c = s[right] -'A'
            frequency[c]++

            maxFreq = maxOf(maxFreq, frequency[c])

            if ((right - left + 1) - maxFreq > k) {

                c = s[left++] - 'A'
                frequency[c]--
            }

            ans = right - left + 1
            right++
        }

        return ans
    }
}

fun main() {
    println(LongestRepeatingCharacterReplacement().characterReplacement("AABABBA", 1))
}
