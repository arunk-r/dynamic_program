package com.dynamic.program.design.medium.strings

/**
 * https://leetcode.com/problems/map-sum-pairs/description/
 *
 * 677. Map Sum Pairs
 * Medium
 * 1.5K
 * 142
 * company
 * Google
 * Akuna Capital
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 *
 * Example 1:
 *
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 * Constraints:
 *
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 */
class MapSumPairs {
    data class Trie(val c: Char, val map: HashMap<Char, Trie> = hashMapOf(), var cnt: Int = 0)
    val map = hashMapOf<String , Int>()
    val root= Trie(' ')

    fun insert(key: String, `val`: Int) {
        val old = map[key] ?: 0
        map[key] = `val`
        var node = root
        for(c in key) {
            var v = node.map[c]
            if(v == null) {
                v = Trie(c)
                node.map[c] = v
            }
            node = v
            node.cnt = ((node.cnt - old) + `val`)
        }
    }

    fun sum(prefix: String): Int {
        var node = root
        for(c in prefix) {
            val v = node.map[c] ?: return 0
            node = v
        }
        return node.cnt
    }
}
