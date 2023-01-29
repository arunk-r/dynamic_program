package com.dynamic.program.backtracking

/**
 * Generate Parentheses
 */
class GenerateParentheses {
    fun generateParenthesis1(n: Int): List<String> {
        val i = intArrayOf()
        i[0]
        i.joinToString("").toInt()
        var result = mutableSetOf<String>("")
        val input = "()"
        for (i in 0 until n) {
            val lst = mutableSetOf<String>()
            result.forEach{ s ->
                if (s.isEmpty()){
                    lst.add(input)
                }
                else {
                    for( c in s.indices) {
                        lst.add("${s.substring(0,c)}$input${s.substring(c)}")
                    }
                }
            }
            result = lst
        }
        return result.toList()
    }

    fun generateParenthesis(n: Int): List<String> {
        var result = mutableSetOf<String>()
        backtrack(n,0,0,"", result)
        return result.toList()
    }

    fun backtrack(n: Int, open: Int, close: Int, s: String, result: MutableSet<String>) {
        if (open == n && close == n) {
            result.add(s)
            return
        }

        if(open < n) {
            backtrack(n, open+1, close, "$s(", result)
        }
        if(close < open) {
            backtrack(n, open, close+1, "$s)", result)
        }
    }
}