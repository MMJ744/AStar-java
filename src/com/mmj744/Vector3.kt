package com.mmj744

import kotlin.math.sqrt

data class Vector3(val x: Int, val y: Int, val z: Int) {

    fun magnitude(): Int {
        return sqrt((x * x + y * y + z * z).toDouble()).toInt()
    }

    fun sqrMagnitue(): Int {
        return x * x + y * y + z * z;
    }

    operator fun unaryMinus(): Vector3 {
        return Vector3(-x, -y, -z);
    }

    operator fun minus(v: Vector3): Vector3 {
        return Vector3(x - v.x, y - v.y, z - v.z);
    }

    operator fun plus(v: Vector3): Vector3 {
        return Vector3(x + v.x, y + v.y, z + v.z);
    }

    override fun equals(other: Any?): Boolean {
        return other is Vector3 && x == other.x && y == other.y && z == other.z;
    }

    override fun toString(): String {
        return "($x,$y,$z)"
    }
}