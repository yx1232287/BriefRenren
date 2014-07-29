package com.xiangyan.briefrenren;

import java.util.ArrayList;
import java.util.Vector;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.briefrenren.R;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.xiangyan.feed.get.FeedGet;
import com.xiangyan.feed.get.FeedGetRequestParam;
import com.xiangyan.feed.get.FeedGetResponseBean;

public class HotStatusFragment extends Fragment{
	private View rootView;
	private MyRenren myRenren;
	private LinearLayout main_layout;
	private Vector<StatusLayout> statusLayouts;
	private MainActivity mainActivity;
	
	private ProgressDialog progressDialog;
	private ArrayList<FeedGet> feedGet;
	
	
	
	public void setMyRenren(MyRenren myRenren) {
		this.myRenren = myRenren;
	}
	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hot_status,
				container, false);
		this.rootView = rootView;
		this.main_layout = (LinearLayout) rootView.findViewById(R.id.hot_status_main_layout);
		this.statusLayouts = new Vector<StatusLayout>();
		this.init();
		return rootView;
	}
	
	/**
	 * 异步获取数据
	 */
	private void init() {
		AsyncMyRenren asyncMyRenren = new AsyncMyRenren(myRenren);
		showProgress("请稍等", "读取中...");
		FeedGetRequestParam param = new FeedGetRequestParam();
		AbstractRequestListener<FeedGetResponseBean> listener = new AbstractRequestListener<FeedGetResponseBean>() {

			@Override
			public void onComplete(final FeedGetResponseBean bean) {
				mainActivity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						dismissProgress();
						feedGet = bean.getFeedGet();
						showInfo();
					}
				});
			}

			@Override
			public void onRenrenError(final RenrenError renrenError) {
				mainActivity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast toast=Toast.makeText(mainActivity.getApplicationContext(), "连接失败", Toast.LENGTH_SHORT);  
						toast.show(); 
						dismissProgress();
					}
				});
			}

			@Override
			public void onFault(final Throwable fault) {
				mainActivity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast toast=Toast.makeText(mainActivity.getApplicationContext(), "连接失败，可能是网络故障", Toast.LENGTH_SHORT);  
						toast.show(); 
						dismissProgress();
					}
				});
			}
		};
		asyncMyRenren.feedGet(param, listener);
	}
	
	private void showInfo() {
		for(int i = 0; i < feedGet.size(); i++) {
			if(feedGet.get(i).getAttachment().toString().equals("[]")) {
				continue;
			}else if(feedGet.get(i).getPrefix().split("转自").length > 3) {
				StatusLayout sl = new StatusLayout(mainActivity, true);
				sl.setMessage_view_text(feedGet.get(i).getAttachment().get(0).getContent());
				sl.setName_view_text(feedGet.get(i).getName());
				sl.setAvatar_url(feedGet.get(i).getHeadurl());
				sl.setShare_view_text(feedGet.get(i).getPrefix());
				sl.setTime_view_text(feedGet.get(i).getUpdate_time());
				main_layout.addView(sl);
				statusLayouts.add(sl);
			}
		}
	}
	
	protected void showProgress(String title, String message) {
		progressDialog = ProgressDialog.show(this.mainActivity, title, message);
	}
	
	/**
	 * 取消等待框
	 */
	protected void dismissProgress() {
		if (progressDialog != null) {
			try {
				progressDialog.dismiss();
			} catch (Exception e) {

			}
		}
	}
}
