package com.mmj744

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Testing 2D")
        val aStar = AStar()
        println(aStar.test())
        println("Testing 3D")
        val aStar3 = AStar3()
        println(aStar3.test())
    }
}