import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage
import kotlin.math.tan

class MainLab3_2 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_2()
            val nodes = listOf(
                Pair(0.0, 2.4142),
                Pair(1.9, 1.0818),
                Pair(2.8, 0.50953),
                Pair(3.7, 0.11836),
                Pair(4.6, -0.24008),
            )
            println(lab.cubicSplineInterpolation(2.66666667, nodes))
            Application.launch(MyFirstChart1::class.java)
        }
    }

    class MyFirstChart1: BaseChart() {


        override val h = 900.0
        override val w = 1400.0

        override fun start(stage: Stage) {
            val nodes = listOf(
                Pair(0.0, 2.4142),
                Pair(1.9, 1.0818),
                Pair(2.8, 5.50953),
                Pair(3.7, 0.11836),
                Pair(4.6, -0.24008),
            )
            val canvas = Canvas(w, h)
            val gc = canvas.graphicsContext2D
            val lab: Lab3_2 = Lab3_2()
            val nodes1 = mutableListOf<Pair<Double, Double>>()
            val zoom = 100.0
            val shiftX = w/2 - w/ 4
            val shiftY = h/2 + h/4
            val stepX = 1.0
            val stepY = 1.0
            var i = 0.0
            while (i < nodes.last().first) {
                nodes1.add(Pair(i, lab.cubicSplineInterpolation(i, nodes)))
                i += 0.01
            }

            drawAxis(gc, zoom, shiftX, shiftY, stepX, stepY, Color.BLACK)
            drawFun(gc, nodes, zoom, shiftX, shiftY, Color.RED)
            drawFun(gc, nodes1, zoom, shiftX, shiftY, Color.GREEN)
            val root = Group()
            root.children.add(canvas)
            stage.scene = Scene(root)
            stage.show()
        }
    }
}