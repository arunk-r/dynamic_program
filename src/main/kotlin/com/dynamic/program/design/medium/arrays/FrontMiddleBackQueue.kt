package com.dynamic.program.design.medium.arrays

class FrontMiddleBackQueue {
    data class Node(val v: Int, var prev: Node? = null, var next: Node? = null)

    val head = Node(0)
    val tail = Node(0)
    var mid: Node? = head
    var size = 0

    init {
        head.next = tail
        tail.prev = head
    }

    fun pushFront(`val`: Int) {
        val newNode = Node(`val`)
        add(head, newNode) // new node is added right to head
        size++
        if (size == 1) {    // if its the first ever node
            mid = head.next // make it the mid
        }
        if (size % 2 == 0) {    // mid is the former of the two mids
            mid = mid?.prev
        }
    }

    fun pushMiddle(`val`: Int) {
        val newNode = Node(`val`)
        // if size is even, new mid will be at perfect centre
        // that is, right next to mid
        if (size % 2 == 0) {
            add(mid, newNode) // goes right next to mid
            mid = mid?.next
        } else {
            add(mid?.prev, newNode) // go next to mid's prev
            mid = mid?.prev
        }
        size++
    }

    fun pushBack(`val`: Int) {
        val newNode = Node(`val`)
        // gets added right before tail
        // i.e. right next to tail's current prev
        add(tail.prev, newNode)
        size++
        // if size becomes odd, mid will move to perfect centre
        if (size % 2 == 1) {
            mid = mid?.next
        }
    }

    fun popFront(): Int {
        if (size == 0) {
            return -1
        }
        if (size == 1) {  // if list wil become empty
            mid = head // default mid is head
        } else if (size % 2 == 0) {
            // list will become odd, mid will move to next node
            mid = mid?.next
        }
        val v = head.next?.v
        delete(head.next) // delete the node next to head
        size--
        return v ?: -1
    }

    fun popMiddle(): Int {
        if (size == 0) {
            return -1
        }
        val node: Node? = mid
        if (size == 1) {  // list wil become empty
            mid = head // default mid is head
        } else {
            // if list will become odd, mid will go to next node
            // if list will become even, mid will go to prev node
            mid = if (size % 2 == 0) mid?.next else mid?.prev
        }
        delete(node) // delete the node (old mid)
        size--
        return node?.v ?: -1
    }

    fun popBack(): Int {
        if (size == 0) {
            return -1
        }
        if (size == 1) {  // list wil become empty
            mid = head // default mid is head
        } else if (size % 2 == 1) { // if list will become even
            mid = mid?.prev // mid will move to prev node
        }
        val v = tail.prev?.v
        delete(tail.prev) // delete the preceding node of tail
        size--
        return v ?: -1
    }

    // Adds a node `newNode` right next to a reference node `ref`
    private fun add(ref: Node?, newNode: Node) {
        newNode.next = ref!!.next
        ref.next = newNode
        newNode.next!!.prev = newNode
        newNode.prev = ref
    }

    // Deletes the passed in node 'node'
    private fun delete(node: Node?) {
        node!!.next!!.prev = node.prev
        node.prev!!.next = node.next
    }

}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * var obj = FrontMiddleBackQueue()
 * obj.pushFront(`val`)
 * obj.pushMiddle(`val`)
 * obj.pushBack(`val`)
 * var param_4 = obj.popFront()
 * var param_5 = obj.popMiddle()
 * var param_6 = obj.popBack()
 */

fun main() {
    val f = FrontMiddleBackQueue()
    f.pushFront(1)
    f.pushBack(2)
    f.pushMiddle(3)
    f.pushMiddle(4)
    println(f.popFront())
    println(f.popMiddle())
    println(f.popMiddle())
    println(f.popBack())
    println(f.popFront())
}
