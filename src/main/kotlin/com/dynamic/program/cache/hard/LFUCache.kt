package com.dynamic.program.cache.hard

import java.util.PriorityQueue
import java.util.TreeMap

class LFUCache(val capacity: Int) {
    data class Cache(val f: Int, val v: Int)
    private val cache = HashMap<Int, Cache>()
    private val freq = TreeMap<Int, MutableSet<Int>>()
    private fun insert(key: Int, fre: Int, v: Int) {
        cache[key] = Cache(fre, v)
        freq.putIfAbsent(fre, mutableSetOf())
        freq[fre]?.add(key)
    }

    fun get(key: Int): Int {
        val v = cache[key] ?: return -1
        val usedKeys = freq[v.f]!!
        usedKeys.remove(key)
        if(usedKeys.isEmpty()) {
            freq.remove(v.f)
        }
        insert(key, v.f+1, v.v)
        return v.v
    }

    fun put(key: Int, value: Int) {
        if(capacity < 0) return

        val v = cache[key]
        if(v != null) {
            val usedKeys = freq[v.f]!!
            usedKeys.remove(key)
            if(usedKeys.isEmpty()) {
                freq.remove(v.f)
            }
            insert(key, v.f+1, value)
            return
        }

        if(capacity == cache.size) {
            val minF = freq.firstKey()
            val usedKeys = freq[minF]!!
            val k = usedKeys.first()
            usedKeys.remove(k)
            cache.remove(k)
            if(usedKeys.isEmpty()) {
                freq.remove(minF)
            }
        }
        insert(key, 1, value)
    }
}

fun main() {

    val list = mutableListOf<Pair<Int, Int>>()
    list.add(Pair(3,1))
    list.add(Pair(1,0))
    list.add(Pair(1,1))
    list.add(Pair(2,1))
    list.add(Pair(2,0))
    list.add(Pair(3,0))
    list.sortBy { it.second }
    println(list)
    /*val l = LFUCache(2)
    println(l.get(2))
    l.put(2, 6)
    println(l.get(1))
    l.put(1, 5)
    l.put(1, 2)
    println(l.get(1))
    println(l.get(2))*/
}
