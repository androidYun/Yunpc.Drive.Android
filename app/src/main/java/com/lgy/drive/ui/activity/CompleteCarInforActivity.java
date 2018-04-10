package com.lgy.drive.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jph.takephoto.model.TResult;
import com.lgy.drive.MainActivity;
import com.lgy.drive.PresneterView.CompleInforView;
import com.lgy.drive.R;
import com.lgy.drive.base.BaseTakePhotoActivity;
import com.lgy.drive.model.http.resp.DriveResp;
import com.lgy.drive.persenter.CompleInforPresenter;
import com.lgy.drive.utils.imageutils.ImageUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ${lgy} on 2018/4/214:10
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class CompleteCarInforActivity extends BaseTakePhotoActivity<CompleInforPresenter> implements CompleInforView {


    @BindView(R.id.ev_car_number)
    EditText evCarNumber;
    @BindView(R.id.ev_car_type)
    EditText evCarType;
    @BindView(R.id.iv_drive_license)
    ImageView ivDriveLicense;
    @BindView(R.id.btn_next_step)
    Button btnNextStep;
    private String driveLicenseUrl;

    @Override
    public void onCompleteSuccess(DriveResp driveResp) {

    }

    @Override
    public void upLoadHeadImage(String path) {

    }

    @Override
    public void upLoadIDCardImage(String path) {

    }

    @Override
    public void upDriveLicenseImage(String path) {
        driveLicenseUrl = path;
    }

    @Override
    public void onCompleteCarSuccess(DriveResp driveResp) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_complete_car_infor;
    }

    @OnClick({R.id.iv_drive_license, R.id.btn_next_step})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_drive_license:
                applyForpermission(WRITE_READ_FILE_CODE);
                break;
            case R.id.btn_next_step:
                String carName = evCarType.getText().toString();
                String carNumber = evCarNumber.getText().toString();
                mPresenter.completeCarInfor(driveLicenseUrl,carName,carNumber);
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
            mPresenter.upDriveLicenseImages(originalPath);
        }
    }

}
