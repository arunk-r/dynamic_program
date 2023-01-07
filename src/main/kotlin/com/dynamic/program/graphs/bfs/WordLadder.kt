package com.dynamic.program.graphs.bfs

/**
 * Word Ladder
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * Example 1:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 *
 * Example 2:
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * Constraints:
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 *
 */
class WordLadder {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        val nWordLst = HashSet(wordList)
        val q = ArrayDeque<Pair<String, Int>>()
        q.addLast(Pair(beginWord,1))
        val seen = hashSetOf<String>()
        seen.add(beginWord)
        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            val lst = wordList(cur.first, nWordLst)
            lst.forEach { n ->
                if (!seen.contains(n)) {
                    seen.add(n)
                    q.addLast(Pair(n, cur.second+1))
                    if (n == endWord) return cur.second+1
                }
            }
        }
        return 0
    }

    private fun wordList(w: String, wordList: Set<String>): List<String> {
        val lst = mutableListOf<String>()
        for (i in w.indices) {
            for (nChr in w[i] + 1..'z') {
                val v = "${w.substring(0, i)}$nChr${w.substring(i + 1)}"
                if (wordList.contains(v)) {
                    lst.add(v)
                }
            }
            for (nChr in 'a' until w[i]) {
                val v = "${w.substring(0, i)}$nChr${w.substring(i + 1)}"
                if (wordList.contains(v)) {
                    lst.add(v)
                }
            }
        }
        return lst
    }
}

fun main() {
    println(WordLadder().ladderLength("hit", "cog", listOf("hot", "dot", "dog", "lot", "log", "cog")))
    println(WordLadder().ladderLength("a", "c", listOf("a", "b", "c")))
}