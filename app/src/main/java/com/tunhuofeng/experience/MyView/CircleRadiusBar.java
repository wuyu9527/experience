package com.tunhuofeng.experience.MyView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;


import com.tunhuofeng.experience.MyView.MyViewBean.BarChart;

import java.util.ArrayList;


/**
 * Created by whx on 2017/5/19.
 */

public abstract class CircleRadiusBar extends ViewGroup {

    protected Context context;

    public static final Double MAX_ROUND = 0.5;
    public static final Double MIN_ROUND = 0.361111;


    public CircleRadiusBar(Context context) {
        super(context);
        this.context =context;
        init();
    }

    public CircleRadiusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        init();
    }

    public CircleRadiusBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        init();
    }

    /**
     * 数据
     */
    protected ArrayList<BarChart> pieChart;
    protected int pieTotal;
    /**
     * 所有比例
     */
    protected ArrayList<Float> piePro;

    /**
     * 名字
     */
    protected ArrayList<String> pieName;

    /**
     * 每个半圆颜色
     */
    protected ArrayList<Integer> pieColor;
    /**
     * 数字
     */
    protected Paint numP;
    /**
     * 数字字体颜色
     */
    int numPColor;
    /**
     * 柱子名字画笔
     */
    protected Paint nameP;
    /**
     * 名字颜色
     */
    int namePColor;
    /**
     * 圆画笔
     */
    protected Paint chartP;

    /**
     * 圆颜色
     */
    int chartPColor;

    /**
     * 圆数字大小
     */
    float numSize;
    /**
     * 圆名字大小
     */
    float nameSize;
    /**
     * 圆宽度
     */
    protected float roundWidth;
    /**
     * 圆半径
     */
    protected float circleRadius;
    /**
     * 屏幕宽
     */
    protected int screenWidth;
    /**
     * 屏幕高
     */
    protected int screenHeight;
    /**
     * 中心X坐标
     */
    protected int startX = 0;
    /**
     * 中心Y坐标
     */
    protected int startY = 0;
    /**
     * view 宽高
     */
    protected int myWidth;
    protected int myHeight;
    /**
     * 动画速度
     */
    protected float speedNum;// 720*0.014

    /**
     * 圆
     */
    protected RectF rectF;

    /**
     * 屏幕密度
     * / 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     */
    protected Float density;

    /**
     * 算出总数
     */
    protected float count;


    protected void init(){
        setWillNotDraw(false);
        pieChart =new ArrayList<>();
        piePro =new ArrayList<>();
        pieName =new ArrayList<>();
        pieColor =new ArrayList<>();
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        numP =new Paint(Paint.ANTI_ALIAS_FLAG);
        nameP =new Paint(Paint.ANTI_ALIAS_FLAG);
        chartP =new Paint(Paint.ANTI_ALIAS_FLAG);

        // 设置画笔为抗锯齿
        chartP.setAntiAlias(true);
        // 设置颜色为红色
        chartP.setColor(Color.RED);
        /**
         * 画笔样式分三种： 1.Paint.Style.STROKE：描边 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        chartP.setStyle(Paint.Style.STROKE);
        /**
         * 设置描边的粗细，单位：像素px 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        chartP.setStrokeWidth(20);
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = getResources().getDisplayMetrics();
        density  = dm.density/2;
    }

    protected void setRectF(){


//        int center = myWidth / 2;
//        int radius = center - center/2;
//        rectF = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限

        float left  = (myWidth-circleRadius)/2;
        float top = (myHeight-circleRadius)/2;
        float right = left+circleRadius;
        float bottom = top+circleRadius;

        rectF =new RectF(left,top,right,bottom);


    }

    public void setPieChart(ArrayList<BarChart> pieChart) {
        count = 0;
        pieTotal = pieChart.size();
        for (BarChart barChart : pieChart) {
            barChart.setRectF(rectF);
            pieColor.add(barChart.getColor());
            pieName.add(barChart.getWeek());
            piePro.add(barChart.getNum());
            count +=barChart.getNum();
        }
        for (int i = 0; i < pieChart.size(); i++) {
            BarChart.Coordinate coordinate = new BarChart.Coordinate();
            float proportion = pieChart.get(i).getNum()/count;
            coordinate.setProportion(proportion);
            coordinate.setSweepAngle(proportion*360);
            if (i==0) {
                coordinate.setStartAngle(0);
            }else {
                coordinate.setStartAngle(pieChart.get(i - 1).getCoordinate().getSweepAngle());

            }
            pieChart.get(i).setCoordinate(coordinate);
        }
        this.pieChart = pieChart;
        postInvalidate();
    }
}
