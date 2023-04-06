package com.dynamic.program.medium.longest_palindromic_substring

class LongestPalindromicSubstring {
    fun longestPalindrome(s: String): String {
        var result = ""
        for (i in s.indices) {
            val odd = lookForPalindrome(i, i, s)
            val even = lookForPalindrome(i, i + 1, s)

            if (odd.length > even.length && odd.length > result.length) {
                result = odd
            } else if (even.length > result.length) {
                result = even
            }
        }
        return result
    }

    private fun lookForPalindrome(l: Int, r: Int, s: String): String {
        var left = l
        var right = r

        var substr = ""

        while (left >= 0 && right < s.length && s[left] == s[right]) {
            substr = s.substring(left, right + 1)
            left--
            right++
        }

        return substr
    }

    fun longestPalindrome1(string: String): String {
        // Write your code here.
        var currentLongestPalindrome = Pair(0, 1)
        string
        for (i in 1 until string.length - 1) {
            val odd = getLongestPalindromeFrom(string, i - 1, i + 1)
            val even = getLongestPalindromeFrom(string, i - 1, i)

            val longest = if (odd.second - odd.first > even.second - even.first) odd else even
            currentLongestPalindrome = if (longest.second - longest.first > currentLongestPalindrome.second - currentLongestPalindrome.first) longest else currentLongestPalindrome
        }

        return string.substring(currentLongestPalindrome.first, currentLongestPalindrome.second)
    }

    private fun getLongestPalindromeFrom(string: String, i: Int, j: Int): Pair<Int, Int> {
        var leftIdx = i
        var rightIdx = j
        while (leftIdx >= 0 && rightIdx < string.length) {
            if (string[leftIdx] != string[rightIdx]) {
                break
            }
            leftIdx--
            rightIdx++
        }
        return Pair(leftIdx + 1, rightIdx)
    }
}

fun main() {
    println(LongestPalindromicSubstring().longestPalindrome("babad"))
}
