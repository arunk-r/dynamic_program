package com.dynamic.program.design.easy.arrays

/**
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/description/
 *
 * 170. Two Sum III - Data structure design
 * Easy
 * 639
 * 424
 * company
 * LinkedIn
 * Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.
 *
 * Implement the TwoSum class:
 *
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.
 *
 *
 * Example 1:
 *
 * Input
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * Output
 * [null, null, null, null, true, false]
 *
 * Explanation
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4, return true
 * twoSum.find(7);  // No two integers sum up to 7, return false
 *
 *
 * Constraints:
 *
 * -105 <= number <= 105
 * -231 <= value <= 231 - 1
 * At most 104 calls will be made to add and find.
 */
class TwoSumIII {
    val map = hashMapOf<Int, Int>()
    fun add(number: Int) {
        map.putIfAbsent(number, 0)
        map[number] = map[number]!! + 1
    }

    fun find(value: Int): Boolean {
        for((left, cnt) in map) {
            val right = value - left
            val r = map[right]
            if (r != null) {
                if(left == right && cnt == 1) continue
                return true
            }
        }
        return false
    }
}
