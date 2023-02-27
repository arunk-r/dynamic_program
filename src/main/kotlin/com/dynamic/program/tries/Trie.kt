package com.dynamic.program.tries

class Trie() {
    data class PrefixTree(val childrens: HashMap<Char, PrefixTree>  = hashMapOf(), val suggestions: MutableList<String> = mutableListOf())
    val root = PrefixTree()

    fun insert(word: String) {
        var node = root
        for(c in word) {
            node.childrens.putIfAbsent(c, PrefixTree())
            node = node.childrens[c]!!
            node.suggestions.add(word)
            node.suggestions.sort()
        }
    }
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        intervals.sortWith(kotlin.Comparator{x,y -> x[0] - y[0]})
        val result = mutableListOf<IntArray>()
        return result.toTypedArray()
    }

    fun search(word: String): Boolean {
        var node: PrefixTree? = root
        for(c in word) {
            if (node != null) {
                node = node.childrens[c]
                if (node == null) return false
            } else return false
        }
        return node!!.suggestions.contains(word)
    }

    fun startsWith(prefix: String): Boolean {
        var node: PrefixTree? = root
        for(c in prefix) {
            if (node != null) {
                node = node.childrens[c]
            } else {
                return false
            }
        }
        return node != null
    }

}

fun main() {
    var obj = Trie()
    obj.insert("")
    var param_2 = obj.search("a")
    var param_3 = obj.startsWith("a")
}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */
