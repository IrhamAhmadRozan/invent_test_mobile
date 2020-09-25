package com.developers.rozan.logutil.feature.MainActivity;

import com.developers.rozan.logutil.base.BaseView;
import com.developers.rozan.logutil.model.ItemProduct;
import com.developers.rozan.logutil.model.ItemProductPrice;

import java.util.List;

public interface MainView extends BaseView {

    void getProductSuccess(List<ItemProduct> product);
    void getProductFailed(String localizedMessage);

    void getProductPriceSuccess(List<ItemProductPrice> productPrice);
    void getProductPriceFailed(String localizedMessage);
}
