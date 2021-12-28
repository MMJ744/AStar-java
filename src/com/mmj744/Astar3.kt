package com.mmj744

class AStar3 {

    fun findShortestPath(map: List<Vector3>, start: Vector3, goal: Vector3): List<Vector3?>? {
        val path = ArrayList<Vector3?>()
        val openList = ArrayList<Node>()
        val noneVisitedNodes = ArrayList<Node>()
        var current: Node?
        for (v in map) {
            //first create nodes
            val n = Node(v, Int.MAX_VALUE, calcH(v, goal), Int.MAX_VALUE, v == goal, null)
            if (v == start) {
                n.g = 0
                openList.add(n)
            } else noneVisitedNodes.add(n)
        }
        if (openList.isEmpty()) return null
        do {
            openList.sortWith { x: Node, y: Node -> x.f.compareTo(y.f) }
            current = openList.removeAt(0) //Take node with lowest f value from the list
            noneVisitedNodes.remove(current)
            val neighbours = getNeighbours(current, noneVisitedNodes)
            //Calculate f values for neighbours
            for (neighbour in neighbours) {
                val temp = 1 + current.g + neighbour.h
                if (temp >= neighbour.f) continue
                neighbour.f = temp
                neighbour.parent = current
                neighbour.g = 1 + current.g
                openList.add(neighbour)
            }
        } while (!current!!.goal && openList.size > 0) //Until goal reached or no path found

        if (!current.goal) return null //No path found
        path.add(current.L)
        while (current!!.parent.also { current = it } != null) //Construct the path starting from goal
        {
            path.add(current!!.L)
        }
        path.reverse()
        return path
    }

    private fun calcH(n: Vector3, g: Vector3): Int //Applies the heuristic to calculate h
    {
        return (g - n).magnitude() //Using the Manhattan heuristic
    }

    private fun getNeighbours(n: Node?, map: List<Node>): List<Node> //gets all neighbouring nodes
    {
        return map.stream().filter { x: Node -> (x.L - n!!.L).sqrMagnitude() == 1 }.toList()
    }

    fun test(): Boolean {
        //Test Neighbour function
        val n1 = Node(Vector3(1, 0, 0))
        val n2 = Node(Vector3(0, 1, 0))
        val n3 = Node(Vector3(0, 0, 1))
        val list = ArrayList<Node>()
        list.add(n1)
        list.add(n2)
        list.add(n3)
        list.add(Node(Vector3(0, 0, 0)))
        list.add(Node(Vector3(1, 1, 0)))
        list.add(Node(Vector3(2, 2, 0)))
        list.add(Node(Vector3(3, 3, 0)))
        val n = Node(Vector3(0, 0, 0))
        val x = getNeighbours(n, list)
        var passed = x.contains(n1) && x.contains(n2) && x.contains(n3)
        val map = ArrayList<Vector3>()
        map.add(Vector3(0, 0, 0))
        map.add(Vector3(0, 0, 1))
        map.add(Vector3(0, 1, 1))
        map.add(Vector3(0, 2, 1))
        map.add(Vector3(0, 3, 1))
        map.add(Vector3(0, 3, 0))
        map.add(Vector3(0, 4, 0))
        map.add(Vector3(1, 4, 0))
        map.add(Vector3(1, 2, 0))
        map.add(Vector3(2, 2, 0))
        map.add(Vector3(2, 3, 0))
        map.add(Vector3(2, 4, 0))
        map.add(Vector3(2, 0, 0))
        map.add(Vector3(2, 1, 0))
        val path = findShortestPath(map, Vector3(0, 0, 0), Vector3(2, 4, 0))
        passed = passed && path != null
        println(path)
        return passed
    }


    data class Node(
        var L: Vector3,
        var g: Int = Int.MAX_VALUE,
        var h: Int = 0,
        var f: Int = Int.MAX_VALUE,
        var goal: Boolean = false,
        var parent: Node? = null
    )
}