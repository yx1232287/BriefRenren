package com.xiangyan.briefrenren;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StatusLayout extends LinearLayout{
	
	private LinearLayout message_layout;
	private RelativeLayout option_layout;
	private LinearLayout head_layout;
	private LinearLayout content_layout;
	
	private ImageView avatar_view;
	private TextView name_view;
	private TextView message_view;
	private TextView share_view;
	private TextView time_view;
	
	private Button share_button;
	private Button comment_button;
	
	private boolean hasPrefix;

	public StatusLayout(Context context, boolean hasPrefix) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
		llp.setMargins(10, 5, 10, 5);
		this.setLayoutParams(llp);
		this.hasPrefix = hasPrefix;
		this.innerInit();
	}
	
	private void innerInit() {
		message_layout = new LinearLayout(this.getContext());
		message_layout.setOrientation(LinearLayout.HORIZONTAL);
		option_layout = new RelativeLayout(this.getContext());
		head_layout = new LinearLayout(this.getContext());
		head_layout.setOrientation(LinearLayout.VERTICAL);
		content_layout = new LinearLayout(this.getContext());
		content_layout.setOrientation(LinearLayout.VERTICAL);
		
		avatar_view = new ImageView(this.getContext());
		message_view = new TextView(this.getContext());
		name_view = new TextView(this.getContext());
		time_view = new TextView(this.getContext());
		time_view.setTextColor(Color.GRAY);
		time_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10f);
//		share_button = new Button(this.getContext());
//		comment_button = new Button(this.getContext());
		
		head_layout.addView(avatar_view);
		head_layout.addView(name_view);
		if(hasPrefix) {
			share_view = new TextView(this.getContext());
			//share_view.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
			share_view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f);
			content_layout.addView(share_view);
		}
		content_layout.addView(message_view);
		content_layout.addView(time_view);
		
		message_layout.addView(head_layout);
		message_layout.addView(content_layout);
//		option_layout.addView(comment_button);
//		option_layout.addView(share_button);
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		lp.setMargins(5, 5, 15, 5);
		avatar_view.setLayoutParams(lp);
		
//		RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); 
//        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE); 
//        comment_button.setLayoutParams(lp); 
//
//		lp=new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE); 
//        lp.addRule(RelativeLayout.LEFT_OF, comment_button.getId()); 
        
		this.addView(message_layout);
//		this.addView(option_layout);
		
	}

	public ImageView getAvatar_view() {
		return avatar_view;
	}

	public void setAvatar_url(String url) {
		this.avatar_view.setImageBitmap(this.returnBitMap(url));
	}

	public TextView getMessage_view() {
		return message_view;
	}

	public void setMessage_view_text(String text) {
		this.message_view.setText(text);
	}

	public Button getShare_button() {
		return share_button;
	}

	public void setShare_button(Button share_button) {
		this.share_button = share_button;
	}

	public Button getComment_button() {
		return comment_button;
	}

	public void setComment_button(Button comment_button) {
		this.comment_button = comment_button;
	}

	public TextView getName_view() {
		return name_view;
	}

	public void setName_view_text(String text) {
		this.name_view.setText(text);
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.avatar_view.setImageBitmap(bitmap);
	}

	public TextView getShare_view() {
		return share_view;
	}

	public void setShare_view_text(String text) {
		this.share_view.setText(text);
	}

	public TextView getTime_view() {
		return time_view;
	}

	public void setTime_view_text(String text) {
		this.time_view.setText(text);
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
