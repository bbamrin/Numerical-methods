class MainLab2_2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab2_2()
            val newtonSolution = lab.newtonSolve(
                1.0,
                1.0,
                Lab2_2::f1,
                Lab2_2::f2,
                Lab2_2::df1dx1,
                Lab2_2::df1dx2,
                Lab2_2::df2dx1,
                Lab2_2::df2dx2,
                0.001
            )
            lab.printMatrixInt(newtonSolution,5)
            println("f1(solution): ${Lab2_2.f1(newtonSolution[0,0], newtonSolution[1,0]).toBigDecimal().toPlainString()}")
            println("f2(solution): ${Lab2_2.f2(newtonSolution[0,0], newtonSolution[1,0]).toBigDecimal().toPlainString()}")

            val iterationSolution = lab.iterationSolve(
                1.0,
                1.0,
                Lab2_2::f1Iteration,
                Lab2_2::f2Iteration,
                Lab2_2.iterationQ,
                0.001
            )
            lab.printMatrixInt(iterationSolution,5)
            println("f1(solution): ${Lab2_2.f1(iterationSolution[0,0], iterationSolution[1,0]).toBigDecimal().toPlainString()}")
            println("f2(solution): ${Lab2_2.f2(iterationSolution[0,0], iterationSolution[1,0]).toBigDecimal().toPlainString()}")
        }
    }
}