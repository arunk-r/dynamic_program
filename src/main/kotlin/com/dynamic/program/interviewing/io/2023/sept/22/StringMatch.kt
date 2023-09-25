package com.dynamic.program.interviewing.io.`2023`.sept.`22`

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 */
class StringMatch {
    data class Data(val s: String, val p: String)
    val calls = HashMap<Data, Boolean>()

    fun isMatch(s: String, p: String): Boolean {
        val newData = Data(s, p)
        if (calls.containsKey(newData)) return calls[newData]!!

        if (s.isEmpty() && p.isEmpty()){
            calls[newData] = true
            return true
        }
        if (p.isEmpty()) {
            calls[newData] = false
            return false
        }
        if (s.isEmpty()) {
            return p[0] == '*' && isMatch(s, p.substring(1))
        }
        if (s[0] == p[0] || p[0] == '?') {
            return isMatch(s.substring(1), p.substring(1))
        }
        if (p[0] == '*') {
            return isMatch(s.substring(1), p.substring(1)) || isMatch(s, p.substring(1)) || isMatch(s.substring(1), p)
        }
        calls[newData] = false
        return false
    }
}


fun main() {
    val obj = StringMatch()
    println(obj.isMatch("aa", "a")) // False
    println(obj.isMatch("aa", "*")) // True
    println(obj.isMatch("cb", "?a")) // False
    println(obj.isMatch("adceb", "*a*b")) // True
    println(obj.isMatch("acdcb", "a*c?b")) // False
    println(obj.isMatch("adceb", "*a*b*")) // True
}
