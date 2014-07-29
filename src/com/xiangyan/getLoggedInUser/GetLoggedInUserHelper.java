package com.xiangyan.getLoggedInUser;

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

public class GetLoggedInUserHelper {
	/**
     * renren对象
     */
    private MyRenren myRenren;
    
    public GetLoggedInUserHelper (MyRenren myRenren) {
        this.myRenren = myRenren;
    }
    
    /**
	 * 同步调用users.getLoggedInUser接口<br>
	 * 
	 * @param param
	 *            请求对象
	 * @return 返回{@link GetLoggedInUserResponseBean}对象
	 * @throws RenrenException
	 */
    public GetLoggedInUserResponseBean getLoggedInUser (GetLoggedInUserRequestParam param) throws RenrenException, Throwable {
        
        Bundle parameters = param.getParams();
        GetLoggedInUserResponseBean usersBean = null;
        try {
            String response = myRenren.requestJSON(parameters);
			if (response != null) {
				Util.checkResponse(response, Renren.RESPONSE_FORMAT_JSON);

			} else {
				Util.logger("null response");
				throw new RenrenException(RenrenError.ERROR_CODE_UNKNOWN_ERROR, "null response", "null response");
			}
            usersBean = new GetLoggedInUserResponseBean(response);
        } catch (RuntimeException re) {
			Util.logger("runtime exception " + re.getMessage());
			throw new Throwable(re);
		}
        return usersBean;
    }
    
    
    /**
	 * 异步方法调用users.getLoggedInUser接口<br>
	 * 
	 * @param pool
	 *            线程池
	 * @param param
	 *            请求对象
	 * @param listener
	 *            回调
	 */
    public void asyncGetLoggedInUser (Executor pool, final GetLoggedInUserRequestParam param, final AbstractRequestListener<GetLoggedInUserResponseBean> listener) {
        
        pool.execute(new Runnable() {
            
            @Override
            public void run() {
           
                try {
                	GetLoggedInUserResponseBean usersBean = getLoggedInUser(param);
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
