package com.liuyf.demos.kotlin

fun main(args: Array<String>) {

    println("Hello, World!")

    // Using string templates
    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"

    println(s2)

    // Using conditional expressions
    fun maxOf(a: Int, b: Int): Int {

        if (a > b) {
            return a
        } else {
            return b
        }
    }

    println(maxOf(5, 10))

    // Using if as expression
    fun maxOfTwo(a: Int, b: Int) = if (a > b) a else b

    println(maxOfTwo(15, 10))

    println("------------------------------------------")
    // Using a for loop
    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

    println("------------------------------------------")

    // Using a while loop
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index ++
    }

    println("------------------------------------------")

    // Using when expression
    fun describe(obj: Any): String =
            when (obj) {
                1 -> "one"
                "Hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "Not a string"
                else -> "Unknow"
            }

    println(describe("abc"))
    println(describe(100))
    println(describe(false))

    println("------------------------------------------")

    // Using ranges
    val x = 10
    val y = 9
    if (x in 1 .. y + 1) {
        println("fits in range")
    }

    println("------------------------------------------")

    // Iterating over a range
    for (z in 1 .. 5) {
        println(z)
    }

    println("------------------------------------------")

    // Read-only map
    val map = mapOf(
            "a" to 1,
            "b" to 2,
            "c" to 3
    )

    for ((k, v) in map) {
        println("$k -> $v")
    }
}