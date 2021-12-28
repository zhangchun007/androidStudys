### viewpager概述
viewpager不直接处理每一个视图而是将各个视图与一个键联系起来。这个键用来跟踪且唯一代表一个页面，不仅如此，该键还独立于这个页面所在adapter的位置。
当pageradapter将要改变的时候他会调用startUpdate函数，接下来会调用一次或多次的instantiateItem或者destroyItem。最后在更新的后期会调用finishUpdate。
当finishUpdate返回时 instantiateItem返回的对象应该添加到父ViewGroup destroyItem返回的对象应该被ViewGroup删除。
methodisViewFromObject(View, Object)代表了当前的页面是否与给定的键相关联。

对于非常简单的pageradapter或许你可以选择用page本身作为键，在创建并且添加到viewgroup后instantiateItem方法里返回该page本身即可destroyItem将会将该page从viewgroup里面移除。
isViewFromObject方法里面直接可以返回view == object。

对于上面两段话，我这里有两点要着重讲一下：

1、第一段说明了，键（Key）的概念，首先这里要清楚的一点是，每个滑动页面都对应一个Key，而且这个Key值是用来唯一追踪这个页面的，也就是说每个滑动页面都与一个唯一的Key一一对应。
大家先有这个概念就好，关于这个Key是怎么来的，下面再讲。

2、第二段简单讲了一个应用，即将当前页面本身的View作为Key。其实这个应用就是我们前一章讲的例子应用。不太理解？没关系，下面细讲。下面我们讲讲Key的问题
原文链接：https://blog.csdn.net/harvic880925/article/details/38487149

### PageAdapter——PageView的适配器
PageAdapter 必须重写的四个函数：

* boolean isViewFromObject(View arg0, Object arg1)
* int getCount() 
* void destroyItem(ViewGroup container, int position,Object object)
* Object instantiateItem(ViewGroup container, int position)

先看看各个函数，我们上面都做了什么吧：

#### getCount():返回要滑动的VIew的个数

```
@Override
public int getCount() {
	// TODO Auto-generated method stub
	return viewList.size();
}
```
#### destroyItem（）：从当前container中删除指定位置（position）的View;

```
@Override
public void destroyItem(ViewGroup container, int position,
		Object object) {
	// TODO Auto-generated method stub
	container.removeView(viewList.get(position));
}
```
#### instantiateItem()：做了两件事，第一：将当前视图添加到container中，第二：返回当前View
```
@Override
public Object instantiateItem(ViewGroup container, int position) {
	// TODO Auto-generated method stub
		container.addView(viewList.get(position));
		
		
		return viewList.get(position);
	}
};
```

这个函数的实现的功能是创建指定位置的页面视图。适配器有责任增加即将创建的View视图到这里给定的container中，这是为了确保在finishUpdate(viewGroup)返回时this is be done!

返回值：返回一个代表新增视图页面的Object（Key），这里没必要非要返回视图本身，也可以这个页面的其它容器。
其实我的理解是可以代表当前页面的任意值，只要你可以与你增加的View一一对应即可，比如position变量也可以做为Key（最后我们举个例子试试可不可行）

心得 ：
1、从说明中可以看到，在代码中，我们的责任是将指定position的视图添加到conatiner中
2、Key的问题：从这个函数就可以看出，该函数返回值就是我们根据参数position增加到conatiner里的View的所对应的Key！！！！！！！

#### isViewFromObject():
`
@Override
public boolean isViewFromObject(View arg0, Object arg1) {
	// TODO Auto-generated method stub
	return arg0 == arg1;
}
`
功能：该函数用来判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View)
返回值：如果对应的是同一个View，返回True，否则返回False。
由于在instantiateItem（）中，我们作为Key返回来的是当前的View，所以在这里判断时，我们直接将Key与View看是否相等来判断是否是同一个View。

### 前面讲解了ViewPager的普通实现方法，但android官方最推荐的一种实现方法却是使用fragment
我们前面用的适配器是PagerAdapter，而对于fragment,它所使用的适配器是：FragmentPagerAdapter.先看看官方对于这个类的解释：
FragmentPagerAdapter派生自PagerAdapter，它是用来呈现Fragment页面的，这些Fragment页面会一直保存在fragment manager中，以便用户可以随时取用。
这个适配器最好用于有限个静态fragment页面的管理。尽管不可见的视图有时会被销毁，但用户所有访问过的fragment都会被保存在内存中。
因此fragment实例会保存大量的各种状态，这就造成了很大的内存开销。所以如果要处理大量的页面切换，建议使用FragmentStatePagerAdapter.
每一个使用FragmentPagerAdapter的ViewPager都要有一个有效的ID集合，有效ID的集合就是Fragment的集合（感谢夫诸同学的提示）
对于FragmentPagerAdapter的派生类，只需要重写getItem(int)和getCount()就可以了。

> 原文链接：https://blog.csdn.net/harvic880925/article/details/38660861

### 获取当前fragment对象实例
`
    protected fun getCurFragment(pageIndex: Int): WeatherPageFragment? {
        try {
            val fragment =
                getAdapter().instantiateItem(binding.vp, pageIndex) as WeatherPageFragment
            if (fragment.isAdded) {
                return fragment
            }
        } catch (e: Exception) {
        }
        return null
    }

`

### 安卓开发之详解getChildFragmentManager和getsupportFragmentManager和getFragmentManager详解
* getFragmentManager()所得到的是所在fragment 的父容器的管理器，
* getChildFragmentManager()所得到的是在fragment   里面子容器的管理器。
* getSupportFragmentManager()主要用于支持 3.0以下android系统API版本，3.0以上系统可以直接调用getFragmentManager() ，因为fragment是3.0以后才出现的组件，
为了这之前的系统版本也能使用fragment,借助V4包里面的getSupportFragmentManager()方法来间接获取FragmentManager()对象，
3.0版本之后，有了Fragment的api，就可以直接使用getFragmentManager()这个方法来获取对象。

*⚠️ 1.Fragment嵌套Fragment要用getChildFragmentManager
> https://blog.csdn.net/allan_bst/article/details/64920076
