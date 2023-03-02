package com.dynamic.program.strings_arrays.medium

/**
 * 49. Group Anagrams
Medium

Amazon

Yandex

Bloomberg
Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]


Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = hashMapOf<String, MutableList<String>>()
        for(s in strs) {
            val v1 = s.toCharArray().sorted().joinToString("")
            if (!map.containsKey(v1)) {
                map[v1] = mutableListOf()
            }
            map[v1]!!.add(s)
        }

        val result = mutableListOf<MutableList<String>>()
        map.forEach{ (_,v) ->
            result.add(v)
        }

        return result
    }
}
