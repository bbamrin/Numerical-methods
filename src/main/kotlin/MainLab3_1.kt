import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import javafx.stage.Stage
import kotlin.math.*

class MainLab3_1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_1()
            val nodes = listOf(
                Pair(Math.PI/8, 1/tan(Math.PI/8)),
                Pair(2*Math.PI/8, 1/tan(2*Math.PI/8)),
                Pair(3*Math.PI/8, 1/tan(3*Math.PI/8)),
                Pair(4*Math.PI/8, 1/tan(4*Math.PI/8)),
            )
            val nodes1 = listOf(
                Pair(Math.PI/8, 1/tan(Math.PI/8)),
                Pair(5*Math.PI/16, 1/tan(5*Math.PI/16)),
                Pair(3*Math.PI/8, 1/tan(3*Math.PI/8)),
                Pair(Math.PI/2, 1/tan(Math.PI/2)),
            )
            val lagrange = lab.lagrangeInterpolation(
                Math.PI/3,
                nodes
            )
            val lagrange1 = lab.lagrangeInterpolation(
                Math.PI/3,
                nodes1
            )
            val newton = lab.newtonInterpolation(
                Math.PI/3,
                nodes
            )
            val newton1 = lab.newtonInterpolation(
                Math.PI/3,
                nodes1
            )
            println("lagrange: $lagrange")
            println("\nnewton: $newton")
            println("\nlagrange1: $lagrange1")
            println("\nnewton1: $newton1")
            println("\nreal: ${1/ tan(Math.PI/3)}")
            println("\nlagrange error: ${abs(lagrange - 1/ tan(Math.PI/3))}")
            println("\nnewton error: ${abs(newton - 1/ tan(Math.PI/3))}")
            println("\nlagrange1 error: ${abs(lagrange1 - 1/ tan(Math.PI/3))}")
            println("\nnewton1 error: ${abs(newton1 - 1/ tan(Math.PI/3))}")
            Application.launch(MyFirstChart0::class.java)
        }


        class MyFirstChart0: BaseChart() {


            override val h = 900.0
            override val w = 1400.0

            override fun start(stage: Stage) {
                val lab: Lab3_1 = Lab3_1()
                val nodes = listOf(
                    Pair(Math.PI/8, 1/tan(Math.PI/8)),
                    Pair(2*Math.PI/8, 1/tan(2*Math.PI/8)),
                    Pair(3*Math.PI/8, 1/tan(3*Math.PI/8)),
                    Pair(4*Math.PI/8, 1/tan(4*Math.PI/8)),
                )
                val nodes1 = mutableListOf<Pair<Double, Double>>()
                val nodes2 = mutableListOf<Pair<Double, Double>>()
                val canvas = Canvas(w, h)
                val gc = canvas.graphicsContext2D
                val zoom = 200.0
                val shiftX = w/2 - w/ 4
                val shiftY = h/2 + h/4
                val stepX = 1.0
                val stepY = 1.0
                var i = 0.0
                while (i < nodes.last().first) {
                    nodes1.add(Pair(i, lab.lagrangeInterpolation(i, nodes)))
                    nodes2.add(Pair(i, lab.newtonInterpolation(i, nodes)))
                    i += 0.1
                }

                drawAxis(gc, zoom, shiftX, shiftY, stepX, stepY, Color.BLACK)
                drawFun(gc, nodes, zoom, shiftX, shiftY, Color.RED)
                drawFun(gc, nodes1, zoom, shiftX, shiftY, Color.GREEN)
//                drawFun(gc, nodes2, zoom, shiftX, shiftY, Color.BLUE)
                val root = Group()
                root.children.add(canvas)
                stage.scene = Scene(root)
                stage.show()
            }
        }
    }
}