# 哈工大冰峰实验室小型技术中台

    
为了实验室以后更快的构建业务，创建了这个小型中台

## 如何使用：
 - 将app包下的base层文件全部拷贝到新项目中
 
 - 将gradle配置中的依赖全部导入新工程即可（注意project的gradle中的allprojects中含有maven依赖
 
 - 将res包下的anim文件夹拷贝到新项目中
 
 - RxHttp即便sync成功后仍然会报错，解决方法：编译一遍即可。
 
 
## 详细的每个业务场景使用的技术构建

### 1.上拉加载，下拉刷新框架：

1.为了优化用户体验，在使用recyclerView（简称rv）时，如果不需要刷新和加载，那么就利用框架SmartRefreshLayout包裹rv，可以让布局灵活滚动（类似iOS)，提高交互体验，下面就是此纯滚动模式Layout（也就是无需上拉加载下拉刷新的模式Layout）的xml文档属性如下，直接粘贴到xml中即可，在最新的冰峰base项目中，我已经添加，名称如下：layout_pure_scroll_example.xml
 
 <br/>
 
2.如果需要刷新和分页上拉加载的，用如下xml代码即可

```xml

<com.scwang.smartrefresh.layout.SmartRefreshLayout
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:srlEnableAutoLoadMore="true"
    app:srlEnableLoadMore="false"
    app:srlEnableRefresh="false"
    app:srlFooterMaxDragRate="5"
    app:srlReboundDuration="850">

    <com.scwang.smartrefresh.layout.header.ClassicsHeader
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:srlEnableLastTime="false"
        app:srlTextPulling="下拉刷新"
        app:srlTextRelease="释放刷新" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never" />


    <com.scwang.smartrefresh.layout.footer.ClassicsFooter
        android:id="@+id/footer"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:srlFinishDuration="0" />

</com.scwang.smartrefresh.layout.SmartRefreshLayout>
```

详细的分页加载和下拉刷新的页面请用你的Activity继承BasePagingActivity


### 2.图片相关：

1.图片加载
```Java
Glide.with(context).load(url).into(imageView);
```

2.图片预览
```Java
ImagePreviewUtil.showPreView(context, url);
```

### 3.底部弹框

```Java
BottomDialog bottomDialog = new BottomDialog().setFragmentManager(getFragmentManager())
                .setLayoutRes(R.layout.bottom_dialog_share)
                .setDimAmount(0.3f)
                .setCancelOutside(true)
                .setViewListener(dialogView -> {
                    //在这里设置你度画框中的的点击事件
                    dialogView.findViewById(R.id.xxx).setOnClickListener(xxx);
                });
        bottomDialog.show();
```

### 4.多tab页面

1.每个fragment：
未完待续





 





 
 
 
  

