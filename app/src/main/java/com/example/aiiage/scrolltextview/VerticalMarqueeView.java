package com.example.aiiage.scrolltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

import java.util.List;

/**
 * AiiageSteamRobot
 *
 * @author Anter lu.xd@aiiage.com
 * @date 2018/7/23
 */

public class VerticalMarqueeView extends ViewFlipper {
    /**
     * Alpha animation enable
     */
    private boolean isSetAlphaAnim = true;
    /**
     * Animation interval
     */
    private int interval = 5000;
    /**
     * Animation duration
     */
    private int animDuration = 2000;

    public VerticalMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MarqueeViewUp, defStyleAttr, 0);
        isSetAlphaAnim = ta.getBoolean(R.styleable.MarqueeViewUp_isSetAlphaAnim, isSetAlphaAnim);
        interval = ta.getInteger(R.styleable.MarqueeViewUp_interval, interval);
        animDuration = ta.getInteger(R.styleable.MarqueeViewUp_animDuration, animDuration);
        setFlipInterval(interval);
        // In or out animation: alpha, continuous
        AlphaAnimation animationIn = new AlphaAnimation(0, 1);
        animationIn.setDuration(animDuration);
        AlphaAnimation animationOut = new AlphaAnimation(1, 0);
        animationOut.setDuration(animDuration);
        // TranslateAnimation: show
        TranslateAnimation translateAnimationIn = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        // TranslateAnimation: dismiss
        TranslateAnimation translateAnimationOut = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        translateAnimationIn.setDuration(animDuration);
        translateAnimationOut.setDuration(animDuration);
        // In
        AnimationSet animationInSet = new AnimationSet(false);
        animationInSet.addAnimation(translateAnimationIn);
        // Out
        AnimationSet animationOutSet = new AnimationSet(false);
        animationOutSet.addAnimation(translateAnimationOut);
        // alpha animation enable
        if (isSetAlphaAnim) {
            animationInSet.addAnimation(animationIn);
            animationOutSet.addAnimation(animationOut);
        }
        setInAnimation(animationInSet);
        setOutAnimation(animationOutSet);
    }


    /**
     * Set flipping views array
     */
    public void setViews(final List<View> views) {
        if (views == null || views.size() == 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < views.size(); i++) {
            final int position = i;
            // Single tap listener
            views.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position, views.get(position));
                    }
                }
            });
            addView(views.get(i));
        }
        startFlipping();
    }

    /**
     * Single tap click listener
     */
    private OnItemClickListener onItemClickListener;

    /**
     * Set single tap callback
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Single tap callback interface
     */
    public interface OnItemClickListener {
        /**
         * On item click
         *
         * @param position On click position
         * @param view     On click view
         */
        void onItemClick(int position, View view);
    }
}