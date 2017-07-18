package com.tunhuofeng.experience.MyView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ViewFlipper;

import com.tunhuofeng.experience.R;


/**
 * Created by whx on 2017/6/8.
 */

public class EditGoodsPrice extends PopupWindow {

    private Activity mContext;
    private View mMenuView;
    private ViewFlipper viewFlipper;

    public EditGoodsPrice(Activity context,int H) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.window_edit_goods_price, null);
        viewFlipper = new ViewFlipper(context);
        viewFlipper.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));




        viewFlipper.addView(mMenuView);
        viewFlipper.setFlipInterval(6000000);
        this.setContentView(viewFlipper);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(H);
        this.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(dw);
        this.update();

    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        viewFlipper.startFlipping();
    }
}
