package com.dynamic.program.DoublyLinkedList

class Node(value: Int) {
    val value = value
    var prev: Node? = null
    var next: Node? = null
}

class DoublyLinkedList {
    private var head: Node? = null
    private var tail: Node? = null

    fun setHead(node: Node) {
        // Write your code here.
        if (head == null) {
            head = head
            tail = node
            return
        }
        this.insertBefore(this.head!!, node)
    }

    fun setTail(node: Node) {
        // Write your code here.
        if (tail == null) {
            setHead(node)
            return
        }
        this.insertAfter(this.tail!!, node)
    }

    fun insertBefore(node: Node, nodeToInsert: Node) {
        // Write your code here.
        if (nodeToInsert == this.head && nodeToInsert == this.tail) return
        remove(nodeToInsert)
        nodeToInsert.prev = node.prev
        nodeToInsert.next = node

        if (node.prev == null ) {
            this.head = nodeToInsert
        } else {
            node.prev!!.next = nodeToInsert
        }
        node.prev = nodeToInsert
    }

    fun insertAfter(node: Node, nodeToInsert: Node) {
        // Write your code here.
        if (nodeToInsert == this.head && nodeToInsert == this.tail) return
        remove(nodeToInsert)
        nodeToInsert.prev = node
        nodeToInsert.next = node.next
        if (node.next == null ) {
            this.tail = nodeToInsert
        } else {
            node.next!!.prev = nodeToInsert
        }
        node.next = nodeToInsert
    }

    fun insertAtPosition(position: Int, nodeToInsert: Node) {
        // Write your code here.
        if ( position == 1) {
            setHead(nodeToInsert)
            return
        }
        var node: Node? = this.head
        var cp = 1
        while (node != null && cp++ != position ) {
            node = node.next
        }

        if (node != null) {
            insertBefore(node, nodeToInsert)
        } else {
            setTail(nodeToInsert)
        }
    }

    fun removeNodesWithValue(value: Int) {
        // Write your code here.
        var pointer = this.head
        while (pointer != null) {
            var nextNode = pointer.next
            if (pointer.value == value) {
                remove(pointer)
            }
            pointer = nextNode
        }
    }

    fun remove(node: Node) {
        // Write your code here.
        if (node == this.head) {
            this.head = node.next
        }
        if (node == this.tail) {
            this.tail = node.prev
        }
        this.removeNodeInList(node)
    }

    fun containsNodeWithValue(value: Int): Boolean {
        // Write your code here.
        var node = this.head
        while (node != null) {
            if(node.value == value) return true
            node = node.next
        }
        return false
    }

    fun removeNodeInList(node: Node) {
        if (node.prev != null) node.prev!!.next = node.next
        if (node.next != null) node.next!!.prev = node.prev
        node.prev = null
        node.next = null
    }

    fun getHead(): Node? {
        return this.head
    }

    fun getTail(): Node? {
        return this.tail
    }
}

fun main() {
   val dl = DoublyLinkedList()
    val n = Node(1)
    dl.setHead(n)
    dl.insertBefore(n, Node(2))
    dl.insertBefore(n, Node(3))

    println(dl)
}