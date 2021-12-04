package com.algoexpert.program

import java.util.*

class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()

    fun breadthFirstSearch(): List<String> {
        // Write your code here.
        val list = mutableListOf<String>()
        val queue: Queue<Node> = LinkedList()
        queue.add(this)

        while (queue.size != 0) {
            val node = queue.poll()
            list.add(node.name)
            queue.addAll(this.children)
        }
        return list
    }
}
