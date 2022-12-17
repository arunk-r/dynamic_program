package com.dynamic.program.hashing

/**
 * Equal Row and Column Pairs
 * Given a n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.
 *
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).
 *
 * Example 1:
 * Input: grid = [
 *      3,2,1
 *      1,7,6
 *      2,7,7
 * ]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 *
 * time complexity is O(n^2)
 */

fun equalRowAndColumnPairs(list: List<List<Int>>): Int {
    var ans = 0
    val rowMap = hashMapOf<String, Int>()
    val colMap = hashMapOf<String, Int>()
    for (i in list.indices) {
        val rowKey = list[i].joinToString("")
        val colKey = StringBuffer()
        for (j in list.indices) {
            colKey.append(list[j][i])
        }
        rowMap[rowKey] = rowMap[rowKey]?.plus(1) ?: 1
        val key = colKey.toString()
        colMap[key] = colMap[key]?.plus(1) ?: 1
    }
    rowMap.keys.forEach { k ->
        val rowVal = rowMap[k] ?: 0
        val colVal = colMap[k] ?: 0
        ans += (rowVal * colVal)
    }
    return ans
}

fun main() {
    println(equalRowAndColumnPairs(listOf(listOf(3, 2, 1), listOf(1, 7, 6), listOf(2, 7, 7))))
    println(equalRowAndColumnPairs(listOf(listOf(3,1,2,2), listOf(1,4,4,5), listOf(2,4,2,2), listOf(2,4,2,2))))
}
