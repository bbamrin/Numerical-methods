import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow

class Lab2_1 {

    companion object {
        fun f(x: Double): Double = x*x*x + x*x - x - 0.5

        fun df(x: Double): Double = 3*x*x + 2*x - 1

        fun fIteration(x: Double) = Math.cbrt(0.5 + x - x*x)

        fun fIterationTest(x: Double) = ln(4 - 3*x)/2

        const val iterationQ = 0.37

        const val iterationQTest = 0.64

        const val iterationX0 = (0.8+0.9)/2

        const val iterationX0Test = 0.475

        const val newtonX0 = 0.9
    }

    fun newtonSolve(
        f: (Double)->Double,
        df: (Double) -> Double,
        x: Double,
        precision: Double,
        itCount: Int = 0
    ):Pair<Double, Int> {
        val x1 = x - f(x)/df(x)
        if (abs(x1 - x) < precision)
            return Pair(x1, itCount)
        return newtonSolve(f, df, x1, precision, itCount + 1)
    }

    fun iterationSolve(
        f: (Double)->Double,
        x: Double,
        q: Double,
        precision: Double,
        itCount: Int = 0
    ):Pair<Double, Int> {
        val x1 = f(x)
        if (abs(x1 - x)*q <= precision * (1-q))
            return Pair(x1, itCount)
        return iterationSolve(f, x1, q, precision, itCount + 1)
    }
}