package com.xiangyan.getLoggedInUser;

import android.os.Bundle;

import com.renren.api.connect.android.common.RequestParam;
import com.renren.api.connect.android.exception.RenrenException;

public class GetLoggedInUserRequestParam extends RequestParam{
	
	private static final String METHOD = "users.getLoggedInUser";

	
	@Override
	public Bundle getParams() throws RenrenException {
		Bundle parameters = new Bundle ();
        parameters.putString("method", METHOD);
        return parameters;
	}

}
