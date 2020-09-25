package com.developers.rozan.logutil.base;

public interface BasePresenter<T extends BaseView> {

    void onAttach(T view);
    void onDetach();
}
