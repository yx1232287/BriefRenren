package com.xiangyan.feed.get;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.renren.api.connect.android.common.ResponseBean;
import com.renren.api.connect.android.exception.RenrenException;
import com.xiangyan.status.gets.StatusGets;

public class FeedGetResponseBean extends ResponseBean{

	public FeedGetResponseBean(String response) {
		super(response);
		if (response == null) {
            return;
        }
    	
        try {
            JSONArray array = new JSONArray(response);
            if (array != null) {
            	feedGet = new ArrayList<FeedGet>();
                int size = array.length();
                for (int i = 0; i < size; i++) {
                	FeedGet feed = new FeedGet();
                	feed.parse(array.optJSONObject(i));
                    if (feed != null) {
                        feedGet.add(feed);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RenrenException e) { 
			e.printStackTrace();
		}
	}

	private ArrayList<FeedGet> feedGet;

	public ArrayList<FeedGet> getFeedGet() {
		return feedGet;
	}

	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (feedGet != null) {
            for (FeedGet feed : feedGet) {
                sb.append(feed.toString()).append("\r\n");
            }
        }
        return sb.toString();
    }
}
