import com.sun.marlin.DPathConsumer2D
import kotlin.math.pow

class Lab3_5 {

    private val interpolator = Lab3_1()

    fun rectangleMethod(a: Double, b: Double, h: Double, f: (Double) -> Double): Double {
        var i = a + h
        var result = 0.0
        while (i <= b) {
            val x = i - h/2
            result += interpolator.lagrangeInterpolation(x, listOf(Pair(x, f(x))))
            i += h
        }
        return result * h
    }

    fun trapezoidalMethod(a: Double, b: Double, h: Double, f: (Double) -> Double): Double {
        var i = a + h
        var result = 0.0
        while (i <= b) {
            val nodes = listOf(
                Pair(i - h, f(i - h)),
                Pair(i, f(i))
            )
            val f1 = interpolator.lagrangeInterpolation(i - h, nodes)
            val f2 = interpolator.lagrangeInterpolation(i, nodes)
            result += (f1 + f2)
            i += h
        }
        return result * h/2
    }

    fun simpsonMethod( a: Double, b: Double, h: Double, f: (Double) -> Double): Double {
        var i = a + 2*h
        var result = 0.0
        while (i <= b) {
            val nodes = listOf(
                Pair(i - 2*h, f(i - 2*h)),
                Pair(i - h, f(i - h)),
                Pair(i, f(i))
            )
            val f1 = interpolator.lagrangeInterpolation(i - 2*h, nodes)
            val f2 = 4 * interpolator.lagrangeInterpolation(i - h, nodes)
            val f3 = interpolator.lagrangeInterpolation(i, nodes)
            result += (f1 + f2 + f3)
            i += 2*h
        }
        return result * h/3
    }

    fun rungeRombergMethod(
        f: (Double, Double, Double, (Double) -> Double) -> Double,
        g: (Double) -> Double,
        a: Double,
        b: Double,
        h1: Double,
        h2: Double,
        p: Int
    ): Double {
        val f1 = f(a, b, h1, g)
        val f2 = f(a, b, h2, g)
        return f1 + (f1 - f2)/((h2/h1).pow(p) - 1)
    }
}

