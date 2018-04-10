package com.lgy.drive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.jph.takephoto.model.TResult;
import com.lgy.drive.PresneterView.CompleInforView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseTakePhotoActivity;
import com.lgy.drive.common.Constants;
import com.lgy.drive.enumbean.DriveStateEnum;
import com.lgy.drive.listence.TimePickListener;
import com.lgy.drive.model.http.resp.DriveBean;
import com.lgy.drive.model.http.resp.DriveResp;
import com.lgy.drive.persenter.CompleInforPresenter;
import com.lgy.drive.utils.LogUtil;
import com.lgy.drive.utils.StringUtils;
import com.lgy.drive.utils.TimePickerViewUtils;
import com.lgy.drive.utils.TimeUtils;
import com.lgy.drive.utils.ToastUtil;
import com.lgy.drive.utils.imageutils.ImageLoaderProxy;
import com.lgy.drive.utils.imageutils.ImageUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/3/2915:33
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class CompleteUserInforActivity extends BaseTakePhotoActivity<CompleInforPresenter> implements CompleInforView, TimePickListener {


    @BindView(R.id.ev_name)
    EditText evName;
    @BindView(R.id.ev_idcard)
    EditText evIdcard;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.tv_drive_license_time)
    TextView tvDriveLicenseTime;
    @BindView(R.id.iv_idcard)
    ImageView ivIdcard;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;
    @BindView(R.id.ev_drive)
    EditText evDrive;

    private String headUrl;

    private String idCardUrl;

    private String driveLicenseTime;

    private String mSex = "男";

    TimePickerView timePickView;

    private int flag;//1  选择上传身份证  2  上传头像


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_complete_userinfor;
    }

    @Override
    protected void initView() {
        super.initView();
        timePickView = TimePickerViewUtils.getTimePickView(this, 1, this);

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        DriveBean driveBean = (DriveBean) extras.getSerializable(DriveBean.class.getName());
        if (driveBean.state == DriveStateEnum.WAN_USERINFOR.getCode()) {
            evIdcard.setText(driveBean.idcardno);
            evName.setText(driveBean.name);
            if (driveBean.sex.equals("男")) {
                rbMale.setChecked(true);
            } else {
                rbFemale.setChecked(true);
            }
            evDrive.setText(driveBean.driveno);
            tvDriveLicenseTime.setText(driveBean.getlicensetime);
            idCardUrl = driveBean.idcardheadurl;
            headUrl = driveBean.drivehead;
            driveLicenseTime = driveBean.getlicensetime;
            mSex=driveBean.sex;
            ImageLoaderProxy.getInstace().displayImage(Constants.BASE_URL + driveBean.idcardheadurl, ivIdcard);
            ImageLoaderProxy.getInstace().displayImage(Constants.BASE_URL + driveBean.drivehead, ivHead);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rb_female:
                        mSex = "女";
                        break;
                    case R.id.rb_male:
                        mSex = "男";
                        break;
                }
            }
        });
    }

    @Override
    public void onCompleteSuccess(DriveResp driveResp) {
        startActivity(new Intent(this, CompleteCarInforActivity.class));
    }

    @Override
    public void upLoadHeadImage(String path) {
        headUrl = path;
        ImageLoaderProxy.getInstace().displayImage(Constants.BASE_URL + path, ivHead);
        LogUtil.d(Constants.BASE_URL + path);
    }

    @Override
    public void upLoadIDCardImage(String path) {
        idCardUrl = path;
        ImageLoaderProxy.getInstace().displayImage(path, ivIdcard);
    }

    @Override
    public void onSelectTime(int code, Date date) {
        driveLicenseTime = TimeUtils.getTime(date);
        tvDriveLicenseTime.setText(driveLicenseTime);
    }

    @Override
    public void upDriveLicenseImage(String path) {

    }

    @Override
    public void onCompleteCarSuccess(DriveResp driveResp) {

    }

    @OnClick({R.id.tv_drive_license_time, R.id.btn_next_step, R.id.iv_idcard, R.id.iv_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_drive_license_time:
                timePickView.show();
                break;
            case R.id.btn_next_step:
                String name = evName.getText().toString();
                String idCard = evIdcard.getText().toString();
                String driveNo = evDrive.getText().toString();
                if (StringUtils.isEmpty(name)) {
                    ToastUtil.show(this, "真实姓名不能为空");
                    return;
                }
                if (StringUtils.isEmpty(idCard)) {
                    ToastUtil.show(this, "身份证不能为空");
                    return;
                }
                if (StringUtils.isEmpty(driveNo)) {
                    ToastUtil.show(this, "驾驶证编号不能为空");
                    return;
                }
                if (StringUtils.isEmpty(idCardUrl)) {
                    ToastUtil.show(this, "请上传身份证照片");
                    return;
                }
                if (StringUtils.isEmpty(headUrl)) {
                    ToastUtil.show(this, "请上传个人真实头像");
                    return;
                }
                if (StringUtils.isEmpty(name)) {
                    ToastUtil.show(this, "真实姓名不能为空");
                    return;
                }
                mPresenter.completeDriveInfor(name, mSex, idCard, driveNo, driveLicenseTime, idCardUrl, headUrl);
                break;
            case R.id.iv_idcard:
                flag = 1;
                applyForpermission(WRITE_READ_FILE_CODE);
                break;
            case R.id.iv_head:
                flag = 2;
                applyForpermission(WRITE_READ_FILE_CODE);
                break;
        }
    }

    @Override
    protected void onPermissionSuccess(int code) {
        super.onPermissionSuccess(code);
        if (code == WRITE_READ_FILE_CODE) {
            getTakePhoto().onPickFromGalleryWithCrop(ImageUtils.getImageUri(), ImageUtils.getCropOptions(500, 500));
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (result != null && result.getImages() != null && result.getImages().size() > 0) {
            String originalPath = result.getImages().get(0).getOriginalPath();
            if (flag == 1) {
                mPresenter.upIdCardImages(originalPath);
            } else {
                mPresenter.upHeadImages(originalPath);
            }
        }
    }


}
