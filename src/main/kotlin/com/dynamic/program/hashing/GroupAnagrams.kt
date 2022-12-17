package com.dynamic.program.hashing

/**
 * Group Anagrams
 *
 * Given an array of strings strs, group the anagrams together.
 *
 * For example, given strs = ["eat","tea","tan","ate","nat","bat"], return [["bat"],["nat","tan"],["ate","eat","tea"]].
 *
 * time complexity of O(n⋅m⋅logm)
 */

fun anagrams(list: List<String>): List<List<String>> {
    val map = hashMapOf<String, MutableList<String>>()
    list.forEach { str ->
        val s = str.toCharArray().apply { sort() }.joinToString("")
        if (map.containsKey(s)) {
            map[s]?.add(str)
        } else {
            map[s] = mutableListOf(str)
        }
    }
    return map.values.toList()
}

fun main() {
    println(anagrams(listOf("eat","tea","tan","ate","nat","bat")))
}
