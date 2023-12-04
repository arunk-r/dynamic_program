package com.dynamic.program.data.structures

import java.util.*
import kotlin.reflect.KFunction1

class Challenge5 {
    fun const(a: Any, b: Any): ((Any, Any) -> Any) -> Any {
        fun pair(f: (Any, Any) -> Any): Any {
            return f(a, b)
        }
        return ::pair
    }

    fun car(f: ((Any, Any) -> Any) -> Any): Any  {
        fun left(a: Any, b: Any): Any {
            return a
        }
        return f(::left)
    }

    fun cdr(f: ((Any, Any) -> Any) -> Any): Any  {
        fun right(a: Any, b: Any): Any {
            return b
        }
        return f(::right)
    }

    fun scheduler(f: KFunction1<Int, Unit>, n: Long) {
        Timer().schedule(
            object : TimerTask() {
                override fun run() {
                    f(1)
                }
            },
            n
        )
    }
}

fun main() {
    val c = Challenge5()
    println(c.car(c.const(2,3)))
    println(c.cdr(c.const(2,3)))
    c.scheduler(::print, 5)
}

fun print(n: Int) {
    println("I'm done! $n")
}
