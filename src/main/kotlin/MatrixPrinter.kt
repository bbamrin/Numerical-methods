import org.ejml.simple.SimpleMatrix
import java.math.RoundingMode

open class MatrixPrinter {
    fun printMatrixInt(matrix: SimpleMatrix) {

        for (i in 0 until matrix.numRows()) {
            for (j in 0 until matrix.numCols())
                print("${matrix[i, j].toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)} ")
            println()
        }
    }
}