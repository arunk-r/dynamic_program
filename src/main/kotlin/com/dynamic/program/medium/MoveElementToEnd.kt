package com.dynamic.program.medium

/**
 * <div class="html">
<p>
You're given an array of integers and an integer. Write a function that moves
all instances of that integer in the array to the end of the array and returns
the array.
</p>
<p>
The function should perform this in place (i.e., it should mutate the input
array) and doesn't need to maintain the order of the other integers.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [2, 1, 2, 2, 2, 3, 4, 2]
<span class="CodeEditor-promptParameter">toMove</span> = 2
</pre>
<h3>Sample Output</h3>
<pre>[1, 3, 4, 2, 2, 2, 2, 2] <span class="CodeEditor-promptComment">// the numbers 1, 3, and 4 could be ordered differently</span>
</pre>
</div>
 */
fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {
    // Write your code here.
    val endList = array.filter { it == toMove }
    val otherList = array.filter { it != toMove }

    return otherList.plus(endList)
}