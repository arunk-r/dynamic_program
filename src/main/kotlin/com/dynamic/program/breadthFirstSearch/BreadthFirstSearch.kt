package com.dynamic.program.breadthFirstSearch

import java.util.*

class BSFNode(name: String) {
    val name: String = name
    val children = mutableListOf<BSFNode>()

    fun breadthFirstSearch(): List<String> {
        // Write your code here.
        val list = mutableListOf<String>()
        val queue: Queue<BSFNode> = LinkedList()
        queue.add(this)

        while (queue.size != 0) {
            val node = queue.poll()
            list.add(node.name)
            queue.addAll(this.children)
        }
        return list
    }
}
