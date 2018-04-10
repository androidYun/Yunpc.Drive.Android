package com.lgy.drive.persenter;

import com.lgy.drive.PresneterView.AddRecordView;
import com.lgy.drive.base.RxPresenter;
import com.lgy.drive.model.http.req.RecordReq;
import com.lgy.drive.model.http.resp.RecordResp;
import com.lgy.drive.model.net.DefineSubscriber;
import com.lgy.drive.model.net.RetrofitHelper;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ${lgy} on 2018/4/308:30
 * 邮箱1343168198@qq.com
 * 描述： describe
 * 修改内容：
 */

public class AddRecordPresenter extends RxPresenter<AddRecordView> {

    @Inject
    public AddRecordPresenter() {
    }

    public void addRecord(RecordReq recordReq) {
        Subscription subscribe = RetrofitHelper.getUserApi().addRecord(recordReq).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefineSubscriber<RecordResp>(mView) {
                    @Override
                    protected boolean isShowProgress() {
                        return true;
                    }

                    @Override
                    protected void onSuccess(RecordResp recordResp) {
                        mView.addRecordSuccess();
                    }
                });
        addSubscribe(subscribe);
    }
}
