package com.dynamic.program.design.hard

class RandomizedCollection {
    val map = HashMap<Int, Int>()
    val list = mutableListOf<Pair<Int, Int>>()
    fun insert(`val`: Int): Boolean {
        var returnVal = false
        if(!map.containsKey(`val`)) { // not found
            map[`val`] = list.size // add ref to map
            list.add(Pair(`val`, 0)) // add default value
            returnVal = true
        }
        val data = list[map[`val`]!!] // get data
        list[map[`val`]!!] = Pair(data.first, data.second+1) // increment the counter
        return returnVal
    }

    fun remove(`val`: Int): Boolean {
        if(!map.containsKey(`val`)) return false
        val cur = map[`val`]!! // get the idx
        val data = list[cur] // get the data
        if(data.second > 1) { // check for current ele count
            list[cur] = Pair(data.first, data.second-1)
        } else { // last element then swap with last element
            val lst = list[list.size -1] // get last data
            list[cur] = lst // replace current with last
            map[lst.first] = cur // update map reference
            list.removeAt(list.size -1) // remove last element
            map.remove(`val`) // remove cur value ref from map
        }
        return true
    }

    fun getRandom(): Int {
        val rand = java.util.Random()
        return list[rand.nextInt(list.size)].first
    }
}

fun main() {
    val r = RandomizedCollection()
    println(r.insert(1))
    println(r.remove(2))
    println(r.insert(2))
    println(r.getRandom())
    println(r.remove(1))
    println(r.insert(2))
    println(r.getRandom())
}
