package com.developers.rozan.logutil.feature;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.rozan.logutil.R;
import com.developers.rozan.logutil.database.AllProduct;
import com.developers.rozan.logutil.database.DatabaseHelper;
import com.developers.rozan.logutil.database.Product;
import com.developers.rozan.logutil.database.ProductPrice;
import com.developers.rozan.logutil.model.ItemProduct;
import com.developers.rozan.logutil.model.ItemProductPrice;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainView, MainAdapter.OnItemClickListener,
        MainAdapter.OnQuerySearchListener, SearchView.OnQueryTextListener {

    ProgressDialog dialog;
    MainPresenter mainPresenter;
    MainAdapter mainAdapter;
    DatabaseHelper databaseHelper;
    List<AllProduct> allProducts;

    RecyclerView recyclerView;
    SearchView searchView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        databaseHelper = new DatabaseHelper(this);
        dialog = new ProgressDialog(this);
        mainPresenter = new MainPresenter();
        mainPresenter.onAttach(this);
        mainPresenter.getProduct();
    }

    @Override
    public void getProductSuccess(List<ItemProduct> product) {
        Product productSqlite = new Product();
        for (int i = 0; i < product.size(); i++) {
            productSqlite.setKodeBarang(product.get(i).getKodeBarang());
            productSqlite.setNamaBarang(product.get(i).getNamaBarang());
            productSqlite.setImage(product.get(i).getImage());
            databaseHelper.saveProduct(productSqlite);
        }
        mainPresenter.getProductPrice();
    }

    @Override
    public void getProductFailed(String localizedMessage) {
        Toast.makeText(this, "Product error : " + localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getProductPriceSuccess(List<ItemProductPrice> productPrice) {
        ProductPrice productPriceSqlite = new ProductPrice();
        for (int i = 0; i < productPrice.size(); i++) {
            productPriceSqlite.setId(productPrice.get(i).getId());
            productPriceSqlite.setKodeBarang(productPrice.get(i).getKodeBarang());
            productPriceSqlite.setNamaBarang(productPrice.get(i).getNamaBarang());
            productPriceSqlite.setHargaBarang(productPrice.get(i).getHargaBarang());
            productPriceSqlite.setCabang(productPrice.get(i).getCabang());
            databaseHelper.saveProductPrice(productPriceSqlite);
        }

        allProducts = databaseHelper.readRecord();
        initAdapter(allProducts);
    }

    @Override
    public void getProductPriceFailed(String localizedMessage) {
        Toast.makeText(this, "Product Price error : " + localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttachView() {
        mainPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        mainPresenter.onDetach();
    }

    @Override
    public void showLoading() {
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void onItemClicked(AllProduct allProduct) {

    }

    private void initView() {
        recyclerView = findViewById(R.id.rvProduct);
        searchView = findViewById(R.id.searchView);
        imageView = findViewById(R.id.icFilter);

        searchView.setOnQueryTextListener(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetDialogTheme);
                final View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.llBottom));
                bottomSheetView.findViewById(R.id.btnLetters).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Collections.sort(allProducts, new Comparator<AllProduct>() {
                            @Override
                            public int compare(AllProduct allProduct, AllProduct t1) {
                                return allProduct.getNamaBarang().compareTo(t1.getNamaBarang());
                            }
                        });
                        mainAdapter.notifyDataSetChanged();
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetView.findViewById(R.id.btnNumbers).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Collections.sort(allProducts, new Comparator<AllProduct>() {
                            @Override
                            public int compare(AllProduct allProduct, AllProduct t1) {
                                return String.valueOf(allProduct.getHargaBarang()).compareTo(String.valueOf(t1.getHargaBarang()));
                            }
                        });
                        mainAdapter.notifyDataSetChanged();
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
    }

    private void initAdapter(List<AllProduct> allProductsList) {
        mainAdapter = new MainAdapter(allProductsList);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (mainAdapter != null) {
            mainAdapter.getFilter().filter(s);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (mainAdapter != null) {
            mainAdapter.getFilter().filter(s);
        }
        return false;
    }

    @Override
    public void onNotFoundData(String key, final boolean isNotFound) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isNotFound) {
                    showErrorHandlingPage("Produk tidak ditemukan");
                }
            }
        });
    }

    private void showErrorHandlingPage(String reason) {
        Toast.makeText(this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onQuerySearch(String query) {
        searchView.setQuery(query, true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onAttachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDetachView();
    }

    public static String formatCurrencyDouble(Context context, String isValue) {
        DecimalFormat formatter;
        DecimalFormatSymbols symbols;
        String value = null;

        formatter = new DecimalFormat("#,###,###.##");
        symbols = new DecimalFormatSymbols(new Locale("ind", "in"));
        formatter.setDecimalFormatSymbols(symbols);
        value = formatter.format(Double.valueOf(isValue));

        return value;
    }
}