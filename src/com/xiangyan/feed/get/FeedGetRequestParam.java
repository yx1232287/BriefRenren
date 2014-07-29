package com.xiangyan.feed.get;

import android.os.Bundle;

import com.renren.api.connect.android.common.RequestParam;
import com.renren.api.connect.android.exception.RenrenException;

public class FeedGetRequestParam extends RequestParam{
	
	public static final String METHOD = "feed.get";
	public static final String TYPE_STATUS = "10";
	
	private String type;
	private int page;
	private int count;
	private int uid;
	
	

	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public int getPage() {
		return page;
	}




	public void setPage(int page) {
		this.page = page;
	}




	public int getCount() {
		return count;
	}




	public void setCount(int count) {
		this.count = count;
	}




	public int getUid() {
		return uid;
	}




	public void setUid(int uid) {
		this.uid = uid;
	}




	@Override
	public Bundle getParams() throws RenrenException {
		Bundle parameters = new Bundle ();
        parameters.putString("method", METHOD);
        if(type != null && type != "") {
        	parameters.putString("type", type);
        } else {
        	parameters.putString("type", TYPE_STATUS);
        }
        if (page != 0) {
            parameters.putString("page", page+"");
        }
        if (count != 0) {
            parameters.putString("count", count+"");
        }
		if (uid != 0) {
	        parameters.putString("uid", uid+"");
        }
        return parameters;
	}

}
