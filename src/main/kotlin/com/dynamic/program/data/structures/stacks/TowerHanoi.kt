package com.dynamic.program.data.structures.stacks

class TowerHanoi {
    fun towerOfHanoi(n: Int, source: Int, target: Int, auxiliary: Int) {
        if(n > 0) {
            towerOfHanoi(n-1, source, auxiliary, target)
            println("Move disk $n from $source to $target")
            towerOfHanoi(n-1, auxiliary, target, source)
        }
    }
}

fun main() {
    val t = TowerHanoi()
    println(t.towerOfHanoi(3, 1, 3, 2))
}
