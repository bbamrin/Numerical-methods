import org.ejml.simple.SimpleMatrix
import kotlin.math.pow

class Lab3_2: MatrixPrinter() {

    private val triDiagonalSolver = Lab1_2()

    private fun h(nodes: List<Pair<Double, Double>>, i: Int): Double {
        return nodes[i].first - nodes[i - 1].first
    }

    private fun getRightValue(nodes: List<Pair<Double, Double>>, i: Int): Double {
        return 3 * ((nodes[i].second - nodes[i - 1].second)/h(nodes, i)
                - (nodes[i - 1].second - nodes[i - 2].second)/h(nodes, i - 1))
    }

    private fun getCVector(nodes: List<Pair<Double, Double>>): SimpleMatrix {
        val rightVector = SimpleMatrix(nodes.count() - 2, 1)
        val cMatrix = SimpleMatrix(nodes.count() - 2, nodes.count() - 2)
        val solution = SimpleMatrix(nodes.count() - 2, 1)
        cMatrix[0, 0] = 2 * (h(nodes, 1) + h(nodes, 2))
        cMatrix[0, 1] = h(nodes, 2)
        rightVector[0,0] = getRightValue(nodes, 2)
        for (i in 1 until nodes.count() - 3) {
            cMatrix[i, i - 1] = h(nodes, i + 1)
            cMatrix[i, i] = 2 * (h(nodes, i + 1) + h(nodes, i + 2))
            cMatrix[i, i + 1] = h(nodes, i + 2)
            rightVector[i, 0] = getRightValue(nodes, i + 2)
        }
        cMatrix[nodes.count() - 3, nodes.count() - 4] = h(nodes, nodes.count() - 2)
        cMatrix[nodes.count() - 3, nodes.count() - 3] = 2 * (h(nodes, nodes.count() - 2) + h(nodes, nodes.count() - 1))
        rightVector[nodes.count() - 3, 0] = getRightValue(nodes, nodes.count() - 1)
        triDiagonalSolver.triSolve(matrix = cMatrix, vector = rightVector, solution = solution)
        return solution
    }

    private fun getCubicSplinesTable(nodes: List<Pair<Double, Double>>):List<Pair<ClosedFloatingPointRange<Double>, List<Double>>> {
        var cVector = SimpleMatrix(1, 1)
        cVector = cVector.concatRows(getCVector(nodes))
        val cubicSplinesTable: MutableList<Pair<ClosedFloatingPointRange<Double>, List<Double>>> = mutableListOf()
        for (i in 1 until nodes.count() - 1) {
            val h = h(nodes, i)
            cubicSplinesTable.add(
                Pair(
                    nodes[i - 1].first.rangeTo(nodes[i].first),
                    listOf(
                        nodes[i - 1].second,
                        (nodes[i].second - nodes[i - 1].second)/h - h*(cVector[i, 0] + 2*cVector[i - 1, 0])/3,
                        cVector[i - 1],
                        (cVector[i, 0] - cVector[i - 1, 0])/(3*h)
                    )
                )
            )
        }
        val last = nodes.count() - 1
        val h = h(nodes, last)
        cubicSplinesTable.add(
            Pair(
                nodes[last - 1].first.rangeTo(nodes[last].first),
                listOf(
                    nodes[last - 1].second,
                    (nodes[last].second - nodes[last - 1].second)/h - 2*h*cVector[last - 1, 0]/3,
                    cVector[last - 1],
                    -cVector[last - 1, 0]/(3*h)
                )
            )
        )
        return cubicSplinesTable
    }

    fun cubicSplineInterpolation(x: Double, nodes: List<Pair<Double, Double>>): Double {
        val spline = getCubicSplinesTable(nodes).first{ it.first.contains(x) }
        val t = x - spline.first.start
        return spline.second[0] + spline.second[1]*t + spline.second[2] * t.pow(2) + spline.second[3] * t.pow(3)
    }
}