package com.dynamic.program.lists

fun sumList(node: Node?): String {
    if (node != null) {
        return sumList(node.next) + node.value.toString()
    }
    return ""
}

fun main() {
    val v1 = Node(7, Node(1, Node(6)))
    val v2 = Node(8, Node(4, Node(2)))

    println(sumList(v1).toInt() + sumList(v2).toInt())
}
