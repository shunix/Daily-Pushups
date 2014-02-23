package com.shunix.dailypushups.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.shunix.dailypushups.BuildConfig;

/**
 * @author Ray WANG <admin@shunix.com>
 * @version 1.0.0
 * @since Feb 23rd, 2014
 */
public class NumberBadge extends View {

    /**
     * the default size of the view, measured in dip.
     */
    private static final int DEFAULT_SIZE = 100;
    /**
     * used to save the number in the view.
     */
    private int number;
    /**
     * used to store the background color.
     */
    private int circleColor = Color.GRAY;
    /**
     * used to store the text color.
     */
    private int textColor = Color.WHITE;
    /**
     * used to store the width of the view.
     */
    private int width;
    /**
     * used to store the height of the view.
     */
    private int height;
    /**
     * The paint used to draw the circle.
     */
    private Paint circlePaint;
    /**
     * Center point of the center.
     */
    private Point circleCenter;
    /**
     * Radius of the circle.
     */
    private float circleRadius;
    /**
     * Tha paint used to draw the number.
     */
    private Paint textPaint;
    /**
     * The metrics to store the font information.
     */
    private Paint.FontMetrics fontMetrics;
    /**
     * The height of the text.
     */
    private double textHeight;
    /**
     * The width of the text.
     */
    private double textWidth;

    public NumberBadge(Context context, AttributeSet attrs) {
        super(context, attrs);
        number = 888888;
        if (BuildConfig.DEBUG) {
            circleColor = Color.GRAY;
        }
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        textPaint = new Paint((Paint.ANTI_ALIAS_FLAG));
        textPaint.setColor(textColor);
        circleCenter = new Point();
        fontMetrics = textPaint.getFontMetrics();
        textHeight = Math.ceil(fontMetrics.descent - fontMetrics.ascent) + 2;
        if (BuildConfig.DEBUG) {
            Log.d("textHeight", String.valueOf(textHeight));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(calculateMeasure(widthMeasureSpec), calculateMeasure(heightMeasureSpec));
        height = getHeight();
        width = getWidth();
        if (BuildConfig.DEBUG) {
            Log.d("height", String.valueOf(height));
            Log.d("width", String.valueOf(width));
        }
        circleCenter.set(width / 2, height / 2);
        // Set the radius to the 2/5 of the minimum of width and height.
        circleRadius = Math.min(height, width) * 2 / 5;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // Draw the circle.
        canvas.drawCircle(circleCenter.x, circleCenter.y, circleRadius, circlePaint);
        // Draw the text.
        textPaint.setTextSize(circleRadius / (1 + String.valueOf(number).length() * 0.5f) * 1.6f);
        textWidth = textPaint.measureText(String.valueOf(number));
        int startX = (int) (canvas.getWidth() / 2 - textWidth / 2);
        int startY = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2));
        canvas.drawText(String.valueOf(number), startX, startY, textPaint);
        canvas.restore();
    }

    /**
     * used to set the number on the view.
     *
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
        // Need to redraw now.
        invalidate();
    }

    /**
     * get the proper size for the current view.
     */
    private int calculateMeasure(int measureSpec) {
        int result = (int) (DEFAULT_SIZE * getResources().getDisplayMetrics().density);
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }

        return result;
    }

}
