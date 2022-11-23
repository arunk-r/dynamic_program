package com.dynamic.program.strings_arrays

import kotlin.math.abs

/**
 * OneAway: There are 3 types of edits that can be performed on string
 * 1. Insert a character
 * 2. Remove a character
 * 3. Replace a character
 *
 * Given two strings, write a function to check if they are one edit away.
 */

fun editAway(s1: String, s2: String): Boolean {
    var numEdits = 0
    if (s1 == s2) return false
    if (abs(s1.length - s2.length) > 1) {
        return false
    }


    val d = swap(s1, s2)
    val lgStr = d.first
    val smStr = d.second
    var i = smStr.length - 1
    var j = lgStr.length - 1
    while (i >= 0 && j >= 0) {
        if (smStr[i] == lgStr[j]) {
            if (i > 0) {
                i--
            }
            if (j > 0) {
                j--
            }
        } else {
            val idx = findNextMatch(lgStr[j], smStr, i)
            if (idx == -1) {
                j--
                numEdits++
            } else if (j - idx == 1) {
                j = idx - 1
                i--
            } else {
                return false
            }
        }
        if (i == 0 && j == 0) {
            if (smStr[i] != lgStr[j]) {
                numEdits++
            }
            break
        }
    }
    if (numEdits == 1) return true
    return false
}

fun swap(s1: String, s2: String): Pair<String, String> {
    return if (s1.length > s2.length) {
        Pair(s1, s2)
    } else {
        Pair(s2, s1)
    }
}

fun findNextMatch(c: Char, s: String, idx: Int): Int {
    for (i in idx downTo 0) {
        if (c == s[i]) {
            return i
        }
    }
    return -1
}

fun main() {
    println(editAway("pale", "ple"))
    println(editAway("pales", "pale"))
    println(editAway("pale", "bale"))
    println(editAway("pale", "bake"))
    println(editAway("pale", "plee"))
}
