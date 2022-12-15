package com.dynamic.program.hashing

/**
 * Check if All Characters Have Equal Number of Occurrences
 *
 * Given a string s, determine if all characters have the same frequency.
 *
 * For example, given s = "abacbc", return true. All characters appear twice. Given s = "aaabb", return false. "a" appears 3 times, "b" appears 2 times. 3 != 2.
 */

fun occurrences(s: String): Boolean {
    val map = hashMapOf<Char, Int>()
    s.forEach { c ->
        map[c] = map[c]?.plus(1) ?: 1
    }
    // regular map
    /*val values = map.values
    val mapData = values.first()
    values.forEach { v -> if (v != mapData) return false }
    return true*/

    // more advanced
    return HashSet(map.values).size == 1
}

fun main() {
    println(occurrences("abacbc"))
    println(occurrences("aaabb"))
}
