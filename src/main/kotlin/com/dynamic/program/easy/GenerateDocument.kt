package com.dynamic.program.easy

/**
 * 
You're given a string of available characters and a string representing a
document that you need to generate. Write a function that determines if you
can generate the document using the available characters. If you can generate
the document, your function should return true; otherwise, it
should return false.


You're only able to generate the document if the frequency of unique
characters in the characters string is greater than or equal to the frequency
of unique characters in the document string. For example, if you're given
characters = "abcabc" and document = "aabbccc" you
<b>cannot</b> generate the document because you're missing one c.


The document that you need to create may contain any characters, including
special characters, capital letters, numbers, and spaces.

Note: you can always generate the empty string ("").
Sample Input
characters = "Bste!hetsi ogEAxpelrt x "
document = "AlgoExpert is the Best!"

Sample Output
true


 */

fun generateDocument(characters: String, document: String): Boolean {
    // Write your code here.
    val chrMap = mutableMapOf<String,Int>()
    val docMap = mutableMapOf<String,Int>()
    for(c in characters.indices) {
        if(chrMap.keys.contains(characters[c].toString())) {
            chrMap[characters[c].toString()] = chrMap[characters[c].toString()]!! + 1
        } else{
            chrMap[characters[c].toString()] = 1
        }
    }

    for(c in document.indices) {
        if(docMap.keys.contains(document[c].toString())) {
            docMap[document[c].toString()] = docMap[document[c].toString()]!! + 1
        } else{
            docMap[document[c].toString()] = 1
        }
    }
    println(chrMap)
    println(docMap)

    docMap.forEach { (k, v) ->
        if (chrMap[k] == null || chrMap[k]!! < v) {
            return false
        }
    }
    return true
}