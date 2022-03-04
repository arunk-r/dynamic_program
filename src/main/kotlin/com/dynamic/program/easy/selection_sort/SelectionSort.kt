package com.dynamic.program.easy.selection_sort

/**
 * <div class="html">
<p>
Write a function that takes in an array of integers and returns a sorted
version of that array. Use the Selection Sort algorithm to sort the array.
</p>
<p>
If you're unfamiliar with Selection Sort, we recommend watching the Conceptual
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
fun selectionSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    var min:Int
    for (i in 0 until array.size) {
        min = i
        for (j in (i + 1) until array.size) {
            if (array[j] < array[min]) {
                min = j
            }
        }
        swap(min, i, array)
    }
    return array
}

fun swap(i: Int, j: Int, array: MutableList<Int>) {
    val temp = array[i]
    array[i] = array[j]
    array[j] = temp
}