import org.ejml.simple.SimpleMatrix

class MainLab1_4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = SimpleMatrix(
                arrayOf(
                    doubleArrayOf(4.0, 2.0, 1.0),
                    doubleArrayOf(2.0, 5.0, 3.0),
                    doubleArrayOf(1.0, 3.0, 6.0),
                )
            )

            val lab = Lab1_4()
            val res = lab.rotationMethod(matrix, 0.01)
            println("eigen values matrix: ")
            lab.printMatrixInt(res.first)
            println("\neigen vector matrix: ")
            lab.printMatrixInt(res.second)
        }
    }
}