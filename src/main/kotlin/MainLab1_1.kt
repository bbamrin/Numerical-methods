import org.ejml.simple.SimpleMatrix

class MainLab1_1 {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val l1 = Lab1_1()
            val matrix = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(3.0, -8.0, 1.0, -7.0),
                    doubleArrayOf(6.0, 4.0, 8.0, 5.0),
                    doubleArrayOf(-1.0, 1.0, -9.0, -3.0),
                    doubleArrayOf(-6.0, 6.0, 9.0, -4.0),
                )
            )
            val luPair = l1.luDecomposition(matrix)
            println("initial matrix:")
            l1.printMatrixInt(matrix)
            println("\nL matrix:")
            l1.printMatrixInt(luPair.first)
            println("\nU matrix:")
            l1.printMatrixInt(luPair.second)
            println("\nL*U:")
            println(luPair.first.mult(luPair.second))
            println()
            val solution = l1.luSolve(
                luPair.first,
                luPair.second,
                SimpleMatrix(arrayOf(doubleArrayOf(96.0, -13.0, -54.0, 82.0))).transpose()
            )
            println("\nsolution:")
            l1.printMatrixInt(solution)
            println("\nmatrix * solution:")
            l1.printMatrixInt(matrix.mult(solution))
            val inversedMatrix = l1.luInverse(luPair.first, luPair.second)
            println("\ninversed:")
            l1.printMatrixInt(inversedMatrix)
            println("\ninversed * not inversed:")
            l1.printMatrixInt(inversedMatrix.mult(matrix))
            println()
            println("${l1.luDeterminant(luPair.first, luPair.second)}")
        }
    }
}