package com.xiangyan.status.gets;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.renren.api.connect.android.common.ResponseBean;
import com.renren.api.connect.android.exception.RenrenException;
import com.renren.api.connect.android.users.UserInfo;

public class StatusGetsResponseBean extends ResponseBean{

	/**
	 * 构造UsersGetInfoResponseBean对象
	 * @param response 返回的数据 json格式
	 * @throws RenrenException
	 */
    public StatusGetsResponseBean (String response) {
    	super(response);
    	if (response == null) {
            return;
        }
    	
        try {
            JSONArray array = new JSONArray(response);
            if (array != null) {
            	statusGets = new ArrayList<StatusGets>();
                int size = array.length();
                for (int i = 0; i < size; i++) {
                	StatusGets status = new StatusGets();
                    status.parse(array.optJSONObject(i));
                    if (status != null) {
                        statusGets.add(status);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (RenrenException e) { 
			e.printStackTrace();
		}
	}

	/**
     * user数组
     */
    private ArrayList<StatusGets> statusGets;

    
    
    public ArrayList<StatusGets> getStatusGets() {
		return statusGets;
	}



	@Override
    public String toString () {
        StringBuffer sb = new StringBuffer();
        if (statusGets != null) {
            for (StatusGets status : statusGets) {
                sb.append(status.toString()).append("\r\n");
            }
        }
        return sb.toString();
    }
}
