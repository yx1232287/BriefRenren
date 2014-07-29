package com.xiangyan.status.gets;

import java.util.concurrent.Executor;

import android.os.Bundle;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.Util;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.exception.RenrenException;
import com.renren.api.connect.android.users.UsersGetInfoRequestParam;
import com.renren.api.connect.android.users.UsersGetInfoResponseBean;
import com.xiangyan.briefrenren.MyRenren;

public class StatusGetsHelper {
	private MyRenren myRenren;
	
	public StatusGetsHelper(MyRenren myRenren) {
		this.myRenren = myRenren;
	}
	
	public StatusGetsResponseBean getStatus (StatusGetsRequestParam param) throws RenrenException, Throwable {
		Bundle parameters = param.getParams();
		StatusGetsResponseBean statusGetsBean = null;
		try {
			String response = myRenren.requestJSON(parameters);
			if(response != null) {
				Util.checkResponse(response, Renren.RESPONSE_FORMAT_JSON);
			} else {
				Util.logger("null response");
				throw new RenrenException(RenrenError.ERROR_CODE_UNKNOWN_ERROR, "null response", "null response");
			}
			statusGetsBean = new StatusGetsResponseBean(response);
		} catch (RuntimeException re) {
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
		return statusGetsBean;
	}
	
	public void asyncGetStatus (Executor pool, final StatusGetsRequestParam param, final AbstractRequestListener<StatusGetsResponseBean> listener) {
		pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					StatusGetsResponseBean usersBean = getStatus(param);
                    if (listener != null) {
                        listener.onComplete(usersBean);
                    }
                } catch (RenrenException e) {
                	Util.logger("renren exception " + e.getMessage());
                    if (listener != null) {
                        listener.onRenrenError(new RenrenError(e.getMessage()));
                        e.printStackTrace();
                    }
                } catch (Throwable e) {
                	Util.logger("on fault " + e.getMessage());
                    if (listener != null) {
                    	listener.onFault(e);
                    }
                }
				
			}
			
		});
	}
	
}
