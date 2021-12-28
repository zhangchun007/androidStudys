package com.jimmy.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Description:     文字
 * @Author:         zhangchun
 * @CreateDate:     2021/7/10
 * @Version:        1.0
 *
 */
class CustomView4(context: Context, attrs: AttributeSet? = null, defstyle: Int = 0) :
    View(context, attrs, defstyle) {

    private var paint: Paint
    private var text = "嘻嘻，真有意思"

    private var string = "风萧萧兮易水寒，壮士一去兮不复返"

    private var floatArray = floatArrayOf(80f, 100f, 80f, 200f, 80f, 300f, 80f, 400f)

    init {
        paint = Paint()
        paint.isAntiAlias = true //抗锯齿功能
        paint.color = Color.RED //设置画笔颜色
        paint.style = Paint.Style.STROKE //设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
        paint.strokeWidth = 5f //设置画笔宽度
        paint.textSize = 80f//设置文字大小

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        /**
         * 一、Paint相关设置
         *
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

        /**
         * (1):绘图样式的区别：
         */

        //绘图样式，设置为填充
        paint.style = Paint.Style.FILL
        canvas.drawText(text, 10f, 100f, paint)


        //绘图样式设置为描边
        paint.style = Paint.Style.STROKE
        canvas.drawText(text, 10f, 200f, paint)

        //绘图样式设置为描边
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawText(text, 10f, 300f, paint)


        /**
         * (2):文字样式设置及倾斜度正负区别
         */

        //样式设置
        paint.isFakeBoldText = true//设置是否为粗体文字
        paint.isUnderlineText = true //设置下划线
        paint.isStrikeThruText = true ////设置带有删除线效果

        //设置字体水平倾斜度，普通斜体字是-0.25，可见往右斜
        paint.textSkewX = -0.25f
        canvas.drawText(text, 10f, 100f, paint)

        //水平倾斜度设置为：0.25，往左斜
        paint.textSkewX = 0.25f
        canvas.drawText(text, 10f, 200f, paint)

        /**
         * (3):水平拉伸设置（ paint.setTextScaleX(2);）
         *
         * 写三行字，第一行，水平未拉伸的字体；第二行，水平拉伸两倍的字体；
         * 第三行，水平未拉伸和水平拉伸两部的字体写在一起，可以发现，仅是水平方向拉伸，高度并未改变
         */

        //变通样式字体
        canvas.drawText(text, 10f, 100f, paint);

        //水平方向拉伸两倍
        paint.textScaleX = 2f //只会将水平方向拉伸，高度不会变
        canvas.drawText(text, 10f, 200f, paint);

        //写在同一位置,不同颜色,看下高度是否看的不变
        paint.textScaleX = 1f//先还原拉伸效果
        canvas.drawText(text, 10f, 300f, paint);

        paint.color = Color.GREEN
        paint.textScaleX = 2f//重新设置拉伸效果
        canvas.drawText(text, 10f, 300f, paint);


        // 二：canvas 绘图方式
        /**
         * （1）、普通水平绘制
         * 构造函数：
         * void drawText (String text, float x, float y, Paint paint)
         * void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
         * void drawText (String text, int start, int end, float x, float y, Paint paint)
         * void drawText (char[] text, int index, int count, float x, float y, Paint paint)
         */


        /**
         * （2）指定个个文字位置
         *
         * void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
         * void drawPosText (String text, float[] pos, Paint paint)
         * 说明：
         * 第一个构造函数：实现截取一部分文字绘制；
         * 参数说明：
         * char[] text：要绘制的文字数组
         * int index:：第一个要绘制的文字的索引
         * int count：要绘制的文字的个数，用来算最后一个文字的位置，从第一个绘制的文字开始算起
         * float[] pos：每个字体的位置，同样两两一组，如｛x1,y1,x2,y2,x3,y3……｝
         */
        canvas.drawPosText(text, floatArray, paint)

        /**
         * (3):沿路径绘制
         *
         * void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
         * void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
         *
         * 参数说明：
         * 有关截取部分字体绘制相关参数（index,count），没难度，就不再讲了，下面首重讲hOffset、vOffset
         * float hOffset  : 与路径起始点的水平偏移距离
         * float vOffset  : 与路径中心的垂直偏移量
         */

        var circlePath = Path()
        circlePath.addCircle(220f, 200f, 180f, Path.Direction.CCW)
        canvas.drawPath(circlePath, paint)

        var circlePath2 = Path()
        circlePath2.addCircle(750f, 200f, 180f, Path.Direction.CCW)
        canvas.drawPath(circlePath2, paint)

        paint.color = Color.GREEN

        //hoffset、voffset参数值全部设为0，看原始状态是怎样的
        canvas.drawTextOnPath(string, circlePath, 0f, 0f, paint);
        //第二个路径，改变hoffset、voffset参数值
        canvas.drawTextOnPath(string, circlePath2, 80f, 30f, paint);


        //三：字体样式设置
        /**
         * 在Paint中设置字体样式：
         * paint.setTypeface(typeface);
         *
         * Typeface相关
         * Typeface是专门用来设置字体样式的，通过paint.setTypeface()来指定。可以指定系统中的字体样式，也可以指定自定义的样式文件中获取。
         * 要构建Typeface时，可以指定所用样式的正常体、斜体、粗体等，如果指定样式中，没有相关文字的样式就会用系统默认的样式来显示，一般默认是宋体。
         *
         * 创建Typeface：
         * Typeface create(String familyName, int style) //直接通过指定字体名来加载系统中自带的文字样式
         * Typeface create(Typeface family, int style)     //通过其它Typeface变量来构建文字样式
         * Typeface createFromAsset(AssetManager mgr, String path) //通过从Asset中获取外部字体来显示字体样式
         * Typeface createFromFile(String path)//直接从路径创建
         * Typeface createFromFile(File path)//从外部路径来创建字体样式
         * Typeface defaultFromStyle(int style)//创建默认字体
         *
         * 上面的各个参数都会用到Style变量,Style的枚举值如下:
         * Typeface.NORMAL  //正常体
         * Typeface.BOLD //粗体
         * Typeface.ITALIC //斜体
         * Typeface.BOLD_ITALIC //粗斜体
         */

        /**
         * (1):使用系统中的字体
         */
        var familyName="宋体"
        var font=Typeface.create(familyName,Typeface.NORMAL)
        paint.typeface=font
        canvas.drawText(text,10f,100f,paint)


        /**
         * (2):自定义字体
         *
         * 自定义字体的话，我们就需要从外部字体文件加载我们所需要的字形的，从外部文件加载字形所使用的Typeface构造函数如下面三个：
         * Typeface createFromAsset(AssetManager mgr, String path) //通过从Asset中获取外部字体来显示字体样式
         * Typeface createFromFile(String path)//直接从路径创建
         * Typeface createFromFile(File path)//从外部路径来创建字体样式
         *
         * 后面两个从路径加载难度不大，而我们一般也不会用到，这里我们说说从Asset文件中加载；
         * 首先在Asset下建一个文件夹，命名为Fonts，然后将字体文件jian_luobo.ttf 放入其中
         */

        var mgr=context.assets
        var typeface=Typeface.createFromAsset(mgr,"fonts/weather_forecast_fonts.ttf")
        paint.typeface=typeface
        canvas.drawText(text,10f,100f,paint)



    }
}