package com.tunhuofeng.experience.Util;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;


/**
 * Created by whx on 2017/2/22.
 */

public class FrescoUtils {
    private static boolean isInit = false;

    /**
     * 显示缩略图
     *
     * @param draweeView     draweeView
     * @param url            url
     * @param resizeWidthDp  resizeWidth
     * @param resizeHeightDp resizeHeight
     */
    public static void showThumb(SimpleDraweeView draweeView, String url, int resizeWidthDp, int resizeHeightDp) {
        if (url == null || "".equals(url))
            return;
        if (draweeView == null)
            return;
        initialize(draweeView.getContext());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .build();
        draweeView.setController(controller);
    }

    /**
     * initialize
     *
     * @param context context
     */
    public static void initialize(Context context) {
        if (isInit)
            return;
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(context, config);
        isInit = true;
    }
}
