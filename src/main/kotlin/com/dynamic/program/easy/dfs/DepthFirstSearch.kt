package com.dynamic.program.easy.dfs

/**
 *
 */
class Node(name: String) {
    val name: String = name
    val children = mutableListOf<Node>()

    fun depthFirstSearch(): List<String> {
        // Write your code here.
        val list = ArrayList<String>()
        depthFirstSearch(this, list)
        return list
    }

    private fun depthFirstSearch(node: Node?, list: ArrayList<String>) {
        node?.let{list.add(it.name)}
        if (node!!.children.isNullOrEmpty()) {
            return
        }
        node.children.forEach {
            depthFirstSearch(it,list)
        }
    }
}