package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Description: Paint与Canvas
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 */
class CustomView1(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {

    private var paint: Paint
    private var linesPts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)
    private var pointPts = floatArrayOf(10f, 10f, 100f, 100f, 200f, 200f, 400f, 400f)

    init {
        paint = Paint()
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.FILL //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 5f //设置画笔宽度
//        paint.setShadowLayer(10f, 15f, 15f, Color.GREEN);//设置阴影

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //设置画布背景颜色
        canvas.drawRGB(255, 255, 255)

        //基本几何图形绘制
        /**
         * 1:画直线
         * startX:开始点X坐标
         * startY:开始点Y坐标
         * stopX:结束点X坐标
         * stopY:结束点Y坐标
         */
        canvas.drawLine(100f, 100f, 200f, 200f, paint)
        /**
         * 2:画多条直线
         * pts:是点的集合，大家下面可以看到，这里不是形成连接线，而是每两个点形成一条直线，pts的组织方式为｛x1,y1,x2,y2,x3,y3,……｝
         */
        canvas.drawLines(linesPts, paint)

        /**
         * 3:画点
         * float X：点的X坐标
         * float Y：点的Y坐标
         */
        canvas.drawPoint(100f, 100f, paint)

        /**
         * 4:画多个点
         * void drawPoints (float[] pts, Paint paint)
         * void drawPoints (float[] pts, int offset, int count, Paint paint)
         *
         * float[] pts:点的合集，与上面直线一直，样式为｛x1,y1,x2,y2,x3,y3,……｝
         * int offset:集合中跳过的数值个数，注意不是点的个数！一个点是两个数值；
         * count:参与绘制的数值的个数，指pts[]里人数值个数，而不是点的个数，因为一个点是两个数值
         *
         * 下面举例说明上面offset与count的含义：（跳过第一个点，画出后面两个点，第四个点不画），注意一个点是两个数值！
         */
        canvas.drawPoints(pointPts,2,4,paint)

        /**
         * 5:矩形工具类RectF与Rect
         * RectF：
         * 构造函数有下面四个，但最常用的还是第二个，根据四个点构造出一个矩形；
         * RectF()
         * RectF(float left, float top, float right, float bottom)
         * RectF(RectF r)
         * RectF(Rect r)
         *
         * Rect：
         * 构造函数如下，最常用的也是根据四个点来构造矩形
         * Rect()
         * Rect(int left, int top, int right, int bottom)
         * Rect(Rect r)
         *
         */
        //直接构造
        canvas.drawRect(10f,10f,100f,100f,paint)
        //使用RectF构造
        var rect=RectF(120f, 10f, 210f, 100f)
        canvas.drawRect(rect,paint)
        //使用Rect构造
        val rect2 = Rect(230, 10, 320, 100)
        canvas.drawRect(rect2, paint)

        /**
         * 6：圆角矩形
         * 参数：
         * RectF rect:要画的矩形
         * float rx:生成圆角的椭圆的X轴半径
         * float ry:生成圆角的椭圆的Y轴半径
         */

        var rectRound =  RectF(100f, 10f, 300f, 100f)
        canvas.drawRoundRect(rectRound,20f,10f,paint)

        /**
         * 7:圆形
         * 参数：
         * float cx：圆心点X轴坐标
         * float cy：圆心点Y轴坐标
         * float radius：圆的半径
         */
       canvas.drawCircle(190f, 200f, 150f, paint)

        /**
         * 8:椭圆
         * 椭圆是根据矩形生成的，以矩形的长为椭圆的X轴，矩形的宽为椭圆的Y轴，建立的椭圆图形
         * void drawOval (RectF oval, Paint paint)
         * 参数：
         * RectF oval：用来生成椭圆的矩形
         */
        var rectOval=RectF(100f,10f,300f,100f)
        canvas.drawRect(rectOval, paint);//画矩形
        paint.setColor(Color.GREEN);//更改画笔颜色
        canvas.drawOval(rectOval, paint);//同一个矩形画椭圆

        /**
         * 10、弧
         * 弧是椭圆的一部分，而椭圆是根据矩形来生成的，所以弧当然也是根据矩形来生成的；
         * void drawArc (RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint)
         * 参数：
         * RectF oval:生成椭圆的矩形
         * float startAngle：弧开始的角度，以X轴正方向为0度
         * float sweepAngle：弧持续的角度
         * boolean useCenter:是否有弧的两边，True，还两边，False，只有一条弧
         */

        var rectArc1 =  RectF(100f, 10f, 300f, 100f)
        canvas.drawArc(rectArc1, 0f, 90f, true, paint);

        var rectArc2 =  RectF(400f, 10f, 600f, 100f);
        canvas.drawArc(rectArc2, 0f, 90f, false, paint);



    }
}