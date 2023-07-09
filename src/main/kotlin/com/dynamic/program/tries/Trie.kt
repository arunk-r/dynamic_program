package com.dynamic.program.tries

class Trie() {
    data class Trie(val c: Char, val map: HashMap<Char, Trie> = hashMapOf(), var word: String = "")
    val root = Trie(' ')

    fun insert(word: String) {
        var node = root
        for(c in word) {
            var chld = node.map[c]
            if(chld == null) {
                chld = Trie(c)
                node.map[c] = chld
            }
            node = chld
        }
        node.word = word
    }

    fun search(word: String): Boolean {
        var node: Trie? = root
        for(c in word) {
            node = node!!.map[c]
            if(node == null) return false
        }
        return node?.word == word
    }

    fun startsWith(prefix: String): Boolean {
        var node: Trie? = root
        for(c in prefix) {
            node = node!!.map[c]
            if(node == null) return false
        }
        return true
    }

}

fun main() {
    var obj = Trie()
    obj.insert("apple")
    var param_2 = obj.search("apple")
    var param_3 = obj.startsWith("app")
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
