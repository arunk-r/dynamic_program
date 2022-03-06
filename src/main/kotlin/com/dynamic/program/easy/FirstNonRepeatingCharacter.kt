package com.dynamic.program.easy

/**
 * 
Write a function that takes in a string of lowercase English-alphabet letters
and returns the index of the string's first non-repeating character.


The first non-repeating character is the first character in a string that
occurs only once.


If the input string doesn't have any non-repeating characters, your function
should return -1.

Sample Input
string = "abcdcaf"

Sample Output
1 // The first non-repeating character is "b" and is found at index 1.


 */

fun firstNonRepeatingCharacter(string: String): Int {
    // Write your code here.
    val linkedHashMap = linkedMapOf<String, Int>()
    for(s in string.indices) {
        var dup = false
        for(c in string.indices) {
            if (string[s] == string[c] && s != c) {
                dup = true
            }
        }
        if (!dup) return s
    }
    return -1
}