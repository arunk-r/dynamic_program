package com.dynamic.program.design.easy.arrays

/**
 * https://leetcode.com/problems/design-hashset/description/
 */
class DesignHashSet {
    private val maxSize = 1000
    private val data = Array(1001){BooleanArray(1001)}

    fun add(key: Int) {
        data[key/maxSize][key%maxSize] = true
    }

    fun remove(key: Int) {
        data[key/maxSize][key%maxSize] = false
    }

    fun contains(key: Int): Boolean {
        return data[key/maxSize][key%maxSize]
    }

    val a = arrayOfNulls<Int>(1001)
}


// not a elegant solution
class DesignHashSet1 {
    val data = IntArray(1000001)
    fun add(key: Int) {
        data[key] = 1
    }

    fun remove(key: Int) {
        data[key] = 0
    }

    fun contains(key: Int): Boolean {
        return data[key] == 1
    }
}
