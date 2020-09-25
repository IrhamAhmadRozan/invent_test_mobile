package com.developers.rozan.logutil.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseProduct {

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("status_message_ind")
	private String statusMessageInd;

	@SerializedName("status_message_eng")
	private String statusMessageEng;

	@SerializedName("value")
	private List<ItemProduct> value;

	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}

	public String getStatusCode(){
		return statusCode;
	}

	public void setStatusMessageInd(String statusMessageInd){
		this.statusMessageInd = statusMessageInd;
	}

	public String getStatusMessageInd(){
		return statusMessageInd;
	}

	public void setStatusMessageEng(String statusMessageEng){
		this.statusMessageEng = statusMessageEng;
	}

	public String getStatusMessageEng(){
		return statusMessageEng;
	}

	public void setValue(List<ItemProduct> value){
		this.value = value;
	}

	public List<ItemProduct> getValue(){
		return value;
	}
}