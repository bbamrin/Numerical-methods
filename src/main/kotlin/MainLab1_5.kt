import org.ejml.simple.SimpleMatrix

class MainLab1_5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(1.0, 3.0, 1.0),
                    doubleArrayOf(1.0, 1.0, 4.0),
                    doubleArrayOf(4.0, 3.0, 1.0),
                )
            )

            val lab = Lab1_5()
            val qrPair = lab.getQRDecomposition(matrix)

            println("source matrix:")
            lab.printMatrixInt(matrix)
            println("\nmatrix Q:")
            lab.printMatrixInt(qrPair.first)
            println("\nmatrix R:")
            lab.printMatrixInt(qrPair.second)
            println("\nmatrix Q*R:")
            lab.printMatrixInt(qrPair.first.mult(qrPair.second))
            println("\nmatrix Q inverted:")
            lab.printMatrixInt(qrPair.first.invert())
            println("\nmatrix Q transposed:")
            lab.printMatrixInt(qrPair.first.transpose())
            println("\neigenvalues matrix:")
            lab.printMatrixInt(lab.getEigenvaluesMatrix(matrix, 0.01))
            lab.printEigenValues(lab.getEigenvaluesMatrix(matrix, 0.01))

        }
    }

}