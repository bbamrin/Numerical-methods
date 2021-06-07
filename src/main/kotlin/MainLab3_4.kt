class MainLab3_4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_4()
            val nodes = listOf(
                Pair(0.0, 1.0),
                Pair(0.1, 1.1052),
                Pair(0.2, 1.2214),
                Pair(0.3, 1.3499),
                Pair(0.4, 1.4918),
            )
            val x = 0.2
            println("first derivative: ${lab.firstDerivative(x, nodes)}")
            println("first derivative1: ${lab.firstDerivative1(x, nodes)}")
            println("second derivative: ${lab.secondDerivative(x, nodes)}")
            println("second derivative1: ${lab.secondDerivative1(x, nodes)}")
        }

    }
}