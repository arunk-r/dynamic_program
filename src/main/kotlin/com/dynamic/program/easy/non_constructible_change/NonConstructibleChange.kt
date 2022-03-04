package com.dynamic.program.easy.non_constructible_change

/**
 * Given an array of positive integers representing the values of coins in your
possession, write a function that returns the minimum amount of change (the
minimum sum of money) that you <b>cannot</b> create. The given coins can have
any positive integer value and aren't necessarily unique (i.e., you can have
multiple coins of the same value).

For example, if you're given <span>coins = [1, 2, 5]</span>, the minimum
amount of change that you can't create is <span>4</span>. If you're given no
coins, the minimum amount of change that you can't create is <span>1</span>.

<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">coins</span> = [5, 7, 1, 1, 2, 3, 22]</pre>
<h3>Sample Output</h3>
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