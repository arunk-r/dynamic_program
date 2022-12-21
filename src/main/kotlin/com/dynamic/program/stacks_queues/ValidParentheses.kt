package com.dynamic.program.stacks_queues

import java.util.Stack

/**
 * Valid Parentheses
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The string is valid if all open brackets are closed by the same type of closing bracket in the correct order, and each closing bracket
 * closes exactly one open bracket.
 *
 * For example, s = "({})" and s = "(){}[]" are valid,
 * but s = "(]" and s = "({)}" are not valid.
 */

fun validParentheses(s: String): Boolean{
    val stack = Stack<Char>()
    for(c in s) {
        if (c == '{') {
            stack.push('{')
        } else if (c == '}' && stack.peek() == '{') {
            stack.pop()
        }

        if (c == '(') {
            stack.push('(')
        } else if (c == ')' && stack.peek() == '(') {
            stack.pop()
        }

        if (c == '[') {
            stack.push('[')
        } else if (c == ']' && stack.peek() == '[') {
            stack.pop()
        }
    }
    return stack.isEmpty()
}

fun main() {
    println(validParentheses("{([]){}}"))
    println(validParentheses("({})"))
    println(validParentheses("(){}[]"))
    println(validParentheses("(]"))
    println(validParentheses("({)}"))
}
