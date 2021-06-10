package com.example.androidappremotecontroljoystick.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

import androidx.annotation.Nullable;

public class Joystick extends View implements View.OnTouchListener {
    private final float centerX = (float)(getWidth() / 2);
    private final float centerY = (float)(getWidth() / 2);
    private final int bgRadius = Math.min(getWidth(), getHeight()) / 4;
    private final int fgRadius = Math.min(getWidth(), getHeight()) / 6;

    /**
     * CTOR of Joystick, which is inherited from View
     * @param context
     */
    public Joystick(Context context) {
        super(context);
        setOnTouchListener((OnTouchListener) this);
    }

    /**
     * CTOR of Joystick, which is inherited from View
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public Joystick(Context context,
                    @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs,
                    int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener((OnTouchListener) this);
    }

    /**
     * CTOR of Joytick, which is inherited from View
     * @param context
     * @param attrs
     */
    public Joystick(Context context,
                    @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((OnTouchListener) this);
    }
    /**
     * this function draws the joystick
     * @param x - the new x of the joystick
     * @param y - the new y of the joystick
     */
    private void joystickDraw(float x, float y) {
        Canvas joystickCanvas = new Canvas();
        Paint colors = new Paint();
        joystickCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        colors.setARGB(255, 50, 50, 50);
        joystickCanvas.drawCircle(centerX, centerY, bgRadius, colors);
        colors.setARGB(255, 0, 0, 255);
        joystickCanvas.drawCircle(x, y, fgRadius, colors);
        this.draw(joystickCanvas);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        joystickDraw(centerX, centerY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {
        if (v.equals(this)) {
            if (me.getAction() != me.ACTION_UP) {
                joystickDraw(me.getX(), me.getY());
            } else {
                joystickDraw(centerX, centerY);
            }
        }
        return true;
    }
}
