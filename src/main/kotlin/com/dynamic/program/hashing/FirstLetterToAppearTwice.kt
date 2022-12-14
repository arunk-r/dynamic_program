package com.dynamic.program.hashing

/**
 * Given a string s, return the first character to appear twice. It is guaranteed that the input will have a duplicate character.
 */

fun firstLetterAppearTwice(s: String): Char {
    val set = hashSetOf<Char>()
    for (c in s) {
        if (set.contains(c)) {
            return c
        }
        set.add(c)
    }
    return ' '
}

fun main() {
    println(firstLetterAppearTwice("abcdeda"))
}
