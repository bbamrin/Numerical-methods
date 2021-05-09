import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.scene.shape.ArcType
import javafx.stage.Stage
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.Table
import javax.xml.xpath.XPathNodes


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
            Application.launch(MyFirstChart::class.java)
        }

    }



    class MyFirstChart: Application() {

        private val h = 700.0
        private val w = 700.0

        fun drawFun(context: GraphicsContext, nodes: List<Pair<Double,Double>>, zoom: Int, color: Color) {
            context.stroke = color
            context.lineWidth = 1.0
            for (i in 1..nodes.lastIndex) {
                context.strokeLine(
                    nodes[i - 1].first * zoom ,
                    -nodes[i - 1].second * zoom + h/2,
                    nodes[i].first * zoom,
                    -nodes[i].second * zoom + h/2
                )
            }
        }

        override fun start(stage: Stage) {
            val nodes = listOf(
                Pair(0.0, 0.0),
                Pair(1.7, 1.3038),
                Pair(3.4, 1.8439),
                Pair(5.1, 2.2583),
                Pair(6.8, 2.6077),
                Pair(8.5, 2.9155),
            )
            val canvas = Canvas(w, h)
            val gc = canvas.graphicsContext2D
            val lab = Lab3_3()
            val nodes1 = mutableListOf<Pair<Double, Double>>()
            val nodes2 = mutableListOf<Pair<Double, Double>>()
            var i = 0.0
            while (i < nodes.last().first) {
                nodes1.add(Pair(i, lab.leastSquaresInterpolation(i, nodes, 1)))
                nodes2.add(Pair(i, lab.leastSquaresInterpolation(i, nodes, 2)))
                i += 0.1
            }
            val zoom = 80
            drawFun(gc, nodes, zoom, Color.RED)
            drawFun(gc, nodes1, zoom, Color.GREEN)
            drawFun(gc, nodes2, zoom, Color.BLUE)
            val root = Group()
            root.children.add(canvas)
            stage.scene = Scene(root)
            stage.show()
        }
    }

}