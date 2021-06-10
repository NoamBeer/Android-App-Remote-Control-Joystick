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
    private JoystickListener joystickcb;

    /**
     * CTOR of Joystick, which is inherited from View
     * @param context
     */
    public Joystick(Context context) {
        super(context);
        setOnTouchListener((OnTouchListener) this);
        if (context instanceof JoystickListener) {
            joystickcb = (JoystickListener) context;
        }
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
        if (context instanceof JoystickListener) {
            joystickcb = (JoystickListener) context;
        }
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
        if (context instanceof JoystickListener) {
            joystickcb = (JoystickListener) context;
        }
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

                float displacement = (float) Math.sqrt(Math.pow(me.getX() - centerX, 2) + Math.pow(me.getY() - centerY, 2));
                if (displacement < bgRadius) {
                    joystickDraw(me.getX(), me.getY());
                    joystickcb.onJoystickMoved((me.getX() - centerX) / bgRadius,
                            (me.getY() - centerY) / bgRadius,
                            getId());

                } else {
                    float ratio = bgRadius / displacement;
                    float constrainedX = centerX + (me.getX() - centerX) * ratio;
                    float constrainedY = centerY + (me.getY() - centerY) * ratio;
                    joystickDraw(constrainedX, constrainedY);
                    joystickcb.onJoystickMoved((constrainedX - centerX) / bgRadius,
                            (constrainedY - centerY) / bgRadius,
                            getId());
                }

            } else {
                joystickDraw(centerX, centerY);
                joystickcb.onJoystickMoved(0,0, getId());
            }
        }
        return true;
    }

    public interface JoystickListener {
        void onJoystickMoved(float xPercent, float yPercent, int id);
    }
}
