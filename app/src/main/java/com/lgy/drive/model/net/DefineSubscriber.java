package com.lgy.drive.model.net;

import com.google.gson.JsonParseException;
import com.lgy.drive.base.BaseView;
import com.lgy.drive.enumbean.ResultEnum;
import com.lgy.drive.utils.FastJsonUtil;
import com.lgy.drive.utils.LogUtil;
import com.lgy.drive.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by ${lgy} on 2018/3/1314:01
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public abstract class DefineSubscriber<T> extends Subscriber<String> {
    private BaseView baseView;

    JSONObject jsonObject = null;

    public DefineSubscriber(BaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isShowProgress() && baseView != null) {
            baseView.showLoading();
        }
    }

    @Override
    public void onCompleted() {
        if (isShowProgress() && baseView != null) {
            baseView.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();

            String msg = httpException.getMessage();
            LogUtil.d("code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404 || code == 500) {
                msg = "服务器异常，请稍后再试";
            }
            if(code==401){
                baseView.tokenLose();
                return;
            }
            baseView.showError(msg);
            return;
        } else if (e instanceof RuntimeException) {    //服务器返回的错误
            RuntimeException resultException = (RuntimeException) e;
            baseView.showError(resultException.getMessage());
            return;
        } else if (e instanceof JsonParseException) {
            baseView.showError("解析出错");
            return;
        } else if (e instanceof ConnectException) {
            baseView.showError("服务器链接失败");
            return;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            baseView.showError("证书验证失败");
            return;
        } else {
            baseView.showError("未知错误");
            return;
        }
    }

    @Override
    public void onNext(String s) {
        LogUtil.d("请求结果" + s);
        T t;
        if (StringUtils.isEmpty(s)) {
            baseView.showError("返回数据为空");
        } else {
            try {
                if (jsonObject == null) {
                    jsonObject = new JSONObject(s);
                }
                int code = jsonObject.getInt("code");
                if (code != ResultEnum.RESPONSE_SUCCESS.getCode()) {
                    String errorMsg = jsonObject.getString("message");
                    baseView.showError(errorMsg);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                t = FastJsonUtil.getObject(s, getClazz());
                if (t != null) {
                    onSuccess(t);
                } else {
                    baseView.showError("解析数据失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                baseView.showError(e.getMessage());
                return;
            }
        }
    }

    protected abstract boolean isShowProgress();

    protected abstract void onSuccess(T t);

    public Class<T> getClazz() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) t;
        Class<T> c = (Class<T>) p.getActualTypeArguments()[0];
        return c;
    }

}
