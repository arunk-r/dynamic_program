package com.dynamic.program.dp.medium

/**
 * 241. Different Ways to Add Parentheses
Medium

Facebook
Snapchat
LinkedIn
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.

The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.



Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10


Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
 */
class DifferentWaysToAddParentheses {
    fun diffWaysToCompute(expression: String): List<Int> {
        val v = expression.toIntOrNull()
        if (v != null) {
            return listOf(v)
        }
        val lst = mutableListOf<Int>()
        val ops = setOf('-', '+', '*')
        for (i in expression.indices) {
            if (ops.contains(expression[i])) {
                val leftOps = diffWaysToCompute(expression.substring(0, i))
                val rightOps = diffWaysToCompute(expression.substring(i+1, expression.length))
                for (l in leftOps) {
                    for (r in rightOps) {
                        lst.add(calculate(expression[i], l, r))
                    }
                }
            }
        }
        return lst
    }

    private fun calculate(op: Char, left: Int, right: Int): Int {
        return when (op) {
            '-' -> left - right
            '+' -> left + right
            '*' -> left * right
            else -> 0
        }
    }
}

fun main() {
    println(DifferentWaysToAddParentheses().diffWaysToCompute("2-1-1"))
}
