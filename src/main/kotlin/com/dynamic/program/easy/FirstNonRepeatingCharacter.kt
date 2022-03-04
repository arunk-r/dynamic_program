package com.dynamic.program.easy

/**
 * <div class="html">
<p>
Write a function that takes in a string of lowercase English-alphabet letters
and returns the index of the string's first non-repeating character.
</p>
<p>
The first non-repeating character is the first character in a string that
occurs only once.
</p>
<p>
If the input string doesn't have any non-repeating characters, your function
should return <span>-1</span>.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">string</span> = "abcdcaf"
</pre>
<h3>Sample Output</h3>
<pre>1 <span class="CodeEditor-promptComment">// The first non-repeating character is "b" and is found at index 1.</span>
</pre>
</div>
 */

fun firstNonRepeatingCharacter(string: String): Int {
    // Write your code here.
    val linkedHashMap = linkedMapOf<String, Int>()
    for(s in string.indices) {
        var dup = false
        for(c in string.indices) {
            if (string[s] == string[c] && s != c) {
                dup = true
            }
        }
        if (!dup) return s
    }
    return -1
}