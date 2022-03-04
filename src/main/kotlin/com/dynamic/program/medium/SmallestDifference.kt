package com.dynamic.program.medium

/**
 * <div class="html">
<p>
Write a function that takes in two non-empty arrays of integers, finds the
pair of numbers (one from each array) whose absolute difference is closest to
zero, and returns an array containing these two numbers, with the number from
the first array in the first position.
</p>
<p>
Note that the absolute difference of two integers is the distance between
them on the real number line. For example, the absolute difference of -5 and 5
is 10, and the absolute difference of -5 and -4 is 1.
</p>
<p>
You can assume that there will only be one pair of numbers with the smallest
difference.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">arrayOne</span> = [-1, 5, 10, 20, 28, 3]
<span class="CodeEditor-promptParameter">arrayTwo</span> = [26, 134, 135, 15, 17]
</pre>
<h3>Sample Output</h3>
<pre>[28, 26]</pre>
</div>
 */
fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {
    // Write your code here.
    arrayOne.sort()
    arrayTwo.sort()
    var smallEle1 = 0
    var smallEle2 = 0
    var diff = Int.MAX_VALUE
    var arrayOnePointer = 0
    var arrayTwoPointer = 0
    while (arrayOnePointer < arrayOne.size && arrayTwoPointer < arrayTwo.size) {
        val cd = Math.abs(arrayOne[arrayOnePointer] - arrayTwo[arrayTwoPointer])
        if (diff > cd) {
            diff = cd
            smallEle1 = arrayOne[arrayOnePointer]
            smallEle2 = arrayTwo[arrayTwoPointer]
        }
        if (arrayOne[arrayOnePointer] < arrayTwo[arrayTwoPointer]) {
            arrayOnePointer++
        } else if (arrayOne[arrayOnePointer] > arrayTwo[arrayTwoPointer]) {
            arrayTwoPointer++
        } else {
            return listOf(arrayOne[arrayOnePointer], arrayTwo[arrayTwoPointer])
        }

    }

    return listOf(smallEle1, smallEle2)
}