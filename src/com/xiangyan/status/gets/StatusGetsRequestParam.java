package com.xiangyan.status.gets;

import android.os.Bundle;

import com.renren.api.connect.android.common.RequestParam;
import com.renren.api.connect.android.exception.RenrenException;
import com.renren.api.connect.android.users.UserInfo;

public class StatusGetsRequestParam extends RequestParam{
	
	private static final String METHOD = "status.gets";
	
	private int page;
	private int count;
	private int uid;
	
	

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
