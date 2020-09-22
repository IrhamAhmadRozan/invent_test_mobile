package com.developers.rozan.inventtestmobile.model;

import com.google.gson.annotations.SerializedName;

public class ItemProduct {

	@SerializedName("image")
	private String image;

	@SerializedName("kode_barang")
	private String kodeBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setKodeBarang(String kodeBarang){
		this.kodeBarang = kodeBarang;
	}

	public String getKodeBarang(){
		return kodeBarang;
	}

	public void setNamaBarang(String namaBarang){
		this.namaBarang = namaBarang;
	}

	public String getNamaBarang(){
		return namaBarang;
	}
}