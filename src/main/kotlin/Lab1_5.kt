import org.ejml.simple.SimpleMatrix
import kotlin.math.sign
import kotlin.math.sqrt

class Lab1_5: MatrixPrinter() {

    private fun getHouseholderVector(matrix: SimpleMatrix, col: Int): SimpleMatrix {
        val vector = SimpleMatrix(matrix.numRows(), 1)
        var sum = 0.0
        for (i in 0 until matrix.numRows()) {
            if (i < col)
                vector[i, 0] = 0.0
            if (i == col) {
                for (j in i until matrix.numRows())
                    sum += matrix[j, col] * matrix[j, col]
                vector[i,0] = matrix[col, col] + sign(matrix[col,col]) * sqrt(sum)
            }
            if (i > col)
                vector[i, 0] = matrix[i,col]
        }
        return vector
    }

    private fun getHouseholderMatrix(matrix: SimpleMatrix, col: Int): SimpleMatrix {
        val v = getHouseholderVector(matrix, col)
        val divider = v.transpose().mult(v)
        val tmp = (v.mult(v.transpose())).divide(divider[0, 0])
        return SimpleMatrix.identity(matrix.numRows()) - tmp - tmp
    }

    fun getQRDecomposition(matrix: SimpleMatrix): Pair<SimpleMatrix, SimpleMatrix> {

        var matrixQ = SimpleMatrix.identity(matrix.numRows())
        var matrixR = matrix

        for (i in 0 until matrix.numRows()) {
            val matrixH = getHouseholderMatrix(matrixR, i)
            matrixQ = matrixQ.mult(matrixH)
            matrixR = matrixH.mult(matrixR)
        }
        return Pair(matrixQ, matrixR)
    }


    private fun isPrecisionReached(matrix: SimpleMatrix, precision: Double): Boolean {
        var sum = 0.0
        for (i in 0 until matrix.numRows()) {
            for (j in 0 until i) {
                if (i > j + 1)
                    sum += matrix[i, j]*matrix[i, j]
            }
        }
        return sqrt(sum) <= precision
    }

    fun getEigenvaluesMatrix(matrix: SimpleMatrix, precision: Double): SimpleMatrix {
        var matrixA = matrix

        while (!isPrecisionReached(matrixA, precision)) {
            val qrPair = getQRDecomposition(matrixA)
            matrixA = qrPair.second.mult(qrPair.first)
        }
        return matrixA
    }

}