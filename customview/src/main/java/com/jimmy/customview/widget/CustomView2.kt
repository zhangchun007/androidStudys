package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.provider.DocumentsContract
import android.util.AttributeSet
import android.view.View

/**
 * @Description: 路径及文字
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 *    canvas中绘制路径利用：
 *    void drawPath (Path path, Paint paint)
 *
 */
class CustomView2(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {

    private var paint: Paint

    init {
        paint = Paint()
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.STROKE //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 5f //设置画笔宽度

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /**
         * 1:直线路径
         * void moveTo (float x1, float y1):直线的开始点；即将直线路径的绘制点定在（x1,y1）的位置；
         * void lineTo (float x2, float y2)：直线的结束点，又是下一次绘制直线路径的开始点；lineTo（）可以一直用；
         * void close ():如果连续画了几条直线，但没有形成闭环，调用Close()会将路径首尾点连接起来，形成闭环；
         */

//        var path = Path()
//        path.moveTo(10f, 10f)
//        path.lineTo(10f, 100f)
//        path.lineTo(300f, 100f)
//        path.lineTo(500f, 100f)
//        path.close()
//        canvas.drawPath(path, paint)

        /**
         * 2:矩形路径
         * void addRect (float left, float top, float right, float bottom, Path.Direction dir)
         * void addRect (RectF rect, Path.Direction dir)
         *
         * Path.Direction.CCW：是counter-clockwise缩写，指创建逆时针方向的矩形路径；
         * Path.Direction.CW：是clockwise的缩写，指创建顺时针方向的矩形路径；
         *
         * 问：从效果图中，看不出顺时针生成和逆时针生成的任何区别，怎么会没区别呢？
         * 答：当然没区别啦，无论正时针还是逆时针，仅仅是生成方式不同而已，矩形就那么大画出来的路径矩形当然与矩形一样大了。
         * 问：那生成方式有什么区别呢？
         * 答：生成方式的区别在于，依据生成方向排版的文字！后面我们会讲到文字，文字是可以依据路径排版的，那文字的行走方向就是依据路径的生成方向
         */

//        var CCWRectpath = Path()
//        var rect1 = RectF(50f, 50f, 240f, 200f)
//        CCWRectpath.addRect(rect1, Path.Direction.CCW)
//
//        var CWRectpath = Path()
//        var rect2 = RectF(290f, 50f, 480f, 200f)
//        CWRectpath.addRect(rect2, Path.Direction.CW)
//
//        //画出两个路径
//        canvas.drawPath(CCWRectpath, paint)
//        canvas.drawPath(CWRectpath, paint)
//
//        //依据路径写出文字
//        var text = "风萧萧兮易水寒，壮士一去兮不复返"
//        paint.textSize = 35f
//        paint.color = Color.GRAY
//        canvas.drawTextOnPath(text, CCWRectpath, 0f, 18f, paint)
//        canvas.drawTextOnPath(text, CWRectpath, 0f, 18f, paint)

        /**
         * 3:圆角矩形路径
         * void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
         * void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
         * 这里有两个构造函数，部分参数说明如下：
         * 第一个构造函数：可以定制每个角的圆角大小：
         * float[] radii：必须传入8个数值，分四组，分别对应每个角所使用的椭圆的横轴半径和纵轴半径，如｛x1,y1,x2,y2,x3,y3,x4,y4｝，其中，x1,y1对应第一个角的（左上角）用来产生圆角的椭圆的横轴半径和纵轴半径，其它类推……
         *
         * 第二个构造函数：只能构建统一圆角大小
         * float rx：所产生圆角的椭圆的横轴半径；
         * float ry：所产生圆角的椭圆的纵轴半径
         */
        var pathRoundRect = Path()

        var rectRound1 = RectF(50f, 50f, 240f, 200f)
        pathRoundRect.addRoundRect(rectRound1, 10f, 15f, Path.Direction.CCW)

        var rectRound2 = RectF(290f, 50f, 480f, 200f)
        var floatRadii = floatArrayOf(10f, 15f, 20f, 25f, 30f, 35f, 40f, 45f)
        pathRoundRect.addRoundRect(rectRound2, floatRadii, Path.Direction.CCW)

        canvas.drawPath(pathRoundRect,paint)

        /**
         * 4:圆形路径
         * void addCircle (float x, float y, float radius, Path.Direction dir)
         * 参数说明：
         * float x：圆心X轴坐标
         * float y：圆心Y轴坐标
         * float radius：圆半径
         */
        var circlePath=Path()
        circlePath.addCircle(200f,200f,100f,Path.Direction.CCW)
        canvas.drawPath(circlePath,paint)

        /**
         * 5:椭圆路径
         * void addOval (RectF oval, Path.Direction dir)
         * RectF oval：生成椭圆所对应的矩形
         * Path.Direction :生成方式，与矩形一样，分为顺时针与逆时针，意义完全相同，不再重复
         */

        var ovalPath=Path()
        var rectFOval=RectF(50f,50f,240f,200f)
        ovalPath.addOval(rectFOval,Path.Direction.CCW)
        canvas.drawPath(ovalPath,paint)

        /**
         * 6:弧形路径
         * void addArc (RectF oval, float startAngle, float sweepAngle)
         * 参数：
         * RectF oval：弧是椭圆的一部分，这个参数就是生成椭圆所对应的矩形；
         * float startAngle：开始的角度，X轴正方向为0度
         * float sweepAngel：持续的度数；
         */

        var pathArc=Path()
        var rectArc=RectF(50f,50f,240f,200f)
        pathArc.addArc(rectArc,0f,100f)
        canvas.drawPath(pathArc,paint)

        /**
         * 二:文字
         * //普通设置
         * paint.setStrokeWidth (5);//设置画笔宽度
         * paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
         * paint.setStyle(Paint.Style.FILL);//绘图样式，对于设文字和几何图形都有效
         * paint.setTextAlign(Align.CENTER);//设置文字对齐方式，取值：align.CENTER、align.LEFT或align.RIGHT
         * paint.setTextSize(12);//设置文字大小
         *
         * //样式设置
         * paint.setFakeBoldText(true);//设置是否为粗体文字
         * paint.setUnderlineText(true);//设置下划线
         * paint.setTextSkewX((float) -0.25);//设置字体水平倾斜度，普通斜体字是-0.25
         * paint.setStrikeThruText(true);//设置带有删除线效果
         *
         * //其它设置
         * paint.setTextScaleX(2);//只会将水平方向拉伸，高度不会变
         */



    }
}