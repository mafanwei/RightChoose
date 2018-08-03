# RightChoose

右侧快速选择的控件

使用方法：
1.下载项目中的NumberView，导入到自己的项目中
2.在xml中新建  <com.mafanwei.catelcall.View.NumberView
            android:paddingLeft="4dp"
            android:layout_marginTop="10dp"
            android:layout_marginBottom="2dp"
            android:layout_gravity="right"
            android:id="@+id/numberview"
            android:layout_width="20dp"
            android:layout_height="match_parent" />
宽高请自行设定
3.在java中findViewById(R.id.numberview);
4.设置点击事件.setOnClickListener(new NumberView.OnClickListener() {
            @Override
            public void onchoose(String number) {
               //number就是目前用户点选/滑动到的位置
            }
        });
其他方法：
设置文字大小setTextSize（float size）
设置能选择的文字例如：ABCD，12345等（默认效果是ABCD...Z#）
setNumber(String[] strings)

其他请自行阅读源码修改
