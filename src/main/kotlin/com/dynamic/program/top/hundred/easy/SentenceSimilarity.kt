package com.dynamic.program.top.hundred.easy

/**
 * https://leetcode.com/problems/sentence-similarity/
 *
 * 734. Sentence Similarity
 * Easy
 * 23
 * 26
 * company
 * Google
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *
 * They have the same length (i.e., the same number of words)
 * sentence1[i] and sentence2[i] are similar.
 * Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words a and b are similar, and the words b and c are similar, a and c are not necessarily similar.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
 * Output: true
 * Explanation: The two sentences have the same length and each word i of sentence1 is also similar to the corresponding word in sentence2.
 * Example 2:
 *
 * Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
 * Output: true
 * Explanation: A word is similar to itself.
 * Example 3:
 *
 * Input: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
 * Output: false
 * Explanation: As they don't have the same length, we return false.
 *
 *
 * Constraints:
 *
 * 1 <= sentence1.length, sentence2.length <= 1000
 * 1 <= sentence1[i].length, sentence2[i].length <= 20
 * sentence1[i] and sentence2[i] consist of English letters.
 * 0 <= similarPairs.length <= 1000
 * similarPairs[i].length == 2
 * 1 <= xi.length, yi.length <= 20
 * xi and yi consist of lower-case and upper-case English letters.
 * All the pairs (xi, yi) are distinct.
 */
class SentenceSimilarity {
    fun areSentencesSimilar(sentence1: Array<String>, sentence2: Array<String>, similarPairs: List<List<String>>): Boolean {
        if(sentence1.size != sentence2.size) return false
        val set = hashSetOf<Pair<String, String>>()
        for((s,m) in similarPairs) {
            set.add(Pair(s, m))
        }
        for((i, st) in sentence1.withIndex()) {
            if( st != sentence2[i] && Pair(st, sentence2[i]) !in set && Pair(sentence2[i], st) !in set) return false
        }
        return true
    }
    fun areSentencesSimilar1(sentence1: Array<String>, sentence2: Array<String>, similarPairs: List<List<String>>): Boolean {
        if(sentence1.size != sentence2.size) return false
        val map = hashMapOf<String, HashSet<String>>()

        for((s,m) in similarPairs) {
            map.putIfAbsent(s, hashSetOf())
            map.putIfAbsent(m, hashSetOf())
            map[s]?.add(m)
            map[m]?.add(s)
        }

        for((i, st) in sentence1.withIndex()) {
            if(st != sentence2[i]) {
                if(map[st] != null){
                    if(!map[st]!!.contains(sentence2[i])) {
                        return false
                    }
                } else {
                    return false
                }
            }
        }
        return true
    }
}
