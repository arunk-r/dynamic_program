package com.dynamic.program.dp

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 *
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
class WordBreak {
    data class Trie(val c: Char, val map: HashMap<Char, Trie> = hashMapOf(), var end: Boolean = false)
    val root = Trie(' ')

    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        buildTrie(wordDict)
        return find(0, s, root)
    }

    fun find(idx: Int, s: String, node: Trie): Boolean {
        if(idx == s.length) return true
        val d = node.map[s[idx]]
        return if(d == null) false
        else {
            if(d.end) {
                find(idx+1, s, root)
            } else {
                find(idx+1, s, d)
            }
        }
    }

    private fun buildTrie(dict: List<String>) {
        for(word in dict) {
            var node = root
            for(c in word) {
                var d = node.map[c]
                if(d == null) {
                    d = Trie(c)
                    node.map[c] = d
                }
                node = d
            }
            node.end = true
        }
    }

    fun wordBreak1(s: String, wordDict: List<String>): Boolean {
        val set = hashSetOf<String>()
        set.addAll(wordDict)
        val dp = BooleanArray(s.length + 1)
        dp[0] = true
        for (i in 1 .. s.length) {
            for (j in 0 until i) {
                if (dp[j] &&
                    set.contains(s.substring(j, i))
                ) {
                    dp[i] = true
                    break
                }
            }
        }
        return dp[s.length]
    }
}

fun main() {
    println(WordBreak().wordBreak("aaaaaaa", listOf("aaaa", "aa")))
}
