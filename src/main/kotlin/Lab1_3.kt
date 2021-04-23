import org.ejml.simple.SimpleMatrix
import java.math.RoundingMode

class Lab1_3 {

    fun iterationSolve(matrix: SimpleMatrix, vector: SimpleMatrix, precision: Double): Pair<SimpleMatrix, Int> {
        val abEqPair = getEquivalent(matrix, vector)
        var itCount = 0
        if (vectorNorm(abEqPair.second) <= precision)
            return Pair(abEqPair.second, itCount)
        var prevSolution = abEqPair.second
        var currentSolution = SimpleMatrix(1, 1)
        while (true) {
            itCount++
            currentSolution = iteration(abEqPair, prevSolution)
            if (vectorNorm(currentSolution - prevSolution) <= precision)
                return Pair(currentSolution, itCount)
            prevSolution = currentSolution
        }
    }


    fun zeidelSolve(matrix: SimpleMatrix, vector: SimpleMatrix, precision: Double): Pair<SimpleMatrix, Int> {
        val abEqPair = getZeidelEquivalent(matrix, vector)
        var itCount = 0
        if (vectorNorm(abEqPair.second) <= precision)
            return Pair(abEqPair.second, itCount)
        var prevSolution = abEqPair.second
        var currentSolution = SimpleMatrix(1, 1)
        while (true) {
            itCount++
            currentSolution = iteration(abEqPair, prevSolution)
            if (vectorNorm(currentSolution - prevSolution) <= precision)
                return Pair(currentSolution, itCount)
            prevSolution = currentSolution
        }
    }

    private fun iteration(
        abEqPair: Pair<SimpleMatrix, SimpleMatrix>,
        prevSolution: SimpleMatrix? = null
    ): SimpleMatrix =
        if (prevSolution != null) abEqPair.second + abEqPair.first.mult(prevSolution) else abEqPair.second

    private fun getEquivalent(matrix: SimpleMatrix, vector: SimpleMatrix): Pair<SimpleMatrix, SimpleMatrix> {
        val eqMatrix = SimpleMatrix(matrix.numRows(), matrix.numCols())
        val eqVector = SimpleMatrix(matrix.numRows(), 1)
        for (i in 0 until matrix.numRows()) {
            for (j in 0 until matrix.numCols())
                eqMatrix[i, j] = if (j == i) 0.0 else -matrix[i, j] / matrix[i, i]
            eqVector[i, 0] = vector[i, 0] / matrix[i, i]
        }
        return Pair(eqMatrix, eqVector)
    }

    private fun getZeidelEquivalent(matrix: SimpleMatrix, vector: SimpleMatrix): Pair<SimpleMatrix, SimpleMatrix> {
        val left = SimpleMatrix(matrix.numRows(), matrix.numCols())
        val right = SimpleMatrix(matrix.numRows(), matrix.numCols())
        val abEqPair = getEquivalent(matrix, vector)
        val identityMatrix = SimpleMatrix.identity(matrix.numRows())
        for (i in 0 until abEqPair.first.numRows()) {
            for (j in 0 until abEqPair.first.numCols()) {
                if (j < i) left[i, j] = abEqPair.first[i, j]
                else right[i, j] = abEqPair.first[i, j]
            }
        }
        val tmp = (identityMatrix - left).invert()
        return Pair(tmp.mult(right), tmp.mult(abEqPair.second))
    }

    private fun vectorNorm(vector: SimpleMatrix): Double {
        var norm = 0.0
        (0 until vector.numRows()).forEach { norm += vector[it, 0] * vector[it, 0] }
        return Math.sqrt(norm)
    }

    fun printMatrixInt(matrix: SimpleMatrix) {

        for (i in 0 until matrix.numRows()) {
            for (j in 0 until matrix.numCols())
                print("${matrix[i, j].toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)} ")
            println()
        }
    }
}