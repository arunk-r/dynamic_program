package com.dynamic.program.easy

/**
 * <div class="html">
<p>
Write a function that takes in an array of integers and returns a sorted
version of that array. Use the Insertion Sort algorithm to sort the array.
</p>
<p>
If you're unfamiliar with Insertion Sort, we recommend watching the Conceptual
Overview section of this question's video explanation before starting to code.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [8, 5, 2, 9, 5, 6, 3]
</pre>
<h3>Sample Output</h3>
<pre>[2, 3, 5, 5, 6, 8, 9]
</pre>
</div>
 */
fun insertionSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    if(array.isEmpty() || array.size < 2) {
        return array
    }
    for(i in 0 until array.size) {
        var j = i
        while (j >0 && array[j] < array[j - 1]) {
            swap(j, j-1, array)
            j--
        }
    }
    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}
