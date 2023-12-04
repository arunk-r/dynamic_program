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

        val stk = Stack<Any>()
        val num = Stack<Char>()
        for(i in s.length-1 downTo 0) {
            if(s[i] == ' ') continue

            if(s[i] in '0' .. '9') {
                num.push(s[i])
                continue
            }

            if(num.isNotEmpty()) {
                stk.push(getInt(num))
            }

            if(s[i] == '(') {
                val res = eval(stk)
                stk.pop()
                stk.push(res)
            } else {
                stk.push(s[i])
            }
        }

        if(num.isNotEmpty()) {
            stk.push(getInt(num))
        }
        return eval(stk)
    }

    private fun getInt(stk: Stack<Char>): Int {
        var result = 0
        while(stk.isNotEmpty()) {
            result = result * 10 + (stk.pop() - '0')
        }
        return result
    }

    private fun eval(stk: Stack<Any>): Int {
        println(stk)
        if(stk.isEmpty() || stk.peek() !is Int) {
            stk.push(0)
        }
        var result = (stk.pop() as Int)
        while(stk.isNotEmpty() && stk.peek() != ')') {
            val ch = stk.pop() as Char
            if(ch == '+') {
                result += (stk.pop() as Int)
            } else {
                result -= (stk.pop() as Int)
            }
        }
        return result
    }
    /*fun evaluateExpr(stack: Stack<Any>): Int {

        // If stack is empty or the expression starts with
        // a symbol, then append 0 to the stack.
        // i.e. [1, '-', 2, '-'] becomes [1, '-', 2, '-', 0]
        if (stack.empty() || stack.peek() !is Int) {
            stack.push(0)
        }
        var res = stack.pop() as Int

        // Evaluate the expression till we get corresponding ')'
        while (!stack.empty() && stack.peek() as Char != ')') {
            val sign = stack.pop() as Char
            if (sign == '+') {
                res += stack.pop() as Int
            } else {
                res -= stack.pop() as Int
            }
        }
        return res
    }

    fun calculate(s: String): Int {
        var operand = 0
        var n = 0
        val stack = Stack<Any>()
        for (i in s.length - 1 downTo 0) {
            val ch = s[i]
            if (Character.isDigit(ch)) {

                // Forming the operand - in reverse order.
                operand += Math.pow(10.0, n.toDouble()).toInt() * (ch.code - '0'.code)
                n += 1
            } else if (ch != ' ') {
                if (n != 0) {

                    // Save the operand on the stack
                    // As we encounter some non-digit.
                    stack.push(operand)
                    n = 0
                    operand = 0
                }
                if (ch == '(') {
                    val res = evaluateExpr(stack)
                    stack.pop()

                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res)
                } else {
                    // For other non-digits just push onto the stack.
                    stack.push(ch)
                }
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand)
        }

        // Evaluate any left overs in the stack.
        return evaluateExpr(stack)
    }

    fun calculate2(s: String): Int {
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
    }*/
}

fun main() {
    println(BasicCalculator().calculate("1 + 1"))
    //println(BasicCalculator().calculate(" 2-1 + 2 "))
    //println(BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"))
}
