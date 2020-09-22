package com.developers.rozan.inventtestmobile.base;

public interface BaseView {
    void onAttachView();
    void onDetachView();

    void showLoading();
    void hideLoading();
}
