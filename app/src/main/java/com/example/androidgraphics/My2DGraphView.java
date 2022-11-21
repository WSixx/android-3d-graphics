package com.example.androidgraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * created on 18/11/2022
 *
 * @author Lucas Goncalves
 */
public class My2DGraphView extends View {

    private final Paint redPaint;
    private final Paint bluePaint;
    private final Paint greenPaint;
    private final Paint gradientPaint;
    private final Path path;
    private final LinearGradient linearGradient;


    private final int x_0 = 0;
    private final int y_0 = 0;

    public My2DGraphView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public My2DGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public My2DGraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //70 tamanho so appbar
        int viewHeight = getResources().getDisplayMetrics().heightPixels - 70;
        int viewWidth = getResources().getDisplayMetrics().widthPixels;
        int[] plotData = { 110,290,100,200,120,50,310,240,210,130};

        path = createLineGraph(plotData, viewWidth, viewHeight);
        linearGradient = new LinearGradient(50,300,1000,1000, Color.BLUE, Color.RED, Shader.TileMode.MIRROR);
    }

    public My2DGraphView(Context context) {
        this(context, null, -1, -1 );
    }

    public Path createLineGraph(int[] input, int width, int height){
        Point[] ptArray = new Point[input.length];
        int minValue = 999999, maxValue = -999999;

        for (int i = 0; i < input.length; i++) {
            ptArray[i] = new Point(i, input[i]);
            minValue = Math.min(minValue, input[i]);
            maxValue = Math.max(maxValue, input[i]);
        }
        Matrix matrix = new Matrix();
        matrix.setTranslate(0, -minValue);
        double yScale = height / (double)(maxValue-minValue), xScale = width/ (double)(input.length-1);
        matrix.setScale((float) xScale, (float) yScale);
        Path result = new Path();
        result.moveTo(ptArray[0].x, ptArray[0].y);
        for (int i = 1; i < ptArray.length; i++) {
            result.lineTo(ptArray[i].x, ptArray[i].y);
        }
        result.transform(matrix);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPaint(redPaint, 0xffff0000);
        drawPaint(bluePaint, Color.BLUE);
        drawPaint(greenPaint, Color.GREEN);

        gradientPaint.setStyle(Paint.Style.FILL);
        gradientPaint.setShader(linearGradient);
        greenPaint.setARGB(255, 0, 255, 0);

     /*   path.moveTo(50, 300); //0
        path.lineTo(50, 300); //1
        path.lineTo(160,280); //2
        path.lineTo(300,380); //3
        path.lineTo(380,370); //4
        path.lineTo(280,450); //5
        path.lineTo(100,390); //6
        path.lineTo(160,380); //7
        path.lineTo(50,300); //8*/
/*
        float skewX = 2F;
        float skewY = 0F;
        Matrix matrix = new Matrix();
        matrix.setSkew(skewX, skewY);
        matrix.setScale(0.5F, 3F);
        matrix.setRotate((float) Math.toRadians(45.0));
        matrix.setTranslate(550F, 0F);
        myLines.transform(matrix);
        */
        //path.close();

        path.addArc(20F, 15F, 34F, 19F, 38F, 0F);
        canvas.drawPath(path, redPaint);
        //canvas.drawCircle(300,300, 250, bluePaint);
        //canvas.drawRect(500, 500, 700, 700, redPaint);
    }

    private void drawPaint(Paint redPaint, int color) {
        redPaint.setStyle(Paint.Style.STROKE);
        redPaint.setColor(color);
        redPaint.setStrokeWidth(5);
    }
}
