package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/brace-expansion/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 1087. Brace Expansion
 * Medium
 * 621
 * 50
 * company
 * Google
 * You are given a string s representing a list of words. Each letter in the word has one or more options.
 *
 * If there is one option, the letter is represented as is.
 * If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
 * For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].
 *
 * Return all words that can be formed in this manner, sorted in lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "{a,b}c{d,e}f"
 * Output: ["acdf","acef","bcdf","bcef"]
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: ["abcd"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50
 * s consists of curly brackets '{}', commas ',', and lowercase English letters.
 * s is guaranteed to be a valid input.
 * There are no nested curly brackets.
 * All characters inside a pair of consecutive opening and ending curly brackets are different.
 */
class BraceExpansion {
    fun expand(s: String): Array<String> {
        val result = mutableListOf<String>()
        combinationWithBT(0, s, "", result)
        return result.toTypedArray()
    }

    private fun combinationWithBT(idx: Int, s: String, cur: String, result: MutableList<String>) {
        if(idx >= s.length) {
            result.add(cur)
        } else {
            if(s[idx] == '{') {
                val substr = extractString(idx+1, s)
                val nextIdx = idx+substr.length+2
                for(c in substr) {
                    if(c != ','){
                        combinationWithBT(nextIdx, s, "$cur$c", result)
                    }
                }
            } else {
                combinationWithBT(idx+1, s, "$cur${s[idx]}", result)
            }
        }
    }

    private fun extractString(idx: Int, s: String): String {
        val buf = StringBuffer()
        var i = idx
        while(s[i] != '}') {
            buf.append(s[i++])
        }
        return buf.toString()
    }
}
