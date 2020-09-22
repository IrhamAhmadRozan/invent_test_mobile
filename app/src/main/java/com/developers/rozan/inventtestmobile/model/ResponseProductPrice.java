package com.developers.rozan.inventtestmobile.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProductPrice{

	@SerializedName("status_code")
	private String statusCode;

	@SerializedName("status_message_ind")
	private String statusMessageInd;

	@SerializedName("status_message_eng")
	private String statusMessageEng;

	@SerializedName("value")
	private List<ItemProductPrice> value;

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

	public void setValue(List<ItemProductPrice> value){
		this.value = value;
	}

	public List<ItemProductPrice> getValue(){
		return value;
	}
}