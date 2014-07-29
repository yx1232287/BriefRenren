package com.xiangyan.briefrenren;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.xiangyan.feed.get.FeedGetHelper;
import com.xiangyan.feed.get.FeedGetRequestParam;
import com.xiangyan.feed.get.FeedGetResponseBean;
import com.xiangyan.getLoggedInUser.GetLoggedInUserHelper;
import com.xiangyan.getLoggedInUser.GetLoggedInUserRequestParam;
import com.xiangyan.getLoggedInUser.GetLoggedInUserResponseBean;
import com.xiangyan.status.gets.StatusGetsHelper;
import com.xiangyan.status.gets.StatusGetsRequestParam;
import com.xiangyan.status.gets.StatusGetsResponseBean;


public class AsyncMyRenren extends AsyncRenren{
	
	public AsyncMyRenren(MyRenren myRenren) {
		super(myRenren);
	}
	
	public void getLoggedInUser (GetLoggedInUserRequestParam param, AbstractRequestListener<GetLoggedInUserResponseBean> listener) {
		GetLoggedInUserHelper helper = new GetLoggedInUserHelper((MyRenren)this.getRenren());
        helper.asyncGetLoggedInUser(this.getPool(), param, listener);
    }
	
	public void statusGets(StatusGetsRequestParam param, AbstractRequestListener<StatusGetsResponseBean> listener) {
		StatusGetsHelper helper = new StatusGetsHelper((MyRenren) this.getRenren());
		helper.asyncGetStatus(this.getPool(), param, listener);
	}
	
	public void feedGet(FeedGetRequestParam param, AbstractRequestListener<FeedGetResponseBean> listener) {
		FeedGetHelper helper = new FeedGetHelper((MyRenren) this.getRenren());
		helper.asyncGetFeed(this.getPool(), param, listener);
	}
}
