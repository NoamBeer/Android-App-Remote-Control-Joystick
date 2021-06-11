package com.example.androidappremotecontroljoystick.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;

import androidx.annotation.Nullable;

public class Joystick extends View implements View.OnTouchListener {
    private float centerX;
    private float centerY;
    private float bgRadius;
    private float fgRadius;
    private JoystickListener joystickListener;
    private Paint paint;
    private boolean isInit;
    private float width, height;
    private int padding;
    private float currX, currY;
    private Canvas canvas;
    private Bitmap bitmap;


    /**
     * CTOR of Joystick, which is inherited from View
     *
     * @param context
     */
    public Joystick(Context context) {
        super(context);
        setOnTouchListener((OnTouchListener) this);
        if (context instanceof JoystickListener) {
            joystickListener = (JoystickListener) context;
        }
    }

    /**
     * CTOR of Joystick, which is inherited from View
     *
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
            joystickListener = (JoystickListener) context;
        }
    }

    /**
     * CTOR of Joystick, which is inherited from View
     *
     * @param context
     * @param attrs
     */
    public Joystick(Context context,
                    @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener((OnTouchListener) this);
        if (context instanceof JoystickListener) {
            joystickListener = (JoystickListener) context;
        }
    }

    /**
     * Initializes Joystick.
     */
    private void initJoystick() {
        height = getHeight();
        width = getWidth();
        padding = 50;
        bgRadius = Math.min(getWidth(), getHeight()) / 3 - padding;
        fgRadius = Math.min(getWidth(), getHeight()) / 6 - padding;
        currX = width / 2;
        currY = height / 2;
        centerX = width / 2;
        centerY = height / 2;
        paint = new Paint();
        isInit = true;
    }

    /**
     * Draws on canvas.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initJoystick();
        }
        drawBase(canvas);
        drawJoystick(canvas);
        postInvalidateDelayed(10);
        invalidate();
    }

    /**
     * Draws the Joystick's circle in the correct position.
     *
     * @param canvas
     */
    private void drawJoystick(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.black));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(currX, currY, fgRadius + padding, paint);
    }

    /**
     * Draws the outline of the Joystick.
     *
     * @param canvas
     */
    private void drawBase(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.darker_gray));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, bgRadius + padding, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    /**
     * Defines action upon touch.
     *
     * @param v  - view
     * @param me - MotionEvent
     * @return true
     */
    @Override
    public boolean onTouch(View v, MotionEvent me) {
        if (v.equals(this)) {
//            if the action isn't releasing the touch
            if (me.getAction() != me.ACTION_UP) {
                float displacement = (float) Math.sqrt(Math.pow(me.getX() - centerX, 2) + Math.pow(me.getY() - centerY, 2));
//                if the joystick is within the borders
                if (displacement + fgRadius < bgRadius + padding) {
                    currX = me.getX();
                    currY = me.getY();
                    drawJoystick(canvas);
                    joystickListener.onJoystickMoved((currX - centerX) / (fgRadius + padding),
                            (currY - centerY) / (fgRadius + padding));
                } else {
                    float ratio = (fgRadius + padding) / displacement;
                    currX = centerX + (me.getX() - centerX) * ratio;
                    currY = centerY + (me.getY() - centerY) * ratio;
                    drawJoystick(canvas);
                    joystickListener.onJoystickMoved((currX - centerX) / (fgRadius + padding),
                            (currY - centerY) / (fgRadius + padding));
                }
            } else {
                drawJoystick(canvas);
            }
        }
        return true;
    }

    public interface JoystickListener {
        void onJoystickMoved(float xPos, float yPos);
    }
}
