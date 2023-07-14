package com.dynamic.program.design.medium.arrays

/**
 * https://leetcode.com/problems/shortest-word-distance-ii/description/
 *
 * 244. Shortest Word Distance II
 * Medium
 * 1K
 * 309
 * company
 * LinkedIn
 * company
 * Amazon
 * company
 * Google
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 * int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 *
 * Example 1:
 *
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 *
 * Explanation
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 * wordDistance.shortest("coding", "practice"); // return 3
 * wordDistance.shortest("makes", "coding");    // return 1
 *
 *
 * Constraints:
 *
 * 1 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 * At most 5000 calls will be made to shortest.
 */
class WordDistanceII(val wordsDict: Array<String>) {
    val map = hashMapOf<String, MutableList<Int>>()
    init {
        var i = 0
        for(w in wordsDict) {
            map.putIfAbsent(w, mutableListOf())
            map[w]?.add(i++)
        }
    }
    fun shortest(word1: String, word2: String): Int {
        val w1 = map[word1] ?: emptyList<Int>()
        val w2 = map[word2] ?: emptyList<Int>()
        var min_diff = Int.MAX_VALUE
        var l1 = 0
        var l2 = 0
        while(l1 < w1.size && l2 < w2.size) {
            min_diff = minOf(min_diff, Math.abs(w1[l1] - w2[l2]))
            if(w1[l1] < w2[l2]) {
                l1++
            } else {
                l2++
            }
        }
        return min_diff
    }

}

/**
 * Your WordDistanceII object will be instantiated and called as such:
 * var obj = WordDistanceII(wordsDict)
 * var param_1 = obj.shortest(word1,word2)
 */
