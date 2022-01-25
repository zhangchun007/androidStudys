package com.jimmy.androidproject.studyfanxing;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchun
 * @CreateDate: 2021/5/21
 * @Version: 1.0
 */
public class FanXingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //没有泛型会是什么样子的？
        ObjectPoint integerPoint = new ObjectPoint();
        integerPoint.setX(new Integer(100));
        Integer integerX = (Integer) integerPoint.getX();

        ObjectPoint floatPoint = new ObjectPoint();
        floatPoint.setX(new Float(100.12f));
        Float floatX = (Float) floatPoint.getX();

        //但问题来了：注意，注意，我们这里使用了强制转换，我们这里setX（）和getX（）写得很近，
        // 所以我们明确的知道我们传进去的是Float类型，那如果我们记错了呢？
        ObjectPoint floatPoints = new ObjectPoint();
        floatPoints.setX(new Float(100.12f));
        String floatX1 = (String) floatPoints.getX();

        //强制转换时，不会出错。因为编译器也不知道你传进去的是什么，而floatPoint.getX()返回的类型是Object，所以编译时，将Object强转成String是成立的。必然不会报错。
        // 而在运行时，则不然，在运行时，floatPoint实例中明明传进去的是Float类型的变量，非要把它强转成String类型，肯定会报类型转换错误的！

        //---------------------------单变量泛型--------------------------------//
        //那有没有一种办法在编译阶段，即能合并成同一个，又能在编译时检查出来传进去类型不对呢？当然，这就是泛型。
        //泛型是怎么定义及使用
        /**
         * Point<T>
         * 1:泛型的定义
         * 即在类名后面加一个尖括号，括号里是一个大写字母。这里写的是T
         * 2:类中使用泛型
         * 这个T表示派生自Object类的任何类，比如String,Integer,Double等等.所以下面的：定义变量，作为返回值，作为参数传入的定义就很容易理解了。
         * //定义变量
         *  private T x ;
         *  //作为返回值
         * public T getX(){
         *     return x ;
         *  }
         * //作为参数
         * public void setX(T x){
         *     this.x = x ;
         * }
         */

        //泛型使用
        Point<Integer> point1 = new Point<Integer>();
        point1.setX(new Integer(100));
        Log.e("FanXingActivity", point1.getY() + "");

        //FloatPoint使用
        Point<Float> point2 = new Point<Float>();
        point2.setX(new Float(100.12f));
        Log.e("FanXingActivity", point2.getY() + "");

        //泛型实例的构造
        Point<String> p = new Point<String>();
        //这里与普通构造类实例的不同之点在于，普通类构造函数是这样的：Point p = new Point() ;
        //而泛型类的构造则需要在类名后添加上<String>，即一对尖括号，中间写上要传入的类型。

        //重点：
        /**
         * 泛型的优势：
         * 相比我们开篇时使用Object的方式
         * 1：不用强制转换
         * 2：在settVar()时如果传入类型不对，编译时会报错
         */


        //---------------------------多变量泛型--------------------------------//
        /**
         * class MorePoint<T,U>{
         * }
         */
        //也就是在原来的T后面用逗号隔开，写上其它的任意大写字母即可

        //使用
        MorePoint<Integer, String> morePoint = new MorePoint<Integer, String>();
        morePoint.setName("harvic");
        Log.d("FanXingActivity", "morPont.getName:" + morePoint.getName());

        //泛型字母的意义
        /**
         *  E — Element，常用在java Collection里，如：List<E>,Iterator<E>,Set<E>
         *  K,V — Key，Value，代表Map的键值对
         *  N — Number，数字
         *  T — Type，类型，如String，Integer等等
         */

        //---------------------------泛型接口定义以及使用--------------------------------//

        //使用方法一：非泛型类
        // 要清楚的一点是InfoImpl不是一个泛型类！因为他类名后没有<T>！
        // 然后在在这里我们将Info<String>中的泛型变量T定义填充为了String类型。所以在重写时setVar（）和getVar（）时，IDE会也我们直接生成String类型的重写函数。
        InfoImpl info = new InfoImpl("harvic");
        Log.d("FanXingActivity", "info:" + info.getVar());

        //使用方法一：泛型；
        //使用泛型类来继承泛型接口的作用就是让用户来定义接口所使用的变量类型，而不是像方法一那样，在类中写死。
        Info<String> i = new InfoImpls<String>("harvic");
        Log.d("FanXingActivity", "info:" + i.getVar());

        //---------------------------泛型函数定义及使用--------------------------------//
        //方式一：不带返回值
        //从结果中我们可以看到，这两种方法的结果是完全一样的，但他们还有些区别的，区别如下：
        //方法一，可以像普通方法一样，直接传值，任何值都可以（但必须是派生自Object类的类型，比如String,Integer等），函数会在内部根据传进去的参数来识别当前T的类别。
        // 但尽量不要使用这种隐式的传递方式，代码不利于阅读和维护。因为从外观根本看不出来你调用的是一个泛型函数。

        //方法二，与方法一不同的地方在于，在调用方法前加了一个<String>来指定传给<T>的值，如果加了这个<String>来指定参数的值的话，
        // 那StaticMethod（）函数里所有用到的T类型也就是强制指定了是String类型。这是我们建议使用的方式。

        //静态方法
        StaticFans.StaticMethod("adfdsa");//使用方法一
        StaticFans.<String>StaticMethod("adfdsa");//使用方法二

        //常规方法
        StaticFans staticFans = new StaticFans();
        staticFans.OtherMethod(new Integer(123));//使用方法一
        staticFans.<Integer>OtherMethod(new Integer(123));//使用方法二

        //方式二：带返回值

        StaticFans.parseArray("json字符串",SuccessModel.class);


        //泛型数组
        Integer s[]=StaticFans.fun(1,2,3,4,5);
        Integer[] result=StaticFans.fun(s);


        //------------------------泛型进阶-------------------------//

        //泛型绑定 extends
        //泛型绑定--接口
        StringCompareTo result2=StaticFans.min(new StringCompareTo("123"),new StringCompareTo("1234"),new StringCompareTo("12345"));
        Log.e("StringCompareTo","min=="+result2);

        //泛型绑定--类
        String name1=StaticFans.getFruitName(new Banana());
        String name2=StaticFans.getFruitName(new Apple());
        Log.e("getFruitName","name1=="+name1);
        Log.e("getFruitName","name2=="+name2);

        //通配符？
        //通配符？的extends绑定
        Point<? extends Number>point;
        point=new Point<Float>();
        point=new Point<Number>();
        point=new Point<Integer>();
        point=new Point<Double>();
        point=new Point<Long>();
 //       point=new Point<String>(); //错误
 //       point=new Point<Object>();//错误

        //注意 利用<? extends Number>定义的变量，只可取其中的值，不可修改
        Number number = point.getX();
        //point.setX(new Integer(123)); //报错

//        原因：
//        首先，point的类型是由Point<? extends Number>决定的，并不会因为point = new Point<Integer>(3,3) 而改变类型。
//        即便point = new Point<Integer>(3,3);之后，point的类型依然是Point<? extends Number>，即派生自Number类的未知类型！！！
//
//        正因为point的类型为 Point<? extends Number> point，那也就是说，填充Point的泛型变量T的为<? extends Number>，这是一个什么类型？
//        未知类型！！！怎么可能能用一个未知类型来设置内部值！这完全是不合理的。
//        但取值时，正由于泛型变量T被填充为<? extends Number>，所以编译器能确定的是T肯定是Number的子类，编译器就会用Number来填充T


        //通配符？的super绑定
        //CEO extends Manager -- Manager extends Employee
        List<? super Manager>list;

        list=new ArrayList<Employee>();
        //存
        //list.add(new Employee()); //编译错误
        list.add(new Manager());
        list.add(new CEO());

        //取值：
        Object object = list.get(0);
        //Employee employee = list.get(0);//编译错误


    }


}
