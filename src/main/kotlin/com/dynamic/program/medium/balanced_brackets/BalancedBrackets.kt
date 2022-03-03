package com.dynamic.program.medium.balanced_brackets

import java.util.*

fun balancedBrackets(str: String): Boolean {
    // Write your code here.
    val openBrackets = "({["
    val closeBrackets = ")}]"
    val matchingBrackets = mapOf(')' to '(', '}' to '{',']' to '[')
    val stack = Stack<Char>()
    for (ch in str) {
        if (openBrackets.contains(ch)) {
            stack.add(ch)
        } else if (closeBrackets.contains(ch)) {
            if (stack.size == 0) return false
            if (stack.peek() == matchingBrackets[ch]) {
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.isEmpty()
}

fun main() {
    println(balancedBrackets("{[]}{"))
}