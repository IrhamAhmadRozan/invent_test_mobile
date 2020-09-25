package com.developers.rozan.logutil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "invent_test_mobile";

    private static final String TABLE_PRODUCT = "product";
    private static final String KODE_PRODUCT = "kode_barang";
    private static final String NAMA_PRODUCT = "nama_barang";
    private static final String IMAGE_PRODUCT = "image";
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "
            + TABLE_PRODUCT + "(" + KODE_PRODUCT + " TEXT," + NAMA_PRODUCT
            + " TEXT," + IMAGE_PRODUCT + " TEXT" + ")";

    private static final String TABLE_PRODUCT_PRICE = "product_price";
    private static final String ID_PRODUCT_PRICE = "id";
    private static final String KODE_PRODUCT_PRICE = "kode_barang";
    private static final String NAMA_PRODUCT_PRICE = "nama_barang";
    private static final String HARGA_PRODUCT_PRICE = "harga_barang";
    private static final String CABANG_PRODUCT_PRICE = "cabang";
    private static final String CREATE_TABLE_PRODUCT_PRICE = "CREATE TABLE "
            + TABLE_PRODUCT_PRICE + "(" + ID_PRODUCT_PRICE + " TEXT," + KODE_PRODUCT_PRICE
            + " TEXT," + NAMA_PRODUCT_PRICE + " TEXT," + HARGA_PRODUCT_PRICE + " INTEGER," + CABANG_PRODUCT_PRICE
            + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_PRODUCT_PRICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_PRICE);
        onCreate(db);
    }

    public long saveProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KODE_PRODUCT, product.getKodeBarang());
        values.put(NAMA_PRODUCT, product.getNamaBarang());
        values.put(IMAGE_PRODUCT, product.getImage());

        long id = db.insert(TABLE_PRODUCT, null, values);
        return id;
    }

    public long saveProductPrice(ProductPrice productPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_PRODUCT_PRICE, productPrice.getId());
        values.put(KODE_PRODUCT_PRICE, productPrice.getKodeBarang());
        values.put(NAMA_PRODUCT_PRICE, productPrice.getNamaBarang());
        values.put(HARGA_PRODUCT_PRICE, productPrice.getHargaBarang());
        values.put(CABANG_PRODUCT_PRICE, productPrice.getCabang());

        long id = db.insert(TABLE_PRODUCT_PRICE, null, values);
        return id;
    }

    List<AllProduct> allRecords = new ArrayList<>();

    public List<AllProduct> readRecord() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT a.kode_barang, a.image, b.id, b.nama_barang, b.harga_barang, b.cabang " +
                "FROM product a INNER JOIN product_price b on a.kode_barang = b.kode_barang";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                AllProduct allProduct = new AllProduct();
                allProduct.setId(cursor.getString(cursor.getColumnIndex(ID_PRODUCT_PRICE)));
                allProduct.setKodeBarang(cursor.getString(cursor.getColumnIndex(KODE_PRODUCT)));
                allProduct.setNamaBarang(cursor.getString(cursor.getColumnIndex(NAMA_PRODUCT_PRICE)));
                allProduct.setHargaBarang(cursor.getDouble(cursor.getColumnIndex(HARGA_PRODUCT_PRICE)));
                allProduct.setCabang(cursor.getString(cursor.getColumnIndex(CABANG_PRODUCT_PRICE)));
                allProduct.setImage(cursor.getString(cursor.getColumnIndex(IMAGE_PRODUCT)));

                allRecords.add(allProduct);
            } while (cursor.moveToNext());
        }
        return allRecords;
    }
}
