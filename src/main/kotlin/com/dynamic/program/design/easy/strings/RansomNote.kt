package com.dynamic.program.design.easy.strings

/**
 * https://leetcode.com/problems/ransom-note/description/
 *
 * 383. Ransom Note
 * Easy
 * 4.1K
 * 425
 * company
 * Amazon
 * company
 * Adobe
 * company
 * Spotify
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 *
 *
 * Example 1:
 *
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * Example 2:
 *
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * Example 3:
 *
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */
class RansomNote {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val mCnt = IntArray(27)
        for(c in magazine) {
            mCnt[c - 'a']++
        }
        for(c in ransomNote) {
            mCnt[c - 'a']--
            if(mCnt[c - 'a'] < 0) return false
        }
        return true
    }
}
