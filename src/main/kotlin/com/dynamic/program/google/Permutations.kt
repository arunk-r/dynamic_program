package com.dynamic.program.google

fun permutations(value: String): List<String> {
    val result = mutableSetOf<String>()
    for (i in value.indices) {
        result.addAll(prepareCombination(value[i].toString(), value.replace(value[i].toString(), "")))
    }
    return result.toMutableList()
}

fun prepareCombination(swapChar: String, remaining: String): MutableSet<String> {
    val result = mutableSetOf<String>()
    result.add("$swapChar$remaining")
    for (i in 1 until remaining.length) {
        result.add("${remaining.substring(0, i)}$swapChar${remaining.substring(i)}")
    }
    result.add("$remaining$swapChar")
    return result
}

fun main() {
    println(permutations("abcd"))
}
