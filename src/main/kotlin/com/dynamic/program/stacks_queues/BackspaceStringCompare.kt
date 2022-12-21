package com.dynamic.program.stacks_queues

import java.util.Stack
import kotlin.math.max

/**
 * Backspace String Compare
 *
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
 * For example, given s = "ab#c" and t = "ad#c", return true.
 * Because of the backspace, the strings are both equal to "ac".
 */

fun backspaceStringCompare(s: String, t: String): Boolean {
    val sStk = Stack<Char>()
    val tStk = Stack<Char>()
    val max = max(s.length, t.length)
    for (i in 0 until max) {
        if (i < s.length) {
            if (sStk.isNotEmpty() && s[i] == '#') {
                sStk.pop()
            } else {
                sStk.add(s[i])
            }
        }

        if (i < t.length) {
            if (tStk.isNotEmpty() && t[i] == '#') {
                tStk.pop()
            } else {
                tStk.add(s[i])
            }
        }
    }
    return sStk.joinToString("") == tStk.joinToString("")
}

fun main() {
    println(backspaceStringCompare("ab#c", "ad#c"))
}
