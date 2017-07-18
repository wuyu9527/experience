package com.tunhuofeng.experience.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.tunhuofeng.experience.MyView.MyViewBean.BarChart;
import com.tunhuofeng.experience.R;


/**
 * Created by whx on 2017/5/19.
 */

public class CircleRadius extends CircleRadiusBar{



    public CircleRadius(Context context) {
        super(context);
        this.context =context;
    }

    public CircleRadius(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
    }

    public CircleRadius(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        myWidth= View.MeasureSpec.getSize(widthMeasureSpec);
        myHeight= View.MeasureSpec.getSize(heightMeasureSpec);
        roundWidth = (float) ((screenWidth*MAX_ROUND-screenWidth*MIN_ROUND)/2);
        circleRadius = (float) (screenWidth*MIN_ROUND+roundWidth);
        chartPColor = R.color.bg_main;
        chartP.setStrokeWidth(roundWidth);
        setRectF();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pieChart.size()>0) {
            for (BarChart barChart : pieChart) {
                chartP.setColor(getResources().getColor(barChart.getColor()));
                canvas.drawArc(rectF, barChart.getCoordinate().getStartAngle(), barChart.getCoordinate().getSweepAngle(), false, chartP);
            }
        }else {
            chartP.setColor(getResources().getColor(chartPColor));
            canvas.drawArc(rectF, 0, 360, false, chartP);
        }

    }

}
