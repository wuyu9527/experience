package com.tunhuofeng.experience.MyAdapter;

import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tunhuofeng.experience.R;

import java.util.List;

public class HeadImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HeadImgAdapter(@Nullable List<String> data) {
        super(R.layout.item_head_img, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView imageView = helper.getView(R.id.ivHead);
        Log.i("whx", "position:" + helper.getAdapterPosition());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (helper.getAdapterPosition() == 0) {
            layoutParams.setMargins(0, 0, 0, 0);
        } else {
            layoutParams.setMargins(-16, 0, 0, 0);
        }
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundColor(Color.TRANSPARENT);
    }
}
