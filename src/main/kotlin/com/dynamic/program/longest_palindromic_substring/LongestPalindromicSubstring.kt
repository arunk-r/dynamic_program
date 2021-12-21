package com.dynamic.program.longest_palindromic_substring

fun longestPalindromicSubstring(string: String): String {
    // Write your code here.
    var currentLongestPalindrome = Pair(0, 1)

    for (i in 1 until string.length - 1) {
        val odd = getLongestPalindromeFrom(string,i-1, i+1)
        val even = getLongestPalindromeFrom(string, i-1, i)

        val longest = if (odd.second - odd.first > even.second - even.first) odd else even
        currentLongestPalindrome = if (longest.second - longest.first > currentLongestPalindrome.second - currentLongestPalindrome.first) longest else currentLongestPalindrome
    }

    return string.substring(currentLongestPalindrome.first, currentLongestPalindrome.second)
}

fun getLongestPalindromeFrom(string: String, i: Int, j: Int): Pair<Int, Int> {
    var leftIdx = i
    var rightIdx = j
    while (leftIdx >= 0 && rightIdx < string.length) {
        if (string[leftIdx] != string[rightIdx]) {
            break
        }
        leftIdx--
        rightIdx++
    }
    return Pair(leftIdx+1, rightIdx)
}

fun main() {
    println(longestPalindromicSubstring("abafxyzzyxf"))
}