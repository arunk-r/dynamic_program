package com.dynamic.program.easy

/**
 * 
Given a non-empty string of lowercase letters and a non-negative integer
representing a key, write a function that returns a new string obtained by
shifting every letter in the input string by k positions in the alphabet,
where k is the key.


Note that letters should "wrap" around the alphabet; in other words, the
letter z shifted by one returns the letter a.

Sample Input
string = "xyz"
key = 2

Sample Output
"zab"


 */
fun caesarCipherEncryptor(string: String, key: Int): String {
    // Write your code here.
    var newString = ""
    val nk = key % 26
    for(s in string) {
        newString += getNewLetter(s, nk)
    }
    return newString
}

fun getNewLetter(s: Char, key: Int): Char {
    val nc = s.toInt() + key
    return if (nc <= 122) nc.toChar() else (96 + nc % 122).toChar()
}