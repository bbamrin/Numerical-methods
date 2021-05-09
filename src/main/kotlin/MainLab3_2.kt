import kotlin.math.tan

class MainLab3_2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_2()
            val nodes = listOf(
                Pair(0.0, 2.4142),
                Pair(1.9, 1.0818),
                Pair(2.8, 0.50953),
                Pair(3.7, 0.11836),
                Pair(4.6, -0.24008),
            )
            println(lab.cubicSplineInterpolation(2.66666667, nodes))
        }
    }
}