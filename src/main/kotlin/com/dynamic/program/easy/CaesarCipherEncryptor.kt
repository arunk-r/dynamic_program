package com.dynamic.program.easy

/**
 * <div class="html">
<p>
Given a non-empty string of lowercase letters and a non-negative integer
representing a key, write a function that returns a new string obtained by
shifting every letter in the input string by k positions in the alphabet,
where k is the key.
</p>
<p>
Note that letters should "wrap" around the alphabet; in other words, the
letter <span>z</span> shifted by one returns the letter <span>a</span>.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">string</span> = "xyz"
<span class="CodeEditor-promptParameter">key</span> = 2
</pre>
<h3>Sample Output</h3>
<pre>"zab"
</pre>
</div>
 */
fun caesarCipherEncryptor(string: String, key: Int): String {
    // Write your code here.
    var newString = ""
    val nk = key % 26
    for(s in string) {
        newString += getNewLetter(s, nk)
    }
    return newString
}

fun getNewLetter(s: Char, key: Int): Char {
    val nc = s.toInt() + key
    return if (nc <= 122) nc.toChar() else (96 + nc % 122).toChar()
}