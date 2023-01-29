package com.dynamic.program.backtracking

/**
 * Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
class PalindromePartitioning {
    fun partition(s: String): List<List<String>> {
        val result = mutableListOf<MutableList<String>>()
        backtrack(0, s, mutableListOf(), result)
        return result
    }
    fun backtrack(start: Int, s: String, cur: MutableList<String>, result: MutableList<MutableList<String>>) {
        if (start >= s.length) {
            result.add(cur.toMutableList())
        }
        for (end in start until  s.length){
            val s1 = s.substring(start, end + 1)
            if (palindrome(s1)) {
                cur.add(s1)
                backtrack(end+1, s, cur, result)
                cur.removeAt(cur.size - 1)
            }
        }
    }
    fun palindrome(s: String): Boolean {
        var st = 0
        var ed = s.length - 1
        while(st <= ed) {
            if (s[st] != s[ed]) return false
            st++
            ed--
        }
        return true
    }
}

fun main() {
    println(PalindromePartitioning().partition("bb"))
    println(PalindromePartitioning().partition("akabacdcbc"))
    println(PalindromePartitioning().partition("aab"))
    println(PalindromePartitioning().partition("a"))
}