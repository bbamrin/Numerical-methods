import org.ejml.data.Matrix
import org.ejml.simple.SimpleMatrix
import kotlin.math.*

class Lab2_2: MatrixPrinter() {
    companion object {

        fun f1(x1: Double, x2: Double): Double = x1 - cos(x2) - 1

        fun f2(x1: Double, x2: Double): Double = x2 - log10(x1 + 1) - 1

        fun df1dx1(x1: Double, x2: Double): Double = 1.0

        fun df1dx2(x1: Double, x2: Double): Double = sin(x2)

        fun df2dx1(x1: Double, x2: Double): Double = -1/((x1 + 1)* ln(10.0))

        fun df2dx2(x1: Double, x2: Double): Double = 1.0

        fun f1Iteration(x1: Double, x2: Double): Double = 1 + cos(x2)

        fun f2Iteration(x1: Double, x2: Double): Double = 1 + log10(x1 + 1)

        const val iterationQ = 0.867
    }

    private fun vectorNorm(vector: SimpleMatrix): Double {
        var norm = 0.0
        (0 until vector.numRows()).forEach { if (abs(vector[it, 0]) > abs(norm)) norm = vector[it, 0] }
        return abs(norm)
    }

    private fun jacobiMatrix(
        x: Double,
        df1dx1: (Double)->Double,
        df1dx2: (Double)->Double,
        df2dx1: (Double)->Double,
        df2dx2: (Double)->Double
    ) : SimpleMatrix {
        return SimpleMatrix(
            arrayOf(
                doubleArrayOf(df1dx1(x), df1dx2(x)),
                doubleArrayOf(df2dx1(x), df2dx2(x)),
            )
        )
    }

    private fun fVector(
        x: Double,
        f1: (Double)->Double,
        f2: (Double)->Double,
    ) : SimpleMatrix {
        return SimpleMatrix(
            arrayOf(
                doubleArrayOf(f1(x)),
                doubleArrayOf(f2(x)),
            )
        )
    }

    fun newtonSolve(
        x1Start: Double,
        x2Start: Double,
        f1: (Double, Double) -> Double,
        f2: (Double, Double) -> Double,
        df1dx1: (Double, Double)->Double,
        df1dx2: (Double, Double)->Double,
        df2dx1: (Double, Double)->Double,
        df2dx2: (Double, Double)->Double,
        precision: Double
    ): SimpleMatrix {
        var x = SimpleMatrix(2, 1)
        x[0,0] = x1Start
        x[1,0] = x2Start
        while (true) {
            val jacobiMatrix = SimpleMatrix(
                arrayOf(
                        doubleArrayOf(df1dx1(x[0,0], x[1,0]), df1dx2(x[0,0], x[1,0])),
                        doubleArrayOf(df2dx1(x[0,0], x[1,0]), df2dx2(x[0,0], x[1,0])),
                )
            )
            val matrixA1 = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(f1(x[0,0], x[1,0]), df1dx2(x[0,0], x[1,0])),
                    doubleArrayOf(f2(x[0,0], x[1,0]), df2dx2(x[0,0], x[1,0])),
                )
            )
            val matrixA2 = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(df1dx1(x[0,0], x[1,0]), f1(x[0,0], x[1,0])),
                    doubleArrayOf(df2dx1(x[0,0], x[1,0]), f2(x[0,0], x[1,0])),
                )
            )
            val vector = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(matrixA1.determinant()/jacobiMatrix.determinant()),
                    doubleArrayOf(matrixA2.determinant()/jacobiMatrix.determinant()),
                )
            )
            if (vectorNorm(vector) <= precision)
                return x - vector
            x -= vector
        }
    }

    fun iterationSolve(
        x1Start: Double,
        x2Start: Double,
        f1: (Double, Double) -> Double,
        f2: (Double, Double) -> Double,
        q: Double,
        precision: Double
    ): SimpleMatrix {
        var x = SimpleMatrix(2, 1)
        x[0,0] = x1Start
        x[1,0] = x2Start
        while (true) {
            val xNext  = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(f1(x[0,0], x[1,0])),
                    doubleArrayOf(f2(x[0,0], x[1,0])),
                )
            )
            if (q*vectorNorm(xNext - x) <= precision * (1 - q))
                return xNext
            x = xNext
        }
    }
}