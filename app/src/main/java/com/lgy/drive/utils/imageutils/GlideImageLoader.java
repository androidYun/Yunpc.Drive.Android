package com.lgy.drive.utils.imageutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.lgy.drive.MainApplication;
import com.lgy.drive.utils.LogUtil;

import java.io.File;

/**
 * Created by ${lgy} on 2018/1/808:41
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class GlideImageLoader implements ImageLoader {
    RequestManager requestManager;

    private Context mContext;

    @Override
    public void init() {
        if (mContext == null) {
            mContext = MainApplication.getInstance();
        }
        if (requestManager == null) {
            requestManager = Glide.with(mContext);
        }

    }

    @Override
    public void displayImage(String url, ImageView imageView) {
        requestManager.load(url).into(imageView);
    }

    @Override
    public void displayImage(String url, ImageView imageView, int defaultImage) {
        requestManager.load(url).placeholder(defaultImage).dontAnimate().listener(mRequestListener).into(imageView);
    }

    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            LogUtil.d("加载图片出错onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            LogUtil.d("加载成功");
            return false;
        }
    };

    @Override
    public void displayImage(String url, ImageView imageView, int defaultImage, int errorImage) {
        requestManager.load(url).placeholder(defaultImage).dontAnimate().error(defaultImage).into(imageView);
    }

    @Override
    public void displayImage(String url, ImageView imageView, boolean isNeedDefault) {

    }

    @Override
    public void displayImage(File file, ImageView imageView) {
        requestManager.load(file).into(imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView, int defaultImage) {
        requestManager.load(file).placeholder(defaultImage).dontAnimate().into(imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView, int defaultImage, int errorImage) {
        requestManager.load(file).placeholder(defaultImage).dontAnimate().error(defaultImage).into(imageView);
    }

    @Override
    public void displayImage(File file, ImageView imageView, boolean isNeedDefault) {

    }

    @Override
    public void displayImage(Bitmap bitmap, ImageView imageView) {
        requestManager.load(bitmap).into(imageView);
    }

    @Override
    public void displayImage(Bitmap bitmap, ImageView imageView, int defaultImage) {
        requestManager.load(bitmap).placeholder(defaultImage).dontAnimate().into(imageView);
    }

    @Override
    public void displayImage(Bitmap bitmap, ImageView imageView, int defaultImage, int errorImage) {
        requestManager.load(bitmap).placeholder(defaultImage).dontAnimate().error(defaultImage).into(imageView);
    }

    @Override
    public void displayImage(Bitmap bitmap, ImageView imageView, boolean isNeedDefault) {

    }
}
