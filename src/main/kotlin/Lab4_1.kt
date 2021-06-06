import java.nio.DoubleBuffer
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.roundToInt

class Lab4_1 {

    companion object {
        fun f(x: Double, y: Double, z: Double): Double = ((x*x + 1) * y + x * z * (x * x - 1))/(x * x)

        fun g(x: Double, y: Double, z: Double): Double = z

        fun preciseSolution(x: Double) = (1 + exp((x*x)/2))/x

        fun preciseDerivative(x: Double) = (exp(x.pow(2)/2) * (x.pow(2) - 1) - 1)/x.pow(2)

        const val h = 0.1

        var y0 = 1 + exp(0.5)

        var z0 =  -1.0
    }

    fun eulerMethod(
        xRes: Double,
        a: Double,
        b: Double,
        h: Double,
        f: (Double, Double, Double) -> Double,
        g: (Double, Double, Double) -> Double,
        y0: Double,
        z0: Double,
        x0: Double
    ) : Pair<Double, Double> {
        var i = 0
        var x = x0
        var y = y0
        var z = z0
        val k: Int = ((xRes - a)/h).roundToInt()
        while (i++ < k - 1) {
            z += h * f(x, y, z)
            y += h * g(x, y, z)
            x += h
        }
        return Pair(y, z)
    }

    fun rungeKuttaMethod(
        xRes: Double,
        a: Double,
        b: Double,
        h: Double,
        f: (Double, Double, Double) -> Double,
        g: (Double, Double, Double) -> Double,
        y0: Double,
        z0: Double,
        x0: Double
    ) : Pair<Double, Double> {
        var i = 0
        var x = x0
        var y = y0
        var z = z0
        val k: Int = ((xRes - a)/h).roundToInt()
        if (i == k) {
            val K1 = h * g(x, y, z)
            val L1 = h * f(x, y, z)
            val K2 = h * g(x + 0.5 * h, y + 0.5 * K1, z + 0.5 * L1)
            val L2 = h * f(x + 0.5 * h, y + 0.5 * K1, z + 0.5 * L1)
            val K3 = h * g(x + 0.5 * h, y + 0.5 * K2, z + 0.5 * L2)
            val L3 = h * f(x + 0.5 * h, y + 0.5 * K2, z + 0.5 * L2)
            val K4 = h * g(x + h, y + K3, z + L3)
            val L4 = h * f(x + h, y+ K3, z + L3)
            z += (L1 + 2 * L2 + 2 * L3 + L4) / 6
            y += (K1 + 2 * K2 + 2 * K3 + K4) / 6
            x += h
        }
        while (i++ < k) {
            val K1 = h * g(x, y, z)
            val L1 = h * f(x, y, z)
            val K2 = h * g(x + 0.5 * h, y + 0.5 * K1, z + 0.5 * L1)
            val L2 = h * f(x + 0.5 * h, y + 0.5 * K1, z + 0.5 * L1)
            val K3 = h * g(x + 0.5 * h, y + 0.5 * K2, z + 0.5 * L2)
            val L3 = h * f(x + 0.5 * h, y + 0.5 * K2, z + 0.5 * L2)
            val K4 = h * g(x + h, y + K3, z + L3)
            val L4 = h * f(x + h, y+ K3, z + L3)
            z += (L1 + 2 * L2 + 2 * L3 + L4) / 6
            y += (K1 + 2 * K2 + 2 * K3 + K4) / 6
            x += h
        }
        return Pair(y , z)
    }

    fun adamsMethod(
        xRes: Double,
        a: Double,
        b: Double,
        h: Double,
        f: (Double, Double, Double) -> Double,
        g: (Double, Double, Double) -> Double,
        y0: MutableList<Double>,
        z0: MutableList<Double>,
        x0: MutableList<Double>
    ) : Double {
        if (y0.size < 4 )
            return y0.lastOrNull() ?: 0.0
        var x = mutableListOf<Double>()
        x.addAll(x0)
        var y = mutableListOf<Double>()
        y.addAll(y0)
        var z = mutableListOf<Double>()
        z.addAll(z0)
        val k: Int = ((xRes - a)/h).roundToInt()
        var i =  3
        while (i < k) {
            z.add(z[i] + h * (55 * f(x[i], y[i], z[i]) -
                    59 * f(x[i - 1], y[i - 1], z[i - 1]) +
                    37 * f(x[i - 2], y[i - 2], z[i - 2]) -
                    9 * f(x[i - 3], y[i - 3], z[i - 3])) / 24)
            y.add(y[i] + h * (55 * g(x[i], y[i], z[i]) -
                    59 * g(x[i - 1], y[i - 1], z[i - 1]) +
                    37 * g(x[i - 2], y[i - 2], z[i - 2]) -
                    9 * g(x[i - 3], y[i - 3], z[i - 3])) / 24)
            x.add(x[i] + h)
            i++
        }
        return y.last()
    }

    fun rungeRombergMethod(p: Double, y: Double, y1: Double, h: Double): Double {
        return y + (y - y1)/(2.0.pow(p) - 1)
    }

}