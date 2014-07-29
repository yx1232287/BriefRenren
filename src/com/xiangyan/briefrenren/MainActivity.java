package com.xiangyan.briefrenren;

import java.lang.reflect.Method;

import com.example.briefrenren.R;
import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.users.UsersGetInfoRequestParam;
import com.renren.api.connect.android.users.UsersGetInfoResponseBean;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.xiangyan.getLoggedInUser.GetLoggedInUserRequestParam;
import com.xiangyan.getLoggedInUser.GetLoggedInUserResponseBean;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String API_KEY = "d2031f23463b4fd08e3a08741c021e3f";
	
	private static final String SECRET_KEY = "b61c7b370e3d4d9190f9dd7de6a7065c";
	
	private static final String APP_ID = "232792";
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	
	private MyRenren myRenren;
	
	private Handler handler;
	
	private ProgressDialog progressDialog;
	
	private int uid;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		closeStrict();
		myRenren = new MyRenren(API_KEY, SECRET_KEY, APP_ID, this);
		handler = new Handler();
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		if(myRenren.isSessionKeyValid()) {
		    this.gotoMainPage();
		}else {
			gotoLogin();
		}
		
		
	}

	/**
	 * Backward-compatible version of {@link ActionBar#getThemedContext()} that
	 * simply returns the {@link android.app.Activity} if
	 * <code>getThemedContext</code> is unavailable.
	 */
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		return true;
	}
	
	@Override  
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {  
		case R.id.operation:
			myRenren.logout(MainActivity.this);
			this.gotoLogin();
			return true;
		default:
			return super.onOptionsItemSelected(item); 
		}
		
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// When the given dropdown item is selected, show its contents in the
		// container view.
//		Fragment fragment = new DummySectionFragment();
//		Bundle args = new Bundle();
//		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//		fragment.setArguments(args);
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.container, fragment).commit();
		switch(position) {
		case 0:
			this.gotoPrimaryStatus();
			break;
		case 1:
			this.gotoHotStatus();
			break;
		case 2:
			this.gotoMyStatus();
			break;
		case 3:
//			PersonInfoFragment personInfoFragment = new PersonInfoFragment();
//			personInfoFragment.setUid(this.doGetLoggedInUser());
//			//Bundle args = new Bundle();
//			//args.putInt(PersonInfoFragment.ARG_PERSON_ID, position + 1);
//			//fragment.setArguments(args);
//			getSupportFragmentManager().beginTransaction()
//				.replace(R.id.container, personInfoFragment).commit();
			this.doGetLoggedInUser();
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}
	
	private void gotoLogin() {
		setContentView(R.layout.activity_initial);
		ActionBar bar = getActionBar(); 
		bar.hide(); 
		login();
	}
	
	private void gotoMainPage() {
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar(); 
		bar.show();
		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(getActionBarThemedContextCompat(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, new String[] {
								getString(R.string.origin_status),
								getString(R.string.hot_shared),
								getString(R.string.my_status),
								getString(R.string.person_info) }), this);
//		this.gotoOriginStatus();
	}

	
	private void login() {
		final RenrenAuthListener listener = new RenrenAuthListener() {

			@Override
			public void onComplete(Bundle values) {
				Log.d("test",values.toString());
//				Intent intent = new Intent();
//				intent.setClass(InitialActivity.this, MainActivity.class);
//			    startActivity(intent);
				gotoMainPage();
			}

			@Override
			public void onRenrenAuthError(
					RenrenAuthError renrenAuthError) {
//				handler.post(new Runnable() {
//					
//					@Override
//					public void run() {
//						Toast.makeText(InitialActivity.this, 
//								InitialActivity.this.getString(R.string.auth_failed), 
//								Toast.LENGTH_SHORT).show();
//					}
//				});
				myRenren.logout(MainActivity.this);
				login();
			}

			@Override
			public void onCancelLogin() {
			}

			@Override
			public void onCancelAuth(Bundle values) {
			}
			
		};
		String[] permission = {"read_user_status", "read_user_feed"};
		myRenren.authorize(MainActivity.this, permission, listener);
	}
	
	protected void showProgress(String title, String message) {
		progressDialog = ProgressDialog.show(this, title, message);
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
	
	private void doGetLoggedInUser() {
		AsyncMyRenren asyncMyRenren = new AsyncMyRenren(myRenren);
		showProgress("请稍等", "读取中...");
		GetLoggedInUserRequestParam param = new GetLoggedInUserRequestParam();
		AbstractRequestListener<GetLoggedInUserResponseBean> listener = new AbstractRequestListener<GetLoggedInUserResponseBean>() {

			@Override
			public void onComplete(final GetLoggedInUserResponseBean bean) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						PersonInfoFragment personInfoFragment = new PersonInfoFragment();
						personInfoFragment.setUid(Integer.parseInt(bean.toString()));
						personInfoFragment.setRenren(myRenren);
						personInfoFragment.setMainActivity(MainActivity.this);
						getSupportFragmentManager().beginTransaction()
							.replace(R.id.container, personInfoFragment).commit();
						dismissProgress();
					}
				});
			}

			@Override
			public void onRenrenError(final RenrenError renrenError) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast toast=Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT);  
						toast.show(); 
						dismissProgress();
					}
				});
			}

			@Override
			public void onFault(final Throwable fault) {
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						Toast toast=Toast.makeText(getApplicationContext(), "连接失败，可能是网络故障", Toast.LENGTH_SHORT);  
						toast.show(); 
						dismissProgress();
					}
				});
			}
		};
		asyncMyRenren.getLoggedInUser(param, listener);
	}
	
	public void gotoMyStatus() {
		MyStatusFragment myStatus = new MyStatusFragment();
		myStatus.setMainActivity(this);
		myStatus.setMyRenren(myRenren);
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, myStatus).commit();
	}
	
	public void gotoPrimaryStatus() {
		PrimaryStatusFragment primaryStatus = new PrimaryStatusFragment();
		primaryStatus.setMainActivity(this);
		primaryStatus.setMyRenren(myRenren);
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, primaryStatus).commit();
	}
	
	public void gotoHotStatus() {
		HotStatusFragment hotStatus = new HotStatusFragment();
		hotStatus.setMainActivity(this);
		hotStatus.setMyRenren(myRenren);
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, hotStatus).commit();
	}
	
	
	
	private void closeStrict() {
		try {
			Class strictModeClass=Class.forName("android.os.StrictMode");
			Class strictModeThreadPolicyClass=Class.forName("android.os.StrictMode$ThreadPolicy");
			Object laxPolicy = strictModeThreadPolicyClass.getField("LAX").get(null);
			Method method_setThreadPolicy = strictModeClass.getMethod(
					"setThreadPolicy", strictModeThreadPolicyClass );
			method_setThreadPolicy.invoke(null,laxPolicy);
		} catch (Exception e) {
			Log.d("Strict Mode","Error blocking strict mode.");
		}
	}
}
