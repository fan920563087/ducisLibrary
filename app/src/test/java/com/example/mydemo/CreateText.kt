package com.example.mydemo

/**
 * @ClassName: CreateText
 * @Description:
 * @Author: Fan TaoTao
 * @Date: 2020/10/16
 */
internal class CreateText {


    fun createArray(){
        var startTime = System.currentTimeMillis()
        val temp = Array(1000){
            i -> i+1
        }
        var result = 0
        temp.forEach {
            result += it
            print("$it ,")
        }
        result /= temp.size
        var endTime = System.currentTimeMillis()
        println("array使用时间：${(endTime-startTime)}毫秒，平均值：$result")
    }

    fun createIntArray(){
        var startTime = System.currentTimeMillis()
        val temp = IntArray(1000){
                i -> i+1
        }
        var result = 0
        temp.forEach {
            result += it
            print("$it ,")
        }
        result /= temp.size
        var endTime = System.currentTimeMillis()
        println("intArray使用时间：${(endTime-startTime)}毫秒，平均值：$result")
    }
    fun createList(){
        var startTime = System.currentTimeMillis()
        val temp = mutableListOf<Int>()
        for (index in 1..1000){
            temp.add(index)
        }
        var result = temp.sum()
        result /= temp.size
        println(temp)
        var endTime = System.currentTimeMillis()
        println(temp.any { it == 10086 })
        println(temp.sumBy {
            it*2
        })
        println(temp.filter { it > 500 })
        println("list使用时间：${(endTime-startTime)}毫秒，平均值：$result")
    }
}