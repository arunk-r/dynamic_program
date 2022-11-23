package com.dynamic.program.strings_arrays

/**
 * Implement a method to perform basic string compression using the counts of repeated characters.
 * For Ex: aabcccddaa -> a2b1c3d2a2
 *
 * And return the original string using compressed string. Your method should return the original string.
 *
 * Assume the string contains only upper and lowe cases letters
 */

fun stringCompression(s: String): String {
    println("Original String: $s")
    var buf = StringBuffer()
    var i=0
    while (i<s.length) {
        val c = s[i++]
        var cnt = 1
        while (i<s.length && s[i] == c) {
            cnt++
            i++
        }
        buf.append(c.toString()+cnt.toString())
    }
    val cStr = buf.toString()
    println("Compressed String: $cStr")
    buf = StringBuffer()
    i = 0
    while (i<cStr.length ) {
        val c = cStr[i]
        i+=1
        var j = 0
        val nextInt = nextInt(cStr, i)
        i += nextInt.length
        while (j<(nextInt).toInt()) {
            buf.append(c)
            j++
        }

    }
    return buf.toString()
}

fun nextInt(s: String, idx: Int): String {
    val buffer = StringBuffer()
    var i = idx
    while (i<s.length && s[i].isDigit()) {
        buffer.append(s[i++])
    }
    return buffer.toString()
}

fun main() {
    println(stringCompression("aabcccddaa"))
    println(stringCompression("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
}
