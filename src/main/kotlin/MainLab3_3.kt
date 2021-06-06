import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.scene.shape.ArcType
import javafx.stage.Stage
import org.apache.batik.css.engine.value.svg.BaselineShiftManager
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.Table
import java.util.zip.DeflaterOutputStream
import javax.xml.xpath.XPathNodes


class MainLab3_3 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lab = Lab3_3()
            val nodes = listOf(
                Pair(0.0, 0.4713),
                Pair(1.7, 1.0114),
                Pair(3.4, 1.5514),
                Pair(5.1, 3.0916),
                Pair(6.8, 2.6317),
                Pair(8.5, 3.1718),
            )
            val res = lab.getSquaredResidualsSum(nodes, 2)
            val res1 = lab.getSquaredResidualsSum(nodes, 1)
            println(res.toBigDecimal().toPlainString())
            println(res1.toBigDecimal().toPlainString())
            Application.launch(MyFirstChart::class.java)
        }

    }

    class MyFirstChart: BaseChart() {


        override val h = 900.0
        override val w = 1400.0

        override fun start(stage: Stage) {
            val nodes = listOf(
                Pair(0.0, 0.4713),
                Pair(1.7, 1.0114),
                Pair(3.4, 1.5514),
                Pair(5.1, 3.0916),
                Pair(6.8, 2.6317),
                Pair(8.5, 3.1718),
            )
            val canvas = Canvas(w, h)
            val gc = canvas.graphicsContext2D
            val lab = Lab3_3()
            val nodes1 = mutableListOf<Pair<Double, Double>>()
            val nodes2 = mutableListOf<Pair<Double, Double>>()
            val zoom = 100.0
            val shiftX = w/2 - w/4
            val shiftY = h/2
            val stepX = 1.0
            val stepY = 1.0
            var i = 0.0
            while (i < nodes.last().first) {
                nodes1.add(Pair(i, lab.leastSquaresInterpolation(i, nodes, 1)))
                nodes2.add(Pair(i, lab.leastSquaresInterpolation(i, nodes, 2)))
                i += 0.1
            }

            drawAxis(gc, zoom, shiftX, shiftY, stepX, stepY, Color.BLACK)
            drawFun(gc, nodes, zoom, shiftX, shiftY, Color.RED)
            drawFun(gc, nodes1, zoom, shiftX, shiftY, Color.GREEN)
            drawFun(gc, nodes2, zoom, shiftX, shiftY, Color.BLUE)
            val root = Group()
            root.children.add(canvas)
            stage.scene = Scene(root)
            stage.show()
        }
    }

}