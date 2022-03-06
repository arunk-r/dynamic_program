package com.dynamic.program.easy.non_constructible_change

/**
 * Given an array of positive integers representing the values of coins in your
possession, write a function that returns the minimum amount of change (the
minimum sum of money) that you <b>cannot</b> create. The given coins can have
any positive integer value and aren't necessarily unique (i.e., you can have
multiple coins of the same value).

For example, if you're given coins = [1, 2, 5], the minimum
amount of change that you can't create is 4. If you're given no
coins, the minimum amount of change that you can't create is 1.

Sample Input
coins = [5, 7, 1, 1, 2, 3, 22]
Sample Output
20

 */
fun nonConstructibleChange(coins: MutableList<Int>): Int {
    // Write your code here.
    coins.sort()
    var coinValue = 0
    for (coin in coins) {
        if (coin > coinValue+1) return coinValue+1
        coinValue += coin
    }
    return coinValue+1
}

fun main() {
    println(nonConstructibleChange(mutableListOf(5, 7, 1, 1, 2, 3, 22)))
}