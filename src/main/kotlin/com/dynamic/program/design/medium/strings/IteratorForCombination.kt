package com.dynamic.program.design.medium.strings

import java.util.TreeSet

/**
 * https://leetcode.com/problems/iterator-for-combination/description/
 *
 * 1286. Iterator for Combination
 * Medium
 * 1.3K
 * 102
 * company
 * Uber
 * company
 * Google
 * Design the CombinationIterator class:
 *
 * CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
 * next() Returns the next combination of length combinationLength in lexicographical order.
 * hasNext() Returns true if and only if there exists a next combination.
 *
 *
 * Example 1:
 *
 * Input
 * ["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [["abc", 2], [], [], [], [], [], []]
 * Output
 * [null, "ab", true, "ac", true, "bc", false]
 *
 * Explanation
 * CombinationIterator itr = new CombinationIterator("abc", 2);
 * itr.next();    // return "ab"
 * itr.hasNext(); // return True
 * itr.next();    // return "ac"
 * itr.hasNext(); // return True
 * itr.next();    // return "bc"
 * itr.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * 1 <= combinationLength <= characters.length <= 15
 * All the characters of characters are unique.
 * At most 104 calls will be made to next and hasNext.
 * It is guaranteed that all calls of the function next are valid.
 */
class IteratorForCombination(characters: String, combinationLength: Int) {
    private val result = mutableListOf<String>()
    private var counter = 0
    init{
        combination(0, characters, combinationLength, StringBuffer())
    }

    private fun combination(s: Int, characters: String, combinationLength: Int, cur: StringBuffer) {
        if(cur.length == combinationLength) {
            result.add(cur.toString())
        } else {
            for(i in s until characters.length) {
                cur.append(characters[i])
                combination(i+1, characters, combinationLength, cur)
                cur.deleteCharAt(cur.length-1)
            }
        }
    }

    fun next(): String {
        return if(counter < result.size) {
            result[counter++]
        } else {
            ""
        }
    }

    fun hasNext(): Boolean {
        return counter < result.size
    }

}

/**
 * Your IteratorForCombination object will be instantiated and called as such:
 * var obj = IteratorForCombination(characters, combinationLength)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
