package com.dynamic.program.generics

class Array<T>(private var capacity: Int = 16) : Iterable<T> {
    private var data = ArrayList<T>()
    private var len: Int = 0

    init {
        if(capacity < this.capacity) {
            throw IllegalArgumentException("Capacity should be greater than ${this.capacity}")
        }
        data = ArrayList(capacity)
    }
    fun get(idx: Int): T {
        if (idx in 0 until  len) {
            return data[idx]
        }
        throw Exception("Out of bounds!")
    }

    fun add(value: T) {
        if (len == capacity) {
            capacity *= 2
        }
        val newData = ArrayList<T>(capacity)
        var idx = 0
        data.forEach {d ->
            newData[idx++] = d
        }
        data = newData
        len = idx
        data[len++] = value
    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            var index = 0
            override fun hasNext(): Boolean {
                return index < len
            }

            override fun next(): T {
                return get(index++)
            }
        }
    }
}
