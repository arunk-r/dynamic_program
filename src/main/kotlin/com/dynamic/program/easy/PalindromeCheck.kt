package com.dynamic.program.easy

/**
 * 
Write a function that takes in a non-empty string and that returns a boolean
representing whether the string is a palindrome.


A palindrome is defined as a string that's written the same forward and
backward. Note that single-character strings are palindromes.

Sample Input
string = "abcdcba"

Sample Output
true // it's written the same forward and backward


 */
fun isPalindrome(string: String): Boolean {
    // Write your code here.
    var min = 0
    var max = string.length - 1
    while (min < max) {
        if (string[min] != string[max]) {
            return false
        }
        min++
        max--
    }
    return true
}
