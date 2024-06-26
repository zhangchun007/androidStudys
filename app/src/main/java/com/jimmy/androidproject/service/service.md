### 服务是什么

“Service” 意思即“服务”的意思， Service是Android中实现程序后台运行的解决方案，它适合用于执行不需要和用户交互而还要求长期运行的任务。Service运行在后台，它是不可见的、无界面的程序。Service的运行不依赖于任何用户界面，承担着静悄悄的不为人所注意的工作，即使当程序被切换到后台，或者用户打开了另外一个应用程序，服务仍然能够保持正常运行。
\*\*但是，Service并不是运行在一个独立的进程当中的，而是依赖于创建服务时所在的应用程序进程。\*\*另外，不要被Service的后台概念所迷惑，Service不会自动开启线程，所有的代码都默认运行在主线程当中。使用Service时，如果担心主线程被阻塞，就需要在Service的内部手动创建一个子线程来执行任务。(服务不开启线程执行耗时操作也会产生ANR)
Service可以在很多场合的应用中使用，比如播放多媒体的时候用户启动了其他Activity，这个时候程序要在后台继续播放；比如检测SD卡上文件的变化；再或者在后台记录用户的地理信息位置的改变；或者启动一个服务来运行并一直监听某种动作等等。
\###创建service

```java
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
```

manifest文件中注册：

```java
//     android:enabled="true"//表示启用这个服务
//    android:exported="true" //表示是否允许除了当前程序之外的其他程序访问这个服务
   <service
            android:name=".MyService"
            android:enabled="true"
            android:exported="true"></service>
```

可以看到Myervice继承自service目前只有一个onbind方法比价醒目。这个方法是Service中唯一的一个抽象方法，所以必须在子类实现。
那我们要处理事件的逻辑写在哪里呢？这时我们需要重写Service中的另外一些方法。

```java
public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //该方法在服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Myservice", "---onCreate()---");
    }

    //该方法在每次服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Myservice", "---onStartCommand()---");
        return super.onStartCommand(intent, flags, startId);
    }

    //当服务销毁的会后我们在该方法中回收哪些不在需要使用的资源
    @Override
    public void onDestroy() {
        Log.i("Myservice", "---onDestroy()---");
        super.onDestroy();
    }
}

```

### 启动和停止服务

接下来我们要考虑启动和停止这个服务，因为是四大组件，所以启动跟停止都是用Intent来实现的。

```java
public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_start_service, btn_stop_service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_start_service = findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(this);
        btn_stop_service = findViewById(R.id.btn_stop_service);
        btn_stop_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service://启动服务
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop_service://停止服务
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
        }
    }
}
```

注意：这里完全是由活动来决定服务何时停止的，如果没有点击stop\_service按钮，服务就会一直处在运行状态。那么服务有什么办法让自己停止下来呢？当然可以，只需要在Myservice的任何一个位置调用stopSelf()方法就能让这个服务停止下来。
接下来我们点击按钮测试下：
点击start\_service按钮我们看到

```java
 Log.i("Myservice", "---onCreate()---");
 Log.i("Myservice", "---onStartCommand()---");
```

这两个日志走了，说明服务确实已经启动完成了，我们还可以在手机的设置-->开发者选项-->正在运行的服务 中找到它。
**注意：onCreate()方法是在服务第一次创建的时候调用的，onStartCommand()方法则在每次启动服务的时候都会调用，第一点击start\_service按钮onCreate()，onStartCommand()都会执行，之后如果在连续多次点击几次就只有onStartCommand()方法会执行。**
然后我们再点击下stop\_service，只有如下日志：

```java
Log.i("Myservice", "---onDestroy()---");
```

### 活动和服务进行通信

虽然服务是在活动里面启动的，但在启动了服务之后，活动与服务基本就没什么关系了。那我们能有什么办法让活动与服务的关系更紧密些呢？当然有，这就要借助我们刚刚忽略的onBind()方法。
比如说：我们这里希望在MyService里提供一个下载功能，然后在活动中可以决定何时开始下载，以及随时查看下载进度。实现这个功能的思路就是创建一个专门的Binder对象来对下载功能进行管理，接下来我们修改MyService代码：

```java
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.i("DownloadBinder", "startDownload() executed");

        }

        public int getPrograss() {
            Log.i("DownloadBinder", "getPrograss() executed");
            return 0;

        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
    .......
}
```

这里我们新建了一个DownloadBinder继承自Binder,然后在内部提供了开始下载以及查看下载进度的方法。接下来我们在要activity中去绑定服务，当绑定服务之后，就可以调用该服务里的Binder提供的方法了。

```java
public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_bind_service, btn_unbind_service;

    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getPrograss();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_bind_service = findViewById(R.id.btn_bind_service);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service = findViewById(R.id.btn_unbind_service);
        btn_unbind_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind_service://绑定服务
                Intent startIntent = new Intent(this, MyService.class);
                bindService(startIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service://解绑服务
                unbindService(connection);
                break;
        }
    }
}

```

这里我们创建了一个ServiceConnection 的匿名类，并重写了onServiceConnected()和onServiceDisconnected()两个方法，这两个方法会在活动与服务绑定成功以及活动与活动与服务断开的时候调用。在onServiceConnected()方法中我们通过乡下转型拿到了DownloadBinder的实例，有了这个实例活动与服务的关系更加密切。这里我们只是简单的调用了DownloadBinder的方法。

```java
Intent startIntent = new Intent(this, MyService.class);
bindService(startIntent, connection, BIND_AUTO_CREATE);
```

*   参数一：构建的Intent对象
*   参数二：ServiceConnection 对象
*   参数三：表示在活动和服务进行绑定后自动床架服务。这会使得MyService的onCreate()方法得到执行，而onStartCommand()方法不会执行。
    **注意：任何一个服务在整个应用程序范围内都是通用的，即MyService不仅可以和MainActivity绑定，还可以和任何一个其他的活动进行绑定，而且在绑定完成后他们都可以获取到相同的DownloadBinder实例。**

### 服务的生命周期

![服务的生命周期.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/52cc014ae9ac4f82a6b8acae2ddd6761~tplv-k3u1fbpfcp-watermark.image#?w=432\&h=521\&s=164583\&e=png\&b=f8ede5)

#### startService()的生命周期

*   onCreate()：创建服务
*   onStartCommand()：服务开始运行（在2.0以前版本中，使用onStart()回调方法）
*   onDestroy() ：服务被停止

**注意：**

*   启动试服务虽然美的偶用一次startService()方法，onStartCommand()就会执行一次，但是实际上服务都只会存在一个实例，所以不管调用多少次onStartCommand()方法，只需要调用一次stopService()或stopSelf()方法，服务就会停止下来。
*   被启动的服务是由其它组件调用startService()方法而启动的，该方法会导致被启动服务的生命周期方法onStartCommand()被回调。当服务是被启动状态后，其生命周期与启动它的组件无关，即使启动服务的组件（Activity，BroadcastReceiver）已经被销毁，该服务还可以在后台无限期运行（当该应用所在的进程被销毁服务也就停止了）。除非调用stopSelf()或stopService()来停止该服务。

#### bindService()的生命周期

*   onCreate()：创建服务
*   onBind()：绑定服务，服务开始运行
*   onUnbind()：取消绑定
*   onDestroy() ：服务被停止

**注意：**

*   调用者与服务绑定在一起，调用者一旦退出，服务也就终止
*   在程序中调用：context.bindService()会触发执行Service生命周期中的onCreate()、onBind()回调方法，此时服务开始运行；
*   onBind将返回给客户端一个IBind接口实例，IBind允许客户端回调服务的方法，比如得到Service运行的状态或其他操作。此后调用者（Context，例如Activity）会和Service绑定在一起；
*   如果调用Service的调用者Context退出了，那么会依次调用Service生命周期中的onUnbind()、onDestroy()回调方法，会让服务停止；
*   所以BindService的生命周期为：onCreate --> onBind(只一次，不可多次绑定) --> onUnbind --> onDestory。
*   在Service每一次的开启关闭过程中，只有onStartCommand()可被多次调用(通过多次startService调用)，其他onCreate()、onBind()、onUnbind()、onDestory()在一个生命周期中只能被调用一次。

### 使用前台服务

服务机会都是在后台运行的，一直以来它都是默默地做着辛苦的工作。但是服务的系统优先级还是比较低的，当系统出现内存不足的情况时，就有可能回收掉正在后台运行的服务。如果你希望服务可以一直保持运行状态，而不会由于系统内存不足的原因导致被回收，就可以考虑使用前台服务。前台服务和普通服务最大的区别就在于，它会一直有一个正在运行的图标在系统的状态栏显示，下拉状态栏后可以看到更加详细的信息，非常类似于通知的效果。
接下来我们看一下怎么创建一个前台服务，其实并不复杂，在MyService的onCreat()方法中添加如下代码：

```java
 //该方法在服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Myservice", "---onCreate()---");

        Intent intent = new Intent(this, SendActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("this is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }
```

当我们点击startService()方法的时候，调用startForeground()方法会让MyService()变成一个前台服务。

![前台服务.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eae1ef8b872b4bdcb725c9fe96a3826d~tplv-k3u1fbpfcp-watermark.image#?w=540\&h=960\&s=116601\&e=png\&b=1f1f1f)

### IntentService

由于服务中的代码都是默认运行在主线程当中的，如果直接在服务里去处理一些耗时的逻辑，就很容易出现ANR。这时就要使用多线编程的技术，我们应该在服务具体的方法里开启一个子线程，然后处理一些耗时的逻辑，因此一个标准的服务可以写成如下形式：

```java
public class MyService extends Service {
  ...
   @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Myservice", "---onStartCommand()---");
        
        new Thread(new Runnable() {
            @Override
            public void run() {
              //处理具体的耗时逻辑  
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }
  ...
}
```

但是这种服务一旦启动就会一直处于运行状态，必须调用stopService()或者stopSelf()方法才能让服务停止下来。所以，如果想要实现让一个服务在执行完毕后自动停止的功能，就可以这样写：

    public class MyService extends Service {
      ...
       @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i("Myservice", "---onStartCommand()---");
            
            new Thread(new Runnable() {
                @Override
                public void run() {
                  //处理具体的耗时逻辑  
                  stopSelf();
                }
            }).start();
            return super.onStartCommand(intent, flags, startId);
        }
      ...
    }

为了可以简单地创建一个异步的，会自动停止的服务，Android专门提供了一个IntentService类，这个类就很好的解决了前面两种尴尬的处理方式。接下来我们新建一个MyIntentService类继承自IntentService,代码如下：

```java
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的id
        Log.i("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyIntentService", "onDestroy() executed ");
    }
}
```

这里首先要提供一个无参的构造函数，并且必须在其内部调用父类的有参构造函数。然后子类中去实现onHandleIntent()这个抽象方法，这个方法可以去处理一些具体的逻辑，而且还不用担心ANR的问题，因为这个方法已经是在子线程中运行的了。这里我们又重写了onDestroy()方法，这个服务在运行结束后会自动停止。

我们在代码中启动服务：

```java
     case R.id.btn_intent_service://启动IntentSercice
                Log.i("sendActivity","Thread id is"+Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
```

不要忘记在Manifest文件中注册

```java
  <service android:name=".MyIntentService" />
```

点击按钮查看日志：
![image.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/fce6f6416aca43109af5c31c1f194251~tplv-k3u1fbpfcp-zoom-1.image)
从日志中可以看到，MyIntentService和activty所在的线程ID不一样，而且onDestroy()方法也得到了执行，说明MyIntentService 在运行完毕后确实自动停止了。

![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/db35c666e2384fbc8a182da9da491386~tplv-k3u1fbpfcp-watermark.image#?w=1023\&h=107\&s=101111\&e=png\&b=faf8f8)

### 补充知识（进程和生命周期）

Android操作系统尝试尽可能长时间保持应用的进程，但当可用内存很低时要移走一部分进程。哪些程序可以运行，哪些要被销毁？答案是：重要级别低的进程可能被淘汰。
按照重要性排列，一共可以分成5级：

###### 1、前台运行进程：

用户此时需要处理和显示的进程。符合下列条件任何一个，这个进程就被认为是前台运行进程。

*   与用户正发生交互；
*   它控制一个与用户交互的必须的基本的服务；
*   一个正在调用生命周期回调方法的service（如onCreate()、onStart()、onDestroy()）；
*   一个正在运行onReceive()方法的广播接收对象。

销毁前台运行进程是系统万不得已的、最后的选择——当内存不够系统继续运行下去时，杀掉一些前台进程来保证能够响应用户的需求。

###### 2、可用进程：

一个可用进程没有任何前台组件，但它仍然可以影响到用户的界面。下面情况发生时，可以称该进程为可用进程。
它是一个非前台的activity，但对用户仍然可用（onPause()方法已经被调用）。例如：前台的activity是一个允许上一个activity可见的对话框。也就是说当前activity中是一个对话框，对话框之外的地方能看到前一个activity的界面。

##### 3、服务进程：

服务进程是一个通过调用startService()方法启动的服务，并且不属于前两种情况。尽管服务进程没有直接被用户看到，但他们确实是用户所关心的，比如后台播放音乐或网络下载数据，所以系统保证他们的运行。

###### 4、后台进程：

一个后台进程就是非当前正在运行的activity（activity的onStop()方法已经被调用），他们不会对用户体验造成直接的影响，当没有足够内存来运行前台可见程序时，他们将会被终止。
通常，后台进程会有很多个在运行，LRU最近使用程序列表来保证经常运行的activity能最后一个被终止。
\#####5、空线程：
一个空线程没有运行任何可用应用程序，保留他们的唯一原因是为了设立一个缓存机制，来加快组件启动的时间。系统经常杀死这些内存来平衡系统的整个系统的资源，进程缓存和基本核心缓存之间的资源。
