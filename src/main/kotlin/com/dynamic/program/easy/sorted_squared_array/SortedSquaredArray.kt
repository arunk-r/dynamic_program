package com.dynamic.program.easy.sorted_squared_array

/**
 *
Write a function that takes in a non-empty array of integers that are sorted
in ascending order and returns a new array of the same length with the squares
of the original integers also sorted in ascending order.

<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [1, 2, 3, 5, 6, 8, 9]
</pre>
<h3>Sample Output</h3>
[1, 4, 9, 25, 36, 64, 81]

 */
fun sortedSquaredArray(array: List<Int>): List<Int> {
    // Write your code here.
    var output = ArrayList<Int>()
    for (i in array) {
        output.add(i * i)
    }
    output.sort()
    return output
}

fun main() {
    println(listOf(1, 2, 3, 5, 6, 8, 9))
}