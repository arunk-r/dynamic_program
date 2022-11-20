package com.dynamic.program.strings_arrays

/**
 * Write a method to replace all spaces with '%20'. You am assume that the string has sufficient space at the end to hold the additional
 * characters, and that you are given the string true length.
 *
 * time = O(n)
 *
 * In this example, I am joining Array<Char> to string for easy output. (this is not part of the problem definition)
 */
fun urlify(s: String, tLen: Int): String?{
    val output = Array<Char>(s.length) {' '}
    if (s.length == tLen) return null
    var checkEmpty = true
    var aIdx = s.length -1

    for (i in s.length -1 downTo 0) {
        if (checkEmpty && s[i] == ' ') continue
        if (aIdx < 0) return null
        checkEmpty = false

        if (s[i] == ' ') {
            fillEmpty(output, aIdx)
            aIdx -= 3
        }else {
            output[aIdx--] = s[i]
        }
    }

    //just for friendly out - not part of problem statement.
    return output.joinToString("")
}

fun fillEmpty(a:Array<Char>, aIdx: Int) {
    var v = aIdx
    a[v--] = '0'
    a[v--] = '2'
    a[v] = '%'
}

fun main() {
    println(urlify("Mr John Smith   ", 13))
}
