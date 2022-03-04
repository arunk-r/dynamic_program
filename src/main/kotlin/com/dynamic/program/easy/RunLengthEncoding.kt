package com.dynamic.program.easy

/**
 * <div class="html">
<p>
Write a function that takes in a non-empty string and returns its run-length
encoding.
</p>
<p>
From Wikipedia, "run-length encoding is a form of lossless data compression in
which runs of data are stored as a single data value and count, rather than as
the original run." For this problem, a run of data is any sequence of
consecutive, identical characters. So the run <span>"AAA"</span> would be
run-length-encoded as <span>"3A"</span>.
</p>
<p>
To make things more complicated, however, the input string can contain all
sorts of special characters, including numbers. And since encoded data must be
decodable, this means that we can't naively run-length-encode long runs. For
example, the run <span>"AAAAAAAAAAAA"</span> (12 <span>A</span>s), can't
naively be encoded as <span>"12A"</span>, since this string can be decoded as
either <span>"AAAAAAAAAAAA"</span> or <span>"1AA"</span>. Thus, long runs (runs
of 10 or more characters) should be encoded in a split fashion; the
aforementioned run should be encoded as <span>"9A3A"</span>.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">string</span> = "AAAAAAAAAAAAABBCCCCDD"
</pre>
<h3>Sample Output</h3>
<pre>"9A4A2B4C2D"
</pre>
</div>
 */
fun runLengthEncoding(string: String): String {
    // Write your code here.
    var cnt = 1
    val nc = mutableListOf<Char>()
    for(i in 1..string.length-1) {
        if(string [i] != string[i-1] || cnt == 9) {
            nc.add(cnt.toString()[0])
            nc.add(string[i-1])
            cnt = 0
        }
        cnt++

    }
    nc.add(cnt.toString()[0])
    nc.add(string[string.length-1])
    return nc.joinToString("")
}