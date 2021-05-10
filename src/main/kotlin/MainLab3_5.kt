import kotlin.math.pow

class MainLab3_5 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_5()
            val f: (Double) -> Double = {
                (3*it + 4)/(2*it + 7)
            }
            val f1: (Double) -> Double = {
               it/(3*it + 4).pow(2)
            }
            val x0 = -1.0
            val x1 = 1.0
            val h1 = 0.5
            val h2 = 0.25
            println("rectangle h = $h1: ${lab.rectangleMethod(x0, x1, h1, f1)}")
            println("trapezoidal h = $h1: ${ lab.trapezoidalMethod(x0, x1, h1, f1) }")
            println("simpson h = $h1: ${ lab.simpsonMethod(x0, x1, h1, f1) }")
            println("rectangle h = $h2: ${lab.rectangleMethod(x0, x1, h2, f1)}")
            println("trapezoidal h = $h2: ${ lab.trapezoidalMethod(x0, x1, h2, f1) }")
            println("Simpson h = $h2: ${ lab.simpsonMethod(x0, x1, h2, f1) }")
            println("Runge-Romberg error: ${lab.rungeRombergMethod(lab::rectangleMethod, f1, x0, x1, h1, h2, 1)}")
            println("Runge-Romberg error: ${lab.rungeRombergMethod(lab::trapezoidalMethod, f1, x0, x1, h1, h2, 2)}")
            println("Runge-Romberg error: ${lab.rungeRombergMethod(lab::simpsonMethod, f1, x0, x1, h1, h2, 4)}")

        }

    }
}