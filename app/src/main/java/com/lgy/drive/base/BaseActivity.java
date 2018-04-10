package com.lgy.drive.base;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lgy.drive.utils.AppManager;
import com.lgy.drive.utils.LogUtil;
import com.lgy.drive.utils.permission.DefaultRationale;
import com.lgy.drive.utils.permission.PermissionSetting;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.Request;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Description: Activity基类
 * Creator: yxc
 * date: 17/9/14
 */
public abstract class BaseActivity extends SupportActivity {
    public final static int CAMER_CODE = 1000;

    public final static int SCAN_QR_CODE = 1001;//扫描二维码

    public final static int WRITE_READ_FILE_CODE = 1002;//读写文件

    public final static int CALL_PHONE_CODE = 1003;//拨打电话

    protected Activity mContext;

    private Unbinder mUnBinder;

    private Rationale mRationale;
    private PermissionSetting mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d( this.getClass().getName() + "------>onCreate");
        init();
        setContentView(getLayout());
        getIntentData();
        mContext = this;
        mUnBinder = ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    protected void init() {
        setTranslucentStatus(true);
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d( this.getClass().getName() + "------>onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d( this.getClass().getName() + "------>onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(this.getClass().getName() + "------>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(this.getClass().getName() + "------>onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(this.getClass().getName() + "------>onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d( this.getClass().getName() + "------>onDestroy");
        if (mUnBinder != null)
            mUnBinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }


    /**
     * 设置沉浸式
     *
     * @param on
     */


    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
    public void applyForpermission(final int code) {
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);
        Request permission=null;
        switch (code) {
            case CAMER_CODE:
                permission= AndPermission.with(this).permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
            case SCAN_QR_CODE:
                permission=AndPermission.with(this).permission(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.VIBRATE);
                break;
            case WRITE_READ_FILE_CODE:
                permission=AndPermission.with(this).permission( Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
                break;
            case CALL_PHONE_CODE:
                permission=AndPermission.with(this).permission( Manifest.permission.CALL_PHONE);
                break;
        }
        if(permission!=null){
            permission
                    .rationale(mRationale)
                    .onGranted(new Action() {
                        @Override
                        public void onAction(List<String> permissions) {
                            onPermissionSuccess(code);
                        }
                    })
                    .onDenied(new Action() {
                        @Override
                        public void onAction(@NonNull List<String> permissions) {
                            if (AndPermission.hasAlwaysDeniedPermission(BaseActivity.this, permissions)) {
                                mSetting.showSetting(permissions);
                            }
                        }
                    })
                    .start();
        }
    }



    protected static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    protected abstract int getLayout();

    protected void initView() {
    }

    protected void initEvent() {
    }
    protected void initData() {
    }

    protected void getIntentData() {
    }

    protected void onPermissionSuccess(int code) {

    }

    protected void onPermissionFail(int code) {

    }
}
