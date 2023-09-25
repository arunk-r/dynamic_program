package com.dynamic.program.top.hundred.medium

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/description/?envType=study-plan-v2&envId=premium-algo-100
 *
 * 247. Strobogrammatic Number II
 * Medium
 * 877
 * 232
 * company
 * Facebook
 * company
 * Uber
 * company
 * Google
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: ["11","69","88","96"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["0","1","8"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 14
 */
class StrobogrammaticNumberII {
    val pairs = listOf(Pair('0', '0'), Pair('1', '1'), Pair('6', '9'), Pair('8', '8'), Pair('9', '6'))
    fun findStrobogrammatic(n: Int): List<String> {
        return findStrobogrammatic(n, n)
    }

    private fun findStrobogrammatic(n: Int, target: Int): MutableList<String> {
        if (n <= 0) return mutableListOf("")
        else if (n == 1) return mutableListOf("0", "1", "8")
        else {
            val curLst = findStrobogrammatic(n-2, target)
            val newLst = mutableListOf<String>()
            curLst.forEach { cur ->
                pairs.forEach{ (f, l) ->
                    if(f != '0' || n != target) {
                        newLst.add("$f$cur$l")
                    }
                }
            }
            return newLst
        }
    }
}
