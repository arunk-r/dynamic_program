package com.dynamic.program.design.medium.browser_history.linked_list

class DesignBrowserHistory(private val url: String) {
    data class Node(val url: String, var prev: Node? = null, var next: Node? = null)
    var cur = Node(url)

    fun visit(url: String) {
        cur.next = Node(url, cur)
        cur = cur.next!!
    }

    fun back(steps: Int): String {
        var idx = steps
        while(idx-- > 0 && cur.prev != null) {
            cur = cur.prev!!
        }
        return cur.url
    }

    fun forward(steps: Int): String {
        var idx = steps
        while(idx-- > 0 && cur.next != null) {
            cur = cur.next!!
        }
        return cur.url
    }
}
