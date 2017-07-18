package com.tunhuofeng.experience.MyView.MyViewBean;

/**
 * Created by whx on 2017/6/11.
 */

public class PieBean {
    private int id;
    private int color;
    private int num;
    private String name;
    private float startAngle;//开始圆弧起点
    private float sweepAngle;//终点

    @Override
    public String toString() {
        return "PieBean{" +
                "id=" + id +
                ", color=" + color +
                ", num=" + num +
                ", name='" + name + '\'' +
                ", startAngle=" + startAngle +
                ", sweepAngle=" + sweepAngle +
                '}';
    }

    public PieBean(int id, int color, int num, String name) {
        this.id = id;
        this.color = color;
        this.num = num;
        this.name = name;
    }

    public PieBean(int id, int color, int num, String name, float startAngle, float sweepAngle) {
        this.id = id;
        this.color = color;
        this.num = num;
        this.name = name;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
