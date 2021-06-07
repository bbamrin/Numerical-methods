class Lab3_1 {

    fun lagrangeInterpolation(x: Double, nodes: List<Pair<Double, Double>>): Double {
        var result = 0.0
        for (i in 0 until nodes.count()) {
            var product = 1.0
            for (j in 0 until nodes.count()) {
                if (i != j)
                    product *= (x - nodes[j].first)/(nodes[i].first - nodes[j].first)
            }
            result += nodes[i].second*product
        }
        return result
    }

    fun dividedDifference(nodes: List<Pair<Double, Double>>): Double {
        if (nodes.count() == 1)
            return nodes[0].second
        val subArgsList1 = nodes.subList(0, nodes.count() - 1)
        val subArgsList2 = nodes.subList(1, nodes.count())
        return (dividedDifference(subArgsList1) - dividedDifference(subArgsList2)) /
                (nodes.first().first - nodes.last().first)
    }

    fun newtonInterpolation(x: Double, nodes: List<Pair<Double, Double>>): Double {
        var result = 0.0
        for (i in 0 until nodes.count()) {
            var product = 1.0
            for (j in 0 until i) {
                product *= x - nodes[j].first
            }
            result += product*dividedDifference(nodes.subList(0, i + 1))
        }
        return result
    }

}