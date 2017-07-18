package com.tunhuofeng.experience.MyView;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


import com.tunhuofeng.experience.MyView.MyViewBean.PieBean;
import com.tunhuofeng.experience.R;

import java.util.ArrayList;

/**
 * Created by whx on 2017/6/11.
 */

public class PieView extends View {
    public static final Double MAX_ROUND = 0.5;
    public static final Double MIN_ROUND = 0.361111;

    private ArrayList<PieBean> pieBeanArrayList;//园的数据
    private int type = 0;//默认 0 代表第一次进入 1 代表已经进入过了

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PieView(Context context) {
        super(context);
    }

    public void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);//设置空心
    }

    public ArrayList<PieBean> getPieBeanArrayList() {
        return pieBeanArrayList;
    }

    public void setPieBeanArrayList(ArrayList<PieBean> pieBeanArrayList) {
        init();
        this.pieBeanArrayList = pieBeanArrayList;
        int count = 0;
        for (PieBean pieBean : pieBeanArrayList) {
            count += pieBean.getNum();
        }
        for (int i = 0; i < pieBeanArrayList.size(); i++) {
            float bili = (float) pieBeanArrayList.get(i).getNum() / count;
            PieBean pieBean = pieBeanArrayList.get(i);
            if (i == 0) {
                pieBean.setStartAngle(0);
                pieBean.setSweepAngle(bili * 360);
            } else if (i > 0) {
                PieBean pieBean1 = pieBeanArrayList.get(i - 1);
                if (i == pieBeanArrayList.size() - 1) {
                    int start = (int) (pieBean1.getSweepAngle() + pieBean1.getStartAngle());
                    pieBean.setStartAngle(start);
                    pieBean.setSweepAngle(360 - start);
                } else {
                    pieBean.setStartAngle(pieBean1.getSweepAngle() + pieBean1.getStartAngle());
                    float sweep = bili * 360;
                    pieBean.setSweepAngle(sweep);
                }
            }
        }
        allCount = pieBeanArrayList.size();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        num = 0;
        postInvalidate();
    }


    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    private RectF rectF;//固定圆弧
    private Paint paint;//画笔
    private int num;//数据下标
    private int allCount;//圆弧数量
    private float roundWidth;//笔宽
    private int start = -90;//起始点
    private int sweep = 0;//距离
    private int graySweep = 0;
    private ArrayList<Integer> numCount = new ArrayList<>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (pieBeanArrayList != null) {
            if (num < allCount) {
                setRoundPie(canvas);
            } else {
                if (num == allCount) {
                    for (int i = 0; i < pieBeanArrayList.size(); i++) {
                        PieBean pieBean = pieBeanArrayList.get(i);
                        paint.setColor(getResources().getColor(pieBean.getColor()));
                        if (i == 0) {
                            canvas.drawArc(rectF, -90, pieBean.getSweepAngle(), false, paint);//小弧形
                        } else {
                            canvas.drawArc(rectF, pieBean.getStartAngle() - 90, pieBean.getSweepAngle(), false, paint);//小弧形
                        }
                    }
                } else {
                    paint.setColor(getResources().getColor(R.color.bg_main));
                    paint.setAntiAlias(true);
                    paint.setStrokeWidth(roundWidth);
                    paint.setStyle(Paint.Style.STROKE);//设置空心
                    canvas.drawArc(rectF, 0, 360, false, paint);//小弧形
                }
            }
        }
    }


    private void setRoundPie(Canvas canvas) {
        PieBean pieBean = pieBeanArrayList.get(num);
        paint.setColor(getResources().getColor(pieBean.getColor()));
        canvas.drawArc(rectF, start, sweep, false, paint);//小弧形
        if (sweep < pieBean.getSweepAngle()) {
            if (pieBean.getSweepAngle() < 3) {
                sweep++;
            } else {
                sweep += 6;
            }
            postInvalidate();
        } else {
            if (num != allCount - 1) {
                PieBean pieBean1 = pieBeanArrayList.get(num);
                pieBean1.setStartAngle(start);
                pieBean1.setSweepAngle(sweep);
                numCount.add(num);
            }
            num++;
            start = sweep + start;
            sweep = 0;
            if (num < allCount) {
                postInvalidate();
            }
        }
        for (int i : numCount) {
            PieBean pieBean1 = pieBeanArrayList.get(i);
            paint.setColor(getResources().getColor(pieBean1.getColor()));
            canvas.drawArc(rectF, pieBean1.getStartAngle(), pieBean1.getSweepAngle(), false, paint);//小弧形
        }
    }

    /**
     * view 宽高
     */
    protected int myWidth;
    protected int myHeight;
    /**
     * 圆半径
     */
    protected float circleRadius;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        myWidth = MeasureSpec.getSize(widthMeasureSpec);
        myHeight = MeasureSpec.getSize(heightMeasureSpec);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        roundWidth = (float) ((screenWidth * MAX_ROUND - screenWidth * MIN_ROUND) / 2);
        circleRadius = (float) (screenWidth * MIN_ROUND + roundWidth);
        float left = (myWidth - circleRadius) / 2;
        float top = (myHeight - circleRadius) / 2;
        float right = left + circleRadius;
        float bottom = top + circleRadius;
        rectF = new RectF(left, top, right, bottom);

    }
}
