package com.dynamic.program.easy

/**
 * <div class="html">
<p>
Write a function that takes in an array of integers and returns a sorted
version of that array. Use the Bubble Sort algorithm to sort the array.
</p>
<p>
If you're unfamiliar with Bubble Sort, we recommend watching the Conceptual
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
fun bubbleSort(array: MutableList<Int>): List<Int> {
    // Write your code here.
    var swap = true
    while(swap){
        swap = false
        for(i in 0 until array.size-1){
            if(array[i] > array[i+1]){
                val temp = array[i]
                array[i] = array[i+1]
                array[i + 1] = temp

                swap = true
            }
        }
    }
    return array
}