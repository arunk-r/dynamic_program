package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/add-bold-tag-in-string/
 *
 * 616. Add Bold Tag in String
 * Medium
 * 1K
 * 179
 * company
 * Google
 * company
 * Facebook
 * company
 * ByteDance
 * You are given a string s and an array of strings words.
 *
 * You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.
 *
 * If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
 * If two substrings wrapped by bold tags are consecutive, you should combine them.
 * Return s after adding the bold tags.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcxyz123", words = ["abc","123"]
 * Output: "<b>abc</b>xyz<b>123</b>"
 * Explanation: The two strings of words are substrings of s as following: "abcxyz123".
 * We add <b> before each substring and </b> after each substring.
 * Example 2:
 *
 * Input: s = "aaabbb", words = ["aa","b"]
 * Output: "<b>aaabbb</b>"
 * Explanation:
 * "aa" appears as a substring two times: "aaabbb" and "aaabbb".
 * "b" appears as a substring three times: "aaabbb", "aaabbb", and "aaabbb".
 * We add <b> before each substring and </b> after each substring: "<b>a<b>a</b>a</b><b>b</b><b>b</b><b>b</b>".
 * Since the first two <b>'s overlap, we merge them: "<b>aaa</b><b>b</b><b>b</b><b>b</b>".
 * Since now the four <b>'s are consecuutive, we merge them: "<b>aaabbb</b>".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 0 <= words.length <= 100
 * 1 <= words[i].length <= 1000
 * s and words[i] consist of English letters and digits.
 * All the values of words are unique.
 *
 *
 * Note: This question is the same as 758: https://leetcode.com/problems/bold-words-in-string/
 */
class AddBoldTagInString {
    fun addBoldTag(s: String, words: Array<String>): String {
        val bool = BooleanArray(s.length)
        for(word in words) {
            var start = s.indexOf(word)
            while(start != -1) {
                for(j in start until start+word.length) {
                    bool[j] = true
                }
                start = s.indexOf(word, start+1)
            }
        }

        val buf =StringBuffer()
        val openB = "<b>"
        val closeB = "</b>"
        for(i in bool.indices) {
            if(bool[i] && (i == 0 || !bool[i-1])) {
                buf.append(openB)
            }
            buf.append(s[i])
            if(bool[i] && (i == s.length-1 || !bool[i+1])) {
                buf.append(closeB)
            }
        }

        return buf.toString()
    }
}
