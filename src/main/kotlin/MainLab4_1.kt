import java.util.zip.DeflaterOutputStream

class MainLab4_1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab4_1()
            var x0 = 1.0
            var x = 2.0
            var h = 0.0001
            var k = ((x - 1) / h).toInt()
            val a = 1.0
            val b = 2.0

            var euler = lab.eulerMethod(
                xRes = x,
                a = a,
                b = b,
                h = h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = Lab4_1.y0,
                z0 = Lab4_1.z0,
                x0 = x0
            ).first

            var euler1 = lab.eulerMethod(
                xRes = x,
                a = a,
                b = b,
                h = 2*h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = Lab4_1.y0,
                z0 = Lab4_1.z0,
                x0 = x0
            ).first

            var rungeKutta = lab.rungeKuttaMethod(
                xRes = x,
                a = a,
                b = b,
                h = h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = Lab4_1.y0,
                z0 = Lab4_1.z0,
                x0 = x0
            ).first

            var rungeKutta1 = lab.rungeKuttaMethod(
                xRes = x,
                a = a,
                b = b,
                h = 2*h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = Lab4_1.y0,
                z0 = Lab4_1.z0,
                x0 = x0
            ).first

            var i = a

            val fList = mutableListOf<Double>()
            val fdList = mutableListOf<Double>()
            val xList = mutableListOf<Double>()
            val fList1 = mutableListOf<Double>()
            val fdList1 = mutableListOf<Double>()
            val xList1 = mutableListOf<Double>()
            while (i  < a + 4 * h) {
                var rungeKuttaPair = lab.rungeKuttaMethod(
                    xRes = i,
                    a = a,
                    b = b,
                    h = h,
                    f = Lab4_1::f,
                    g = Lab4_1::g,
                    y0 = Lab4_1.y0,
                    z0 = Lab4_1.z0,
                    x0 = x0
                )
                fList.add(rungeKuttaPair.first)
                fdList.add(rungeKuttaPair.second)
                xList.add(i)
                i += h
            }

            while (i  < a + 4 * 2*h) {
                var rungeKuttaPair = lab.rungeKuttaMethod(
                    xRes = i,
                    a = a,
                    b = b,
                    h = h,
                    f = Lab4_1::f,
                    g = Lab4_1::g,
                    y0 = Lab4_1.y0,
                    z0 = Lab4_1.z0,
                    x0 = x0
                )
                fList1.add(rungeKuttaPair.first)
                fdList1.add(rungeKuttaPair.second)
                xList1.add(i)
                i += 2*h
            }

            fdList[0] = Lab4_1.preciseDerivative(xList[0])
            fList[0] = Lab4_1.preciseSolution(xList[0])
            var adams = lab.adamsMethod(
                xRes = x,
                a = a,
                b = b,
                h = h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = fList,
                z0 = fdList,
                x0 = xList
            )

            var adams1 = lab.adamsMethod(
                xRes = x,
                a = a,
                b = b,
                h = h,
                f = Lab4_1::f,
                g = Lab4_1::g,
                y0 = fList1,
                z0 = fdList1,
                x0 = xList1
            )

            val euler_romberg: Double = lab.rungeRombergMethod(1.0, euler, euler1, h)
            val rungeKutta_romberg: Double = lab.rungeRombergMethod(4.0, rungeKutta, rungeKutta1, h)
            val adams_romberg: Double = lab.rungeRombergMethod(4.0, adams, adams1, h)

            println("euler method at x = $x: ${euler}")
            println("rungeKutta method at x = $x: ${rungeKutta}")
            println("adams method at x = $x: ${adams}")
            println("euler_romberg: $euler_romberg")
            println("rungeKutta_romberg: $rungeKutta_romberg")
            println("euler_romberg: $adams_romberg")
            println("precise solution at x = $x: ${Lab4_1.preciseSolution(x)}")

        }

    }
}