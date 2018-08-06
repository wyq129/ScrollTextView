package com.example.aiiage.scrolltextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.substring;

public class ScrollTextActivity extends Activity {
    private MarqueeView marqueeView1;
    private TextView voice_tv_words,stop;
    List<String> data = new ArrayList<>();
    List<View> views1 = new ArrayList<>();
    static int Strint_size;
    private boolean mStopMarquee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text);
        initParam();
        //initdata();
        initView();
    }
    /**
     * 实例化控件
     */
    private void initParam() {
       marqueeView1 = (MarqueeView) findViewById(R.id.upview1);
        voice_tv_words=findViewById(R.id.voice_tv_words);
        stop=findViewById(R.id.tv_stop);
        marqueeView1.setVisibility(View.GONE);
        voice_tv_words.setVisibility(View.VISIBLE);

    }

    /**
     * 初始化界面程序
     */
    private void initView() {
        data = new ArrayList<>();
        //final  String s="21wqsqwsqw";
         String s="你听说过一见钟情吗？ 那个十二岁的夏天，校门口边上 他倚在单车边上，微微回头寻找我,我撞上他眼睛里,他撞进我心底 没有任何对话,他送了我一路,那段路好长好长,我走了好久好久。";
        s = s.replaceAll("\t|\n", "");
        voice_tv_words.setText(s);
                List<String> list =  getStrList(s,21);
                for(int i=0;i<list.size();i++) {
                    data.add(list.get(i));
                }
                if (Strint_size>2){
                    voice_tv_words.setVisibility(View.GONE);
                    marqueeView1.setVisibility(View.VISIBLE);
                    if (s.isEmpty()){
                        return;
                    }

                    setViewTwoLines();
                    marqueeView1.setViews(views1);
                }

                else
                {
                    voice_tv_words.setText(s);
                    voice_tv_words.setVisibility(View.VISIBLE);
                    marqueeView1.setVisibility(View.GONE);
                }

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marqueeView1.stopFlipping();
                marqueeView1.removeAllViews();
                Toast.makeText(ScrollTextActivity.this, "停止滚动", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void setViewTwoLines() {
        views1.clear();//记得加这句话，不然可能会产生重影现象
        for (int i = 0; i < data.size(); i = i + 2) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);

            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //奇数条
                tv2.setText(data.get(i + 1).toString());
            } else {//偶数条
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                //moreView.findViewById(R.id.rl2).setVisibility(View.GONE);
                //修改了最后一个没有 将第一个拼接到最后显示
               // tv2.setText(data.get(0).toString());
            }
            //添加到循环滚动数组里面去
            views1.add(moreView);
            moreView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    marqueeView1.setViews(views1);
                    Toast.makeText(ScrollTextActivity.this, "停止滚动", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }
    public static List<String> getStrList(String inputString, int length,
                                          int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        Strint_size=size;
        System.out.println("wyq129:size "+size);
        return list;
    }
    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

}
