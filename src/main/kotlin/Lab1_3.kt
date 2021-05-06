import org.ejml.simple.SimpleMatrix
import java.math.RoundingMode
import kotlin.math.sqrt

class Lab1_3: MatrixPrinter() {

    private fun vectorNorm(vector: SimpleMatrix): Double {
        var norm = 0.0
        (0 until vector.numRows()).forEach { norm += vector[it, 0] * vector[it, 0] }
        return sqrt(norm)
    }

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

    private fun iteration(
        abEqPair: Pair<SimpleMatrix, SimpleMatrix>,
        prevSolution: SimpleMatrix
    ): SimpleMatrix = abEqPair.second + abEqPair.first.mult(prevSolution)

    fun iterationSolve(matrix: SimpleMatrix, vector: SimpleMatrix, precision: Double): Pair<SimpleMatrix, Int> {
        val abEqPair = getEquivalent(matrix, vector)
        var itCount = 0
        if (vectorNorm(abEqPair.second) <= precision)
            return Pair(abEqPair.second, itCount)
        var prevSolution = abEqPair.second
        var currentSolution: SimpleMatrix
        while (true) {
            itCount++
            currentSolution = iteration(abEqPair, prevSolution)
            if (vectorNorm(currentSolution - prevSolution) <= precision)
                return Pair(currentSolution, itCount)
            prevSolution = currentSolution
        }
    }

    private fun zeidelIteration(
        abEqPair: Pair<SimpleMatrix, SimpleMatrix>,
        prevSolution: SimpleMatrix
    ): SimpleMatrix {
        val solution = SimpleMatrix(abEqPair.first.numRows(), 1)

        for (i in 0 until abEqPair.first.numRows()) {
            solution[i, 0] += abEqPair.second[i, 0]
            for (j in 0 until abEqPair.first.numRows()) {
                solution[i, 0] +=
                    abEqPair.first[i, j] * if (j >= i) prevSolution[j, 0] else solution[j, 0]
            }
        }
        return solution
    }

    fun zeidelSolve(matrix: SimpleMatrix, vector: SimpleMatrix, precision: Double): Pair<SimpleMatrix, Int> {
        val abEqPair = getEquivalent(matrix, vector)
        var itCount = 0
        if (vectorNorm(abEqPair.second) <= precision)
            return Pair(abEqPair.second, itCount)
        var prevSolution = abEqPair.second
        var currentSolution: SimpleMatrix
        while (true) {
            itCount++
            currentSolution = zeidelIteration(abEqPair, prevSolution)
            if (vectorNorm(currentSolution - prevSolution) <= precision)
                return Pair(currentSolution, itCount)
            prevSolution = currentSolution
        }
    }

}