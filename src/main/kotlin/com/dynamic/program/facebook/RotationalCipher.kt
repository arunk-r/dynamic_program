package com.dynamic.program.facebook


fun main(args : Array<String>) {
    println(rotationalCipher("abc-789", 3))
}

fun rotationalCipher(input: String, rotationFactor: Int): String {
    // Write your code here
    var output = ""
    for (c in input) {
        if(c.isUpperCase() || c.isLowerCase()) {
            val newAdd = rotationFactor/26;
            println(newAdd)
        } else if (c.isDigit()) {
            val newAdd = rotationFactor/10;
            println(newAdd)
        } else {
            output += c.toString()
        }
    }
    return output
}