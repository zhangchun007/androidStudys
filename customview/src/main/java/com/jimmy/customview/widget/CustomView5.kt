package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Description:     canvas变换与操作
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 */
class CustomView5(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {

    private var paint: Paint

    init {
        paint = Paint()
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.FILL //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 5f //设置画笔宽度

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //一：平移（translate）
        //canvas中有一个函数translate（）是用来实现画布平移的，画布的原状是以左上角为原点，向左是X轴正方向，向下是Y轴正方向，如下
        /**
         * 1：void translate(float dx, float dy)
         * 参数说明：
         * float dx：水平方向平移的距离，正数指向正方向（向右）平移的量，负数为向负方向（向左）平移的量
         * flaot dy：垂直方向平移的距离，正数指向正方向（向下）平移的量，负数为向负方向（向上）平移的量
         */
//        paint.color = Color.GREEN
//        canvas.translate(100f, 100f)
//        var rect1 = Rect(0, 0, 400, 200)
//        canvas.drawRect(rect1, paint)

        //二：屏幕显示与Canvas的关系
        //很多童鞋一直以为显示所画东西的改屏幕就是Canvas，其实这是一个非常错误的理解，比如下面我们这段代码：
        //这段代码中，同一个矩形，在画布平移前画一次，平移后再画一次，大家会觉得结果会怎样？
        var paintGreen = generatePaint(Color.GREEN, Paint.Style.STROKE, 5f)
        var paintRed = generatePaint(Color.RED, Paint.Style.STROKE, 5f)
        var rect = Rect(0, 0, 400, 220)
        //在平移画布前用绿色画下边框
        canvas.drawRect(rect, paintGreen)

        //平移画布后,再用红色边框重新画下这个矩形
        canvas.translate(100f, 100f)
        canvas.drawRect(rect, paintRed)
        //大家是不是会觉得这两个边框会重合？实际结果是这样的。这个结果的关键问题在于，为什么绿色框并没有移动？
        /**
         * 这是由于屏幕显示与Canvas根本不是一个概念！Canvas是一个很虚幻的概念，相当于一个透明图层（用过PS的同学应该都知道），
         * 每次Canvas画图时（即调用Draw系列函数），都会产生一个透明图层，然后在这个图层上画图，画完之后覆盖在屏幕上显示。所以上面的两个结果是由下面几个步骤形成的：
         *
         * (1)调用canvas.drawRect(rect1, paint_green);时，产生一个Canvas透明图层，由于当时还没有对坐标系平移，所以坐标原点是（0，0）；
         * 再在系统在Canvas上画好之后，覆盖到屏幕上显示出来
         * (2)然后再第二次调用canvas.drawRect(rect1, paint_red);时，又会重新产生一个全新的Canvas画布，但此时画布坐标已经改变了，即向右和向下分别移动了100像素，
         * 所以此时的绘图方式为：（合成视图，从上往下看的合成方式）
         *
         * 下面对上面的知识做一下总结：
         * 1、每次调用canvas.drawXXXX系列函数来绘图进，都会产生一个全新的Canvas画布。
         * 2、如果在DrawXXX前，调用平移、旋转等函数来对Canvas进行了操作，那么这个操作是不可逆的！每次产生的画布的最新位置都是这些操作后的位置。（关于Save()、Restore()的画布可逆问题的后面再讲）
         * 3、在Canvas与屏幕合成时，超出屏幕范围的图像是不会显示出来的。
         */

        //三：旋转（Rotate）
        /**
         * 画布的旋转是默认是围绕坐标原点来旋转的，这里容易产生错觉，看起来觉得是图片旋转了，其实我们旋转的是画布，以后在此画布上画的东西显示出来的时候全部看起来都是旋转的。
         * 其实Roate函数有两个构造函数：
         *
         * void rotate(float degrees)
         * void rotate (float degrees, float px, float py)
         * 第一个构造函数直接输入旋转的度数，正数是顺时针旋转，负数指逆时针旋转，它的旋转中心点是原点（0，0）
         * 第二个构造函数除了度数以外，还可以指定旋转的中心点坐标（px,py）
         */

        var paint_green = generatePaint(Color.GREEN, Paint.Style.FILL, 5f)
        var paint_red = generatePaint(Color.GREEN, Paint.Style.STROKE, 5f)
        var rectRotat = Rect(300, 10, 500, 100)
        canvas.drawRect(rectRotat, paint_red)
        //顺时针旋转画布
        canvas.rotate(30f)
        canvas.drawRect(rectRotat, paint_green)

        //四：缩放（scale）
        /**
         * public void scale (float sx, float sy)
         * public final void scale (float sx, float sy, float px, float py)
         * 我就先讲讲第一个构造函数的参数吧
         * float sx:水平方向伸缩的比例，假设原坐标轴的比例为n,不变时为1，在变更的X轴密度为n*sx;所以，sx为小数为缩小，sx为整数为放大
         * float sy:垂直方向伸缩的比例，同样，小数为缩小，整数为放大
         *
         * 注意：这里有X、Y轴的密度的改变，显示到图形上就会正好相同，比如X轴缩小，那么显示的图形也会缩小。一样的。
         */
        var rectScale = Rect(10, 10, 200, 100)
        canvas.drawRect(rectScale, paintGreen)

        canvas.scale(0.5f, 1f)
        canvas.drawRect(rectScale, paintRed)

        //五：扭曲（skew）
        /**
         * void skew (float sx, float sy)
         *
         * 参数说明：
         * float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
         * float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值，
         */
        var rectSkew = Rect(10, 10, 200, 100)
        canvas.drawRect(rectSkew, paintGreen)
        canvas.skew(1.732f, 0f)
        canvas.drawRect(rectSkew, paintRed)

        //六：裁剪画布（clip系列函数）
        /**
         * 裁剪画布是利用Clip系列函数，通过与Rect、Path、Region取交、并、差等集合运算来获得最新的画布形状。
         * 除了调用Save、Restore函数以外，这个操作是不可逆的，一但Canvas画布被裁剪，就不能再被恢复！
         *
         * Clip系列函数如下：
         * boolean clipPath(Path path)
         * boolean clipPath(Path path, Region.Op op)
         * boolean clipRect(Rect rect, Region.Op op)
         * boolean clipRect(RectF rect, Region.Op op)
         * boolean clipRect(int left, int top, int right, int bottom)
         * boolean clipRect(float left, float top, float right, float bottom)
         * boolean clipRect(RectF rect)
         * boolean clipRect(float left, float top, float right, float bottom, Region.Op op)
         * boolean clipRect(Rect rect)
         * boolean clipRegion(Region region)
         * boolean clipRegion(Region region, Region.Op op)
         */
        canvas.drawColor(Color.RED)
        canvas.clipRect(Rect(100,100,200,200))
        canvas.drawColor(Color.GREEN)

        //七：画布的保存与恢复（save()、restore()）
        /**
         * Save（）：每次调用Save（）函数，都会把当前的画布的状态进行保存，然后放入特定的栈中；
         * restore（）：每当调用Restore（）函数，就会把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布，并在这个画布上做画。
         */

        canvas.drawColor(Color.RED);

        //保存当前画布大小即整屏
        canvas.save();

        canvas.clipRect( Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);

        //恢复整屏画布
        canvas.restore();
        canvas.drawColor(Color.BLUE);
        /**
         * 下面我通过一个多次利用Save（）、Restore（）来讲述有关保存Canvas画布状态的栈的概念：代码如下：
         * 在这段代码中，总共调用了四次Save操作。上面提到过，每调用一次Save（）操作就会将当前的画布状态保存到栈中，所以这四次Save（）所保存的状态的栈的状态如下：
         * 第四次：Rect(300, 300, 600, 600)
         * 第三次：Rect(200, 200, 700, 700)
         * 第二次：Rect(100, 100, 800, 800)
         * 第一次：全屏大小
         * 注意在，第四次Save（）之后，我们还对画布进行了canvas.clipRect(new Rect(400, 400, 500, 500));操作，并将当前画布画成白色背景。也就是上图中最小块的白色部分，是最后的当前的画布。
         */
        canvas.drawColor(Color.RED);
        //保存的画布大小为全屏幕大小
        canvas.save();

        canvas.clipRect( Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);
        //保存画布大小为Rect(100, 100, 800, 800)
        canvas.save();

        canvas.clipRect( Rect(200, 200, 700, 700));
        canvas.drawColor(Color.BLUE);
        //保存画布大小为Rect(200, 200, 700, 700)
        canvas.save();

        canvas.clipRect( Rect(300, 300, 600, 600));
        canvas.drawColor(Color.BLACK);
        //保存画布大小为Rect(300, 300, 600, 600)
        canvas.save();

        canvas.clipRect( Rect(400, 400, 500, 500));
        canvas.drawColor(Color.WHITE);


        //将栈顶的画布状态取出来，作为当前画布，并画成黄色背景
        canvas.restore();
        canvas.drawColor(Color.YELLOW);
        //上段代码中，把栈顶的画布状态取出来，作为当前画布，然后把当前画布的背景色填充为黄色




    }

    private fun generatePaint(color: Int, style: Paint.Style, width: Float): Paint {
        var paint = Paint()
        paint.color = color
        paint.style = style
        paint.strokeWidth = width
        return paint
    }
}

