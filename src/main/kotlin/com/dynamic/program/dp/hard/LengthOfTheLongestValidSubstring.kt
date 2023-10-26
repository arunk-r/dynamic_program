package com.dynamic.program.dp.hard


/**
 * https://leetcode.com/problems/length-of-the-longest-valid-substring/description/
 * 2781. Length of the Longest Valid Substring
 * Hard
 * 429
 * 6
 * company
 * Amazon
 * You are given a string word and an array of strings forbidden.
 *
 * A string is called valid if none of its substrings are present in forbidden.
 *
 * Return the length of the longest valid substring of the string word.
 *
 * A substring is a contiguous sequence of characters in a string, possibly empty.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" and "aabc". The length of the longest valid substring is 4.
 * It can be shown that all other substrings contain either "aaa" or "cb" as a substring.
 * Example 2:
 *
 * Input: word = "leetcode", forbidden = ["de","le","e"]
 * Output: 4
 * Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid substring is 4.
 * It can be shown that all other substrings contain either "de", "le", or "e" as a substring.
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 105
 * word consists only of lowercase English letters.
 * 1 <= forbidden.length <= 105
 * 1 <= forbidden[i].length <= 10
 * forbidden[i] consists only of lowercase English letters.
 */
class LengthOfTheLongestValidSubstring {
    fun longestValidSubstring(word: String, forbidden: List<String>): Int {
        val set: MutableSet<String> = HashSet()
        var max = 0
        for (s in forbidden) {
            max = maxOf(max, s.length)
            set.add(s)
        }
        var ans = 0
        val sb = StringBuilder()
        for (ch in word) {
            sb.append(ch)
            val len = sb.length
            for (j in len - 1 downTo maxOf(0, len - max)) {
                val s = sb.substring(j)
                if (set.contains(s)) {
                    sb.delete(0, j + 1)
                    break
                }
            }
            ans = maxOf(sb.length, ans)
        }
        return ans
    }

    data class Trie(val map: HashMap<Char, Trie> = HashMap(), var word: Boolean = false)
    val root = Trie()
    var max = 0
    fun longestValidSubstring1(word: String, forbidden: List<String>): Int {
        val set = mutableSetOf<String>()
        val maxWordLen = buildTrie(forbidden)
        combination(word, set, maxWordLen)
        set.forEach{ w ->
            checkForForbidden(w, 0, root, "")
        }
        return max
    }

    private fun checkForForbidden(s: String, idx: Int, node: Trie, curStr: String) {
        if(idx < s.length) {
            max = maxOf(max, curStr.length)
            val next = node.map[s[idx]]
            if(next != null && next.word) {
                checkForForbidden(s, idx+1, root, "")
            } else {
                checkForForbidden(s, idx+1, root, curStr+s[idx])
            }
        }
    }

    private fun buildTrie(forbidden: List<String>): Int{
        var maxWordLen = 0
        for(word in forbidden) {
            maxWordLen = maxOf(maxWordLen, word.length)
            var node = root
            for(c in word) {
                val v = node.map[c]
                if(v == null) {
                    node.map[c] = Trie()
                }
                node = node.map[c]!!
            }
            node.word = true
        }
        return maxWordLen
    }

    private fun combination(s: String, result: MutableSet<String>, maxWordLen: Int) {
        var step = 1
        for(i in 0 until s.length) {
            var idx = 0
            while(idx + step < s.length && step <= maxWordLen) {
                result.add(s.substring(idx, idx + step))
                idx++
            }
            result.add(s.substring(idx))
            step++
        }
    }
}

fun main() {
    println(LengthOfTheLongestValidSubstring().longestValidSubstring("cbaaaabc", listOf("aaa","cb")))
}
