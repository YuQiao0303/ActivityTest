# ActivityTest
project created while reading chapter 2 of "First code line" By Guo Lin

here are some notes while reading this chapter:

# 参考资料
[Android Studio 下载](https://developer.android.google.cn/studio/)
[Android Studio官方文档/教程](https://developer.android.google.cn/training/basics/firstapp/creating-project)
[《第一行代码》（一本安卓开发教材，适合仅有java基础的新手入门）](http://www.mycodes.net/195/10016.htm)

# 快捷键
shift + F10 运行
Ctrl + O 重写方法
logd , Tab 自动补全Log.d
在方法外logt ,Tab 会自动以当前类名生成TAG常量
# Android四大组件

||组件|说明|
|-|-|-|
1|活动（Activity） |所有Android应用程序的门面， 凡是在应用中你看得到的东西， 都是放在活动中的。|
2|服务（Service）|就比较低调了， 你无法看到它，但它会一直在后台默默地运行， 即使用户退出了应用， 服务仍然是可以继续运行的。
3|广播接收器（Broadcast Receiver） |允许你的应用接收来自各处的广播消息， 比如电话、 短信等， 当然你的应用同样也可以向外发出广播消息。 
4|内容提供器（Content Provider） |则为应用程序之间共享数据提供了可能， 比如你想要读取系统电话簿中的联系人， 就需要通过内容提供器来实现。


# 项目结构的不同模式
![此处可选项目结构的模式](https://upload-images.jianshu.io/upload_images/15426916-0fb680b38529ec68.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
项目结构的模式有：
- Android（简明快速，不适合新手）
- Project  （真实的目录结构）
……
![可选的项目结构模式](https://upload-images.jianshu.io/upload_images/15426916-a8b2c3766e40e821.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 真机调试
安卓手机用usb连接电脑，在安卓手机上点：
设置-》关于手机/我的设备/。。。  -》连续点击版本号，让开发者选项显示出来，在启动usb调试。
点运行（shift+F10），选该手机，就可以了。instant run可以选上：
[深入理解Android Instant Run运行机制](https://blog.csdn.net/xiangzhihong8/article/details/64906131)

# 日志
logd , Tab 自动补全Log.d
在方法外logt ,Tab 会自动以当前类名生成TAG常量


# 变量或id：引用&定义

|情况|用法|
|-|-|
|XML中引用一个id |@id/id_name |
|XML中定义一个id| @+id/id_name|
|代码中引用字符串|R.string.app_name |


# layout的一些属性

|属性|意义|
|-|-|
match_parent| 跟父元素一样
wrap_content| 刚好能包含里面的内容就行

# 活动的注册&主活动设置
1. 活动创建时，launcher activity 表示是主活动

2. 所有活动要在AndroidManifest.xml 中注册才能生效
![](https://upload-images.jianshu.io/upload_images/15426916-5df36554b91d99dd.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
3. 活动注册
>在最外层的<manifest> 标签中通过package 属性指定程序的包名如：com.example.activitytest 
在<activity> 标签中我们使用了android:name 来指定具体注册哪一个活动， 填入”.activityName “。
4. 主活动设置
>在<activity> 标签的内部加入<intent-filter> 标签， 并
在这个标签里添加<action android:name="android.intent.action.MAIN"/> 和<category android: name="android.intent.category.LAUNCHER" /> 这两句声明即可。
4. 示例代码如下：
    
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.activitytest">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="This is FirstActivity"   //这里的内容会显示在标题上
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity android:name=".FirstActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

效果如下：
![效果图](https://upload-images.jianshu.io/upload_images/15426916-9cfde54f0f6f0b35.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 更多活动
- 在AndroidManifest.xml 中，注册了所有活动，同时用intent-filter标签指定了他们能响应的action, category, data
- 在一个活动中，要想打开另一个活动，有两种方法：
   1. 显式intent：指定具体打开哪个活动
   2. 隐式intent：指定action, category, data 中的一些。
- 每个intent可以制定一个action和多个category。

# 注释技巧
在想注释的地方输入/**并回车，会自动出现注释

# 活动的4种状态
每个活动在其生命周期中最多可能会有四种状态：

||状态|在返回栈中的位置|是否可见|
|-|-|-|-|
1|运行状态|栈顶||
2|暂停状态|不再栈顶|可见
3|停止状态|不再栈顶|不可见
4|销毁状态|不再栈中||

# 活动的7个回调方法
Activity类中定义了7个回调方法， 覆盖了活动生命周期的每一个环节：

||回调方法|说明|
|-|-|-|
1|onCreate() |  每个活动中我们都重写了这个方法， 它会在活动**第一次被创建**的时候调用。 你应该在这个方法中完成活动的初始化操作， 比如说**加载布局、 绑定事件**等。|
2|onStart() | 这个方法在活动**由不可见变为可见**的时候调用。
3|onResume() | 这个方法在活动**准备好和用户进行交互的时候**调用。 此时的活动一定位于返回栈的**栈顶**， 并且处于运行状态。
4|onPause() | 这个方法在系统**准备去启动或者恢复另一个活动**的时候调用。 我们通常会在这个方法中将一些消耗CPU的资源释放掉， 以及保存一些关键数据， 但这个方法的执行速度一定要快， 不然会影响到新的栈顶活动的使用。
5|onStop() | 这个方法在活动**完全不可见**的时候调用。 它和onPause() 方法的主要区别在于， 如果启动的新活动是一个对话框式的活动， 那onPause() 方法会得到执行，而onStop() 方法并不会执行。
6|onDestroy() | 这个方法在活动**被销毁之前**调用， 之后活动的状态将变为销毁状态。
7|onRestart() | 这个方法在活动**由停止状态变为运行状态**之前调用， 也就是活动被重新启动了。

以上7个方法中除了onRestart() 方法， 其他都是两两相对的， 从而又可以将活动分为3种生存期。

- 完整生存期 
>活动在**onCreate() 方法和onDestroy() 方法**之间所经历的， 就是完整生存期。 一般情况下， 一个活动会在onCreate() 方法中完成各种初始化操作， 而在onDestroy() 方法中完成释放内存的操作。
- 可见生存期 
> 活动在**onStart() 方法和onStop() 方法**之间所经历的， 就是可见生存期。 在可见生存期内， 活动对于用户总是可见的， 即便有可能无法和用户进行交互。 我们可以通过这两个方法， 合理地管理那些对用户可见的资源。 比如在onStart() 方法中对资源进行加载， 而在onStop() 方法中对资源进行释放， 从而保证处于停止状态的活动不会占用过多内存。
- 前台生存期 
> 活动在**onResume() 方法和onPause() 方法**之间所经历的就是前台生存期。 在前台生存期内， 活动总是处于运行状态的， 此时的活动是可以和用户进行交互的，我们平时看到和接触最多的也就是这个状态下的活动。

![官方示意图](https://upload-images.jianshu.io/upload_images/15426916-d2b25c0d2f91b900.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 防止活动被回收时损失临时数据
- onSaveInstanceState() 回调方法， 在活动被回收之前一定会被调用
- onSaveInstanceState() 方法会携带一个Bundle 类型的参数
- Bundle 提供了一系列的方法用于保存数据：putString() 方法，putInt() 方法……
- 每个保存方法需要传入两个参数， 第一个参数是键， 用于后面从Bundle
中取值， 第二个参数是真正要保存的内容。
- onCreate() 方法其实也有一个Bundle 类型的参数,在一般情况下都是null.但是如果在活动被系统回收之前有通过onSaveInstanceState() 方法来保存数据的话，这个参数就会带有之前所保存的全部数据， 我们只需要再通过相应的取值方法将数据取出即可。
- Intent还可以结合Bundle 一起用于传递数据， 首先可以把需要传递的数据都保存在Bundle 对象中， 然后再将Bundle 对象存放在Intent里。 到了目标活动之后先从Intent中取出Bundle ， 再从Bundle 中一一取出数据。

具体方法是：
1. 在可能被回收，但需要保存临时数据的activity里加：

```
@Override
protected void onSaveInstanceState(Bundle outState) {
super.onSaveInstanceState(outState);
String tempData = "Something you just typed";
outState.putString("data_key", tempData);
}
```

2. 在该activity的onCreate()中，加上：

```
if (savedInstanceState != null) {
String tempData = savedInstanceState.getString("data_key");
Log.d(TAG, tempData);
}
```

# 活动的启动模式

|||
|-|-|
|standard |启动一个活动时（startActivity），不管它是否已经在返回栈里，都新建一个该活动的实例(调用onCreate)
|singleTop |启动一个活动时，如果它已经在返回栈顶，就直接使用它，不新建实例；若在栈中，不再栈顶，还是要新建
|singleTask |每次启动该活动时，如果发现在栈中，则直接使用该实例， 并把在这个活动之上的所有活动统统出栈， 如果没有发现就会创建一个新的活动实例。
|singleInstance|启动该活动时，新建一个返回栈，该返回栈里只有这一个活动|

# 修改启动模式
修改AndroidManifest.xml中FirstActivity的launchMode 属性：

```
<activity
android:name=".FirstActivity"
android:launchMode="singleTop"
android:label="This is FirstActivity">
<intent-filter>
<action android:name="android.intent.action.MAIN" /><category android:name="android.intent.category.LAUNCHER" />
</intent-filter>
</activity>
```
