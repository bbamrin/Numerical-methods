import org.ejml.simple.SimpleMatrix
import java.math.RoundingMode

class Lab1_1: MatrixPrinter() {
    fun luDecomposition(matrix: SimpleMatrix): Pair<SimpleMatrix, SimpleMatrix> {
        if ((matrix.numCols() == matrix.numRows()).not())
            throw IllegalArgumentException()
        val lower = SimpleMatrix(matrix.numRows(), matrix.numCols())
        val upper = SimpleMatrix(matrix.numRows(), matrix.numCols())
        for (i in 0 until matrix.numRows()) {
            for (k in i until matrix.numRows()) {
                var sum = 0.0
                for (j in 0 until i)
                    sum += (lower[i, j] * upper[j, k])
                upper[i,k] = matrix[i, k] - sum
            }
            for (k in i until matrix.numRows()) {
                if (i == k) {
                    lower[i, k] = 1.0
                    continue
                }
                var sum = 0.0
                for (j in 0 until i)
                    sum += (lower[k, j] * upper[j, i])
                lower[k,i] = (matrix[k, i] - sum)/upper[i,i]
            }
        }
        return Pair(lower, upper)
    }

    private fun forwardSubstitution(lower: SimpleMatrix, b: SimpleMatrix): SimpleMatrix {
        if (b.numCols() != 1 || b.numRows() != lower.numRows())
            throw IllegalArgumentException()
        val result = SimpleMatrix(b.numRows(), 1)
        for (i in 0 until b.numRows()) {
            var tmp = b[i, 0]
            for (j in 0 until i)
                tmp -= lower[i, j] * result[j, 0]
            result[i] = tmp / lower[i, i]
        }
        return result
    }

    private fun backwardSubstitution(upper: SimpleMatrix, b: SimpleMatrix): SimpleMatrix {
        if (b.numCols() != 1 || b.numRows() != upper.numRows())
            throw IllegalArgumentException()
        val result = SimpleMatrix(b.numRows(), 1)
        for (i in (b.numRows() - 1) downTo 0) {
            var tmp = b[i, 0]
            for (j in (i + 1) until b.numRows())
                tmp -= upper[i, j] * result[j, 0]
            result[i] = tmp / upper[i,i]
        }
        return result
    }

    fun luSolve(lower: SimpleMatrix, upper: SimpleMatrix, b: SimpleMatrix): SimpleMatrix {
        val y = forwardSubstitution(lower, b)
        return backwardSubstitution(upper, y)
    }



    fun luInverse(lower: SimpleMatrix, upper: SimpleMatrix): SimpleMatrix {
        if ((lower.numRows() != upper.numRows()) || (lower.numCols() != upper.numCols()) || (lower.numCols() != lower.numRows()))
            throw IllegalArgumentException()
        val identityMatrix = SimpleMatrix.identity(lower.numRows())
        val inversedCols: MutableList<SimpleMatrix> = mutableListOf()
        for (i in 1..identityMatrix.numCols()) {
            val b = identityMatrix.cols(i - 1, i)
            inversedCols.add(luSolve(lower,upper, b))
        }
        return SimpleMatrix(0,0).concatColumns(*inversedCols.toTypedArray())
    }

    fun luDeterminant(lower: SimpleMatrix, upper: SimpleMatrix): Double {
        if ((lower.numRows() != upper.numRows()) || (lower.numCols() != upper.numCols()) || (lower.numCols() != lower.numRows()))
            throw IllegalArgumentException()
        var d1 = 1.0
        var d2 = 1.0
        for (i in 0 until lower.numCols())
            d1 *= lower[i,i]
        for (i in 0 until upper.numCols())
            d2 *= upper[i,i]
        return d1*d2
    }
}