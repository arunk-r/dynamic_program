package com.dynamic.program.medium

/**
 * <div class="html">
<p>
Write a function that takes in an n x m two-dimensional array (that can be
square-shaped when n == m) and returns a one-dimensional array of all the
array's elements in spiral order.
</p>
<p>
Spiral order starts at the top left corner of the two-dimensional array, goes
to the right, and proceeds in a spiral pattern all the way until every element
has been visited.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">array</span> = [
[1,   2,  3, 4],
[12, 13, 14, 5],
[11, 16, 15, 6],
[10,  9,  8, 7],
]
</pre>
<h3>Sample Output</h3>
<pre>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
</pre>
</div>
 */
fun spiralTraverse(array: List<List<Int>>): List<Int> {
    // Write your code here.
    val output = mutableListOf<Int>()
    var startingRow = 0
    var startingColumn = 0
    var endingRow = array.size - 1
    var endingColumn = array[0].size - 1
    while (startingRow <= endingRow && startingColumn <= endingColumn) {
        for (c in startingColumn until endingColumn+1) {
            output.add(array[startingRow][c])
        }

        for (r in (startingRow + 1) until endingRow+1) {
            output.add(array[r][endingColumn])
        }

        for (c in (endingColumn - 1) downTo startingColumn) {
            if (startingRow == endingRow) break
            output.add(array[endingRow][c])
        }

        for (r in (endingRow - 1) downTo (startingColumn + 1)) {
            if (startingColumn == endingColumn) break
            output.add(array[r][startingColumn])
        }
        startingColumn++
        startingRow++
        endingColumn--
        endingRow--
    }
    return output
}
