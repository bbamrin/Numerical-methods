import org.ejml.simple.SimpleMatrix
import java.math.RoundingMode

class Lab1_2 {
    fun triSolve(
        matrix: SimpleMatrix,
        vector: SimpleMatrix,
        solution: SimpleMatrix,
        p: Double = 1.0,
        q: Double = 1.0,
        i: Int
    ): Double {
        val a = if (i > 0) matrix[i, i - 1] else 0.0
        val b = matrix[i, i]
        val c = if (i < matrix.numRows() - 1) matrix[i, i + 1] else 0.0
        val d = vector[i, 0]
        val p1: Double = -c / (b + a * p)
        val q1: Double = (d - a * q) / (b + a * p)
        var x = 0.0
        if (i < matrix.numRows() - 1) {
            x = p1 * triSolve(matrix = matrix, vector = vector, solution = solution, p = p1, q = q1, i = i + 1) + q1
            solution[i, 0] = x
            return x
        }
        solution[i, 0] = q1
        return q1
    }

    fun printMatrixInt(matrix: SimpleMatrix) {

        for (i in 0 until matrix.numRows()) {
            for (j in 0 until matrix.numCols())
                print("${matrix[i, j].toBigDecimal().setScale(2, RoundingMode.HALF_DOWN)} ")
            println()
        }
    }
}