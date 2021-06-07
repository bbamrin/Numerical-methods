import org.ejml.simple.SimpleMatrix
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.roundToInt

class Lab4_2: MatrixPrinter() {

    companion object {
        fun f(x: Double, y: Double, z: Double): Double = (x*y - 2 * z)/x

        fun g(x: Double, y: Double, z: Double): Double = z

        fun q(x: Double): Double = -1.0

        fun p(x: Double): Double = 2/x

        fun preciseSolution(x: Double) = exp(-x)/x

        const val h = 0.1

        var y0 = exp(-1.0)

        var y1 = exp(-2.0)/2

        var z0 =  1.0

        var z1 =  0.8
    }

    private val cauchySolver = Lab4_1()

    private val triDiagonalSolver: Lab1_2 = Lab1_2()

    fun shootingMethod(
        xRes: Double,
        a: Double,
        b: Double,
        f: (Double, Double, Double) -> Double,
        g: (Double, Double, Double) -> Double,
        h: Double,
        y0: Double,
        y1: Double,
        z0: Double,
        z1: Double,
        x0: Double,
        precision: Double
    ) : Double {
        var yRes1 = cauchySolver.rungeKuttaMethod(b, a, b, h, f, g, y0, z0, x0).first
        var yRes2 = cauchySolver.rungeKuttaMethod(b, a, b, h, f, g, y0, z1, x0).first
        var nTmp: Double
        var n1: Double = z1
        var n2 = z1 - (z1 - z0)*(yRes2 - y1)/(yRes2 - yRes1)
        while (abs(yRes2 - y1) > precision) {
            yRes1 = yRes2
            yRes2 = cauchySolver.rungeKuttaMethod(b, a, b, h, f, g, y0, n2, x0).first
            nTmp = n2
            n2 -= (n2 - n1) * (yRes2 - y1) / (yRes2 - yRes1)
            n1 = nTmp
        }
        return cauchySolver.rungeKuttaMethod(xRes, a, b, h, f, g, y0, n2, x0).first
    }

    fun finiteDifferenceMethod(
        a: Double,
        b: Double,
        f: (Double, Double, Double) -> Double,
        g: (Double, Double, Double) -> Double,
        p: (Double) -> Double,
        q: (Double) -> Double,
        h: Double,
        y0: Double,
        y1: Double
    ) : List<Pair<Double, Double>> {
        val n = (abs(b - a)/h).roundToInt()
        var x = a + h
        val triDiagonalMatrix = SimpleMatrix(n - 1, n - 1)
        val rightVector = SimpleMatrix(n - 1 , 1)
        val solution = SimpleMatrix(n - 1, 1)
        val result = mutableListOf<Pair<Double, Double>>()

        rightVector[0] = -(1 - p(x)*h/2)*y0
        triDiagonalMatrix[0, 0] = (-2 + h.pow(2)*q(x))
        triDiagonalMatrix[0, 1] = (1 + p(x)*h/2)
        for (i in 1..n-3) {
            triDiagonalMatrix[i, i - 1] = (1 - p(x)*h/2)
            triDiagonalMatrix[i, i] = (-2 + h.pow(2)*q(x))
            triDiagonalMatrix[i, i + 1] = (1 + p(x)*h/2)
            rightVector[i] = 0.0
            x += h
        }
        rightVector[n-2] = -(1 + p(x)*h/2)*y1
        triDiagonalMatrix[n - 2, n - 3] = (1 - p(x)*h/2)
        triDiagonalMatrix[n - 2, n - 2] = (-2 + h.pow(2)*q(x))
        triDiagonalSolver.triSolve(triDiagonalMatrix, rightVector, solution)
        x = a
        result.add(Pair(x, y0))
        for (i in 0 until solution.numRows()) {
            x += h
            result.add(Pair(x, solution[i]))
        }
        x += h
        result.add(Pair(x, y1))
        return result
    }
}