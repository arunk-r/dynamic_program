package com.dynamic.program.easy.valid_subsequence

/**
 *
Given two non-empty arrays of integers, write a function that determines
whether the second array is a subsequence of the first one.

A subsequence of an array is a set of numbers that aren't necessarily adjacent
in the array but that are in the same order as they appear in the array. For
instance, the numbers <span>[1, 3, 4]</span> form a subsequence of the array
<span>[1, 2, 3, 4]</span>, and so do the numbers <span>[2, 4]</span>. Note
that a single number in an array and the array itself are both valid
subsequences of the array.

<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [5, 1, 22, 25, 6, -1, 8, 10]
<span class="CodeEditor-promptParameter">sequence</span> = [1, 6, -1, 10]
</pre>
<h3>Sample Output</h3>
true
 */
fun isValidSubsequence(array: List<Int>, sequence: List<Int>): Boolean {
    // Write your code here.
    var arrIndex = 0
    var seqIndex = 0
    while(arrIndex < array.size && seqIndex < sequence.size) {
        if (array[arrIndex] == sequence[seqIndex]) {
            seqIndex++
        }
        arrIndex++
    }
    return seqIndex == sequence.size
}