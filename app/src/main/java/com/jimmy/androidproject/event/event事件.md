### ACTION_DOWN
* 对于 dispatchTouchEvent，onTouchEvent，return true是终结事件传递。return false 是回溯到父View的onTouchEvent方法。
* ViewGroup 想把自己分发给自己的onTouchEvent，需要拦截器onInterceptTouchEvent方法return true 把事件拦截下来。
* ViewGroup 的拦截器onInterceptTouchEvent 默认是不拦截的，所以return super.onInterceptTouchEvent()=return false；
* View 没有拦截器，为了让View可以把事件分发给自己的onTouchEvent，View的dispatchTouchEvent默认实现（super）就是把事件分发给自己的onTouchEvent。

### 关于ACTION_MOVE 和 ACTION_UP
* ACTION_MOVE和ACTION_UP在传递的过程中并不是和ACTION_DOWN 一样，你在执行ACTION_DOWN的时候返回了false，后面一系列其它的action就不会再得到执行了
* 所以我们就基本可以得出结论如果在某个控件的dispatchTouchEvent 返回true消费终结事件，那么收到ACTION_DOWN 的函数也能收到 ACTION_MOVE和ACTION_UP。
* 对于在onTouchEvent消费事件的情况：在哪个View的onTouchEvent 返回true，那么ACTION_MOVE和ACTION_UP的事件从上往下传到这个View后就不再往下传递了，
  而直接传给自己的onTouchEvent 并结束本次事件传递过程。

> 总结： 

对于ACTION_MOVE、ACTION_UP总结：ACTION_DOWN事件在哪个控件消费了（return true）， 
那么ACTION_MOVE和ACTION_UP就会从上往下（通过dispatchTouchEvent）做事件分发往下传，就只会传到这个控件，不会继续往下传，
如果ACTION_DOWN事件是在dispatchTouchEvent消费，那么事件到此为止停止传递，如果ACTION_DOWN事件是在onTouchEvent消费的，
那么会把ACTION_MOVE或ACTION_UP事件传给该控件的onTouchEvent处理并结束传递。
 
 > 链接：https://www.jianshu.com/p/e99b5e8bd67b


### 关于onTouch和onClick事件谁先执行的

onTouch是优先于onClick执行的，并且onTouch执行了两次，一次是ACTION_DOWN，一次是ACTION_UP(你还可能会有多次ACTION_MOVE的执行，如果你手抖了一下)
。因此事件传递的顺序是先经过onTouch，再传递到onClick。

### 如果我们尝试把onTouch方法里的返回值改成true会出现什么问题？
onClick方法不再执行了

`
public boolean dispatchTouchEvent(MotionEvent event) {
    if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
            mOnTouchListener.onTouch(this, event)) {
        return true;
    }
    return onTouchEvent(event);
}
`
原因：任何一个view的touch事件都会执行dispatchTouchEvent()方法，如果onTouch()方法返回true则不会执行onTouchEvent()方法。
而onClick事件是在onTouchEvent()的ACTION_UP事件里面

> https://blog.csdn.net/guolin_blog/article/details/9097463

### 解决事件滑动冲突的思路及方法
#### 常见滑动冲突的三种情况
* 父容器上下滑动，子容器左右滑动 比如：scrollView里面嵌套Viewpager
* 父容器和子容器都是同一个方向 比如：viewpager 里面嵌套viewpager(貌似viewpager内部已经处理了) 或者viewpager 里面嵌套webview(需要横向滑动)
* 上述两种情况的嵌套

#### 下面的两种方法针对第一种情况（滑动方向不同）
* 外部解决法
`
@Override
public boolean onInterceptTouchEvent(MotionEvent ev) {
    final float x = ev.getX();
    final float y = ev.getY();

    final int action = ev.getAction();
    switch (action) {
        case MotionEvent.ACTION_DOWN:
            mDownPosX = x;
            mDownPosY = y;

            break;
        case MotionEvent.ACTION_MOVE:
            final float deltaX = Math.abs(x - mDownPosX);
            final float deltaY = Math.abs(y - mDownPosY);
            // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
            if (deltaX > deltaY) {
                return false;
            }
    }

    return super.onInterceptTouchEvent(ev);
}

`
* 内部解决法
实现思路 如下，重写子 View的dispatchTouchEvent方法，在Action_down 动作中通过方法 requestDisallowInterceptTouchEvent（true） 先请求 父 View不要拦截事件，
这样保证子 View 能够接受到 Action_move 事件，再在 Action_move 动作中根据自己的逻辑是否要拦截事件，不需要拦截事件的话再交给 父 View 处理。
`
@Override
public boolean dispatchTouchEvent(MotionEvent ev) {
    int x = (int) ev.getRawX();
    int y = (int) ev.getRawY();
    int dealtX = 0;
    int dealtY = 0;
    
    switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            dealtX = 0;
            dealtY = 0;
            // 保证子View能够接收到Action_move事件
            getParent().requestDisallowInterceptTouchEvent(true);
            break;
        case MotionEvent.ACTION_MOVE:
            dealtX += Math.abs(x - lastX);
            dealtY += Math.abs(y - lastY);
            Log.i(TAG, "dealtX:=" + dealtX);
            Log.i(TAG, "dealtY:=" + dealtY);
            // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
            if (dealtX >= dealtY) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            lastX = x;
            lastY = y;
            break;
        case MotionEvent.ACTION_CANCEL:
            break;
        case MotionEvent.ACTION_UP:
            break;

    }
    return super.dispatchTouchEvent(ev);
}

`
> 原文链接：https://blog.csdn.net/gdutxiaoxu/article/details/52939127

