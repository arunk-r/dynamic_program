package com.dynamic.program.medium.bst_validate

/**
 * <div class="html">
<p>
Write a function that takes in a potentially invalid Binary Search Tree (BST)
and returns a boolean representing whether the BST is valid.
</p>
<p>
Each <span>BST</span> node has an integer <span>value</span>, a
<span>left</span> child node, and a <span>right</span> child node. A node is
said to be a valid <span>BST</span> node if and only if it satisfies the BST
property: its <span>value</span> is strictly greater than the values of every
node to its left; its <span>value</span> is less than or equal to the values
of every node to its right; and its children nodes are either valid
<span>BST</span> nodes themselves or <span>None</span> / <span>null</span>.
</p>
<p>
A BST is valid if and only if all of its nodes are valid
<span>BST</span> nodes.
</p>
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">tree</span> =   10
/     \
5      15
/   \   /   \
2     5 13   22
/           \
1            14
</pre>
<h3>Sample Output</h3>
<pre>true</pre>
</div>
 */
open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun validateBst(tree: BST): Boolean {
    // Write your code here.
    return validateBstHelper(tree, Int.MIN_VALUE, Int.MAX_VALUE)
}

fun validateBstHelper(tree: BST?, min: Int, max: Int): Boolean {
    if (tree == null) return true
    if (tree.value < min || tree.value >= max) return false
    val leftValidated = validateBstHelper(tree.left, min, tree.value)
    return leftValidated && validateBstHelper(tree.right, tree.value, max)
}