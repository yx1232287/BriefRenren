package com.xiangyan.getLoggedInUser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.renren.api.connect.android.common.ResponseBean;

public class GetLoggedInUserResponseBean extends ResponseBean {
	
	private static final String KEY_UID = "uid";
	
	public GetLoggedInUserResponseBean(String response) {
		super(response);
		if (response == null) {
            return;
        }
    	
        try {
            JSONObject json = new JSONObject(response);
                loggedInUserID = json.optInt(KEY_UID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}

	private int loggedInUserID;

	public int getLoggedInUserID() {
		return loggedInUserID;
	}
	
	@Override
    public String toString () {
        return this.loggedInUserID+"";
    }
}
