package com.dynamic.program.strings_arrays

/**
 * brute force method string reverse and check.
 * time O(n)
 *
 * use 2 pointers to convince you know the other method as well
 * time O(n/2) ~ O(n)
 */
fun palindrome(s: String): Boolean {
    var st = 0
    var ed = s.length - 1
    while (st <= ed) {
        if (s[st] != s[ed]) {
            return false
        }
        st++
        ed--
    }
    return true
}

fun main() {
    println(palindrome("racecar"))
    println(palindrome("madam"))
    println(palindrome("i am crazy"))
}