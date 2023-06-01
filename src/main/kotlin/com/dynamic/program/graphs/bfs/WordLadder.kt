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
        val wordSet = hashSetOf<String>()
        wordSet.addAll(wordList)
        if (!wordSet.contains(endWord)) return 0
        val seen = hashSetOf<String>()
        seen.add(beginWord)

        val q = ArrayDeque<Pair<String, Int>>()
        q.addLast(Pair(beginWord, 1))
        val map = hashMapOf<String, MutableList<String>>()
        while(q.isNotEmpty()) {
            for (i in q.size-1 downTo 0) {
                val (word, cnt) = q.removeFirst()
                map.putIfAbsent(word, mutableListOf())
                val combinations = getList(word, wordSet, seen)
                combinations.forEach { w ->
                    map[word]!!.add(w)
                    map.putIfAbsent(w, mutableListOf())
                    map[w]!!.add(word)
                    q.addLast(Pair(w, cnt + 1))
                    seen.add(w)
                    if (w == endWord){
                        seen.remove(w)
                    }
                }
            }
        }
        println(map)
        val cur = mutableListOf<String>()
        val seen1 = hashSetOf<String>()
        cur.add(endWord)
        seen1.add(endWord)
        dfs(endWord, beginWord, map, cur, seen1)
        println(result)
        return 0
    }
    val result = mutableListOf<MutableList<String>>()
    private fun dfs(begin: String, end: String, map: HashMap<String, MutableList<String>>, cur: MutableList<String>, seen: HashSet<String>) {
        if (begin == end) {
            result.add(cur.toMutableList().reversed().toMutableList())
            return
        }
        map[begin]?.forEach {word ->
            if (!seen.contains(word)) {
                seen.add(word)
                cur.add(word)
                dfs(word, end, map, cur, seen)
                seen.remove(word)
                cur.removeAt(cur.size -1)
            }
        }
    }

    private fun getList(word: String, set: HashSet<String>, seen: HashSet<String>): List<String> {
        val result = mutableListOf<String>()
        for(i in word.indices) {
            for(c in word[i]+1 .. 'z') {
                addToList(word, i, c, set, seen, result)
            }
            for(c in 'a' until word[i]) {
                addToList(word, i, c, set, seen, result)
            }
        }
        return result
    }

    private fun addToList(word: String, idx: Int, c: Char, set: HashSet<String>, seen: HashSet<String>, result: MutableList<String>) {
        val s = "${word.substring(0,idx)}$c${word.substring(idx+1,word.length)}"
        if (set.contains(s) && !seen.contains(s)) {
            result.add(s)
        }
    }
}

fun main() {
    println(WordLadder().ladderLength("hit", "cog", listOf("hot", "dot", "dog", "lot", "log", "cog")))
    println(WordLadder().ladderLength("a", "c", listOf("a", "b", "c")))
}
