package com.dynamic.program.medium.group_anagrams

/**
 *  Anagrams are strings made up of exactly the same letters, where order doesn't matter. For example,
 *  cinema and iceman, foo and ofo
 */

fun groupAnagrams(words: List<String>): List<List<String>> {
    // Write your code here.
    val orderedMap = mutableMapOf<String, MutableList<String>>()
    for (idx in words.indices) {
        val sortedString = (words[idx].toCharArray().sorted().joinToString(""))
        val subList = orderedMap.getOrPut(sortedString) { mutableListOf(words[idx]) }
        if (!subList.contains(words[idx])) {
            subList.add(words[idx])
        }

    }
    return orderedMap.values.toList()
}

fun main() {
    println(groupAnagrams(listOf("yo", "act", "flop", "tac", "foo", "cat", "oy", "olfp")))
}