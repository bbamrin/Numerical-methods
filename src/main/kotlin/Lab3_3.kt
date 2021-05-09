import org.ejml.simple.SimpleMatrix
import java.nio.DoubleBuffer
import kotlin.math.PI
import kotlin.math.pow

class Lab3_3: MatrixPrinter() {

    private val luSolver = Lab1_1()

    private fun getLeastSquaresPolynomial(nodes: List<Pair<Double, Double>>, degree: Int): List<Double> {
        val coefficientMatrix = SimpleMatrix(degree + 1, degree + 1)
        val rightPartVector = SimpleMatrix(degree + 1, 1)
        for (i in 0..degree) {
            for (j in 0..degree) {
                var sum = 0.0
                for (k in 0..nodes.lastIndex)
                    sum += nodes[k].first.pow(j + i)
                coefficientMatrix[i,j] = sum
            }
            var sum = 0.0
            for (k in 0..nodes.lastIndex)
                sum += nodes[k].first.pow(i) * nodes[k].second
            rightPartVector[i, 0] = sum
        }
        val luPair = luSolver.luDecomposition(coefficientMatrix)
        val resultVector = luSolver.luSolve(luPair.first, luPair.second, rightPartVector)
        val resultList = mutableListOf<Double>()
        for (i in 0 until resultVector.numRows())
            resultList.add(resultVector[i, 0])
        return resultList
    }

    fun leastSquaresInterpolation(x: Double, nodes: List<Pair<Double, Double>>, degree: Int): Double {
        var res = 0.0
        val polynomial = getLeastSquaresPolynomial(nodes, degree)
        for (i in 0 until polynomial.count()) {
            res += polynomial[i]*x.pow(i)
        }
        return res
    }

    fun getSquaredResidualsSum(nodes: List<Pair<Double, Double>>, degree: Int): Double {
        var res = 0.0
        for (i in 0..nodes.lastIndex) {
            res += (leastSquaresInterpolation(nodes[i].first, nodes, degree) - nodes[i].second).pow(2)
        }
        return res
    }
}