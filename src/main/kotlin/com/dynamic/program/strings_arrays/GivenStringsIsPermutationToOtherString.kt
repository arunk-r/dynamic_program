package com.dynamic.program.strings_arrays

/**
 * Solution 1 using sort both strings and compare -> best sort algorithm takes nlogn to sort string. so 2(nlogn) ~ O(nlogn).. more expensive
 *
 * Solution2 taking advantage of secondary datastore to keep track of character.
 * loop1 to collect data  O(n)
 * loop2 to validate data O(n)
 * ---------------------- O(n + n) = O(2n) ~ O(n)
 */
fun givenStringIsPermutationToOther(s1: String, s2: String): Boolean {
    if (s1.length != s2.length) return false
    if (s1 == s2) return true
    val map1 = mutableMapOf<Char, Int>()
    val map2 = mutableMapOf<Char, Int>()
    for (i in s1.indices) {
        map1.putIfAbsent(s1[i], 0)
        map1[s1[i]] = map1[s1[i]]?.plus(1)?:0

        map2.putIfAbsent(s2[i], 0)
        map2[s2[i]] = map2[s2[i]]?.plus(1)?:0
    }

    for (i in s2.indices) {
        if (map1[s1[i]] == map2[s1[i]]) {
            map1.remove(s1[i])
            map2.remove(s1[i])
        }
    }

    if (map1.isEmpty() && map2.isEmpty()) return true
    return false
}

fun main() {
    println(givenStringIsPermutationToOther("abadgdfg", "abadgdgk"))
    println(givenStringIsPermutationToOther("abadgdfg", "abadgdgf"))
}
