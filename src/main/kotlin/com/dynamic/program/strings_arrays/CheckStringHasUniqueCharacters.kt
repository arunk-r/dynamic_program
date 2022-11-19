package com.dynamic.program.strings_arrays

/**
 * without using any addition spaces
 */

fun isUniqueNPow2(s: String): Boolean {
    for (i in s.indices) {
        for (j in i until s.length) {
            if (i == j) continue
            else {
                if (s[i].code == s[j].code) return false
            }
        }
    }
    return true
}

/**
 * using additional space
 *  only if string characters are ASCII
 */

fun isUniqueN(s: String): Boolean {
    val ref = MutableList(128) { false }
    for (i in s.indices) {
        if (ref[s[i].code]) {
            return false
        } else {
            ref[s[i].code] = true
        }
    }
    return true
}


fun main() {
    println(isUniqueNPow2("abca"))
    println(isUniqueN("abca"))
}
