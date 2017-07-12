###1. 系统架构
系统采用开放式设计，架构清晰，结构层次如下所示：
1. 终端用户：手机终端及安装的智能客户端软件、手机客户端技术方案。
2. 网络接入：移动运营商提供的接入承载。
3. 移动办公系统：服务器系统内部架构。
4. 内部IT系统：已有的信息管理平台。

###2. 逻辑架构
通过移动服务器来实现对业务数据的衔接，响应智能客户端程序，主要依靠Wifi和4G等作为数据传输方式，通过安全连接将客户应用服务器上的内容请求推送到客户手机端，使得用户可以随时随地的实现移动学习和移动应用。

###3. 功能架构及关键技术

应用由于功能并不复杂未采用MVP架构（会导致接口数剧增），而采用常用的MVC，业务逻辑、数据、界面显示分离，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。其中M层处理数据，业务逻辑等；V层处理界面的显示结果；C层起到桥梁的作用，来控制V层和M层通信以此来达到分离视图显示和业务逻辑层。

![mvc][1]

 1. M层：适合做一些业务逻辑处理，比如数据库存取操作，网络操作，复杂的算法，耗时的任务等都在model层处理
 2. V层：应用层中处理数据显示的部分，XML布局可以视为V层，显示Model层的数据结果。
 3. C层：在Android中，Activity处理用户交互问题，因此可以认为Activity是控制器，Activity读取V视图层的数据（eg.读取当前EditText控件的数据），控制用户输入（eg.EditText控件数据的输入），并向Model发送数据请求（eg.发起网络请求等）。

###4. 关键业务模块
#### 整体结构
![各模块][2]
![各类依赖继承关系][3]
项目按照类型，功能模块进行多个分包（base,activity,fragment,permisson,adapter,view,net,bean,tools等），使得结构清晰明了，易于修改编辑

 - base包
![base][4]
base包下封装了所有activity和fragment的基类，将业务逻辑分开写到多个抽象方法，供子类实现，避免view初始化，事件监听，数据获取，设置数据，获取保存数据等都写在onCreate方法中
```
    //初始化view
    protected abstract void initView();

    //设置监听器
    protected abstract void setListener();

    //初始化数据
    protected abstract void initData();

    //view显示数据
    protected abstract void setData();

    /**
     * Bundle  传递数据
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);
```
以及提供了初始化Toolbar,显示loading，error对话框，显示各类型toast,页面跳转，数据传递方法

 - activity包
APP各大功能界面显示

 - fragment包
用于展示个别页面的UI的片段，具有良好的解耦合设计，能为多个activity重用

 - bean包
各实体类，封装各类数据

 - permission包
 由于android 6.0开始应用无法直接获取一些和用户隐私，手机安全相关的危险权限，需通过动态权限申请获取，而权限请求API调用不是太方便，因此通过封装申请逻辑最终得到一个权限申请活动，任何要用到的地方只需继承它即可

 - net包
封装了服务器接口，豆瓣API调用以及豆瓣图书爬虫，自动开启异步，数据获取解析完后，通过传入的listener回调

 - adapter包
控件的适配器类，将数据设置显示到界面上

 - tools包
各类工具类，包括数据加密，图片虚化，获取图片主色调，SharedPreferences保存，对话框，网路状态检查，RecyclerView万能适配器等

###5. 使用的第三方包
- [Gson](https://github.com/google/gson)
Gson是一个可用于将Java对象转换为JSON表示形式的Java库。它也可以用于将JSON字符串转换为等效的Java对象。Gson可以使用任意Java对象，包括没有源代码的预先存在的对象。从而避免手动解析接口请求返回数据，以及向服务器发生JSON数据的繁琐工作。

- [OkHttp](https://github.com/square/okhttp)
OkHttp作为应用网络请求框架，实现高效流畅请求/响应API。
OkHttp是默认情况下高效的HTTP客户端：
1. HTTP / 2支持允许对同一主机的所有请求共享套接字。
2. 连接池减少请求延迟（如果HTTP / 2不可用）。
3. 透明GZIP缩小下载大小。
4. 响应缓存可以完全避免重复请求的网络。

- [glide](https://github.com/bumptech/glide)
Glide是面向Android的快速高效的开源媒体管理和图像加载框架，将媒体解码，内存和磁盘缓存以及资源池化包含在一个简单易用的界面中。主要重点是尽可能平滑和快速地滚动任何类型的图像列表，但Glide对于几乎任何需要获取，调整大小和显示远程图像的情况也是有效的。
使用Glide进行高效，流畅，易用的图片加载，其强大的缓存机制极大减少加载本地，网络图片时内存消耗。

- [RxJava](https://github.com/ReactiveX/RxJava)
RxJava是Reactive Extensions的Java VM实现：用于通过使用observable序列来组合异步和基于事件的程序的库。它扩展观察者模式以支持数据/事件序列，并添加允许以声明方式组合序列的操作符，同时提取对低级线程，同步，线程安全性和并发数据结构等问题的隐藏。
使用RxJava作为应用异步加载框架，使得异步操作随着程序逻辑变得越来越复杂，依然能够保持简洁。

- [zxing](https://github.com/zxing/zxing)
ZXing（“斑马线”）是一种开源的，多格式的1D / 2D条形码图像处理库，在Java中实现，端口与其他语言。应用中作为二维码/条形码扫码依赖库。
###8. 数据获取的来源
####8.1 用户，管理员，资讯等数据
通过调用服务器端提供的接口调用获得，运用check valu，数据加密等方式验证客户端信息，从而保证接口安全性。
####8.2 扫码录书数据
通过豆瓣图书Api V2的ISBN获取书籍详情信息，再进行转换为服务器数据库所需数据类型进行上传。


  [1]: http://img.blog.csdn.net/20150605112142444
  [2]: http://upload-images.jianshu.io/upload_images/5734256-a769e1c72d7f9d49.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
  [3]: http://upload-images.jianshu.io/upload_images/5734256-bfd806df617e1fe0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
  [4]: http://upload-images.jianshu.io/upload_images/5734256-278de8f953d36f7b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240
