package com.lgy.drive.base;

public interface BaseView {
    void showError(String msg);

    void showLoading();


    void hideLoading();

    void tokenLose();//Token失效
}
