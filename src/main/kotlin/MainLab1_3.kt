import org.ejml.simple.SimpleMatrix

class MainLab1_3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(20.0, 5.0, 7.0, 1.0),
                    doubleArrayOf(-1.0, 13.0, 0.0, -7.0),
                    doubleArrayOf(4.0, -6.0, 17.0, 5.0),
                    doubleArrayOf(-9.0, 8.0, 4.0, -25.0),
                )
            )

            val vector = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(-117.0, -1.0, 49.0, -21.0)
                )
            ).transpose()

            val lab = Lab1_3()
            println("simple iteration solution: ")
            var solutionPair = lab.iterationSolve(matrix = matrix, vector = vector, precision = 0.001)
            var solution = solutionPair.first
            lab.printMatrixInt(solution)
            println("\ncheck: ")
            lab.printMatrixInt(matrix.mult(solution))
            println("iteration count: ${solutionPair.second}")

            println("\nzeidel solution: ")
            solutionPair = lab.zeidelSolve(matrix = matrix, vector = vector, precision = 0.001)
            solution = solutionPair.first
            lab.printMatrixInt(solution)
            println("\ncheck: ")
            lab.printMatrixInt(matrix.mult(solution))
            println("iteration count: ${solutionPair.second}")
        }
    }
}