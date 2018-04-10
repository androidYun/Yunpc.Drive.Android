package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.CompleInforView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.resp.DriveResp;
import com.lgy.drive.model.http.resp.UploadResp;
import com.lgy.drive.model.net.DefineSubscriber;
import com.lgy.drive.model.net.RetrofitHelper;
import com.lgy.drive.utils.FileUtils;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${lgy} on 2018/3/1211:46
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class CompleInforPresenter extends RxPresenter<CompleInforView> {

    @Inject
    public CompleInforPresenter() {
    }

    public void completeDriveInfor(String name, String sex, String idcardno, String driveno,String getlicensetime, String idcardheadurl, String drivehead) {
        Subscription subscribe = RetrofitHelper.getUserApi().completeUserInfor(name, sex, idcardno, driveno,getlicensetime, idcardheadurl, drivehead).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<DriveResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(DriveResp driveResp) {
                        mView.onCompleteSuccess(driveResp);
                    }
                });
        addSubscribe(subscribe);
    }


    public void completeCarInfor(String drivelicenseurl, String carname, String carnumber) {
        Subscription subscribe = RetrofitHelper.getUserApi().completeCarInfor(drivelicenseurl, carname, carnumber).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<DriveResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(DriveResp driveResp) {
                        mView.onCompleteCarSuccess(driveResp);
                    }
                });
        addSubscribe(subscribe);
    }


    public void upHeadImages(String filePath) {
        MultipartBody.Part part = RetrofitHelper.imagePart(FileUtils.getSuffix(filePath), "file", filePath);
        RetrofitHelper.getUserApi().uploadImages(part).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<UploadResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(UploadResp uploadResp) {
                        mView.upLoadHeadImage(uploadResp.data.imageUrl);
                    }
                });
    }

    public void upIdCardImages(String filePath) {
        MultipartBody.Part part = RetrofitHelper.imagePart(FileUtils.getSuffix(filePath), "file", filePath);
        RetrofitHelper.getUserApi().uploadImages(part).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<UploadResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(UploadResp uploadResp) {
                        mView.upLoadIDCardImage(uploadResp.data.imageUrl);
                    }
                });
    }

    /**
     * 上传驾驶证照片
     * @param filePath
     */
    public void upDriveLicenseImages(String filePath) {
        MultipartBody.Part part = RetrofitHelper.imagePart(FileUtils.getSuffix(filePath), "file", filePath);
        RetrofitHelper.getUserApi().uploadImages(part).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<UploadResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(UploadResp uploadResp) {
                        mView.upDriveLicenseImage(uploadResp.data.imageUrl);
                    }
                });
    }
}


