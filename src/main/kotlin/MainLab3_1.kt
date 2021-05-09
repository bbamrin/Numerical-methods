import kotlin.math.*

class MainLab3_1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_1()
            val nodes = listOf(
                Pair(Math.PI/8, 1/tan(Math.PI/8)),
                Pair(2*Math.PI/8, 1/tan(2*Math.PI/8)),
                Pair(3*Math.PI/8, 1/tan(3*Math.PI/8)),
                Pair(4*Math.PI/8, 1/tan(4*Math.PI/8)),
            )
            val nodes1 = listOf(
                Pair(Math.PI/8, 1/tan(Math.PI/8)),
                Pair(5*Math.PI/16, 1/tan(5*Math.PI/16)),
                Pair(3*Math.PI/8, 1/tan(3*Math.PI/8)),
                Pair(Math.PI/2, 1/tan(Math.PI/2)),
            )
            val lagrange = lab.lagrangeInterpolation(
                Math.PI/3,
                nodes
            )
            val lagrange1 = lab.lagrangeInterpolation(
                Math.PI/3,
                nodes1
            )
            val newton = lab.newtonInterpolation(
                Math.PI/3,
                nodes
            )
            val newton1 = lab.newtonInterpolation(
                Math.PI/3,
                nodes1
            )
            println("lagrange: $lagrange")
            println("\nnewton: $newton")
            println("\nlagrange1: $lagrange1")
            println("\nnewton1: $newton1")
            println("\nreal: ${1/ tan(Math.PI/3)}")
            println("\nlagrange error: ${abs(lagrange - 1/ tan(Math.PI/3))}")
            println("\nnewton error: ${abs(newton - 1/ tan(Math.PI/3))}")
            println("\nlagrange1 error: ${abs(lagrange1 - 1/ tan(Math.PI/3))}")
            println("\nnewton1 error: ${abs(newton1 - 1/ tan(Math.PI/3))}")
        }
    }
}