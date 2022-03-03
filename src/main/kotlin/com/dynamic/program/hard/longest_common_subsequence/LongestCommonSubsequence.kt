package com.dynamic.program.hard.longest_common_subsequence

/**
 * Write a function that takes in two strings and returns their longest common subsequence.
 *
 * A subsequence of a string is a set of characters that aren't necessarily
 * adjacent in the string but that are in the same order as they appear in the
 * string. For instance, the characters ["a", "c", "d"] form a
 * subsequence of the string "abcd", and so do the characters
 * ["b", "d"]. Note that a single character in a string and the
 * string itself are both valid subsequences of the string.
 *
 * You can assume that there will only be one longest common subsequence.
 *
 * Sample Input
 * str1 = "ZXVVYZW"
 * str2 = "XKYKZPW"
 *
 * Sample Output
 * ["X", "Y", "Z", "W"]
 */
fun longestCommonSubsequence(str1: String, str2: String): List<Char> {
    // Write your code here.
    //construct 2d array and if row and column values are same then take diagonal value and str2[row -1]
    //result[str2.length][str1.length] is the output value
    val lcs = MutableList(str2.length + 1) { MutableList(str1.length + 1) { "" } }

    for (i in 1..str2.length) {
        for (j in 1..str1.length) {
            if (str2[i - 1] == str1[j - 1]) {
                lcs[i][j] = lcs[i - 1][j - 1] + str2[i - 1]
            } else {
                lcs[i][j] = if (lcs[i - 1][j].length > lcs[i][j - 1].length) lcs[i - 1][j] else lcs[i][j - 1]
            }
            //println(lcs)
        }
    }
    return lcs[str2.length][str1.length].toList()
}

fun main() {
    println(longestCommonSubsequence("ZXVVYZW", "XKYKZPW"))
}
