package com.dynamic.program.strings_arrays.easy

import java.util.*

/**
 * 20. Valid Parentheses
Easy
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false


Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
 */
class ValidParentheses {
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        for (c in s) {
            when (c) {
                ')' -> {
                    if (stack.isEmpty() || stack.peek() != '(') return false
                    stack.pop()
                }

                '}' -> {
                    if (stack.isEmpty() || stack.peek() != '{') return false
                    stack.pop()
                }

                ']' -> {
                    if (stack.isEmpty() || stack.peek() != '[') return false
                    stack.pop()
                }

                else -> {
                    stack.push(c)
                }
            }
        }
        return stack.isEmpty()
    }
}
