package com.example.aiiage.scrolltextview;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;


import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyanqin
 * @date
 */
public class MainActivity extends AppCompatActivity {


    private MarqueeView marqueeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueeView=findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是酥小鱼。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. QQ交流群：589780530");
        info.add("4. 个人博客：https://blog.csdn.net/weixin_38703938");
        String info1="啊上的地位的我的我的风采违反阿达就是客户的亲卫队牛牛牛牛牛牛牛牛啊实打实大苏打西安市东西啊是大中心城市的常见的穆斯林课程但是每次都是1";
        //marqueeView.startWithFixedWidth(info1);
        // marqueeView.startWithList(info);
        marqueeView.startWithText(info1);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent=new Intent(MainActivity.this,ScrollTextActivity.class);
                startActivity(intent);
            }
        });
    }
    }