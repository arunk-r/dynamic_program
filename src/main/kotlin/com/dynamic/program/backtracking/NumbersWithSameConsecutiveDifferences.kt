package com.dynamic.program.backtracking

/**
 * Numbers With Same Consecutive Differences
 * Given two integers n and k, return an array of all the integers of length n where the
 * difference between every two consecutive digits is k. You may return the answer in any order.
 * Note that the integers should not have leading zeros. Integers as 02 and 043 are not allowed.
 *
 *
 * Example 1:
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 *
 *
 * Example 2:
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Constraints:
 * 2 <= n <= 9
 * 0 <= k <= 9
 */
class NumbersWithSameConsecutiveDifferences {
    fun numsSameConsecDiff(n: Int, k: Int): IntArray {
        val lst = mutableListOf<IntArray>()
        for (i in 1 until 10) {
            val v = IntArray(n)
            v[0] = i
            lst.add(v)
        }
        val fnl = mutableListOf<Int>()
        backtrack(1,n-1,k, lst, fnl)
        return fnl.toIntArray()
    }

    fun backtrack(idx: Int, n: Int, k: Int, result: MutableList<IntArray>, fnl: MutableList<Int>) {
        if (n == 0 ) return
        val lst = mutableListOf<IntArray>()
        for (i in 0 until 10) {
            result.forEach{ r ->
                val nr = r.copyOf()
                if (Math.abs(nr[idx - 1] - i) == k) {
                    nr[idx] = i
                    lst.add(nr)
                    if (n == 1 && nr[0] != 0) {
                        fnl.add(nr.joinToString("").toInt())
                    }
                }
            }
        }
        backtrack(idx+1, n-1,k, lst, fnl)
    }
}

fun main() {
    println(NumbersWithSameConsecutiveDifferences().numsSameConsecDiff(3,1).toMutableList())
}