package com.dynamic.program.google

fun superSet(value: String): MutableList<String> {
    val result = mutableListOf<String>()
    result.add("")
    repeat(value.indices.count()) {
        result.addAll(setHelper(value[it].toString(), result))
    }
    return result
}

fun setHelper(value: String, result: MutableList<String>): MutableList<String> {
    val newList = mutableListOf<String>()
    result.forEach { r ->
        newList.add(r + value)
    }
    return newList
}

fun main() {
    println(superSet("abc"))
}
