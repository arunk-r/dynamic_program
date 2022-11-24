package com.dynamic.program.strings_arrays

/**
 * rotate matrix 90 degree to right
 *
 * time ~ O(n^2)
 */
fun rotateMatrix(l: ArrayList<ArrayList<Int>>) {
    if (l.size == 0 || l.size != l[0].size) return
    for (i in l.indices) {
        for (j in i until l.size) {
            l[i][j] = l[j][i].also { l[j][i] = l[i][j] }
        }
    }

    for (i in 0 until l.size) {
        for (j in 0 ..l.size/2) {
            l[i][l.size - 1 - j] = l[i][j].also { l[i][j] = l[i][l.size - 1 - j] }
        }
    }
}

fun main() {
    val matrix = arrayListOf(arrayListOf(9,8,7), arrayListOf(6,5,4), arrayListOf(3,2,1))
    rotateMatrix(matrix)
    println(matrix)
}
