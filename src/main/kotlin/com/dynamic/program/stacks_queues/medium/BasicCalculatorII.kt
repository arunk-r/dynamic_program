package com.dynamic.program.stacks_queues.medium

import java.util.Stack

/**
 * 227. Basic Calculator II
Medium

company
Facebook
company
Adobe
company
TikTok
Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5


Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 */
class BasicCalculatorII {
    fun calculate(s: String): Int {
        val stk = Stack<Int>()
        var buf = StringBuffer()
        var prevOp = ""
        for (c in s) {
            if (c != ' ') {
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    operations(stk, prevOp, buf)
                    prevOp = "$c"
                    buf = StringBuffer()
                } else {
                    buf.append(c)
                }
            }
        }
        if (buf.isNotEmpty()) {
            operations(stk, prevOp, buf)
        }

        while(stk.size > 1) {
            eval(stk, "+")
        }
        return stk.peek()
    }

    fun operations(s: Stack<Int>, op: String, buf: StringBuffer) {
        if (op == "*" || op == "/") {
            s.push(buf.toString().toInt())
            eval(s, op)
        } else if (op == "-") {
            s.push(-(buf.toString().toInt()))
        } else {
            s.push(buf.toString().toInt())
        }
    }

    fun eval(s: Stack<Int>, op: String) {
        val r = s.pop()
        val l = s.pop()

        val rlt: Int = if (op == "+") {
            l + r
        } else if (op == "-") {
            l - r
        } else if (op == "*") {
            l * r
        } else {
            l / r
        }
        s.push(rlt)
    }
}
