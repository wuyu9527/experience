package com.tunhuofeng.experience.MyView.MyViewBean;

import android.graphics.RectF;

/**
 * Created by whx on 2016/12/2.
 */

public class BarChart {

    int data_id;
    String week;
    int PNColor;//0=正 1=负
    float num;
    String company;//单位
    int color;
    Coordinate coordinate;
    RectF rectF;

    public RectF getRectF() {
        return rectF;
    }

    public void setRectF(RectF rectF) {
        this.rectF = rectF;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getPNColor() {
        return PNColor;
    }

    public void setPNColor(int PNColor) {
        this.PNColor = PNColor;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public static class Coordinate {
        float proportion;
        float startAngle;
        float sweepAngle;
        float leftX;
        float topY;
        float rightX;
        float bottomY;

        public float getProportion() {
            return proportion;
        }

        public void setProportion(float proportion) {
            this.proportion = proportion;
        }

        public float getStartAngle() {
            return startAngle;
        }

        public void setStartAngle(float startAngle) {
            this.startAngle = startAngle;
        }

        public float getSweepAngle() {
            return sweepAngle;
        }

        public void setSweepAngle(float sweepAngle) {
            this.sweepAngle = sweepAngle;
        }

        public float getLeftX() {
            return leftX;
        }

        public void setLeftX(float leftX) {
            this.leftX = leftX;
        }

        public float getTopY() {
            return topY;
        }

        public void setTopY(float topY) {
            this.topY = topY;
        }

        public float getRightX() {
            return rightX;
        }

        public void setRightX(float rightX) {
            this.rightX = rightX;
        }

        public float getBottomY() {
            return bottomY;
        }

        public void setBottomY(float bottomY) {
            this.bottomY = bottomY;
        }
    }

    @Override
    public String toString() {
        return "BarChart{" +
                "data_id=" + data_id +
                ", week='" + week + '\'' +
                ", PNColor=" + PNColor +
                ", num=" + num +
                ", company='" + company + '\'' +
                ", color=" + color +
                ", coordinate=" + coordinate +
                ", rectF=" + rectF +
                '}';
    }
}
