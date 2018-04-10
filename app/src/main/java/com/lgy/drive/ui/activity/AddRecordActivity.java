package com.lgy.drive.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.lgy.drive.PresneterView.AddRecordView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseMvpActivity;
import com.lgy.drive.bean.CityBean;
import com.lgy.drive.listence.SelectCityListence;
import com.lgy.drive.listence.TimePickListener;
import com.lgy.drive.model.http.req.RecordReq;
import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.persenter.AddRecordPresenter;
import com.lgy.drive.utils.TimePickerViewUtils;
import com.lgy.drive.utils.TimeUtils;
import com.lgy.drive.utils.ToastUtil;
import com.lgy.drive.utils.sp.UserSpUtils;
import com.lgy.drive.view.NaviTitleView;
import com.lgy.drive.view.popu.SelectcityPop;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/4/217:20
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class AddRecordActivity extends BaseMvpActivity<AddRecordPresenter> implements SelectCityListence, TimePickListener, AddRecordView {
    private final static int START_CODE = 101;
    private final static int END_CODE = 102;


    SelectcityPop selectStartcityPop;

    SelectcityPop selectEndcityPop;
    @BindView(R.id.navi_view)
    NaviTitleView naviView;
    @BindView(R.id.tv_start_place)
    TextView tvStartPlace;
    @BindView(R.id.tv_end_place)
    TextView tvEndPlace;
    @BindView(R.id.ev_pass_place)
    EditText evPassPlace;
    @BindView(R.id.ev_content)
    EditText evContent;
    @BindView(R.id.ev_phone)
    EditText evPhone;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    @BindView(R.id.ev_people)
    EditText evPeople;
    private TimePickerView timePickView;

    private CityBean startCityBean;

    private CityBean endCityBean;

    @Override
    protected void initView() {
        super.initView();
        selectStartcityPop = new SelectcityPop(this, START_CODE, this);
        selectEndcityPop = new SelectcityPop(this, END_CODE, this);
        timePickView = TimePickerViewUtils.getTimePickViewYMDH(this, 1, this);
    }

    @Override
    protected void initData() {
        super.initData();
        DriveBean driveBean = UserSpUtils.getDriveBean();
        if (driveBean != null) {
            evPhone.setText(driveBean.phone);
        }
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_add_record;
    }

    @Override
    public void selectCitySuccess(CityBean cityBean, int code) {
        if (code == START_CODE) {
            startCityBean = cityBean;
            selectStartcityPop.dismiss();
            tvStartPlace.setText(cityBean.getCityName());
        } else {
            endCityBean = cityBean;
            selectEndcityPop.dismiss();
            tvEndPlace.setText(cityBean.getCityName());
        }
    }


    @OnClick({R.id.tv_start_place, R.id.tv_end_place, R.id.tv_start_time, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start_place:
                selectStartcityPop.showPopupWindow(tvStartPlace);
                break;
            case R.id.tv_end_place:
                selectEndcityPop.showPopupWindow(tvEndPlace);
                break;
            case R.id.tv_start_time:
                timePickView.show();
                break;
            case R.id.btn_confirm:
                RecordReq recordReq = new RecordReq();
                if (startCityBean != null) {
                    recordReq.setStartplace(startCityBean.getCityName());
                    recordReq.setStartcitycode(startCityBean.getCityCode());
                }
                if (endCityBean != null) {
                    recordReq.setEndcitycode(endCityBean.getCityCode());
                    recordReq.setArriveplace(endCityBean.getCityName());
                }
                recordReq.setContent(evContent.getText().toString());
                recordReq.setPassplace(evPassPlace.getText().toString());
                recordReq.setPhone(evPhone.getText().toString());
                recordReq.setStarttime(tvStartTime.getText().toString());
                recordReq.setTakenumber(Integer.valueOf(evPeople.getText().toString()));
                mPresenter.addRecord(recordReq);
                break;
        }
    }

    @Override
    public void onSelectTime(int code, Date date) {
        String time = TimeUtils.getTime(date);
        tvStartTime.setText(time);
    }

    @Override
    public void addRecordSuccess() {
        ToastUtil.show(this, "添加成功");
        finish();
    }
}
