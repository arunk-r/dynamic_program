package com.dynamic.program.design.medium.browser_history.linked_list

import java.util.Stack

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

class BrowserHistory(homepage: String) {
    private var current = homepage
    private val history = Stack<String>()
    private val back = Stack<String>()

    fun visit(url: String) {
        history.push(current)
        back.clear()
        current = url
    }

    fun back(steps: Int): String {
        var i = 1
        while(history.isNotEmpty() && i++ <= steps) {
            back.push(current)
            current = history.pop()
        }
        return current
    }

    fun forward(steps: Int): String {
        var i = 1
        while(back.isNotEmpty() && i++ <= steps) {
            history.push(current)
            current = back.pop()
        }
        return current
    }

}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * var obj = BrowserHistory(homepage)
 * obj.visit(url)
 * var param_2 = obj.back(steps)
 * var param_3 = obj.forward(steps)
 */
