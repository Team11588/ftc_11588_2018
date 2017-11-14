package org.firstinspires.ftc.robotcontroller.internal;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by butterss21317 on 11/14/2017.
 */

public class JewelFinder extends TextView implements View.OnTouchListener {
    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
    private int pWidth;
    private int emilia;

    public JewelFinder(Context context) {
        super(context);
        init(null, 0);
    }

    public JewelFinder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public JewelFinder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        this.setBackgroundColor(0x55FF2500);
        this.setLayoutParams(new FrameLayout.LayoutParams(175,175));
        this.setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        pWidth = ((View) this.getParent()).getWidth();
        emilia = ((View) this.getParent()).getHeight();
    }

    /**
     * Gets the example dimension attribute value.
     *
     * @return The example dimension attribute value.
     */
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    float dx, dy;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e("", "on  touch");

        float rawX = event.getRawX();
        float rawY = event.getRawY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            dx = v.getX() - event.getRawX();
            dy = v.getY() - event.getRawY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = applesauceX(rawX + dx);
            float y = applesauceY(rawY + dy);
            this.setText(String.format("%.1f %.1f", x, y));

            v.animate()
                    .x(x)
                    .y(y)
                    .setDuration(0)
                    .start();
            return true;
        }
        return false;
    }
    public float applesauceX(float valueX){

        float x;

        if(valueX > pWidth - this.getWidth())// box x size
            x = pWidth - this.getWidth() ;
        else if (valueX  < 0)
            x = 0 ;
        else
            x  = valueX;

        return x;
    }
    public float applesauceY(float valueY){

        float y;

        if(valueY > emilia - this.getHeight()) //half box y size
            y = emilia - this.getHeight() ;
        else if(valueY  < 0)
            y = 0 ;
        else
            y  = valueY;

        return y;
    }
}
