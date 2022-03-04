package com.dynamic.program.easy

/**
 *
Write a function that takes in a sorted array of integers as well as a target
integer. The function should use the Binary Search algorithm to determine if
the target integer is contained in the array and should return its index if it
is, otherwise <span>-1</span>.
</p>
<p>
If you're unfamiliar with Binary Search, we recommend watching the Conceptual
Overview section of this question's video explanation before starting to code.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [0, 1, 21, 33, 45, 45, 61, 71, 72, 73]
<span class="CodeEditor-promptParameter">target</span> = 33
</pre>
<h3>Sample Output</h3>
3

 */
fun binarySearch(array: List<Int>, target: Int): Int {
    // Write your code here.
    return binarySearchHelper(array, target, 0, array.size - 1)
}

fun binarySearchHelper(array: List<Int>, target: Int, left: Int, right: Int): Int {
    if (left > right) {
        return -1
    }

    val m: Int = (left + right) / 2
    if (target == array[m]) {
        return m
    }
    if (target < array[m]) {
        return binarySearchHelper(array, target, left, m-1)
    } else {
        return binarySearchHelper(array, target, m+1, right)
    }
}