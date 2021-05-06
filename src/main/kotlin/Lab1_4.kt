import org.ejml.simple.SimpleMatrix
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Lab1_4: MatrixPrinter() {

    private fun findNonDiagMax(matrix: SimpleMatrix): Pair<Int, Int> {
        var max: Double = Double.MIN_VALUE
        var maxI = 0
        var maxJ = 0
        for (i in 0 until matrix.numRows()) {
            for (j in 0 until matrix.numCols()) {
                if (i != j && matrix[i, j] > max) {
                    max = matrix[i, j]
                    maxI = i
                    maxJ = j
                }
            }
        }
        return Pair(maxI, maxJ)
    }

    private fun calcRotationAngle(matrix: SimpleMatrix, max: Pair<Int, Int>): Double {
        if(matrix[max.first, max.first] == matrix[max.second, max.second])
            return Math.PI/4
        return atan(
            (2*matrix[max.first, max.second]) / (matrix[max.first, max.first] - matrix[max.second, max.second])
        )/2
    }

    private fun getUMatrix(angle: Double, max: Pair<Int, Int>, size: Int): SimpleMatrix {
        val matrix = SimpleMatrix.identity(size)
        matrix[max.first, max.first] = cos(angle)
        matrix[max.second, max.second] = cos(angle)
        matrix[max.first, max.second] = -sin(angle)
        matrix[max.second, max.first] = sin(angle)
        return matrix
    }

    private fun isPrecisionReached(matrix: SimpleMatrix, precision: Double): Boolean {
        var sum = 0.0
        for (i in 0 until matrix.numRows()) {
            for (j in 0 until i) {
                sum += matrix[i, j]*matrix[i, j]
            }
        }
        return sqrt(sum) < precision
    }

    fun rotationMethod(matrix: SimpleMatrix, precision: Double): Pair<SimpleMatrix, SimpleMatrix> {
        var eigenVectorsMatrix: SimpleMatrix = SimpleMatrix.identity(matrix.numRows())
        var matrixA = matrix
        var i = 0
        while (true) {
            val max = findNonDiagMax(matrixA)
            val matrixU = getUMatrix(calcRotationAngle(matrixA, max), max, matrixA.numRows())
            eigenVectorsMatrix = eigenVectorsMatrix.mult(matrixU)
            matrixA = matrixU.transpose().mult(matrixA).mult(matrixU)
            i++
            if (isPrecisionReached(matrixA, precision)) {
                println("iterations: $i")
                return Pair(matrixA, eigenVectorsMatrix)
            }
        }
    }
}