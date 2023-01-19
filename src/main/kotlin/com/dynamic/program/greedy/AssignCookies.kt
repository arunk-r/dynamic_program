package com.dynamic.program.greedy

import java.util.Arrays
import java.util.TreeSet

/**
 * Assign Cookies
 */
class AssignCookies {
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        Arrays.sort(g)
        Arrays.sort(s)
        var sIdx = 0
        var gIdx = 0
        var cnt = 0
        while(sIdx < s.size && gIdx < g.size) {
            if(s[sIdx] < g[gIdx]) {
                sIdx++
            } else {
                sIdx++
                gIdx++
                cnt++
            }
        }

        return cnt
    }
}

fun main() {
    println(AssignCookies().findContentChildren(intArrayOf(1,2,3), intArrayOf(1,2)))
}