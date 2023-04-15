package com.dynamic.program.data.structures.queues

class MaxPriorityIntQueue {
    private val lst = mutableListOf<Int>()

    fun add(e: Int): Boolean {
        lst.add(e)
        heapify()
        return true
    }

    fun remove(): Int {
        if (isEmpty()) throw NoSuchElementException("Empty queue!")
        val v = lst.removeAt(0)
        heapify()
        return v
    }

    fun peek(): Int {
        if (isEmpty()) throw NoSuchElementException("Empty queue!")
        return lst[0]
    }

    fun isEmpty(): Boolean {
        return lst.isEmpty()
    }

    fun size(): Int {
        return lst.size
    }

    private fun heapify() {
        val size = size()
        for (i in size / 2 - 1 downTo 0) {
            maxHeapify(i, size)
        }
    }

    private fun maxHeapify(idx: Int, size: Int) {
        val left = leftNode(idx)
        val right = rightNode(idx)
        var maxI = idx

        if (left < size && lst[left] > lst[maxI]) {
            maxI = left
        }
        if (right < size && lst[right] > lst[maxI]) {
            maxI = right
        }

        if (maxI != idx) {
            kotlin.run { val t = lst[maxI];lst[maxI] = lst[idx];lst[idx] = t }
            maxHeapify(maxI, size)
        }
    }

    fun getAll(): MutableList<Int> = lst

    private fun leftNode(idx: Int): Int = 2 * idx + 1
    private fun rightNode(idx: Int): Int = 2 * idx + 2
}

fun main() {
    val p = MaxPriorityIntQueue()
    println(p.add(1))
    println(p.getAll())
    println(p.remove())
    println(p.isEmpty())
    println(p.add(7))
    println(p.add(3))
    println(p.getAll())
    println(p.add(9))
    println(p.getAll())
    println(p.remove())
    println(p.getAll())
    println(p.remove())
    println(p.getAll())
}
