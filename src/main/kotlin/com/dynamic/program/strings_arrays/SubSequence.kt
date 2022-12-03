package com.dynamic.program.strings_arrays

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * hint: using 2 pointers both starts from 0 index
 * time - O(n) // n is length of string t
 */

fun subsequence(s: String, t:String): Boolean {
    var i = 0
    var j = 0
    if (s.isEmpty() || t.isEmpty()) return false
    while (i < s.length && j < t.length) {
        if (s[i] == t[j]) {
            i++
        }
        j++
    }
    return i == s.length
}

fun main() {
    println(subsequence("ace", "abcde"))
    println(subsequence("", "abcde"))
    println(subsequence("aced", "abcde"))
    println(subsequence("aced", "abc"))
    println(subsequence("aced", ""))
    println(subsequence("", ""))
}
