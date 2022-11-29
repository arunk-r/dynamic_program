package com.dynamic.program.lists


fun intersection(n1: Node, n2: Node, ref: Node?): Pair<Int, Int> {
    var n1Id = -1
    var n2Id = -1
    var idx = 1
    var r1: Node? = n1
    var r2: Node? = n2

    while (r1 != null || r2 != null) {
        if (r1 != null) {
            if (r1 == ref) {
                n1Id = idx
                r1 = null
            } else {
                r1 = r1.next
            }
        }
        if (r2 != null) {
            if (r2 == ref) {
                n2Id = idx
                r2 = null
            } else {
                r2 = r2.next
            }
        }
        idx++
    }
    return Pair(n1Id, n2Id)
}

/**
 * what if input do not provide reference node
 */

fun intersection1(n1: Node, n2: Node): Pair<Int, Int> {
    val n1Cnt = getCount(n1)
    val n2Cnt = getCount(n2)
    var x: Node? = n1
    var y: Node? = n2
    var logger = if (n1Cnt >= n2Cnt) n1Cnt else n2Cnt
    var shorter = if (n1Cnt < n2Cnt) n1Cnt else n2Cnt
    var n1Idx = 1
    var n2Idx = 1
    while (x != null || y != null) {
        if (x == y) {
            return Pair(n1Idx, n2Idx)
        }

        if (logger == shorter) {
            x = x?.next
            shorter --
            n1Idx++
        }
        y = y?.next
        n2Idx++
        logger --
    }
    return Pair(-1, -1)
}

fun getCount(n: Node): Int {
    var cnt = 0
    var n1: Node? = n
    while (n1 != null) {
        n1 = n1.next
        cnt++
    }
    return cnt
}

fun main() {
    val ref = Node(7)
    val h1 = Node(4, ref)
    ref.next = Node(9, Node(8, Node(2)))
    val h2 = Node(2, Node(3, Node(10, ref)))
    println(h1)
    println(h2)
    println(intersection(h1, h2, ref))

    println(intersection1(h1, h2))
}

