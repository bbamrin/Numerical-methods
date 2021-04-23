import org.ejml.simple.SimpleMatrix

class MainLab1_2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(8.0, 4.0, 0.0, 0.0, 0.0),
                    doubleArrayOf(-5.0, 22.0, 8.0, 0.0, 0.0),
                    doubleArrayOf(0.0, -5.0, -11.0, 1.0, 0.0),
                    doubleArrayOf(0.0, 0.0, -9.0, -15.0, 1.0),
                    doubleArrayOf(0.0, 0.0, 0.0, 1.0, 7.0),
                )
            )

            val vector = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(48.0, 125.0, -43.0, 18.0, -23.0)
                )
            ).transpose()

            val solution = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0)
                )
            ).transpose()

            val lab = Lab1_2()
            println("solution: ")
            lab.triSolve(matrix = matrix, vector = vector, solution = solution, i = 0)
            lab.printMatrixInt(solution)
            println("\ncheck: ")
            lab.printMatrixInt(matrix.mult(solution))
        }
    }

}