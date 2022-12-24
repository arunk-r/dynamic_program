package com.dynamic.program.stacks_queues

import java.util.*


/**
 * Final Prices With a Special Discount in a Shop
 *
 * You are given an integer array prices where prices[i] is the price of the ith item in a shop.
 *
 * There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount equivalent to prices[j]
 * where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise, you will not receive any discount at all.
 *
 * Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop, considering the special discount.
 *
 * Example 1:
 * Input: prices = [8,4,6,2,3]
 * Output: [4,2,4,2,3]
 *
 * Explanation:
 * For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
 * For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
 * For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
 * For items 3 and 4 you will not receive any discount at all.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: [1,2,3,4,5]
 *
 * Explanation: In this case, for all items, you will not receive any discount at all.
 *
 * Example 3:
 * Input: prices = [10,1,1,6]
 * Output: [9,0,1,6]
 *
 * Constraints:
 * 1 <= prices.length <= 500
 * 1 <= prices[i] <= 1000
 */

fun finalPrices(prices: IntArray): IntArray {
    val stack = Stack<Int>()
    val map = hashMapOf<Int, Int>()
    for (i in prices.indices) {
        while (stack.isNotEmpty() && prices[stack.peek()] >= prices[i]) {
            map[stack.pop()] = prices[i]
        }
        stack.push(i)
    }
    val list = mutableListOf<Int>()
    for (i in prices.indices) {
        list.add(map[i]?.let { prices[i] - it } ?: prices[i])
    }
    return list.toIntArray()
}

fun main() {
    println(finalPrices(intArrayOf(8, 4, 6, 2, 3)).toMutableList())
    println(finalPrices(intArrayOf(10,1,1,6)).toMutableList())
    println(finalPrices(intArrayOf(8,7,4,2,8,1,7,7,10,1)).toMutableList())
}
