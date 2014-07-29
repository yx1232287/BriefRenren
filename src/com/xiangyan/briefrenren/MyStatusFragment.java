package com.xiangyan.briefrenren;

import java.util.ArrayList;
import java.util.Vector;

import com.example.briefrenren.R;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.xiangyan.getLoggedInUser.GetLoggedInUserRequestParam;
import com.xiangyan.getLoggedInUser.GetLoggedInUserResponseBean;
import com.xiangyan.status.gets.StatusGets;
import com.xiangyan.status.gets.StatusGetsRequestParam;
import com.xiangyan.status.gets.StatusGetsResponseBean;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyStatusFragment extends Fragment {
	
	private View rootView;
	private MyRenren myRenren;
	private LinearLayout main_layout;
	private Vector<StatusLayout> statusLayouts;
	private MainActivity mainActivity;
	
	private ProgressDialog progressDialog;
	private ArrayList<StatusGets> statusGets;
	
	
	
	public void setMyRenren(MyRenren myRenren) {
		this.myRenren = myRenren;
	}
	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_my_status,
				container, false);
		this.rootView = rootView;
		this.main_layout = (LinearLayout) rootView.findViewById(R.id.my_status_main_layout);
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
		StatusGetsRequestParam param = new StatusGetsRequestParam();
		AbstractRequestListener<StatusGetsResponseBean> listener = new AbstractRequestListener<StatusGetsResponseBean>() {

			@Override
			public void onComplete(final StatusGetsResponseBean bean) {
				mainActivity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
//						PersonInfoFragment personInfoFragment = new PersonInfoFragment();
//						personInfoFragment.setUid(Integer.parseInt(bean.toString()));
//						getSupportFragmentManager().beginTransaction()
//							.replace(R.id.container, personInfoFragment).commit();
						dismissProgress();
						statusGets = bean.getStatusGets();
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
		asyncMyRenren.statusGets(param, listener);
	}
	
	private void showInfo() {
		for(int i = 0; i < statusGets.size(); i++) {
			StatusLayout sl = new StatusLayout(mainActivity, false);
			sl.setMessage_view_text(statusGets.get(i).getMessage());
			sl.setTime_view_text(statusGets.get(i).getTime());
			main_layout.addView(sl);
			statusLayouts.add(sl);
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
