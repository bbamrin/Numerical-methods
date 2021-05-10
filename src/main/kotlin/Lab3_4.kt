class Lab3_4 {

    private fun diff(i:Int, nodes: List<Pair<Double, Double>>):Double {
        return (nodes[i].second - nodes[i - 1].second)/(nodes[i].first - nodes[i - 1].first)
    }

    fun firstDerivative(x: Double, nodes: List<Pair<Double, Double>>): Double {
        val i = nodes.indexOfFirst { x <= it.first }
        val a = (diff(i + 1, nodes) - diff(i, nodes))/(nodes[i + 1].first - nodes[i - 1].first)
        return diff(i, nodes) + a * (2 * x - nodes[i - 1].first - nodes[i].first)
    }

    fun secondDerivative(x: Double, nodes: List<Pair<Double, Double>>): Double {
        val i = nodes.indexOfFirst { x <= it.first }
        val a = (diff(i + 1, nodes) - diff(i, nodes))/(nodes[i + 1].first - nodes[i - 1].first)
        return 2*a
    }
}