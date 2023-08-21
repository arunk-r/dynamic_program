package com.dynamic.program.top.hundred.hard

import java.util.Stack

/**
 * https://leetcode.com/problems/basic-calculator-iii/
 *
 * 772. Basic Calculator III
 * Hard
 * 1.1K
 * 272
 * company
 * TikTok
 * company
 * Amazon
 * company
 * Apple
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1+1"
 * Output: 2
 * Example 2:
 *
 * Input: s = "6-4/2"
 * Output: 4
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 *
 * Constraints:
 *
 * 1 <= s <= 104
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 * s is a valid expression.
 */
class BasicCalculatorIII {
    data class Data(val num: Int = 0, val c: Char = ' ', val isChar: Boolean = false)

    private fun evaluate(op: Char, left: Int, right: Int) = when (op) {
        '+' -> left
        '-' -> -left
        '*' -> left * right
        else -> left / right
    }

    var idx = 0
    private val precedenceChar = setOf('*', '/')

    fun calculate(s: String): Int {
        var num = 0
        var prevChar = '+'
        val stk = Stack<Int>()
        while (idx < s.length) {
            val c = s[idx]
            if (c in '0'..'9') {
                num = (num * 10) + (c - '0')
            } else if (c == '(') {
                idx++
                num = calculate(s)
            } else {
                if (prevChar in precedenceChar) {
                    stk.push(evaluate(prevChar, stk.pop(), num))
                } else {
                    stk.push(evaluate(prevChar, num, 0))
                }
                num = 0
                prevChar = c
                if (c == ')') {
                    break
                }
            }
            idx++
        }
        var ans = 0
        while (stk.isNotEmpty()) {
            ans += stk.pop()
        }
        return ans
    }

    fun calculate1(s: String): Int {
        val stk = Stack<Data>()
        var cur = 0
        var prevChar: Char = '+'
        val newS = "$s@"
        for (c in newS) {
            if (c in '0'..'9') {
                cur = (cur * 10) + (c - '0')
            } else if (c == '(') {
                stk.push(Data(0, prevChar, true))
                prevChar = '+'
            } else {
                if (prevChar in precedenceChar) {
                    val d = stk.pop()
                    stk.push(Data(evaluate(prevChar, d.num, cur)))
                } else {
                    stk.push(Data(evaluate(prevChar, cur, 0)))
                }
                cur = 0
                prevChar = c
                if (c == ')') {
                    var result = 0
                    while (!stk.peek().isChar) {
                        val d = stk.pop()
                        result += d.num
                    }
                    cur = result
                    val d = stk.pop()
                    prevChar = d.c
                }
            }
        }
        var ans = 0
        while (stk.isNotEmpty()) {
            ans += stk.pop().num
        }
        return ans
    }
}

fun main() {
    println(BasicCalculatorIII().calculate("1+1"))
    println(BasicCalculatorIII().calculate("6-4/2"))
    println(BasicCalculatorIII().calculate("2*(5+5*2)/3+(6/2+8)"))

    println(BasicCalculatorIII().calculate1("1+1"))
    println(BasicCalculatorIII().calculate1("6-4/2"))
    println(BasicCalculatorIII().calculate1("2*(5+5*2)/3+(6/2+8)"))
}
