package com.xiangyan.feed.get;

import java.util.concurrent.Executor;

import android.os.Bundle;

import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.Util;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.exception.RenrenException;
import com.xiangyan.briefrenren.MyRenren;
import com.xiangyan.status.gets.StatusGetsRequestParam;
import com.xiangyan.status.gets.StatusGetsResponseBean;

public class FeedGetHelper {
	private MyRenren myRenren;
	
	public FeedGetHelper(MyRenren myRenren) {
		this.myRenren = myRenren;
	}
	
	public FeedGetResponseBean getFeed (FeedGetRequestParam param) throws RenrenException, Throwable {
		Bundle parameters = param.getParams();
		FeedGetResponseBean feedGetBean = null;
		try {
			String response = myRenren.requestJSON(parameters);
			if(response != null) {
				Util.checkResponse(response, Renren.RESPONSE_FORMAT_JSON);
			} else {
				Util.logger("null response");
				throw new RenrenException(RenrenError.ERROR_CODE_UNKNOWN_ERROR, "null response", "null response");
			}
			feedGetBean = new FeedGetResponseBean(response);
		} catch (RuntimeException re) {
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
		return feedGetBean;
	}
	
	public void asyncGetFeed (Executor pool, final FeedGetRequestParam param, final AbstractRequestListener<FeedGetResponseBean> listener) {
		pool.execute(new Runnable() {

			@Override
			public void run() {
				try {
					FeedGetResponseBean feedBean = getFeed(param);
                    if (listener != null) {
                        listener.onComplete(feedBean);
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
