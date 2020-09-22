package com.developers.rozan.inventtestmobile.model;

import com.google.gson.annotations.SerializedName;

public class ItemProductPrice {

	@SerializedName("cabang")
	private String cabang;

	@SerializedName("kode_barang")
	private String kodeBarang;

	@SerializedName("nama_barang")
	private String namaBarang;

	@SerializedName("harga_barang")
	private double hargaBarang;

	@SerializedName("id")
	private String id;

	public void setCabang(String cabang){
		this.cabang = cabang;
	}

	public String getCabang(){
		return cabang;
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

	public void setHargaBarang(double hargaBarang){
		this.hargaBarang = hargaBarang;
	}

	public double getHargaBarang(){
		return hargaBarang;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}