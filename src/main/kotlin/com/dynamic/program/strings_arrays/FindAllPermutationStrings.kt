package com.dynamic.program.strings_arrays

/**
 * Find all permutations in a given string
 * where is 's' is input string and 'b' is complete string
 */
private fun findAllPermutationsInAString(s: String, b: String): List<String> {
    val list = mutableListOf<String>()
    val matchValue = stringCount(s)
    val bLen = b.length
    for (idx in b.indices) {
        if ((idx + s.length) > b.length - 1) {
            break
        }
        val subStr = b.substring(idx, (idx + s.length))
        if (stringCount(subStr) == matchValue) {
            list.add(subStr)
        }
    }
    if (bLen % s.length != 0) {
        for (idx in b.length - 1 downTo 0) {
            if (idx - s.length < 0) {
                break
            }
            val subStr = b.substring(idx - s.length, idx)
            if (stringCount(subStr) == matchValue) {
                list.add(subStr)
            }
        }
    }
    return list
}

private fun stringCount(s: String): Int {
    return s.chars().sum()
}

fun main() {
    println(findAllPermutationsInAString("abbc", "cbabadcbbabbcbabaabccbabc"))
    println(findAllPermutationsInAString("ab", "cbabadcbbabbcbabaabccbab"))
}
