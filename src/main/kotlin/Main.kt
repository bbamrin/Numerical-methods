import org.ejml.simple.SimpleMatrix

class Main {
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
            l1.printMatrixInt(matrix)
            println()
            l1.printMatrixInt(luPair.first)
            println()
            l1.printMatrixInt(luPair.second)
            println()
            println(luPair.first.mult(luPair.second))
            println()
            val solution = l1.luSolve(
                    luPair.first,
                    luPair.second,
                    SimpleMatrix(arrayOf(doubleArrayOf(96.0, -13.0, -54.0, 82.0))).transpose()
                )
            l1.printMatrixInt(solution)
            println()
            l1.printMatrixInt(matrix.mult(solution))
            val inversedMatrix = l1.luInverse(luPair.first, luPair.second)
            l1.printMatrixInt(inversedMatrix.mult(matrix))
            println()
            println("${l1.luDeterminant(luPair.first, luPair.second)}")
        }
    }
}