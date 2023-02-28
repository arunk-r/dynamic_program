package com.dynamic.program.backtracking.medium

/**
 * 22. Generate Parentheses
Medium
company
Amazon
company
Adobe
company
Apple
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.



Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]


Constraints:

1 <= n <= 8
 */
class GenerateParentheses {
    fun generateParenthesis(n: Int): List<String> {
        val result = mutableListOf<String>()
        backtracking(result, "", 0, 0, n)
        return result
    }

    private fun backtracking(result: MutableList<String>, str: String, openCnt: Int, closeCnt: Int, max: Int) {
        if (str.length == max*2) {
            result.add(str)
        } else {
            if (openCnt < max) {
                backtracking(result, "$str(", openCnt+1, closeCnt, max)
            }
            if (closeCnt < openCnt) {
                backtracking(result, "$str)", openCnt, closeCnt+1, max)
            }
        }
    }

    fun generateParenthesis1(n: Int): List<String> {
        var result = mutableListOf<String>()
        for(i in 0 until n) {
            result = getCombination(result)
        }

        return result
    }

    private fun getCombination(lst: MutableList<String>): MutableList<String> {
        if(lst.isEmpty()) return mutableListOf("()")

        val set = hashSetOf<String>()
        for(s in lst) {
            for(i in s.indices) {
                set.add("${s.substring(0,i)}()${s.substring(i)}")
            }
        }
        return set.toMutableList()
    }
}

fun main() {
    println(GenerateParentheses().generateParenthesis(3))
}
