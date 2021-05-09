class MainLab3_3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_3()
            val nodes = listOf(
                Pair(0.0, 0.0),
                Pair(1.7, 1.3038),
                Pair(3.4, 1.8439),
                Pair(5.1, 2.2583),
                Pair(6.8, 2.6077),
                Pair(8.5, 2.9155),
            )
            val res = lab.getSquaredResidualsSum(nodes, 2)
            println(res.toBigDecimal().toPlainString())
        }
    }
}