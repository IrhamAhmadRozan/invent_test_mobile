package com.developers.rozan.inventtestmobile.base;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T view);
    void onDetach();
}
