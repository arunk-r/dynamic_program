package com.dynamic.program.medium

/**
 * <div class="html">
<p>
Write a function that takes in a non-empty array of distinct integers and an
integer representing a target sum. The function should find all triplets in
the array that sum up to the target sum and return a two-dimensional array of
all these triplets. The numbers in each triplet should be ordered in ascending
order, and the triplets themselves should be ordered in ascending order with
respect to the numbers they hold.
</p>
<p>
If no three numbers sum up to the target sum, the function should return an
empty array.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [12, 3, 1, 2, -6, 5, -8, 6]
<span class="CodeEditor-promptParameter">targetSum</span> = 0
</pre>
<h3>Sample Output</h3>
<pre>[[-8, 2, 6], [-8, 3, 5], [-6, 1, 5]]
</pre>
</div>
 */
fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {
    // Write your code here
    array.sort()
    val output = mutableListOf<List<Int>>()
    for(cn in 0 until array.size) {
        var l = cn + 1
        var r = array.size -1
        while (l < r) {
            val cs = array[cn] + array[l] + array[r]
            if(cs == targetSum) {
                output.add(listOf(array[cn], array[l], array[r]))
                l++
                r--
            } else if (cs < targetSum) {
                l++
            } else {
                r--
            }
        }
    }
    return output
}