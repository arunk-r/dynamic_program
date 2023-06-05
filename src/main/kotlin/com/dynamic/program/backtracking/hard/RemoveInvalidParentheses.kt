package com.dynamic.program.backtracking.hard

import java.util.Stack


/**
 * 301. Remove Invalid Parentheses
Hard

Bloomberg
Facebook
Amazon
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.



Example 1:

Input: s = "()())()"
Output: ["(())()","()()()"]
Example 2:

Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]
Example 3:

Input: s = ")("
Output: [""]


Constraints:

1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
 */
class RemoveInvalidParentheses {

    fun removeInvalidParentheses(s: String): List<String> {
        val q = ArrayDeque<String>()
        val seen = hashSetOf<String>()
        q.addLast(s)
        seen.add(s)

        val result = mutableListOf<String>()
        var found = false

        while(q.isNotEmpty()) {
            val str = q.removeFirst()
            if (validStr(str)) {
                result.add(str)
                found = true
            }

            if (found) continue

            for(i in str.indices) {
                if (str[i] != '(' && str[i] != ')') continue
                val newStr = "${str.substring(0,i)}${str.substring(i+1,str.length)}"
                if (!seen.contains(newStr)) {
                    q.addLast(newStr)
                    seen.add(newStr)
                }
            }

        }
        return result
    }

    fun validStr(s: String): Boolean {
        val stk = Stack<Char>()
        for(c in s) {
            if (c != '(' && c != ')') continue
            if (c == ')') {
                if (stk.isNotEmpty() && stk.peek() =='(') {
                    stk.pop()
                } else {
                    return false
                }
            } else {
                stk.push(c)
            }
        }

        return stk.isEmpty()
    }


    fun removeInvalidParentheses4(s: String): List<String> {
        val ans = mutableListOf<String>()
        remove(s, ans, 0, 0, charArrayOf('(', ')'))
        return ans
    }

    fun remove(s: String, ans: MutableList<String>, last_i: Int, last_j: Int, par: CharArray) {
        var stack = 0
        var i = last_i
        while (i < s.length) {
            if (s[i] == par[0]) stack++
            if (s[i] == par[1]) stack--
            if (stack >= 0) {
                ++i
                continue
            }
            for (j in last_j..i) if (s[j] == par[1] && (j == last_j || s[j - 1] != par[1])) remove(
                s.substring(
                    0,
                    j
                ) + s.substring(j + 1, s.length), ans, i, j, par
            )
            return
            ++i
        }
        val reversed = StringBuilder(s).reverse().toString()
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, charArrayOf(')', '(')) else  // finished right to left
            ans.add(reversed)
    }

    private val validExpressions: MutableSet<String> = HashSet()

    private fun recurse(s: String, index: Int, leftCount: Int, rightCount: Int, leftRem: Int, rightRem: Int, expression: StringBuilder) {

        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length) {
            if (leftRem == 0 && rightRem == 0) {
                validExpressions.add(expression.toString())
            }
        } else {
            val character = s[index]
            val length = expression.length

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if (character == '(' && leftRem > 0 || character == ')' && rightRem > 0) {
                recurse(
                    s,
                    index + 1,
                    leftCount,
                    rightCount,
                    leftRem - if (character == '(') 1 else 0,
                    rightRem - if (character == ')') 1 else 0,
                    expression
                )
            }
            expression.append(character)

            // Simply recurse one step further if the current character is not a parenthesis.
            if (character != '(' && character != ')') {
                recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression)
            } else if (character == '(') {

                // Consider an opening bracket.
                recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression)
            } else if (rightCount < leftCount) {

                // Consider a closing bracket.
                recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression)
            }

            // Delete for backtracking.
            expression.deleteCharAt(length)
        }
    }

    fun removeInvalidParentheses2(s: String): List<String> {
        var left = 0
        var right = 0

        // First, we find out the number of misplaced left and right parentheses.
        for (i in s.indices) {

            // Simply record the left one.
            if (s[i] == '(') {
                left++
            } else if (s[i] == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = if (left == 0) right + 1 else right

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = if (left > 0) left - 1 else left
            }
        }
        recurse(s, 0, 0, 0, left, right, StringBuilder())
        return validExpressions.toList()
    }

    fun removeInvalidParentheses1(s: String): List<String> {
        val lst = mutableSetOf<String>()
        var buf = StringBuffer()
        for (i in s.indices) {
            if (s[i] in listOf('(', ')')) {
                buf.append(s.substring(0, i))
                buf.append(s.substring(i + 1, s.length))
                if (valid(buf.toString())) {
                    lst.add(buf.toString())
                }
                buf = StringBuffer()
            }
        }
        if (lst.isEmpty()) {
            lst.add("")
        }
        return lst.toList()
    }

    fun valid(s: String): Boolean {
        var cnt = 0
        for (c in s) {
            if (c == '(') {
                cnt++
            } else if (cnt != 0 && c == ')') {
                cnt--
            } else if (cnt == 0 && c == ')') {
                return false
            }
        }
        return cnt == 0
    }
}

fun main() {
    println(RemoveInvalidParentheses().removeInvalidParentheses("n"))
    println(RemoveInvalidParentheses().removeInvalidParentheses("()())()"))
    println(RemoveInvalidParentheses().removeInvalidParentheses("(a)())()"))
}
