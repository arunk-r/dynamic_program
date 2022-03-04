package com.dynamic.program.medium

import kotlin.math.max

/**
 * <div class="html">
<p>
Write a function that takes in a non-empty array of arbitrary intervals,
merges any overlapping intervals, and returns the new intervals in no
particular order.
</p>
<p>
Each interval <span>interval</span> is an array of two integers, with
<span>interval[0]</span> as the start of the interval and
<span>interval[1]</span> as the end of the interval.
</p>
<p>
Note that back-to-back intervals aren't considered to be overlapping. For
example, <span>[1, 5]</span> and <span>[6, 7]</span> aren't overlapping;
however, <span>[1, 6]</span> and <span>[6, 7]</span> <i>are</i> indeed
overlapping.
</p>
<p>
Also note that the start of any particular interval will always be less than
or equal to the end of that interval.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">intervals</span> = [[1, 2], [3, 5], [4, 7], [6, 8], [9, 10]]
</pre>
<h3>Sample Output</h3>
<pre>[[1, 2], [3, 8], [9, 10]]
<span class="CodeEditor-promptComment">// Merge the intervals [3, 5], [4, 7], and [6, 8].</span>
<span class="CodeEditor-promptComment">// The intervals could be ordered differently.</span>
</pre>
</div>
 */

fun mergeOverlappingIntervals(intervals: List<List<Int>>): List<List<Int>> {
    // Write your code here.
    var sortList = intervals.toMutableList().sortedWith( Comparator<List<Int>> { f, s -> f[0].compareTo(s[0]) })
    sortList = sortList.map() {it.toMutableList()}
    var currentList = sortList[0].toMutableList()
    val mergedList = mutableListOf<MutableList<Int>>()
    mergedList.add(currentList)
    for (nextInterval in sortList) {
        val (currentStart, currentEnd) = currentList
        val (nextStart, nextEnd) = nextInterval

        if(currentEnd >= nextStart) {
            currentList[1] = max(currentEnd, nextEnd)
        } else {
            currentList = nextInterval
            mergedList.add(currentList)
        }
    }
    return mergedList
}
