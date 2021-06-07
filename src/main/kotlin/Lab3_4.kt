import kotlin.math.pow

class Lab3_4 {

    private fun diff(i:Int, nodes: List<Pair<Double, Double>>):Double {
        return (nodes[i].second - nodes[i - 1].second)/(nodes[i].first - nodes[i - 1].first)
    }

    private fun factorial(k : Int) :Int {
        if (k == 0)
            return 1
        return k * factorial(k - 1)
    }

    fun firstDerivative(x: Double, nodes: List<Pair<Double, Double>>): Double {
        val i = nodes.indexOfFirst { x <= it.first }
        val a = (diff(i + 1, nodes) - diff(i, nodes))/(nodes[i + 1].first - nodes[i - 1].first)
        return diff(i, nodes) + a * (2 * x - nodes[i - 1].first - nodes[i].first)
    }

    fun secondDerivative(x: Double, nodes: List<Pair<Double, Double>>): Double {
        val i = nodes.indexOfFirst { x <= it.first }
        val a = (diff(i + 1, nodes) - diff(i, nodes)) / (nodes[i + 1].first - nodes[i - 1].first)
        return 2 * a
    }


    fun firstDerivative1(x: Double, n: List<Pair<Double, Double>>): Double {
        val lab = Lab3_1()
        val diff1: Double = lab.dividedDifference(n.subList(0, 2))
        val diff2: Double = lab.dividedDifference(n.subList(0, 3))
        val diff3: Double = lab.dividedDifference(n.subList(0, 4))
        val diff4: Double = lab.dividedDifference(n.subList(0, 5))

        var b1 =
            4 * diff4 * x.pow(3) + 3 * x.pow(2) * (-(n[0].first + n[1].first + n[2].first + n[3].first) * diff4 + diff3);
        var a2 = (n[1].first + n[2].first + n[3].first) * n[0].first
        var a3 = (n[2].first + n[3].first) * n[1].first
        var a4 = n[2].first * n[3].first
        var a5 = (a2 + a3 + a4) * diff4
        var a6 = -diff2 * (n[0].first + n[1].first + n[2].first) + diff2
        var b2 = 2 * (a5 + a6) * x
        var a7 = ((-n[2].first - n[3].first) * n[1].first - n[2].first * n[3].first) * n[0].first
        var a8 = -n[1].first * n[2].first * n[3].first
        var a9 = (a7 + a8) * diff4
        var a10 =
            (n[1].first * diff3 + n[2].first * diff3 - diff2) * n[0].first + (n[2].first * diff3 - diff2) * n[1].first + diff1
        var b3 = (a9 + a10)
        return b1 + b2 + b3
    }

    fun secondDerivative1(x: Double, n: List<Pair<Double, Double>>): Double {
        val lab = Lab3_1()
        val diff1: Double = lab.dividedDifference(n.subList(0, 2))
        val diff2: Double = lab.dividedDifference(n.subList(0, 3))
        val diff3: Double = lab.dividedDifference(n.subList(0, 4))
        val diff4: Double = lab.dividedDifference(n.subList(0, 5))

        var b1 = 12 * diff4 * x.pow(2) + 6 * x * (-(n[0].first + n[1].first + n[2].first + n[3].first) * diff4 + diff3);
        var a2 = (n[1].first + n[2].first + n[3].first) * n[0].first
        var a3 = (n[2].first + n[3].first) * n[1].first
        var a4 = n[2].first * n[3].first
        var a5 = (a2 + a3 + a4) * diff4
        var a6 = -diff2 * (n[0].first + n[1].first + n[2].first) + diff2
        var b2 = 2 * (a5 + a6)
        return b1 + b2
    }
}