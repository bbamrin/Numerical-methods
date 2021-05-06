import org.ejml.simple.SimpleMatrix

class MainLab2_1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab2_1()
            val (newtonSolution , newtonCount) = lab.newtonSolve(
                f = Lab2_1::f,
                df = Lab2_1::df,
                x = Lab2_1.newtonX0,
                precision = 0.001
            )
            println("newton solution: $newtonSolution")
            println("\nnewton count: $newtonCount")
            println("\nf(solution): ${Lab2_1.f(newtonSolution).toBigDecimal().toPlainString()}")
            val (itSolution , itCount) = lab.iterationSolve(
                f = Lab2_1::fIteration,
                x = Lab2_1.iterationX0,
                q = Lab2_1.iterationQ,
                precision = 0.001
            )
            println("\niterations solution: $itSolution")
            println("\niterations count: $itCount")
            println("\nf(solution): ${Lab2_1.f(itSolution).toBigDecimal().toPlainString()}")
        }
    }
}