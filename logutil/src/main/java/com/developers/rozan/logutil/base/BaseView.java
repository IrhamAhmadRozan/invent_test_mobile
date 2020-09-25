package com.developers.rozan.logutil.base;

public interface BaseView {
    void onAttachView();
    void onDetachView();

    void showLoading();
    void hideLoading();
}
