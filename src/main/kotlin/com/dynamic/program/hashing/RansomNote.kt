package com.dynamic.program.hashing

/**
 * Ransom Note
 *
 * Solution
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 * Each letter in magazine can only be used once in ransomNote.
 *
 * Example 1:
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 *
 * Example 2:
 *Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 *
 * Example 3:
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 * Constraints:
 * 1 <= ransomNote.length, magazine.length <= 10^5
 * ransomNote and magazine consist of lowercase English letters.
 */

fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val rMap = hashMapOf<Char, Int>()
    ransomNote.forEach{ c->
        rMap[c] = rMap[c]?.plus(1) ?: 1
    }
    magazine.forEach{ c->
        if (rMap.containsKey(c)) {
            rMap[c] = rMap[c]?.minus(1) ?: 0
            if(rMap[c] == 0) {
                rMap.remove(c)
            }
        }
    }

    return rMap.isEmpty()
}

fun main() {
    println(canConstruct("a", "b"))
    println(canConstruct("aa", "ab"))
    println(canConstruct("aa", "aab"))
}
