package com.dynamic.program.strings_arrays

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 * hint: You must do this by modifying the input array in-place with O(1) extra memory.
 * time - O(n/2) -> O(n)
 */
fun reverseString(s: CharArray) {
    var i = 0
    var j = s.size - 1
    var c:Char
    while(i < j) {
        c = s[i]
        s[i] = s[j]
        s[j] = c
        i++
        j--
    }
}

fun main() {
    val c = charArrayOf('h','e','l','l','o')
    reverseString(c)
    println(c)
}
