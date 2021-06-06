import javafx.application.Application
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

abstract class BaseChart: Application() {

    open val h = 900.0
    open val w = 1400.0

    fun drawFun(
        context: GraphicsContext,
        nodes: List<Pair<Double,Double>>,
        zoom: Double,
        shiftX: Double,
        shiftY: Double,
        color: Color,
    ) {
        context.stroke = color
        context.lineWidth = 1.0
        for (i in 1..nodes.lastIndex) {
            context.strokeLine(
                nodes[i - 1].first * zoom + shiftX,
                -nodes[i - 1].second * zoom + shiftY,
                nodes[i].first * zoom + shiftX,
                -nodes[i].second * zoom + shiftY
            )
        }
    }

    fun drawAxis(
        context: GraphicsContext,
        zoom: Double,
        shiftX: Double,
        shiftY: Double,
        stepX: Double,
        stepY: Double,
        color: Color
    ) {
        var i = 0.0
        var j = 0.0
        context.stroke = color
        context.lineWidth = 1.0
        context.strokeLine(shiftX, shiftY, w, shiftY)
        context.strokeLine(shiftX, shiftY, -w, shiftY)
        context.strokeLine(shiftX, shiftY, shiftX, h)
        context.strokeLine(shiftX, shiftY, shiftX, -h)
        while (i < h * zoom) {
            context.fillText((i/zoom).toString(), i + shiftX, shiftY)
            if (i != 0.0)
                context.fillText((-i/zoom).toString(), -i + shiftX, shiftY)
            context.fillOval( (i + shiftX), shiftY, 4.0, 4.0)
            context.fillOval(-i + shiftX, shiftY, 4.0, 4.0)
            i += stepX * zoom
        }
        while (j < w * zoom) {
            context.fillText((j/zoom).toString(), shiftX, j + shiftY)
            if (j != 0.0)
                context.fillText((j/zoom).toString(), shiftX, -j + shiftY)
            context.fillOval(shiftX, (j + shiftY), 4.0, 4.0)
            context.fillOval(shiftX, (-j + shiftY), 4.0, 4.0)
            j += stepY * zoom
        }
    }
}