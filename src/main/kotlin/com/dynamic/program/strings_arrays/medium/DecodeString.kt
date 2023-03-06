package com.dynamic.program.strings_arrays.medium

import java.util.*


/**
 * 394. Decode String
Medium
Bloomberg
Amazon
Google
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */
class DecodeString {
    fun decodeString(s: String): String {
        val stack = Stack<Char>()
        for(c in s) {
            if(c == ']') {
                val decodedString = mutableListOf<Char>()
                while (!stack.peek().isDigit() && stack.peek() != '[') {
                    decodedString.add(stack.pop())
                }
                stack.pop()
                var k = 0
                var base = 1
                while (stack.isNotEmpty() && stack.peek().isDigit()) {
                    k += (stack.pop() - '0') * base
                    base *= 10
                }

                while (k > 0) {
                    for (j in decodedString.size - 1 downTo 0) {
                        stack.push(decodedString[j])
                    }
                    k--
                }

            } else {
                stack.push(c)
            }
        }
        val c = CharArray(stack.size)
        for (i in c.size -1 downTo 0) {
            if(stack.peek().isDigit()) return ""
            c[i] = stack.pop()
        }
        return String(c)
    }
}

fun main() {
    println(DecodeString().decodeString("3"))
    println(DecodeString().decodeString("3[a]2[bc]"))
    println(DecodeString().decodeString("3[a2[c]]"))
}
