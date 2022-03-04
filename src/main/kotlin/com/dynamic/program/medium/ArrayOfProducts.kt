package com.dynamic.program.medium

/**
 * <div class="html">
<p>
Write a function that takes in a non-empty array of integers and returns an
array of the same length, where each element in the output array is equal to
the product of every other number in the input array.
</p>
<p>
In other words, the value at <span>output[i]</span> is equal to the product of
every number in the input array other than <span>input[i]</span>.
</p>
<p>Note that you're expected to solve this problem without using division.</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [5, 1, 4, 2]
</pre>
<h3>Sample Output</h3>
<pre>[8, 40, 10, 20]
<span class="CodeEditor-promptComment">// 8 is equal to 1 x 4 x 2</span>
<span class="CodeEditor-promptComment">// 40 is equal to 5 x 4 x 2</span>
<span class="CodeEditor-promptComment">// 10 is equal to 5 x 1 x 2</span>
<span class="CodeEditor-promptComment">// 20 is equal to 5 x 1 x 4</span>
</pre>
</div>
 */

fun arrayOfProducts(array: List<Int>): List<Int> {
    // Write your code here.
    val leftArray = mutableListOf<Int>()
    val rightArray = IntArray(array.size)
    val output = mutableListOf<Int>()
    var p = 1
    for (i in 0 until array.size) {
        leftArray.add(p)
        p *= array[i]
    }
    p = 1
    for (i in array.size-1 downTo 0) {
        rightArray[i] = p
        p *= array[i]
    }
    for(i in 0 until leftArray.size) {
        output.add(leftArray[i] * rightArray[i])
    }
    return output
}
