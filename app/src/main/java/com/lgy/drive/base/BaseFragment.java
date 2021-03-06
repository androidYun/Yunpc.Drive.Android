package com.lgy.drive.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgy.drive.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * Description: BaseFragment
 * Creator: yxc
 * date: 2016/9/7 11:40
 */
public abstract class BaseFragment extends SupportFragment implements BaseView {

    private final String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected View rootView;
    protected Unbinder unbinder;
    private boolean isViewPrepared; // 标识fragment视图已经初始化完毕
    private boolean hasFetchData; // 标识已经触发过懒加载数据

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        if (mContext != null) {
            this.mContext = mContext;
        } else {
            this.mContext = getActivity();
        }
        LogUtil.d(getName(), "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(getName(), "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.d(getName(), "onCreateView");
        if (rootView == null) {
            rootView = inflater.inflate(getLayout(), container, false);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initView(inflater);
        initData();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
        LogUtil.d(getName(), "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(getName(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(getName(), "onResume");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();
        LogUtil.d(getName(), "onViewCreated");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(getName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(getName(), "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // view被销毁后，将可以重新触发数据懒加载，因为在viewpager下，fragment不会再次新建并走onCreate的生命周期流程，将从onCreateView开始
        hasFetchData = false;
        isViewPrepared = false;
        LogUtil.d(getName(), "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
        LogUtil.d(getName(), "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(getName(), "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.v(TAG, getClass().getName() + "------>isVisibleToUser = " + isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }

    }

    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected void lazyFetchData() {

    }

    public String getName() {
        return BaseFragment.class.getName();
    }


    protected abstract int getLayout();

    protected void initView(LayoutInflater inflater) {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void tokenLose() {

    }
}
