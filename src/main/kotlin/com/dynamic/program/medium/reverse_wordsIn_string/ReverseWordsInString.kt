package com.dynamic.program.medium.reverse_wordsIn_string

fun reverseWordsInString(string: String): String {
    // Write your code here.
    val words = mutableListOf<String>()
    var startOfWord = 0

    for (idx in string.indices) {
        val character = string[idx]

        if (character == ' ') {
            words.add(string.substring(startOfWord, idx))
            startOfWord = idx
        } else if (string[startOfWord] == ' ') {
            words.add(" ")
            startOfWord = idx
        }
    }
    words.add(string.substring(startOfWord))
    reverseList(words)
    return words.joinToString("")
}

fun reverseList(list: MutableList<String>) {
    var startIdx = 0
    var lastIdx = list.size -1
    while (startIdx <= lastIdx) {
        val tmp = list[startIdx]
        list[startIdx] = list[lastIdx]
        list[lastIdx] = tmp

        startIdx++
        lastIdx--
    }
}

fun main() {
    println(reverseWordsInString("This is Arun's simple   test!"))
    println(reverseWordsInString(reverseWordsInString("This is Arun's simple   test!")))
}