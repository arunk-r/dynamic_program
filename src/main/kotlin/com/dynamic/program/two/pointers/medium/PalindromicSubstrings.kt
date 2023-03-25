package com.dynamic.program.two.pointers.medium

/**"
 * 647. Palindromic Substrings
Medium

company
Goldman Sachs
company
Amazon
company
Facebook
Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
class PalindromicSubstrings {
    fun countSubstrings(s: String): Int {
        var cnt = 0
        for(i in s.indices) {
            cnt += countPalindrome(s, i, i)
            cnt += countPalindrome(s, i, i+1)
        }

        return cnt
    }

    private fun countPalindrome(s: String, l: Int, r: Int): Int {
        var i = l
        var j = r
        var ans = 0

        while (i >= 0 && j < s.length) {
            if (s[i] != s[j]) return ans

            i--
            j++
            ans++
        }

        return ans
    }
}


