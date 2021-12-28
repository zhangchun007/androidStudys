package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.provider.DocumentsContract
import android.util.AttributeSet
import android.view.View

/**
 * 主要三点：
 * 1：利用构造绘制一个区域
 * 2：使用setPath()构造不规则区域
 * 3：区域与区域之间的交集区域
 *
 * @Description:    区域(Range)
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 */
class CustomView3(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {

    private var paint: Paint = Paint()

    init {
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.STROKE //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 2f //设置画笔宽度
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //一：构造Region
        /**
         * 1:基本构造函数
         *
         * public Region()  //创建一个空的区域
         * public Region(Region region) //拷贝一个region的范围
         * public Region(Rect r)  //创建一个矩形的区域
         * public Region(int left, int top, int right, int bottom) //创建一个矩形的区域
         * 上面的四个构造函数，第一个还要配合其它函数使用，暂时不提。
         * 第二个构造函数是通过其它的Region来复制一个同样的Region变量
         * 第三个，第四个才是正规常的，根据一个矩形或矩形的左上角和右下角点构造出一个矩形区域
         */

        /**
         * 2：间接构造
         *  public void setEmpty()  //置空
         *  public boolean set(Region region)//利用新的区域值来替换原来的区域
         *  public boolean set(Rect r)//利用矩形所代表的区域替换原来的区域
         *  public boolean set(int left, int top, int right, int bottom)//根据矩形的两个点构造出矩形区域来替换原来的区域值
         *  public boolean setPath(Path path, Region clip)//后面单独讲//根据路径的区域与某区域的交集，构造出新区域，这个后面具体讲解
         */
        var rgn = Region(10, 10, 100, 100)

//		rgn.set(100, 100, 200, 200);//使用set函数，利用矩形所代表的区域替换原来的区域
        drawRegion(canvas, rgn, paint)

        /**
         * 3：使用setPath()构造不规则区域
         *
         * boolean setPath (Path path, Region clip)
         *参数说明：
         * Path path：用来构造的区域的路径
         * Region clip：与前面的path所构成的路径取交集，并将两交集设置为最终的区域
         *
         * 由于路径有很多种构造方法，而且可以轻意构造出非矩形的路径，这就摆脱了前面的构造函数只能构造矩形区域的限制。
         * 但这里有个问题是要指定另一个区域来取共同的交集，当然如果想显示路径构造的区域，Region clip参数可以传一个比Path范围大的多的区域，取完交集之后，当然是Path参数所对应的区域喽
         */

        var ovalPath = Path()
        var rect = RectF(50f, 50f, 200f, 500f)
        ovalPath.addOval(rect, Path.Direction.CCW)

        var rgnOval = Region()
        rgnOval.setPath(ovalPath, Region(50, 50, 200, 200))
        //画出路径
        drawRegion(canvas, rgnOval, paint)


        //二：矩形集枚举区域——RegionIterator类
        /**
         * 事实上，如果矩形足够小，一定数量的矩形就能够精确表示区域的形状，也就是说，一定数量的矩形所合成的形状，也可以代表区域的形状。
         * RegionIterator类，实现了获取组成区域的矩形集的功能，其实RegionIterator类非常简单，总共就两个函数，一个构造函数和一个获取下一个矩形的函数；
         *
         * RegionIterator(Region region) //根据区域构建对应的矩形集
         * boolean next(Rect r) //获取下一个矩形，结果保存在参数Rect r 中
         *
         * 由于在Canvas中没有直接绘制Region的函数，我们想要绘制一个区域，就只能通过利用RegionIterator构造矩形集来逼近的显示区域。用法如下：
         *
         * 上面我们也都看到了它的用法，首先，根据区域构建一个矩形集，然后利用next(Rect r)来逐个获取所有矩形，绘制出来，最终得到的就是整个区域，
         * 如果我们将上面的画笔Style从FILL改为STROKE，重新绘制椭圆路径，会看得更清楚。
         */

        //三、区域的合并、交叉等操作
        /**
         * 无论是区域还是矩形，都会涉及到与另一个区域的一些操作，比如取交集、取并集等，涉及到的函数有：
         * public final boolean union(Rect r)
         * public boolean op(Rect r, Op op) {
         * public boolean op(int left, int top, int right, int bottom, Op op)
         * public boolean op(Region region, Op op)
         * public boolean op(Rect rect, Region region, Op op)
         *
         * 除了Union(Rect r)是指定合并操作以外，其它四个op（）构造函数，都是指定与另一个区域的操作。其中最重要的指定Op的参数，Op的参数有下面四个：
         * 假设用region1  去组合region2
         * public enum Op {
         * DIFFERENCE(0), //最终区域为region1 与 region2不同的区域
         * INTERSECT(1), // 最终区域为region1 与 region2相交的区域
         * UNION(2),      //最终区域为region1 与 region2组合一起的区域
         * XOR(3),        //最终区域为region1 与 region2相交之外的区域
         * REVERSE_DIFFERENCE(4), //最终区域为region2 与 region1不同的区域
         * REPLACE(5); //最终区域为为region2的区域
         * }
         */

        var rectOne = Rect(100, 100, 400, 200)
        var rectTwo = Rect(200, 0, 300, 300)
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        canvas.drawRect(rectOne, paint)
        canvas.drawRect(rectTwo, paint)

        var regionOne = Region(rectOne)
        var regionTwo = Region(rectTwo)
        //取两个区域的交集
        regionOne.op(regionTwo, Region.Op.INTERSECT)
        paint.style = Paint.Style.FILL
        paint.color = Color.GREEN
        drawRegion(canvas, regionOne, paint)

        //四、其它一些方法
        //Region类除了上面的一些重要的方法以外，还有一些比较容易理解的方法，我就不再一一列举用法了，下面一并列出给大家
        /**
         * /**几个判断方法*/
         * public native boolean isEmpty();//判断该区域是否为空
         * public native boolean isRect(); //是否是一个矩阵
         * public native boolean isComplex();//是否是多个矩阵组合
         *
         *
         * /**一系列的getBound方法，返回一个Region的边界*/
         * public Rect getBounds()
         * public boolean getBounds(Rect r)
         * public Path getBoundaryPath()
         * public boolean getBoundaryPath(Path path)
         *
         * /**一系列的判断是否包含某点 和是否相交*/
         * public native boolean contains(int x, int y);//是否包含某点
         * public boolean quickContains(Rect r)   //是否包含某矩形
         * public native boolean quickContains(int left, int top, int right,int bottom) //是否没有包含某矩阵形
         * public boolean quickReject(Rect r) //是否没和该矩形相交
         * public native boolean quickReject(int left, int top, int right, int bottom); //是否没和该矩形相交
         * public native boolean quickReject(Region rgn);  //是否没和该矩形相交
         *
         *
         * /**几个平移变换的方法*/
         * public void translate(int dx, int dy)
         * public native void translate(int dx, int dy, Region dst);
         * public void scale(float scale) //hide
         * public native void scale(float scale, Region dst);//hide
         *
         */


    }

    private fun drawRegion(canvas: Canvas, region: Region, paint: Paint) {
        var iter = RegionIterator(region)
        var r = Rect()
        while (iter.next(r)) {
            canvas.drawRect(r, paint)
        }
    }
}