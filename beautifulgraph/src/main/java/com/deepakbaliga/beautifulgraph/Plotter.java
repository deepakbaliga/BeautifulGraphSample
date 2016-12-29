package com.deepakbaliga.beautifulgraph;

/**
 * Created by deepakbaliga on 29/12/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Plotter extends View {


    private int numColumns, numRows;
    private int cellWidth, cellHeight;
    private Paint greyPaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint gradientPaint = new Paint();
    private float correction = 3.5f;
    private List<Integer> plots = new ArrayList<>();
    private float[] points;


    public Plotter(Context context) {
        super(context);
    }

    public Plotter(Context context, AttributeSet attrs) {
        super(context, attrs);


        greyPaint.setStyle(Paint.Style.STROKE);
        greyPaint.setColor(context.getResources().getColor(R.color.grey_lines));
        greyPaint.setAntiAlias(true);
        greyPaint.setStrokeWidth(10);



        gradientPaint.setStyle(Paint.Style.STROKE);
        gradientPaint.setAntiAlias(true);
        gradientPaint.setStrokeWidth(10);

        textPaint.setTextSize(26);
        textPaint.setAntiAlias(true);
        textPaint.setAlpha(75);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

    }

    public Plotter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, w, oldw, oldw);
        calculateDimensions();
    }

    //Use this method to draw any custom view
    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.TRANSPARENT);

        if (numColumns == 0 || numRows == 0) {
            return;
        }

        int width = getWidth();
        int height = getWidth();

        drawMatrix(width, height, canvas);
        plotGraph(width, height, canvas);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setRowCol(int row, int col) {
        this.numColumns = col;
        this.numRows = row;
        calculateDimensions();
    }


    private void calculateDimensions() {
        if (numColumns < 1 || numRows < 1) {
            return;
        }

        cellWidth = getWidth() / numColumns;
        cellHeight = getWidth()/numColumns;
        invalidate();
    }


    public void setPlots(List<Integer> plots) {
        this.plots = plots;
        points = new float[plots.size()*2];
    }


    private void drawMatrix(int width, int height, Canvas canvas){

        for (int i = 0; i <= numColumns; i++)
            canvas.drawLine((i * cellWidth)+correction, 0, (i * cellWidth)+correction, height, greyPaint);


        for (int i = 0; i <= numRows; i++)
            canvas.drawLine(0, (i * cellHeight)+correction, width, (i * cellHeight)+correction, greyPaint);

    }


    private void plotGraph(int width, int height, Canvas canvas){

        Shader shader = new LinearGradient(0, 0, 0, 500, getResources().getColor(R.color.colorBlue),  getResources().getColor(R.color.colorGreen), Shader.TileMode.MIRROR /*or REPEAT*/);
        gradientPaint.setShader(shader);


        for(int i=0, j=0; i<plots.size(); i++, j+=2){
            canvas.drawCircle(inBoundX(i)+correction+cellWidth, inBoundY(i, getWidth()),10, gradientPaint);
            points[j] = inBoundX(i)+correction+cellWidth;
            points[j+1] = inBoundY(i, getWidth());
        }

        drawLines(canvas);

    }

    private void drawLines(Canvas canvas) {

        if (points.length<=1)
            return;

        Path path =  new Path();

        //This is to move the cursor
        path.moveTo(0, getWidth());

        for (int i = 0; i<points.length; i+=2){
            path.lineTo(points[i], points[i+1]);

        }


        canvas.drawPath(path, gradientPaint);

    }


    private float inBoundX(int value){
        int perPlot = getWidth()/plots.size() ;

        return perPlot*value;
    }

    private float inBoundY(int i, int height){

        return (float) plots.get(i) * getWidth() / (float) maxValue();
    }


    private float maxValue(){
        int max = plots.get(0);


        for(Integer value: plots)
            if(value>max){
                max = value;
            }


        return max;
    }




}
