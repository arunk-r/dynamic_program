package com.dynamic.program.easy.find_closest_value_in_bst

/**
 *
Write a function that takes in a Binary Search Tree (BST) and a target integer
value and returns the closest value to that target value contained in the BST.

<p>You can assume that there will only be one closest value.</p>

Each <span>BST</span> node has an integer <span>value</span>, a
<span>left</span> child node, and a <span>right</span> child node. A node is
said to be a valid <span>BST</span> node if and only if it satisfies the BST
property: its <span>value</span> is strictly greater than the values of every
node to its left; its <span>value</span> is less than or equal to the values
of every node to its right; and its children nodes are either valid
<span>BST</span> nodes themselves or <span>None</span> / <span>null</span>.

<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">tree</span> =   10
/     \
5      15
/   \   /   \
2     5 13   22
/           \
1            14
<span class="CodeEditor-promptParameter">target</span> = 12
</pre>
<h3>Sample Output</h3>
<pre>13</pre>
 */
open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun findClosestValueInBst(tree: BST, target: Int): Int {
    // Write your code here.
    var node: BST?
    if(target < tree.value) {
        node = tree.left
        if( node == null) {
            return tree.value
        }
        return findClosestValueInBst(node, target)
    } else if (target > tree.value) {
        node = tree.right
        if(node == null) {
            return tree.value
        }
        return findClosestValueInBst(node, target)
    } else {
        return tree.value
    }
}
