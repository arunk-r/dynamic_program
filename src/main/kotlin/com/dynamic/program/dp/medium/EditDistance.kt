package com.dynamic.program.dp.medium

class EditDistance {
    fun minDistance(word1: String, word2: String): Int {
        return minDistance(word1.length,word2.length,word1,word2)
    }

    private fun minDistance(i: Int, j: Int, w1: String, w2: String): Int {
        if(j == 0) {
            return i
        }
        if (i == 0) {
            return j
        }
        if(w1[i-1] == w2[j-1]) {
            return minDistance(i-1, j-1, w1, w2)
        } else {
            val insert = minDistance(i,j-1, w1, w2)
            val delete = minDistance(i-1,j, w1, w2)
            val replace = minDistance(i-1,j-1, w1, w2)
            return minOf(insert, minOf(delete, replace))+1
        }
    }
}

// This is the DFA we have designed above
private val dfa: List<Map<String, Int>> = listOf(
    mapOf("digit" to 1, "sign" to 2, "dot" to 3),
    mapOf("digit" to 1, "dot" to 4, "exponent" to 5),
    mapOf("digit" to 1, "dot" to 3),
    mapOf("digit" to 4),
    mapOf("digit" to 4, "exponent" to 5),
    mapOf("sign" to 6, "digit" to 7),
    mapOf("digit" to 7),
    mapOf("digit" to 7)
)


fun main() {
    val s = "44e016912630333"
    println("2".toBigDecimal())
    println("0089".toBigDecimal())
    println("-0.1".toBigDecimal())
    println("+3.14".toBigDecimal())
    println("4.".toBigDecimal())
    println("-.9".toBigDecimal())
    println("2e10".toBigDecimal())
    println("-90E3".toBigDecimal())
    println("3e+7".toBigDecimal())
    println("+6e-1".toBigDecimal())
    println("53.5e93".toBigDecimal())
    println("-123.456e789".toBigDecimal())
    println("44e016912630333".toBigDecimal())
    //println(EditDistance().minDistance("horse", "ros"))
    //println(EditDistance().minDistance("intention", "execution"))
}
