package com.lgy.drive.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lgy.drive.R;
import com.lgy.drive.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ${lgy} on 2017/8/2211:33
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class NaviTitleView extends RelativeLayout {
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rl_layout)
    RelativeLayout rlLayout;
    @BindView(R.id.lv_left)
    LinearLayout lvLeft;
    @BindView(R.id.ll_title_bar_right)
    LinearLayout llTitleBarRight;
    private int leftResId;

    private String centerTitle;//中间标题

    private int rightResId;//右边资源文件

    private String rightTitle;//右边显示文字

    private int rightTitleColor;//右边文字颜色

    private int titleBgColor;//背景颜色

    private View inflateView;

    private OnClickListener leftClickListener;//左边返回键监听事件

    private OnClickListener rightClickListener;//右边边返回键监听事件

    private Context mContext;

    public NaviTitleView(Context context) {
        super(context);
        this.mContext = context;
        initView(context);
        inflateView();
    }

    public NaviTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NaviTitleView);
        leftResId = typedArray.getResourceId(R.styleable.NaviTitleView_left_res, R.mipmap.ic_back);
        rightResId = typedArray.getResourceId(R.styleable.NaviTitleView_right_res, 0);
        centerTitle = typedArray.getString(R.styleable.NaviTitleView_center_title);
        rightTitle = typedArray.getString(R.styleable.NaviTitleView_right_title);
        rightTitleColor = typedArray.getColor(R.styleable.NaviTitleView_right_title_color, ContextCompat.getColor(context, R.color.base_color));
        titleBgColor = typedArray.getColor(R.styleable.NaviTitleView_title_bg_color, ContextCompat.getColor(context, R.color.default_background_color));
        typedArray.recycle();
        initView(context);
        inflateView();
    }

    public void initView(Context context) {
        this.mContext = context;
        inflateView = LayoutInflater.from(context).inflate(R.layout.include_navi_title, this, true);
        ButterKnife.bind(this, inflateView);
    }

    public void inflateView() {
        if (titleBgColor != 0) {
            rlLayout.setBackgroundColor(titleBgColor);//设置背景色
        }
        tvTitle.setText(centerTitle);
        if (leftResId == 0) {
            ivLeft.setImageResource(leftResId);
        }
        if (rightResId == 0) {
            ivTitleRight.setVisibility(GONE);
        } else {
            ivTitleRight.setVisibility(VISIBLE);
            ivTitleRight.setImageResource(rightResId);
        }
        if (StringUtils.isEmpty(rightTitle)) {
            tvTitleRight.setVisibility(GONE);
        } else {
            tvTitleRight.setVisibility(VISIBLE);
            tvTitleRight.setText(rightTitle);
            tvTitleRight.setTextColor(rightTitleColor);
        }

    }

    @OnClick({R.id.lv_left, R.id.ll_title_bar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lv_left:
                if (leftClickListener == null) {
                    Activity activity = (Activity) mContext;
                    activity.finish();
                } else {
                    leftClickListener.onClick(view);
                }
                break;
            case R.id.ll_title_bar_right:
                if (rightClickListener != null) {
                    rightClickListener.onClick(view);
                }
                break;

        }
    }

    /**
     * 添加左边事件
     *
     * @param leftClickListener
     */
    public void setLeftClickListener(final OnClickListener leftClickListener) {
        this.leftClickListener = leftClickListener;
    }

    /**
     * @param rightClickListener
     */
    public void setRightClickListener(final OnClickListener rightClickListener) {
        this.rightClickListener = rightClickListener;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void visibleLeftRes(boolean isVisible) {
        if (isVisible) {
            lvLeft.setVisibility(VISIBLE);
        } else {
            lvLeft.setVisibility(GONE);
        }
    }
}
