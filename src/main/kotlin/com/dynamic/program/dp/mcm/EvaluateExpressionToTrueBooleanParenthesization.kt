package com.dynamic.program.dp.mcm

/**
 * Given an expression, A, with operands and operators (OR , AND , XOR), in how many ways can you evaluate the expression to true, by grouping in different ways?

Operands are only true and false.

Return the number of ways to evaluate the expression modulo 103 + 3.



Input Format:

The first and the only argument of input will contain a string, A.

The string A, may contain these characters:
'|' will represent or operator
'&' will represent and operator
'^' will represent xor operator
'T' will represent true operand
'F' will false
Output:

Return an integer, representing the number of ways to evaluate the string.
Constraints:

1 <= length(A) <= 150
Example:

Input 1:
A = "T|F"

Output 1:
1

Explanation 1:
The only way to evaluate the expression is:
=> (T|F) = T

Input 2:
A = "T^T^F"

Output 2:
0

Explanation 2:
There is no way to evaluate A to a true statement.
 */
class EvaluateExpressionToTrueBooleanParenthesization {
    fun cnttrue(s: String): Int {
        return solve(s, 0, s.length, true)
    }

    private fun solve(s: String, i: Int, j: Int, isTrue: Boolean): Int {
        if (i > j) return 0
        if (i == j) {
            return if (s[i] == 'T' && isTrue) 1
            else if (s[i] == 'F' && !isTrue) 1
            else 0
        }
        var ans = 0
        for (k in i + 1 until j step 2) {
            val c = s[k]
            val leftTrue = solve(s, i, k-1, true)
            val leftFalse = solve(s, i, k-1, false)
            val rightTrue = solve(s, k + 1, j, true)
            val rightFalse = solve(s, k + 1, j, false)

            when (c) {
                '&' -> {
                    ans += if (isTrue) {
                        leftTrue * rightTrue
                    } else {
                        leftFalse * rightTrue + leftTrue * rightFalse + leftFalse * rightFalse
                    }
                }
                '|' -> {
                    ans += if (isTrue) {
                        leftFalse * rightTrue + leftTrue * rightFalse + leftTrue * rightTrue
                    } else {
                        leftFalse * rightFalse
                    }
                }
                '^' -> {
                    ans += if (isTrue) {
                        leftFalse * rightTrue + leftTrue * rightFalse
                    } else {
                        leftFalse * rightFalse + leftTrue * rightTrue
                    }
                }
            }
        }
        return ans
    }
}

fun main() {
    println(EvaluateExpressionToTrueBooleanParenthesization().cnttrue("T|F"))
}