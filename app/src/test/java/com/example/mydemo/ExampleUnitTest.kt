package com.example.mydemo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        var temp = CreateText()
//        temp.createArray()
//        temp.createIntArray()
        temp.createList()
        println("")

    }
}