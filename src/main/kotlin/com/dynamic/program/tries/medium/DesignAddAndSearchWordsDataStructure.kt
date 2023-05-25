package com.dynamic.program.tries.medium

class DesignAddAndSearchWordsDataStructure {
    data class Trie(val c: Char, var end: Boolean = false, val map: HashMap<Char, Trie> = hashMapOf())
    val root = Trie(' ')
    fun addWord(word: String) {
        var node = root
        for (c in word) {
            val n = node.map[c]
            if (n == null) {
                val nn = Trie(c)
                node.map[c] = nn
                node = nn
            } else {
                node = n
            }
        }
        node.end = true
    }

    fun search(word: String): Boolean {
        return helper(0, word, root)
    }

    fun helper(i: Int, word: String, node: Trie): Boolean {
        if (i == word.length && node.end) return true
        else if (i == word.length) return false
        val c = word[i]
        return if (c == '.') {
            node.map.values.forEach{ n ->
                if (helper(i+1, word, n)) {
                    return true
                }
            }
            false
        } else {
            if (node.map.containsKey(c)) {
                helper(i+1, word, node.map[c]!!)
            } else {
                false
            }
        }
    }
}
