package com.developers.rozan.logutil.network;

import com.developers.rozan.logutil.model.ResponseProduct;
import com.developers.rozan.logutil.model.ResponseProductPrice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("get_m_product")
    Call<ResponseProduct> getProduct();

    @GET("get_product_price")
    Call<ResponseProductPrice> getProductPrice();
}
