package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
 * 151. Reverse Words in a String
 * Medium
 * 6.9K
 * 4.8K
 * company
 * Amazon
 * company
 * Apple
 * company
 * Arista Networks
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 *
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 *
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
class ReverseWordsInAString {
    fun reverseWords1(s: String): String {
        val lst = mutableListOf<String>()

        var left = 0
        var right = s.length - 1
        while(s[left] == ' ') {
            left++
        }
        while(s[right] == ' ') {
            right--
        }
        var st = left
        for(e in left .. right) {
            if(s[e] == ' ') {
                val str = s.substring(st, e)
                if(str.isNotEmpty()) {
                    lst.add(str)
                }
                st = e + 1
            }
            if(e == right){
                val str = s.substring(st, e+1)
                if(str.isNotEmpty()) {
                    lst.add(str)
                }
            }
        }
        lst.reverse()
        return lst.joinToString(" ")
    }

    fun reverseWords(s: String): String {
        val buf = StringBuffer()
        var sLen = s.length
        for (st in sLen - 1 downTo 0) {
            if (s[st] == ' ') {
                sLen = st
            } else if (st == 0 || s[st -1] == ' ') {
                if (buf.isNotEmpty()) {
                    buf.append(' ')
                }
                buf.append(s.substring(st, sLen))
            }
        }
        return buf.toString()
    }
}

fun main() {
    "s".toCharArray().joinToString()
    println(ReverseWordsInAString().reverseWords("the sky is blue"))
}
