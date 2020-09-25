package com.developers.rozan.logutil.feature.MainActivity;

import com.developers.rozan.logutil.base.BasePresenter;
import com.developers.rozan.logutil.model.ResponseProduct;
import com.developers.rozan.logutil.model.ResponseProductPrice;
import com.developers.rozan.logutil.network.InitRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements BasePresenter<MainView> {

    private MainView mainView;

    @Override
    public void onAttach(MainView view) {
        mainView = view;
    }

    @Override
    public void onDetach() {
        mainView = null;
    }

    public void getProduct() {
        mainView.showLoading();

        Call<ResponseProduct> call = InitRetrofit.getApi().getProduct();
        call.enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                ResponseProduct responseProduct = response.body();

                if (responseProduct.getStatusCode().equals("ERR-00-000")) {
                    mainView.getProductSuccess(responseProduct.getValue());
                } else {
                    mainView.getProductFailed(response.message());
                }
                mainView.hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                mainView.getProductFailed(t.getMessage());
                mainView.hideLoading();
            }
        });
    }

    public void getProductPrice() {
        mainView.showLoading();

        Call<ResponseProductPrice> call = InitRetrofit.getApi().getProductPrice();
        call.enqueue(new Callback<ResponseProductPrice>() {
            @Override
            public void onResponse(Call<ResponseProductPrice> call, Response<ResponseProductPrice> response) {
                ResponseProductPrice responseProductPrice = response.body();

                if (responseProductPrice.getStatusCode().equals("ERR-00-000")) {
                    mainView.getProductPriceSuccess(responseProductPrice.getValue());
                } else {
                    mainView.getProductPriceFailed(response.message());
                }
                mainView.hideLoading();
            }

            @Override
            public void onFailure(Call<ResponseProductPrice> call, Throwable t) {
                mainView.getProductPriceFailed(t.getMessage());
                mainView.hideLoading();
            }
        });
    }

}
