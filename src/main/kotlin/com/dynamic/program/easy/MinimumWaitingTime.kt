package com.dynamic.program.easy

/**
 *
You're given a non-empty array of positive integers representing the amounts
of time that specific queries take to execute. Only one query can be executed
at a time, but the queries can be executed in any order.

A query's <b>waiting time</b> is defined as the amount of time that it must
wait before its execution starts. In other words, if a query is executed
second, then its waiting time is the duration of the first query; if a query
is executed third, then its waiting time is the sum of the durations of the
first two queries.

Write a function that returns the minimum amount of total waiting time for all
of the queries. For example, if you're given the queries of durations
<span>[1, 4, 5]</span>, then the total waiting time if the queries were
executed in the order of <span>[5, 1, 4]</span> would be
<span>(0) + (5) + (5 + 1) = 11</span>. The first query of duration
<span>5</span> would be executed immediately, so its waiting time would be
<span>0</span>, the second query of duration <span>1</span> would have to wait
<span>5</span> seconds (the duration of the first query) to be executed, and
the last query would have to wait the duration of the first two queries before
being executed.
</p>
<p>Note: you're allowed to mutate the input array.</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">queries</span> = [3, 2, 1, 2, 6]
</pre>
<h3>Sample Output</h3>
<pre>17
</pre>
</div>
 */
fun minimumWaitingTime(queries: MutableList<Int>): Int {
    // Write your code here.
    queries.sort()
    var waitTime = 0
    for (i in 0 until queries.size -1) {
        waitTime += ((queries.size -1) - i) * queries[i]
    }
    return waitTime
}

fun main() {
    println(minimumWaitingTime(mutableListOf(3, 2, 1, 2, 6)))
}