package com.dynamic.program.graphs.hard

/**
 * 269. Alien Dictionary
Hard

Airbnb
Bloomberg
Amazon
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are
sorted lexicographically
by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.



Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
Example 2:

Input: words = ["z","x"]
Output: "zx"
Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.
 */
class AlienDictionary {
    fun alienOrder(words: Array<String>): String {
        val graph = hashMapOf<Char, MutableList<Char>>()

        for(word in words) {
            for(c in word) {
                graph.putIfAbsent(c, mutableListOf())
            }
        }
        // why we need to compare every word wirth next word; for last word we dont need to compare next word
        for(i in 0 until words.size - 1) {
            val w1 = words[i]
            val w2 = words[i+1]
            val len = minOf(w1.length, w2.length)

            if(w1.length > w2.length && w1.startsWith(w2)) return ""

            for(c in 0 until len) {
                if (w1[c] != w2[c]) {
                    graph[w1[c]]?.add(w2[c])
                    break
                }
            }
        }

        val visited = hashMapOf<Char, Boolean>()
        val result = mutableListOf<Char>()
        graph.keys.forEach { c ->
            if(dfs(c, visited, result, graph)) {
                return ""
            }
        }

        result.reverse()
        return result.joinToString("")
    }

    private fun dfs(chr: Char, visited: HashMap<Char, Boolean>, result: MutableList<Char>, graph: HashMap<Char, MutableList<Char>>): Boolean {
        if (visited.containsKey(chr)) return visited[chr]!!

        visited[chr] = true
        graph[chr]?.forEach{ nei ->
            if (dfs(nei, visited, result, graph)) {
                return true
            }
        }
        visited[chr] = false
        result.add(chr)

        return false
    }
}

fun main() {
    //println(AlienDictionary().alienOrder(arrayOf("wrt","wrf","er","ett","rftt")))
    //println(AlienDictionary().alienOrder(arrayOf("z","x","z")))
    println(AlienDictionary().alienOrder(arrayOf("ac","ab","zc","zb")))
    println(AlienDictionary().alienOrder(arrayOf("z","z")))
}

