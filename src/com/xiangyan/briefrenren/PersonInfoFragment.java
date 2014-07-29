package com.xiangyan.briefrenren;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.briefrenren.R;
import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.users.UserInfo;
import com.renren.api.connect.android.users.UsersGetInfoRequestParam;
import com.renren.api.connect.android.users.UsersGetInfoResponseBean;
import com.xiangyan.feed.get.FeedGetRequestParam;
import com.xiangyan.feed.get.FeedGetResponseBean;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonInfoFragment extends Fragment{
	
	public static final String ARG_PERSON_ID = "person_id";
	private int uid;
	
	private View rootView;
	private Renren renren;
	private MainActivity mainActivity;
	private ProgressDialog progressDialog;
	private ArrayList<UserInfo> usersInfo;
	
	private TextView person_info_id;
	private TextView person_info_name;
	private ImageView person_info_avatar;
	private TextView person_info_zidou;
	private TextView person_info_star;
	
	public void setUid (int uid) {
		this.uid = uid;
		
	}
	
	public void setRenren(Renren renren) {
		this.renren = renren;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_person_info,
				container, false);
		person_info_id = (TextView) rootView.findViewById(R.id.person_info_id);
		person_info_name = (TextView) rootView.findViewById(R.id.person_info_name);
		person_info_avatar = (ImageView) rootView.findViewById(R.id.person_info_avatar);
		person_info_zidou = (TextView) rootView.findViewById(R.id.person_info_zidou);
		person_info_star = (TextView) rootView.findViewById(R.id.person_info_star);
		this.init();
		return rootView;
	}
	
	private void init() {
		AsyncRenren asyncRenren = new AsyncRenren(renren);
		String[] uids = new String[1];
		uids[0] = uid+"";
		showProgress("请稍等", "读取中...");
		UsersGetInfoRequestParam param = new UsersGetInfoRequestParam(uids);
		AbstractRequestListener<UsersGetInfoResponseBean> listener = new AbstractRequestListener<UsersGetInfoResponseBean>() {

			@Override
			public void onComplete(final UsersGetInfoResponseBean bean) {
				mainActivity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						dismissProgress();
						usersInfo = bean.getUsersInfo();
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
		asyncRenren.getUsersInfo(param, listener);
	}
	
	private void showInfo() {
		for(int i = 0; i < usersInfo.size(); i++) {
			this.person_info_id.setText("用户ID："+this.uid);
			this.person_info_name.setText("姓名："+this.usersInfo.get(i).getName());
			if(this.usersInfo.get(i).getZidou() == 1) {
				this.person_info_zidou.setText("VIP用户");
			} else {
				this.person_info_zidou.setText("非VIP用户");
			}
			this.person_info_avatar.setImageBitmap(this.returnBitMap(this.usersInfo.get(i).getHeadurl()));
			if(this.usersInfo.get(i).getStar() == 1) {
				this.person_info_star.setText("星级用户");
			} else {
				this.person_info_star.setText("非星级用户");
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
	
	public Bitmap returnBitMap(String url){  
        URL myFileUrl = null;    
        Bitmap bitmap = null;   
        try {    
            myFileUrl = new URL(url);    
        } catch (MalformedURLException e) {    
            e.printStackTrace();    
        }    
        try {    
            HttpURLConnection conn = (HttpURLConnection) myFileUrl    
              .openConnection();    
            conn.setDoInput(true);    
            conn.connect();    
            InputStream is = conn.getInputStream();    
            bitmap = BitmapFactory.decodeStream(is);    
            is.close();    
        } catch (IOException e) {    
              e.printStackTrace();    
        }    
              return bitmap;    
    }
}
