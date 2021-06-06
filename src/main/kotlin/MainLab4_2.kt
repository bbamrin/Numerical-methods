import java.util.zip.DeflaterOutputStream

class MainLab4_2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab4_2()
            var x = 2.0
            var h = 0.1
            val a = 1.0
            val b = 2.0
            val precision = 0.0001

            var shootingMethod = lab.shootingMethod(
                x,
                a,
                b,
                Lab4_2::f,
                Lab4_2::g,
                h,
                Lab4_2.y0,
                Lab4_2.y1,
                Lab4_2.z0,
                Lab4_2.z1,
                a,
                precision
            )

            var finiteDifferencesMethod = lab.finiteDifferenceMethod(
                a,
                b,
                Lab4_2::f,
                Lab4_2::g,
                Lab4_2::p,
                Lab4_2::q,
                h,
                Lab4_2.y0,
                Lab4_2.y1
            )

            println("shooting method at x = $x: $shootingMethod")
            println("finite differences method at x = $x: ${finiteDifferencesMethod[1].second}")
            println("precise solution at x = $x: ${Lab4_2.preciseSolution(x)}")

        }

    }
}