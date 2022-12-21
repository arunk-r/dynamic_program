package com.dynamic.program.stacks_queues

import java.util.Stack

/**
 * Remove All Adjacent Duplicates In String
 *
 * You are given a string s. Continuously remove duplicates (two of the same character beside each other) until you can't anymore. Return the final string after this.
 * For example, given s = "abbaca", you can first remove the "bb" to get "aaca".
 * Next, you can remove the "aa" to get "ca". This is the final answer.
 */

fun removeAdjacentDuplicates(s: String): String {
    val stack = Stack<Char>()
    for (c in s){
        if (stack.isNotEmpty() && stack.peek() == c) {
            stack.pop()
        } else {
            stack.push(c)
        }
    }
    return stack.joinToString("")
}

fun main() {
    println(removeAdjacentDuplicates("abbaca"))
}
