package com.dynamic.program.google

/**
 * find all permutations for a given string.
 *
 * time complexity = O(n!)
 *
 * can I optimize more?
 * yes, using cache
 */
fun permutations(value: String): List<String> {
    val result = mutableSetOf<String>()
    for (i in value.indices) {
        val substr = when (i) {
            0 -> {
                value.substring(1)
            }

            value.length - 1 -> {
                value.substring(0, i)
            }

            else -> {
                value.substring(0, i) + value.substring(i + 1)
            }
        }
        prepareCombination(value[i].toString(), substr, result)
    }
    return result.toMutableList()
}

fun prepareCombination(permStr: String, remaining: String, output: MutableSet<String>) {
    if (remaining.length == 1) {
        output.add(permStr + remaining)
    } else {
        for (i in remaining.indices) {
            val substr = when (i) {
                0 -> {
                    remaining.substring(i + 1)
                }

                remaining.length - 1 -> {
                    remaining.substring(0, i)
                }

                else -> {
                    remaining.substring(0, i) + remaining.substring(i + 1)
                }
            }
            prepareCombination(permStr + remaining[i], substr, output)
        }
    }
}

fun main() {
    println(permutations("abcd"))
    println(permutations("tact coa"))
}
