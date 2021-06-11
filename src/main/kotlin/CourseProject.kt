import kotlin.math.exp
import kotlin.math.pow

class CourseProject {
    fun getSplineCoeffs(
        n: Int,
        x: List<Double>,
        y: List<Double>,
        p: List<Double>,
        ys1: Double,
        ysn: Double
    ) {
        var A: MutableList<Double> = MutableList(n - 1) { 0.0 }
        var B: MutableList<Double> = MutableList(n - 1) { 0.0 }
        var C: MutableList<Double> = MutableList(n - 1) { 0.0 }
        var D: MutableList<Double> = MutableList(n - 1) { 0.0 }
        var f: MutableList<Double> = MutableList(n - 1) { 0.0 }
        var y1: MutableList<Double> = MutableList(n - 1) {0.0}
        var k: Int
        var n1: Int
        var n2: Int
        var u: Double
        var v: Double
        var w: Double
        var c1: Double = 0.0
        var z1: Double = 0.0
        var d1: Double = 0.0
        var c2: Double
        var z2: Double
        var d2: Double
        var dz: Double
        var sinhdx: Double
        var coshdx: Double
        var h: Double
        var r: Double
        var alpha: Double
        var beta: Double
        var gamma: Double
        var delta: Double

        n2 = n - 2
        if (n2 < 0)
            return
        y1[0] = ys1
        y1[n - 1] = ysn
        A[0] = x[1] - x[0]
        u = (y[1] - y[0])/A[0]
        B[1] = u
        n1 = n - 1
        if (n2 == 0) {
            //do some shit
        }
        for (k in 1 until n1) {
            A[k] = x[k + 1] - x[k]
            v = (y[k + 1] - y[k])/A[k]
            B[k] = v
            f[k] = v - u
            u = v
        }
        f[0] = B[0] - ys1
        f[n - 1] = ysn - B[n1 - 1]
        for (k in 0 until n) {
            if (f[k] == 0.0)
                return//goto exit
        }
        C[0] = 0.0
        D[0] = 0.0
        for (k in 0 until n1 - 1) {
            if (p[k] == 0.0) {
                c2 = 2/A[k]
                z2 = 2 *c2
                d2 = (z2 + c2) * B[k]
                if (k == 1) {
                    //goto change
                    c1 = c2
                    z1 = z2
                    d1 = d2
                    continue
                }
                u = z1 + z2
                r = d1 + d2
                if (k == 2)
                    r -= c1 * ys1
                if (k == n1)
                    r -= c2 * ysn
                z1 = 1/(u + c1 * C[k - 1])
                C[k] = -z1 * c2
                D[k] = z1 * (r - c1 * D[k - 1])
                c1 = c2
                z1 = z2
                d1 = d2
                //goto solve
            } else {
                z1 = A[k]*p[k]
                w = exp(z1)
                z1 = 1.0/w
                sinhdx =  0.5 * (w - z1)
                coshdx = 0.5 * (w + z1)
                z1 = sinhdx/A[k]
                h = 1/(z1 - p[k])
                u = h * (p[k] * coshdx - z1)
                z1 = p[k].pow(2)*sinhdx * h/(u.pow(2) - 1.0)
                c2 = z1
                z2 = z1 * u
                d2 = (u + 1.0) * z1 * B[k]
            }
        }
        y1[n1 - 1] = D[n1 - 1]
        for (k in (n2-1) downTo 1) {
            y1[k] = y1[k + 1] * C[k] + D[k]
        }
        ///
    }
}