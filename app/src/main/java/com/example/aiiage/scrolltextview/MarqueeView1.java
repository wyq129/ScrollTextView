package com.example.aiiage.scrolltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import java.util.List;

public class MarqueeView1 extends ViewFlipper {
    private Context mContext;
    private boolean isSetAlphaAnim = true;
    private int interval = 15000;
    private int animDuration = 12000;
    private MarqueeView1.OnItemClickListener onItemClickListener;
    public MarqueeView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs, 0);
    }
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, com.sunsky.marqueeview.R.styleable.MarqueeViewUp, defStyleAttr, 0);
        this.isSetAlphaAnim = ta.getBoolean(com.sunsky.marqueeview.R.styleable.MarqueeViewUp_isSetAlphaAnim, this.isSetAlphaAnim);
        this.interval = ta.getInteger(com.sunsky.marqueeview.R.styleable.MarqueeViewUp_interval, this.interval);
        this.animDuration = ta.getInteger(com.sunsky.marqueeview.R.styleable.MarqueeViewUp_animDuration, this.animDuration);
        this.setFlipInterval(this.interval);
        AlphaAnimation animationIn = new AlphaAnimation(0.0F, 1.0F);
        animationIn.setDuration((long)this.animDuration);
        AlphaAnimation animationOut = new AlphaAnimation(1.0F, 0.0F);
        animationOut.setDuration((long)this.animDuration);
        TranslateAnimation translateAnimationIn = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
        TranslateAnimation translateAnimationOut = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -1.0F);
        translateAnimationIn.setDuration((long)this.animDuration);
        translateAnimationOut.setDuration((long)this.animDuration);
        AnimationSet animationInSet = new AnimationSet(false);
        animationInSet.addAnimation(translateAnimationIn);
        AnimationSet animationOutSet = new AnimationSet(false);
        animationOutSet.addAnimation(translateAnimationOut);
        if (this.isSetAlphaAnim) {
            animationInSet.addAnimation(animationIn);
            animationOutSet.addAnimation(animationOut);
        }

        this.setInAnimation(animationInSet);
        this.setOutAnimation(animationOutSet);
    }

    public void setViews(final List<View> views) {
        if (views != null && views.size() != 0) {
            this.removeAllViews();

            for(int i = 0; i < views.size(); ++i) {
                final int finalI = i;
                final int finalI1 = i;
                ((View)views.get(i)).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (MarqueeView1.this.onItemClickListener != null) {
                            MarqueeView1.this.onItemClickListener.onItemClick(finalI, (View)views.get(finalI1));
                        }

                    }
                });
                this.addView((View)views.get(i));
            }

            this.startFlipping();
        }
    }
    public void stopView(final List<View> views){
        if (views != null && views.size() != 0) {
            this.removeAllViews();
            this.stopFlipping();
        }
    }
    public void setOnItemClickListener(MarqueeView1.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int var1, View var2);
    }
}
