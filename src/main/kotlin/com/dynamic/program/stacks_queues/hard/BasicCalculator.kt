package com.dynamic.program.stacks_queues.hard

import java.util.*


/**
 * 224. Basic Calculator
Hard

company
Amazon
company
Microsoft
company
Google
Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "1 + 1"
Output: 2
Example 2:

Input: s = " 2-1 + 2 "
Output: 3
Example 3:

Input: s = "(1+(4+5+2)-3)+(6+8)"
Output: 23


Constraints:

1 <= s.length <= 3 * 105
s consists of digits, '+', '-', '(', ')', and ' '.
s represents a valid expression.
'+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
'-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
There will be no two consecutive operators in the input.
Every number and running calculation will fit in a signed 32-bit integer.
 */
class BasicCalculator {
    fun calculate(s: String): Int {
        val stack = Stack<Int>()
        var operand = 0
        var result = 0 // For the on-going result
        var sign = 1 // 1 means positive, -1 means negative
        for (ch in s) {
            if (ch.isDigit()) {

                // Forming operand, since it could be more than one digit
                operand = 10 * operand + (ch.code - '0'.code)
            } else if (ch == '+') {

                // Evaluate the expression to the left,
                // with result, sign, operand
                result += sign * operand

                // Save the recently encountered '+' sign
                sign = 1

                // Reset operand
                operand = 0
            } else if (ch == '-') {
                result += sign * operand
                sign = -1
                operand = 0
            } else if (ch == '(') {

                // Push the result and sign on to the stack, for later
                // We push the result first, then sign
                stack.push(result)
                stack.push(sign)

                // Reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1
                result = 0
            } else if (ch == ')') {

                // Evaluate the expression to the left
                // with result, sign and operand
                result += sign * operand

                // ')' marks end of expression within a set of parenthesis
                // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result *= stack.pop()

                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += stack.pop()

                // Reset the operand
                operand = 0
            }
        }
        return result + sign * operand
    }

    fun calculate1(s: String): Int {
        val stk = Stack<String>()
        var prevOp = ""
        var buf = StringBuffer()

        for (c in s) {
            if (c != ' ') {
                if (c == '(') {
                    stk.push("$c")
                } else if (c.isDigit()) {
                    buf.append(c)
                } else {
                    pushToStack(stk, prevOp, buf)
                    if (c == ')') {
                        handlePrecedence(stk)
                        prevOp = ""
                    } else {
                        prevOp = "$c"
                    }
                    buf = StringBuffer()
                }
            }
        }

        if (buf.isNotEmpty()) {
            pushToStack(stk, prevOp, buf)
        }

        while (stk.size > 1) {
            val r = stk.pop().toInt()
            val l = stk.pop().toInt()
            stk.push(eval(l, "+", r).toString())
        }
        return stk.peek().toInt()
    }

    private fun pushToStack(s: Stack<String>, op: String, buf: StringBuffer) {
        println("$s, $op, $buf")
        if (buf.isNotEmpty()) {
            if (op == "*" || op == "/") {
                s.push(buf.toString())
                val r = s.pop().toInt()
                val l = s.pop().toInt()
                s.push(eval(l, op, r).toString())
            } else if (op == "-") {
                s.push("-${buf.toString()}")
            } else {
                s.push(buf.toString())
            }
        }
    }

    private fun handlePrecedence(s: Stack<String>) {
        println(s)
        while (s.size > 1) {
            val r = s.pop().toInt()
            val l = s.pop().toInt()
            val evl = eval(l, "+", r)
            if (s.peek() == "(") {
                s.pop()
                s.push(evl.toString())
                return
            }
            s.push(evl.toString())
        }
    }

    private fun eval(l: Int, op: String, r: Int): Int = if (op == "+") {
        l + r
    } else if (op == "-") {
        l - r
    } else if (op == "*") {
        l * r
    } else {
        l / r
    }
}

fun main() {
    //println(BasicCalculator().calculate("1 + 1"))
    //println(BasicCalculator().calculate(" 2-1 + 2 "))
    println(BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"))
}
