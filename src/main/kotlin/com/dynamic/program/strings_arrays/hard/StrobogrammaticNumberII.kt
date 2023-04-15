package com.dynamic.program.strings_arrays.hard

/**
 * 247. Strobogrammatic Number II
Medium

company
Facebook
company
Uber
company
Google
Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).



Example 1:

Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]


Constraints:

1 <= n <= 14
 */
class StrobogrammaticNumberII {
    val pairs = listOf(Pair('0', '0'), Pair('1', '1'), Pair('6', '9'), Pair('8', '8'), Pair('9', '6'))
    fun findStrobogrammatic(n: Int): List<String> {
        return strobogrammaticCombination(n, n)
    }

    private fun strobogrammaticCombination(n: Int, target: Int): MutableList<String> {
        if (n == 0) {
            return mutableListOf("")
        }

        if (n == 1) {
            return mutableListOf("0", "1", "8")
        }

        val currentLst = strobogrammaticCombination(n - 2, target)
        val newLst = mutableListOf<String>()

        for (cur in currentLst) {
            pairs.forEach { (f, l) ->
                if (f != '0' || n != target) {
                    newLst.add("$f$cur$l")
                }
            }
        }
        return newLst
    }
}

fun main() {
    println(StrobogrammaticNumberII().findStrobogrammatic(2))
}
