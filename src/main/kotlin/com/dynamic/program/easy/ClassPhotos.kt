package com.dynamic.program.easy

/**
 *
It's photo day at the local school, and you're the photographer assigned to
take class photos. The class that you'll be photographing has an even number
of students, and all these students are wearing red or blue shirts. In fact,
exactly half of the class is wearing red shirts, and the other half is wearing
blue shirts. You're responsible for arranging the students in two rows before
taking the photo. Each row should contain the same number of the students and
should adhere to the following guidelines:
</p>
<ul>
<li>All students wearing red shirts must be in the same row.</li>
<li>All students wearing blue shirts must be in the same row.</li>
<li>
Each student in the back row must be strictly taller than the student
directly in front of them in the front row.
</li>
</ul>
<p>
You're given two input arrays: one containing the heights of all the students
with red shirts and another one containing the heights of all the students
with blue shirts. These arrays will always have the same length, and each
height will be a positive integer. Write a function that returns whether or
not a class photo that follows the stated guidelines can be taken.
</p>
<p>Note: you can assume that each class has at least 2 students.</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">redShirtHeights</span> = [5, 8, 1, 3, 4]
<span class="CodeEditor-promptParameter">blueShirtHeights</span> = [6, 9, 2, 4, 5]
</pre>
<h3>Sample Output</h3>
<pre>true <span class="CodeEditor-promptComment">// Place all students with blue shirts in the back row.</span>
\
 */
fun classPhotos(redShirtHeights: MutableList<Int>, blueShirtHeights: MutableList<Int>): Boolean {
    if (redShirtHeights.size != blueShirtHeights.size) return false
    redShirtHeights.sortDescending()
    blueShirtHeights.sortDescending()
    val isRed = redShirtHeights[0] < blueShirtHeights[0]
    for(i in 0 until redShirtHeights.size) {
        if (isRed) {
            if(redShirtHeights[i] >= blueShirtHeights[i]){
                return false
            }
        } else {
            if(redShirtHeights[i] <= blueShirtHeights[i]){
                return false
            }
        }

    }
    return true
}