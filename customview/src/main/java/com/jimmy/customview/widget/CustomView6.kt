package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Description:    drawText()详解
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 */
class CustomView6(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {
    private var paint: Paint
    private var baseLineX = 0f
    private var baseLineY = 200f
    private var top = 200f
    private var center = 200f
    private var text = "我命由我不由天"

    init {
        paint = Paint()
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.FILL //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 5f //设置画笔宽度
        paint.textSize = 80f//设置文字大小

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //一：概述
        /**
         * 1：四线格与基线
         * 小时候，我们在刚开始学习写字母时，用的本子是四线格的，我们必须把字母按照规则写在四线格内。
         * 那么问题来了，在canvas在利用drawText绘制文字时，也是有规则的，这个规则就是基线！
         * 可见基线就是四线格中的第三条线！
         * 也就是说，只要基线的位置定了，那文字的位置必然是定了的！
         */

        /**
         * 2：canvas.drawText()与基线
         *
         * text:要绘制的文字
         * x：绘制原点x坐标
         * y：绘制原点y坐标
         * paint:用来做画的画笔
         * public void drawText(String text, float x, float y, Paint paint)
         *
         * 但这里有两个参数需要非常注意，表示原点坐标的x和y.很多同学可能会认为，这里传进去的原点参数(x,y)是所在绘制文字所在矩形的左上角的点。但实际上并不是！
         * y所代表的是基线的位置！
         *
         * 结论：
         * 1、drawText是中的参数y是基线的位置。
         * 2、一定要清楚的是，只要x坐标、基线位置、文字大小确定以后，文字的位置就是确定的了。
         */
//        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)

        //写文字
//        paint.color = Color.GREEN
//        paint.textSize = 120f
//        canvas.drawText(text, baseLineX, baseLineY, paint)

        /**
         * 3:paint.setTextAlign(Paint.Align.XXX);
         *
         * 我们在drawText(text, x, y, paint)中传进去的源点坐标(x,y);其中，y表示的基线的位置。那x代表什么呢？
         * x代表所要绘制文字所在矩形的相对位置。相对位置就是指指定点（x,y）在在所要绘制矩形的位置
         * 而相对x坐标的位置，只有左、中、右三个位置了。也就是所绘制矩形可能是在x坐标的左侧绘制，也有可能在x坐标的中间，也有可能在x坐标的右侧。
         * 而定义在x坐标在所绘制矩形相对位置的函数是:
         *
         * (1):setTextAlign(Paint.Align.LEFT) //(x,y)点在文字所在矩形的左侧
         * (2):setTextAlign(Paint.Align.CENTER)//(x,y)点在文字所在矩形的中间
         * (3):setTextAlign(Paint.Align.RIGHT)////(x,y)点在文字所在矩形的右侧
         */

//        paint.color = Color.GREEN
//        paint.textSize = 120f
//        paint.textAlign=Paint.Align.RIGHT
//        canvas.drawText(text, baseLineX, baseLineY, paint)

        //二、drawText的四线格与FontMetrics
        /**
         * 1、Text的绘图四线格
         * 前面我们讲了基线，其实除了基线，系统在绘制Text时，还是有其它线的，我们来看个图：
         * (1) top  可绘制的最高高度所在线
         * (2) ascent 系统建议的，绘制单个字符时，字符应当的最高高度所在线
         * (3) baseline
         * (4) descent 系统建议的，绘制单个字符时，字符应当的最低高度所在线
         * (5) bottom 可绘制的最低高度所在线
         */

        /**
         * 2：FontMetrics
         * 上面我们讲了，系统在画文字时的五条线，baseline、ascent、descent、top、bottom我们知道baseline的位置是我们在构造drawText()时的参数y来决定的，
         * 那ascent,descent,top,bottom这些线的位置要怎么计算出来呢？
         * Android给我们提供了一个类：FontMetrics，它里面有四个成员变量：
         *
         * FontMetrics::ascent;
         * FontMetrics::descent;
         * FontMetrics::top;
         * FontMetrics::bottom;
         *
         * 他们的意义与值的计算方法分别如下：
         * ascent = ascent线的y坐标 - baseline线的y坐标；
         * descent = descent线的y坐标 - baseline线的y坐标；
         * top = top线的y坐标 - baseline线的y坐标；
         * bottom = bottom线的y坐标 - baseline线的y坐标
         *
         * 同理可以得到：
         * ascent线Y坐标 = baseline线的y坐标 + fontMetric.ascent；
         * descent线Y坐标 = baseline线的y坐标 + fontMetric.descent；
         * top线Y坐标 = baseline线的y坐标 + fontMetric.top；
         * bottom线Y坐标 = baseline线的y坐标 + fontMetric.bottom；
         */

        /**
         * 3：获取FontMetrics对象
         *
         * 获取FontMetrics对象是根据paint对象来获取的：
         * Paint paint = new Paint();
         * Paint.FontMetrics fm = paint.getFontMetrics(); //得到的值的类型为float
         * Paint.FontMetricsInt fmInt = paint.getFontMetricsInt(); //得到的值的类型Int
         *
         */

        /**
         * 4:计算Text四线格位置
         */

//        var fm = paint.fontMetrics
//        var ascent = baseLineY + fm.ascent
//        var descent = baseLineY + fm.descent
//        var top = baseLineY + fm.top
//        var bottom = baseLineY + fm.bottom
//        canvas.drawText(text, baseLineX, baseLineY, paint)
//        //画基线
//        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
//        //画top线
//        paint.color = Color.BLUE
//        canvas.drawLine(baseLineX, top, 3000f, top, paint)
//        //画ascent线
//        paint.color = Color.GREEN
//        canvas.drawLine(baseLineX, ascent, 3000f, ascent, paint)
//        //画descent线
//        paint.color = Color.YELLOW
//        canvas.drawLine(baseLineX, descent, 3000f, descent, paint)
//        //画bottom线
//        paint.color = Color.YELLOW
//        canvas.drawLine(baseLineX, bottom, 3000f, bottom, paint)

        //三：所绘文字宽度、高度和最小矩形获取
        /**
         * 1：字符串所占高度和宽度
         *
         *（1）、高度
         * 字符串所占高度很容易得到，直接用bottom线所在位置的Y坐标减去top线所在位置的Y坐标就是字符串所占的高度：
         * Paint.FontMetricsInt fm = paint.getFontMetricsInt();
         * int top = baseLineY + fm.top;
         * int bottom = baseLineY + fm.bottom;
         * //所占高度
         * int height = bottom - top;
         *
         * （2）、宽度
         * int width = paint.measureText(String text);
         */

        /**
         * 2:文字最小矩形
         * 要获取最小矩形，也是通过系统函数来获取的，函数及意义如下：
         *
         * 获取指定字符串所对应的最小矩形，以（0，0）点所在位置为基线
         * @param text  要测量最小矩形的字符串
         * @param start 要测量起始字符在字符串中的索引
         * @param end   所要测量的字符的长度
         * @param bounds 接收测量结果
         *
         * public void getTextBounds(String text, int start, int end, Rect bounds);
         *
         */
        //以（0，0）点所在位置为基线
//        var minRect = Rect()
//        paint.getTextBounds(text, 0, text.length, minRect)
//        Log.e("jimmy","矩形--"+minRect.toShortString())
        //打印结果：[3,-68][557,8]

        /**
         * 大家可能会有疑问，为什么左上角的Y坐标是个负数？从代码中，我们也可以看到，我们并没有给getTextBounds（）传递基线位置。
         * 那它就是以（0，0）为基线来得到这个最小矩形的！所以这个最小矩形的位置就是以（0，0）为基线的结果！
         * 由于paint.getTextBounds（）得到最小矩形的基线是y = 0;那我们直接将这个矩形移动baseline的距离就可以得到这个矩形实际应当在的位置了。
         * 所以矩形应当所在实际位置的坐标是：
         *
         * Rect minRect = new Rect();
         * paint.getTextBounds(text,0,text.length(),minRect);
         * //最小矩形，实际top位置
         * int minTop = bounds.top + baselineY;
         * //最小矩形，实际bottom位置
         * int minBottom = bounds.bottom + baselineY;
         */

        /**
         * ⚠️：
         * 但需要注意的是：矩形右下角的值并不一定是baselinex+width！它的具体取值是跟paint.setTextAlign(Paint.Align.LEFT)有关的，因为我们这里设置为Paint.Align.LEFT，
         * 所以是baselinex+width。如果设置为Paint.Align.CENTER,那么右下角的X坐标值为baselinex+width/2；
         * 再者如果设置为Paint.Align.RIGHT,那么右下角的X坐标就是baselineX;所占矩形的四个角的所有位置是与paint.setTextAlign()的设置紧密相关的，
         * 至于各个点的计算方法就不再细讲了，根据我们前面讲的paint.setTextAlign()的显示效果是非常容易想到的
         */
        //画text所占的区域
//        var fm=paint.fontMetrics
//        var top=baseLineY+fm.top
//        var bottom=baseLineY+fm.bottom
//        var width=paint.measureText(text)
//        var rect=Rect(baseLineX.toInt(),top.toInt(),(baseLineX+width).toInt(),bottom.toInt())
//        paint.color=Color.GREEN
//        canvas.drawRect(rect,paint)
//
//        //画最小矩形
//        var minRect=Rect()
//        paint.getTextBounds(text,0,text.length,minRect)
//        minRect.top=baseLineY.toInt()+minRect.top
//        minRect.bottom=baseLineY.toInt()+minRect.bottom
//        paint.color=Color.RED
//        canvas.drawRect(minRect,paint)
//
//        //写文字
//        paint.color=Color.BLACK
//        canvas.drawText(text,baseLineX,baseLineY,paint)


        //四：定点写字
        //当我们设定一个点，如何到得基线位置，进而画出字符串。
        /**
         * 1、给定左上顶点绘图
         * 这部分，我们假定给出所要绘制矩形的左上角顶点坐标，然后画出这个文字。
         * 在这个图中，我们给定左上角的位置，即(left,top)；我们知道要画文字，drawText（）中传进去的Y坐标是基线的位置，所以我们就必须根据top的位置计算出baseline的位置。
         *
         * FontMetrics.top = top - baseline;
         * 所以baseline = top - FontMetrics.top;
         * 因为FontMetrics.top是可以得到的，又因为我们的top坐标是给定的，所以通过这个公式就能得到baseline的位置了
         */
        //画top线
//        paint.color = Color.YELLOW
//        canvas.drawLine(baseLineX, top, 3000f, top, paint)
//
//        //计算出baseLine位置
//        var fm = paint.fontMetrics
//        var baseLineY = top - fm.top
//
//        //画基线
//        paint.color = Color.RED
//        canvas.drawLine(baseLineX, baseLineY, 3000f, baseLineY, paint)
//
//        //写文字
//        paint.color = Color.GREEN
//        canvas.drawText(text, baseLineX, baseLineY, paint)

        /**
         * 2、给定中间线位置绘图
         * 在这个图中，总共有四条线：top线，bottom线，baseline和center线；
         * 图中center线正是在top线和bottom线的正中间。
         * baseline = center + (FontMetrics.bottom - FontMetrics.top)/2 - FontMetrics.bottom;
         */

        //画center线
        paint.color = Color.YELLOW;
        canvas.drawLine(baseLineX, center, 3000f, center, paint);

        //计算出baseLine位置
        var fm = paint.fontMetricsInt;
        var baseLineYCenter = center + (fm.bottom - fm.top)/2 - fm.bottom;

        //画基线
        paint.color = Color.RED;
        canvas.drawLine(baseLineX, baseLineYCenter, 3000f, baseLineYCenter, paint);

        //写文字
        paint.color = Color.GREEN;
        canvas.drawText(text, baseLineX, baseLineYCenter, paint);





    }
}