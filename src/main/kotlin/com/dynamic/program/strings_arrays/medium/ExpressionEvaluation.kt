package com.dynamic.program.strings_arrays.medium

import java.util.Stack

class ExpressionEvaluation {
    val operands = setOf('+', '-')
    fun calculate(s: String): Int {
        return evalPostfix(postfix(s))
    }

    private fun evalPostfix(data: List<Any>): Int {
        val expr = Stack<Any>()
        for(ele in data) {
            if(ele is Char) {
                val opnd1 = expr.pop() as Int
                val opnd2 = expr.pop() as Int
                val result = if(ele == '+') opnd1 + opnd2 else opnd2 - opnd1
                expr.push(result)
            } else {
                expr.push(ele)
            }
        }
        return expr.peek() as Int
    }

    private fun postfix(s: String): List<Any> {
        val result = mutableListOf<Any>()
        val nums = Stack<Int>()
        val ops = Stack<Char>()
        for(c in s) {
            if(c == ' ') continue
            else if(c in '0' .. '9') {
                nums.push(c - '0')
            } else {
                if(nums.isNotEmpty())
                    result.add(getInt(nums))
                if(c in operands) {
                    while(ops.isNotEmpty() && ops.peek() != '(') {
                        result.add(ops.pop())
                    }
                    ops.push(c)
                } else {
                    if(c == ')') {
                        while(ops.isNotEmpty() && ops.peek() != '(') {
                            result.add(ops.pop())
                        }
                        if(ops.isNotEmpty()) ops.pop()
                    } else {
                        ops.push(c)
                    }
                }
            }
        }
        if(nums.isNotEmpty())
            result.add(getInt(nums))
        while(ops.isNotEmpty()) {
            result.add(ops.pop())
        }
        println(result)
        return result
    }

    private fun getInt(stk: Stack<Int>): Int {
        var result = 0
        while(stk.isNotEmpty()) {
            result = result * 10 + stk.pop()
        }
        return result
    }


    fun evaluate(exp: String): Int {
        val postfixExpr = convertToPostFix(exp)
        return evalPostFix(postfixExpr)
    }

    private fun evalPostFix(expr: MutableList<Any>): Int {
        val expStk = Stack<Int>()
        for (ele in expr) {
            if (ele is Char) {
                val op1 = expStk.pop()
                val op2 = expStk.pop()
                val v = when (ele) {
                    '+' -> op1 + op2
                    '-' -> op1 - op2
                    '*' -> op1 * op2
                    '/' -> op1 / op2
                    else -> 0
                }
                expStk.push(v)
            } else {
                expStk.push(ele as Int)
            }
        }
        return expStk.peek()
    }

    private fun convertToPostFix(s: String): MutableList<Any> {
        val result = mutableListOf<Any>()
        val exprStack = Stack<Char>()
        val numStack = Stack<Int>()
        for (c in s) {
            if (c == ' ') continue
            else if(c in '0' .. '9') {
                numStack.push(c - '0')
            } else {
                if (numStack.isNotEmpty()) {
                    result.add(getNumber(numStack))
                }
                if(c in operators) {
                    while (exprStack.isNotEmpty() && exprStack.peek() != '(' && operatorPresidency(c, exprStack)) {
                        result.add(exprStack.pop())
                    }
                    exprStack.push(c)
                } else if (c == ')') {
                    while (exprStack.isNotEmpty() && exprStack.peek() != '(') {
                        result.add(exprStack.pop())
                    }
                    if(exprStack.isNotEmpty()) {
                        exprStack.pop()
                    }
                } else {
                    exprStack.push(c)
                }
            }
        }
        if (numStack.isNotEmpty()) {
            result.add(getNumber(numStack))
        }
        while (exprStack.isNotEmpty()) {
            result.add(exprStack.pop())
        }
        return result
    }
    private val operators = setOf('+', '-', '*', '\\')
    private val low = setOf('+', '-')
    private val high = setOf('*', '\\')
    private fun operatorPresidency(c: Char, stk: Stack<Char>): Boolean {
        return if (stk.peek() == '(') false
        else if (c in high && stk.peek() in high) true
        else if (c in low && stk.peek() in high) true
        else if (c in low && stk.peek() in low) true
        else false
    }

    private fun getNumber(stk: Stack<Int>): Int {
        var result = 0
        while (stk.isNotEmpty()) {
            result = result * 10 + stk.pop()
        }
        return result
    }
}

fun main() {
    println(ExpressionEvaluation().calculate("(1+(4+5+2)-3)+(6+8)") == 23)
    //println(ExpressionEvaluation().evaluate("2*4+2") == 10)
    //println(ExpressionEvaluation().evaluate("2+4*2") == 10)
    println(ExpressionEvaluation().evaluate("(2+4)*2") == 12)
    println(ExpressionEvaluation().evaluate("2+4+2") == 8)
}
