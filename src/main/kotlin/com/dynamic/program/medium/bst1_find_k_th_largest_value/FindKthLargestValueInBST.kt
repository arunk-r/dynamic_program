package com.dynamic.program.medium.bst1_find_k_th_largest_value

/**
 * <div class="html">
<p>
Write a function that takes in a Binary Search Tree (BST) and a positive
integer <span>k</span> and returns the kth largest integer contained in the
BST.
</p>
<p>
You can assume that there will only be integer values in the BST and that
<span>k</span> is less than or equal to the number of nodes in the tree.
</p>
<p>
Also, for the purpose of this question, duplicate integers will be treated as
distinct values. In other words, the second largest value in a BST containing
values <span>{5, 7, 7}</span> will be <span>7</span>—not <span>5</span>.
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
<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">tree</span> =   15
/     \
5      20
/   \   /   \
2     5 17   22
/   \
1     3
<span class="CodeEditor-promptParameter">k</span> = 3
</pre>
<h3>Sample Output</h3>
<pre>17</pre>
</div>
 */

// This is an input class. Do not edit.
open class BST(value: Int) {
    var value = value
    var left: BST? = null
    var right: BST? = null
}

fun findKthLargestValueInBst(tree: BST, k: Int): Int {
    // Write your code here.
    val visited = mutableListOf<Int>(0, 0)
    reverseOrderSearch(tree, k, visited)
    return visited[1]
}

fun reverseOrderSearch(tree: BST?, k: Int, visited: MutableList<Int>) {
    if (tree == null || visited[0] >= k) return
    reverseOrderSearch(tree.right, k, visited)
    if (visited[0] < k) {
        visited[0] = visited[0]+1
        visited[1] = tree.value
    }
    reverseOrderSearch(tree.left, k, visited)
}