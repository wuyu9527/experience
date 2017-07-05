package com.tunhuofeng.experience.Util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.tunhuofeng.experience.MyApplication;
import com.tunhuofeng.experience.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * Created by whx on 2016/12/15.
 */

public class UtilAll {
    private static DecimalFormat mFormatOne = new DecimalFormat("###,###,###,##0");
    private static DecimalFormat mFormatTwo = new DecimalFormat("###,###,###,##0.##");
    private static DecimalFormat mFormatThree = new DecimalFormat("#############");
    private static DecimalFormat mFormatFour = new DecimalFormat("#############.##");


    public UtilAll() {
    }

    public static String getFormatted(String num) {
        if (num != null && !num.equals("")) {
            float f = Float.parseFloat(num);
            String v = String.valueOf(f);
            if (v.split("\\.")[1].equals("00") || v.split("\\.")[1].equals("0")) {
                return mFormatOne.format(Float.valueOf(v));
            } else {
                return mFormatTwo.format(f);
            }
        } else {
            return "0";
        }
    }

    /**
     * @param num
     * @return 返回无负号 ###,###,###,##0
     */
    public static String getFormat(String num) {
        if (num != null && !num.equals("")) {
            float f = Float.parseFloat(num);
            String v = String.valueOf(f).replace("-", "");
            if (v.split("\\.")[1].equals("00") || v.split("\\.")[1].equals("0")) {
                return mFormatOne.format(Float.valueOf(v));
            } else {
                return mFormatTwo.format(f);
            }
        } else {
            return "0";
        }
    }


    /**
     * @param num
     * @return 返回无负号 ###########0
     * ###########0.##
     */
    public static String removeZero(String num) {
        if (num != null && !num.equals("")) {
            float f = Float.parseFloat(num);
            String v = String.valueOf(f).replace("-", "");
            if (v.split("\\.")[1].equals("00") || v.split("\\.")[1].equals("0")) {
                return mFormatThree.format(Float.valueOf(v));
            } else {
                return mFormatFour.format(f);
            }
        } else {
            return "0";
        }
    }


    /**
     * 获得文字宽度
     *
     * @param text 要计算的字符串
     * @param size 字体大小
     */
    public static float getTextWidth(String text, float size) {
        TextPaint FontPaint = new TextPaint();
        FontPaint.setTextSize(size);
        return FontPaint.measureText(text);
    }

    /**
     * 开启 关闭动画
     *
     * @param view
     */
    public static void animateClose(final View view) {
        if (view.getVisibility() == View.GONE) {
            return;
        }
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, origHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                view.clearAnimation();
            }

        });
        animator.start();
    }

    public static void animateOpen(final View v, int mHiddenViewMeasuredHeight) {
        if (v.getVisibility() == View.VISIBLE) {
            return;
        }
        final ValueAnimator animator = createDropAnimator(v, 0,
                mHiddenViewMeasuredHeight);
        //animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                v.clearAnimation();
            }

        });
        v.setVisibility(View.VISIBLE);
        animator.start();

    }


//    public static void animageOpenBottom(final View v, int mHiddenViewMeasuredHeight){
//        if (v.getVisibility() == View.VISIBLE) {
//            return;
//        }
//        final ValueAnimator animator = createDropAnimator(v, 0,
//                mHiddenViewMeasuredHeight);
//        //animator.setDuration(1000);
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                v.clearAnimation();
//            }
//
//        });
//        v.setVisibility(View.VISIBLE);
//        animator.start();
//    }

    public static ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        if (file != null) {
            long size = 0;
            try {
                File[] fileList = file.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);

                    } else {
                        size = size + fileList[i].length();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //return size/1048576;
            return size;
        } else {
            return 0;
        }
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath 文件地址
     * @param filePath 是否删除
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            return;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0MB";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 输入框全清按钮
     *
     * @param editText
     * @param view
     */
    public static void setAddTextChangedListener(final EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && view.getVisibility() == View.INVISIBLE) {
                    view.setVisibility(View.VISIBLE);
                }
                if (s.length() == 0) {
                    view.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
    }

    // MD5加码。32位
    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    private static AlertDialog dialog = null;
    private static Activity activityOne = null;

    public static void setLogin(final MyApplication app, final Activity activity) {
        if (activityOne == null) {
            dialog = new AlertDialog.Builder(activity)
                    .setMessage("登录已经过期,请退出后重新登录!")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            activity.finish();
                        }
                    })
                    .create();
            activityOne = activity;
        } else if (activityOne != activity) {
            dialog = new AlertDialog.Builder(activity)
                    .setMessage("登录已经过期,请退出后重新登录!")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            activity.finish();
                        }
                    })
                    .create();
            activityOne = activity;
        }
        if (!dialog.isShowing() && activity != null) {
            dialog.show();
        }
    }


    public static String getRandom() {
        String item_no = "";
        Random rd = new Random();
        int m = 0;//生成0-26的随机数
        String n = "";
        for (int i = 0; i < 4; i++) {
            String str = "abcdefghijklmnopqrstuvwxyz0123456789";
            m = rd.nextInt(str.length());
            n = n + str.charAt(m);
        }
        String[] n1 = new String[n.length()];
        for (int i = 0; i < n.length(); i++) {
            n1[i] = n.substring(i, i + 1);
        }
        String time = TimeProcessing.getTodayTime();
        String year = TimeProcessing.getYear(time);
        String month = TimeProcessing.getMonth(time);
        String day = String.valueOf(TimeProcessing.getMonthDay());
        String TimeMill = String.valueOf(System.currentTimeMillis());
        String mill = TimeMill.substring(TimeMill.length() - 4, TimeMill.length());
        String[] arr = new String[mill.length()];
        for (int i = 0; i < mill.length(); i++) {
            arr[i] = mill.substring(i, i + 1);
        }
        item_no = arr[3] + n1[1] + arr[1] + n1[3] + arr[0] + arr[2] + n1[2] + n1[0];
        return year + month + day + randString(item_no);
    }

    public static String randString(String str) {
        StringBuffer result = new StringBuffer();
        int length = str.length();
        char[] chars = str.toCharArray();
        // 数组下标
        int index = -1;
        while (true) {
            // 随机 生成 下标
            index = new Random().nextInt(length);
            // 是否为空
            if (chars[index] != ' ') {
                result.append(chars[index]);
                // 全部获取完毕
                if (result.length() == length) {
                    break;
                }
                // 置空
                chars[index] = ' ';
            } else
                continue;
        }
        return result.toString();
    }


    public static int MIN_MARK = 0;
    public static int MAX_MARK = 90;


    public static void setRegion(final EditText et, int min, int max) {
        MIN_MARK = min;
        MAX_MARK = max;
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double num = Double.parseDouble(s.toString());
                        if (num > MAX_MARK) {
                            s = String.valueOf(MAX_MARK);
                            et.setText(s);
                        } else if (num < MIN_MARK)
                            s = String.valueOf(MIN_MARK);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals("")) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        double markVal = 0;
                        try {
                            markVal = Double.parseDouble(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK) {
                            et.setText(String.valueOf(MAX_MARK));
                        }
                        return;
                    }
                }
            }
        });
    }


    /**
     * 判断服务是否启动,context上下文对象 ，className服务的name
     */
    public static boolean isServiceRunning(Context mContext, String className) {

        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(30);

        if (!(serviceList.size() > 0)) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * @param context
     * @return
     */
    public static byte[] getCer(Context context) {
        byte[] buffer = null;
        try {
            InputStream fis = context.getResources().openRawResource(R.raw.zgbang);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 保存文件内容
     *
     * @param c        文本
     * @param fileName 文件名称
     * @param content  内容
     * @param mode     打开文件模式
     */
    public static void writeFiles(Context c, String fileName, String content, int mode) {
        // 打开文件获取输出流，文件不存在则自动创建
        FileOutputStream fos = null;
        try {
            fos = c.openFileOutput(fileName, mode);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e("ZGB", "文件写入错误");
        }
    }

    /**
     * 读取文件内容
     *
     * @param c        文本
     * @param fileName 文件名称
     * @return 返回文件内容
     */
    public static String readFiles(Context c, String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = c.openFileInput(fileName);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String content = baos.toString();
            fis.close();
            baos.close();
            return content;
        } catch (IOException e) {
            Log.e("ZGB", "文件读取错误");
        }
        return "";
    }

    /**
     * 删除指定文件夹下的所有文件
     *
     * @param path
     * @return
     */
    public static void delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File[] tempList = file.listFiles();
        for (File file1 : tempList) {
            file1.delete();
        }

    }

    /**
     * 计算子项高度
     */
    /*public static void setListViewHeightBasedOnChildren(MyExpandableListView listView ) {
        // 获取ListView对应的Adapter
        BillExpandableAdapter listAdapter = (BillExpandableAdapter) listView.getExpandableListAdapter();
        if (listAdapter == null) {
            // pre -condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getGroupCount(); i++) { // listAdapter.getCount()返回数据项的数目
            View listgroupItem = listAdapter .getGroupView(i, true, null, listView );
            listgroupItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listgroupItem .getMeasuredHeight(); // 统计所有子项的总高度
            System. out.println("height : group" +i +"次" +totalHeight );
            for (int j = 0; j < listAdapter.getChildrenCount( i); j++) {
                View listchildItem = listAdapter .getChildView(i, j, false , null, listView);
                listchildItem.measure(0, 0); // 计算子项View 的宽高
                totalHeight += listchildItem.getMeasuredHeight(); // 统计所有子项的总高度
                System. out.println("height :" +"group:" +i +" child:"+j+"次"+ totalHeight);
            }
        }

        ViewGroup.LayoutParams params = listView .getLayoutParams();
        params.height = totalHeight + ( listView.getDividerHeight() * (listAdapter.getGroupCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params );
    }*/

    /**
     * @param string 字符串
     * @param max    最大打印限制
     * @return 如果没超过限制返回原字符串,超过则截取并带 ...
     */
    public static String setMaxLength(String string, int max) {
        byte[] bytes = new byte[0];
        try {
            bytes = string.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        char[] chars = string.toCharArray();
        if (bytes.length >= max) {
            String str = "";
            for (char aChar : chars) {
                if (str.getBytes().length < max) {
                    str += aChar;
                } else {
                    break;
                }
            }
            return str + "..";
        } else {
            return string;
        }
    }

}
