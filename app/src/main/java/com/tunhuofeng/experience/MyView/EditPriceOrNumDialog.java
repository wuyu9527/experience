package com.tunhuofeng.experience.MyView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tunhuofeng.experience.R;


/**
 * Created by whx on 2017/5/31.
 */

public class EditPriceOrNumDialog extends Dialog {

    private Context context;
    private TextView tvTitle;
    private EditText priceOrNum;
    private TextView tvCancel;
    private TextView tvSure;
    private View.OnClickListener onDefaultClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            cancel();
        }

    };
    private View.OnClickListener onNegativeListener = onDefaultClickListener;
    private String mTitle;
    private String mMessage;
    private String priceNum;
    private LinearLayout dialog_edit_num_or_price;
    private SureOnClickListener listener;

    public EditPriceOrNumDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    public EditText getPriceOrNum() {
        return priceOrNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_num_or_price);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        priceOrNum = (EditText) findViewById(R.id.priceOrNum);
        tvCancel = (TextView) findViewById(R.id.tvCancel);
        tvSure = (TextView) findViewById(R.id.tvSure);
        dialog_edit_num_or_price = (LinearLayout) findViewById(R.id.dialog_edit_num_or_price);
        priceOrNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    cancel();
                    if (!priceOrNum.getText().toString().equals(""))
                        listener.setSure(priceOrNum.getText().toString());
                }
                return false;
            }
        });
    }


    /**
     * 调用完Builder类的create()方法后显示该对话框的方法
     */
    @Override
    public void show() {
        super.show();
        show(this);
    }

    private void show(EditPriceOrNumDialog mDialog) {
        if (!TextUtils.isEmpty(mDialog.mTitle)) {
            mDialog.tvTitle.setText(mDialog.mTitle);
            if (mDialog.mTitle.equals("编辑价格")){
                priceOrNum.setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
                priceOrNum.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String temp = s.toString();
                        int posDot = temp.indexOf(".");
                        if (posDot <= 0) return;
                        if (temp.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
                    }
                });
            }
        }
        mDialog.tvCancel.setOnClickListener(mDialog.onNegativeListener);
        mDialog.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
                if (!priceOrNum.getText().toString().equals(""))
                    listener.setSure(priceOrNum.getText().toString());
            }
        });
        if (!TextUtils.isEmpty(priceNum)) {
            priceOrNum.setText(priceNum);
        }
        priceOrNum.requestFocus();//获取焦点
    }


    public static class Builder {

        private EditPriceOrNumDialog mDialog;

        public Builder(Context context) {
            mDialog = new EditPriceOrNumDialog(context);
        }

        /**
         * 设置对话框标题
         *
         * @param title
         */
        public Builder setTitle(String title) {
            mDialog.mTitle = title;
            return this;
        }


        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         *
         * @param msg
         */
        public Builder setMessage(String msg) {
            mDialog.mMessage = msg;
            return this;
        }

        /**
         * 设置对话框文本内容,如果调用了setView()方法，该项失效
         *
         * @param priceNum
         */
        public Builder setPriceNum(String priceNum) {
            mDialog.priceNum = priceNum;
            return this;
        }

        /**
         * 设置确认按钮的回调
         *
         * @param listener
         */
        public Builder setPositiveButton(SureOnClickListener listener) {
            mDialog.listener = listener;
            return this;
        }


        /**
         * 设置取消按钮的回掉
         *
         * @param onClickListener
         */
        public Builder setNegativeButton(View.OnClickListener onClickListener) {
            mDialog.onNegativeListener = onClickListener;
            return this;
        }


        /**
         * 设置该对话框能否被Cancel掉，默认可以
         *
         * @param cancelable
         */
        public Builder setCancelable(boolean cancelable) {
            mDialog.setCancelable(cancelable);
            return this;
        }

        /**
         * 设置对话框被cancel对应的回调接口，cancel()方法被调用时才会回调该接口
         *
         * @param onCancelListener
         */
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            mDialog.setOnCancelListener(onCancelListener);
            return this;
        }

        /**
         * 设置对话框消失对应的回调接口，一切对话框消失都会回调该接口
         *
         * @param onDismissListener
         */
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            mDialog.setOnDismissListener(onDismissListener);
            return this;
        }

        /**
         * 通过Builder类设置完属性后构造对话框的方法
         */
        public EditPriceOrNumDialog create() {
            return mDialog;
        }
    }

    public interface SureOnClickListener {
        void setSure(String priceOrNum);
    }

}


