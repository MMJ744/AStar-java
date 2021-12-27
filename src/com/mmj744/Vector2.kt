package com.mmj744

data class Vector2(val x: Int, val y: Int) {

    fun magnitude(): Int {
        return Math.sqrt((x * x + y * y).toDouble()).toInt()
    }

    fun sqrMagnitue(): Int {
        return x * x + y * y
    }

    operator fun unaryMinus(): Vector2 {
        return Vector2(-x, -y);
    }

    operator fun minus(v: Vector2): Vector2 {
        return Vector2(x - v.x, y - v.y);
    }

    operator fun plus(v: Vector2): Vector2 {
        return Vector2(x + v.x, y + v.y);
    }

    override fun equals(other: Any?): Boolean {
        return other is Vector2 && x == other.x && y == other.y;
    }

    override fun toString(): String {
        return "($x,$y)"
    }
}