package com.xiangyan.briefrenren;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.content.Context;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.xiangyan.getLoggedInUser.GetLoggedInUserHelper;
import com.xiangyan.getLoggedInUser.GetLoggedInUserRequestParam;
import com.xiangyan.getLoggedInUser.GetLoggedInUserResponseBean;

public class MyRenren extends Renren{
	


	public MyRenren(String apiKey, String secret, String appId, Context context) {
		super(apiKey, secret, appId, context);
	}
	
	

}
