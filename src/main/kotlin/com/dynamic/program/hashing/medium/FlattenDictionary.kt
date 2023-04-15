package com.dynamic.program.hashing.medium

class FlattenDictionary {
    fun flattenDictionary(dict: HashMap<String, Any>) : HashMap<String, String> {
        val result = hashMapOf<String, String>()
        flattenDictionary("", dict, result)
        return result
    }
    private fun flattenDictionary(key: String, dict: HashMap<String, Any>, result: HashMap<String, String>) {
        if (dict.isEmpty()) {
            return
        } else {
            dict.forEach{  (k, v) ->
                val newKey = if (key != "" && k == "") {
                    key
                } else if (key == "") {
                    k
                }
                else {
                    "$key.$k"
                }

                if (v is HashMap<*, *>) {
                    flattenDictionary(newKey, v as HashMap<String, Any>, result)
                } else {
                    result[newKey] = v.toString()
                }
            }
        }
    }
}

fun main() {
    val map = hashMapOf<String, Any>()
    map["Key1"] = "a"
    val map1 = hashMapOf<String, Any>()
    map1["a"] = "1"
    map1["b"] = "2"
    val map2 = hashMapOf<String, Any>()
    map2["a"] = "1"
    map1["c"] = map2
    map["Key2"] = map1

    println(FlattenDictionary().flattenDictionary(map, ))
}
