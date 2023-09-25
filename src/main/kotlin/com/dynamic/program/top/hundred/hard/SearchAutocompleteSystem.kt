package com.dynamic.program.top.hundred.hard

import java.util.TreeSet

/**
 * https://leetcode.com/problems/design-search-autocomplete-system/?envType=study-plan-v2&envId=premium-algo-100
 * 642. Design Search Autocomplete System
 * Hard
 * Topics
 * Companies
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Implement the AutocompleteSystem class:
 *
 * AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 * List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 * Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.
 *
 *
 * Example 1:
 *
 * Input
 * ["AutocompleteSystem", "input", "input", "input", "input"]
 * [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 * Output
 * [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *
 * Explanation
 * AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 * obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 * obj.input("a"); // return []. There are no sentences that have prefix "i a".
 * obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 *
 * Constraints:
 *
 * n == sentences.length
 * n == times.length
 * 1 <= n <= 100
 * 1 <= sentences[i].length <= 100
 * 1 <= times[i] <= 50
 * c is a lowercase English letter, a hash '#', or space ' '.
 * Each tested sentence will be a sequence of characters c that end with the character '#'.
 * Each tested sentence will have a length in the range [1, 200].
 * The words in each input sentence are separated by single spaces.
 * At most 5000 calls will be made to input.
 */
class SearchAutocompleteSystem(sentences: Array<String>, times: IntArray) {
    data class Data(val t: Int, val s: String)
    data class Sentence(
            val set: TreeSet<Data> = TreeSet<Data> { x, y -> if (x.t == y.t) x.s.compareTo(y.s) else y.t - x.t },
            val graph: HashMap<Char, Sentence> = HashMap<Char, Sentence>()
    )

    private val root = Sentence()
    private var current = root
    private var currentStr = ""
    private val rankMap = HashMap<String, Data>()

    init {
        for (i in times.indices) {
            val sentence = sentences[i]
            val rank = times[i]
            constructGraph(sentence, rank)
        }
    }

    private fun constructGraph(sentence: String, rank: Int) {
        val data = rankMap[sentence]
        if (data != null) {
            rankMap[sentence] = Data(data.t + rank, sentence)
        } else {
            rankMap[sentence] = Data(rank, sentence)
        }

        var node = root
        for (c in sentence) {
            var d = node.graph[c]
            if (d == null) {
                d = Sentence()
                node.graph[c] = d
            }
            if (data != null && d.set.contains(data)) {
                d.set.remove(data)
            }
            d.set.add(Data(rank, sentence))
            node = d
        }
    }

    fun input(c: Char): List<String> {
        if (c == '#') {
            constructGraph(currentStr, (rankMap[currentStr]?.t ?: 0) + 1)
            current = root
            currentStr = ""
            return listOf()
        }
        currentStr = "$currentStr$c"
        current = current.graph[c] ?: return listOf()
        var idx = 0
        val result = mutableListOf<String>()
        for (data in current.set) {
            result.add(data.s)
            idx++
            if (idx == 3) {
                return result
            }
        }

        return result
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * var obj = AutocompleteSystem(sentences, times)
 * var param_1 = obj.input(c)
 */


fun main() {
    val sas = SearchAutocompleteSystem(arrayOf("i love you", "island", "iroman", "i love leetcode"), intArrayOf(5, 3, 2, 2))
    println(sas.input('i'))
    println(sas.input(' '))
    println(sas.input('a'))
    println(sas.input('#'))
    println(sas.input('i'))
    println(sas.input(' '))
    println(sas.input('a'))
    println(sas.input('#'))
    println(sas.input('i'))
    println(sas.input(' '))
    println(sas.input('a'))
    println(sas.input('#'))
}
