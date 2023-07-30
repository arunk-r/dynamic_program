package com.dynamic.program.design.medium.tree

/**
 *
 * https://leetcode.com/problems/implement-magic-dictionary/
 *
 * 676. Implement Magic Dictionary
 * Medium
 * 1.3K
 * 195
 * company
 * Google
 * company
 * Facebook
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 *
 * Implement the MagicDictionary class:
 *
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 *
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 *
 *
 * Constraints:
 *
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case English letters.
 * All the strings in dictionary are distinct.
 * 1 <= searchWord.length <= 100
 * searchWord consists of only lower-case English letters.
 * buildDict will be called only once before search.
 * At most 100 calls will be made to search.
 */
class MagicDictionary {
    data class Trie(val c: Char, val map: HashMap<Char, Trie> = hashMapOf(), var end: Boolean = false)
    val root = Trie(' ')
    fun buildDict(dictionary: Array<String>) {
        for(word in dictionary) {
            var node = root
            for(c in word) {
                var t = node.map[c]
                if(t == null) {
                    t = Trie(c)
                    node.map[c] = t
                }
                node = t
            }
            node.end = true
        }
    }

    fun search(sw: String): Boolean {
        return findMatch(root, 0, sw, 0)
    }

    private fun findMatch(node: Trie, i: Int, sw: String, cnt: Int): Boolean {
        if(i == sw.length && cnt == 1 && node.end) return true
        else if(i >= sw.length || cnt > 1) return false
        node.map.forEach{ (k, v) ->
            if(findMatch(v, i+1, sw, if (k == sw[i]) cnt else cnt+1)) {
                return true
            }
        }
        return false
    }

}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * var obj = MagicDictionary()
 * obj.buildDict(dictionary)
 * var param_2 = obj.search(searchWord)
 */


fun main() {
    val md = MagicDictionary()
    md.buildDict(arrayOf("hello","hallo","leetcode"))
    println(md.search("hello"))
}
