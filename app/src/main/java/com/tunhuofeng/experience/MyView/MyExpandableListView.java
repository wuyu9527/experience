package com.tunhuofeng.experience.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/****
 * 
 * 自定义ExpandableListView控件
 * 
 * @author whx
 *
 */
public class MyExpandableListView extends ExpandableListView {


	public MyExpandableListView(Context context) {
		super(context);
	}

	public MyExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyExpandableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}



}
