package com.dynamic.program.easy.remove_duplicates_from_linkedList

/**
 *
You're given the head of a Singly Linked List whose nodes are in sorted order
with respect to their values. Write a function that returns a modified version
of the Linked List that doesn't contain any nodes with duplicate values. The
Linked List should be modified in place (i.e., you shouldn't create a brand
new list), and the modified Linked List should still have its nodes sorted
with respect to their values.

Each <span>LinkedList</span> node has an integer <span>value</span> as well as
a <span>next</span> node pointing to the next node in the list or to
<span>None</span> / <span>null</span> if it's the tail of the list.

<h3>Sample Input</h3>
<pre><span class="CodeEditor-promptParameter">linkedList</span> = 1 -&gt; 1 -&gt; 3 -&gt; 4 -&gt; 4 -&gt; 4 -&gt; 5 -&gt; 6 -&gt; 6 <span class="CodeEditor-promptComment">// the head node with value 1</span>
</pre>
<h3>Sample Output</h3>
<pre>1 -&gt; 3 -&gt; 4 -&gt; 5 -&gt; 6 <span class="CodeEditor-promptComment">// the head node with value 1</span>

 */
// This is an input class. Do not edit.
open class LinkedList(value: Int) {
    var value = value
    var next: LinkedList? = null
}

fun removeDuplicatesFromLinkedList(linkedList: LinkedList): LinkedList {
    // Write your code here.
    var current: LinkedList? = linkedList
    var placeHolder: LinkedList? = linkedList
    while(current != null){
        println(current.value)
        if (placeHolder!!.value < current.value) {
            placeHolder.next = current
            placeHolder = placeHolder.next
        }
        current = current.next
    }
    return linkedList
}