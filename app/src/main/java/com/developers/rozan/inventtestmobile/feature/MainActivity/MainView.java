package com.developers.rozan.inventtestmobile.feature.MainActivity;

import com.developers.rozan.inventtestmobile.base.BaseView;
import com.developers.rozan.inventtestmobile.model.ItemProduct;
import com.developers.rozan.inventtestmobile.model.ItemProductPrice;

import java.util.List;

public interface MainView extends BaseView {

    void getProductSuccess(List<ItemProduct> product);
    void getProductFailed(String localizedMessage);

    void getProductPriceSuccess(List<ItemProductPrice> productPrice);
    void getProductPriceFailed(String localizedMessage);
}
