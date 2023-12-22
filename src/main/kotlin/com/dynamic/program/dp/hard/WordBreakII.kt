package com.dynamic.program.dp.hard

class WordBreakII {
    data class Trie(val nodes: HashMap<Char, Trie> = hashMapOf(), var word: String = "")
    val root = Trie()
    val result = mutableListOf<String>()
    fun wordBreak(s: String, wordDict: List<String>): List<String> {
        // trie creation
        for(word in wordDict) { //each word
            var node = root
            for(c in word) { // each char
                var nn = node.nodes[c] // find in node
                if(nn == null) { // not found
                    nn = Trie() // create
                    node.nodes[c] = nn // add to map
                }
                node = nn // move to down
            }
            node.word = word // attach word
        }

        findAllCombination(0, s, "", root)
        return result
    }

    private fun findAllCombination(idx: Int, s: String, cur: String, node: Trie) {
        println("$idx, $cur")
        if(idx > s.length) {
            return
        } else if(idx == s.length) {
            result.add(cur.substring(0, cur.length-1))
        } else {
            val next = node.nodes[s[idx]]
            if(next != null) {
                val word = next.word
                if(word.isNotEmpty()) {
                    findAllCombination(idx+1, s, "$cur$word ", root)
                }
                if(idx+1 < s.length) {
                    findAllCombination(idx + 1, s, cur, next)
                }
            }
        }
    }
}

fun main() {
    println(WordBreakII().wordBreak("catsanddog", listOf("cat","cats","and","sand","dog")))
}
