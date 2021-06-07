import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.stage.Stage

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
            println("finite differences method at x = $x: ${finiteDifferencesMethod.last().second}")
            println("precise solution at x = $x: ${Lab4_2.preciseSolution(x)}")


            Application.launch(MyFirstChart::class.java)
        }

    }

    class MyFirstChart : BaseChart() {


        override val h = 900.0
        override val w = 1400.0

        override fun start(stage: Stage) {
            val canvas = Canvas(w, h)
            val gc = canvas.graphicsContext2D
            val nodes1 = mutableListOf<Pair<Double, Double>>()
            val nodes2 = mutableListOf<Pair<Double, Double>>()
            val zoom = 500.0
            val shiftX = w / 2 - w / 4
            val shiftY = h / 2
            val stepX = 1.0
            val stepY = 1.0
            var i = 0.0
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
            var t = a
            while (t <= b) {
                nodes1.add(
                    Pair(
                        t,
                        lab.shootingMethod(
                            t,
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
                    )
                )
                nodes2.add(Pair(t, Lab4_2.preciseSolution(t)))
                t += h
            }
            nodes1[0] = finiteDifferencesMethod[0]
            drawAxis(gc, zoom, shiftX, shiftY, stepX, stepY, Color.BLACK)
//            drawFun(gc, finiteDifferencesMethod, zoom, shiftX, shiftY, Color.RED)
            drawFun(gc, nodes1, zoom, shiftX, shiftY, Color.GREEN)
//            drawFun(gc, nodes2, zoom, shiftX, shiftY, Color.BLUE)
            val root = Group()
            root.children.add(canvas)
            stage.scene = Scene(root)
            stage.show()
        }
    }

}