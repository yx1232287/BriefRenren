package com.xiangyan.status.gets;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.renren.api.connect.android.exception.RenrenException;
import com.renren.api.connect.android.users.UserInfo.HSInfo;
import com.renren.api.connect.android.users.UserInfo.HomeTownLocation;
import com.renren.api.connect.android.users.UserInfo.UniversityInfo;
import com.renren.api.connect.android.users.UserInfo.WorkInfo;

public class StatusGets {
	
	 public static final String KEY_UID = "uid";
	 public static final String KEY_STATUS_ID = "status_id";
	 public static final String KEY_TIME = "time";
	 public static final String KEY_MESSAGE = "message";
	 public static final String KEY_COMMENT_COUNT = "comment_count";
	 public static final String KEY_SOURCE_NAME = "source_name";
	 public static final String KEY_SOURCE_URL = "source_url";
	 public static final String KEY_FORWARD_MESSAGE = "forward_message";
	 public static final String KEY_ROOT_STATUS_ID = "root_status_id";
	 public static final String KEY_ROOT_MESSAGE = "root_message";
	 public static final String KEY_ROOT_UID = "root_uid";
	 public static final String KEY_ROOT_USERNAME = "root_username";
	 public static final String KEY_FORWARD_COUNT = "forward_count";
	 public static final String KEY_PLACE = "place";

	 public static class Place {
		 public static final String KEY_LATITUDE = "latitude";
		 public static final String KEY_LBS_ID = "lbs_id";
		 public static final String KEY_LOCATION = "location";
		 public static final String KEY_LONGITUDE = "longitude";
		 public static final String KEY_NAME = "name";
		 public static final String KEY_URL = "url";
		 
		 private String latitude;
		 private int lbs_id;
		 private String location;
		 private String longitude;
		 private String name;
		 private String url;
		 
		 public static ArrayList<Place> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Place> place = new ArrayList<StatusGets.Place>();
			 int size = array.length();
			 for(int i = 0; i < size; i++) {
				 Place temp = parse(array.optJSONObject(i));
				 if(temp != null) {
					 place.add(temp);
				 }
			 }
			 return place;
		 }
		 
		 public static Place parse(JSONObject object) {
			 if(object == null) {
				 return null;
			 }
			 Place place = new Place();
			 place.latitude = object.optString(KEY_LATITUDE);
			 place.lbs_id = object.optInt(KEY_LBS_ID);
			 place.location = object.optString(KEY_LOCATION);
			 place.longitude = object.optString(KEY_LONGITUDE);
			 place.name = object.optString(KEY_NAME);
			 place.url = object.optString(KEY_URL);
			 return place;
		 }

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public int getLbs_id() {
			return lbs_id;
		}

		public void setLbs_id(int lbs_id) {
			this.lbs_id = lbs_id;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		 
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
            sb.append("\t").append(KEY_LATITUDE).append(" = ").append(latitude).append("\r\n");
            sb.append("\t").append(KEY_LBS_ID).append(" = ").append(lbs_id).append("\r\n");
            sb.append("\t").append(KEY_LOCATION).append(" = ").append(location).append("\r\n");
            sb.append("\t").append(KEY_LONGITUDE).append(" = ").append(longitude).append("\r\n");
            sb.append("\t").append(KEY_NAME).append(" = ").append(name).append("\r\n");
            sb.append("\t").append(KEY_URL).append(" = ").append(url).append("\r\n");
            return sb.toString();
		}
	 }
	 
	 private int uid;
	 private int status_id;
	 private String time;
	 private String message;
	 private int comment_count;
	 private String source_name;
	 private String source_url;
	 private ArrayList<Place> place;
	 private String forward_message;
	 private int root_status_id;
	 private String root_message;
	 private int root_uid;
	 private String root_username;
	 private int forward_count;
	 
	 
	 public StatusGets parse(JSONObject object) throws RenrenException {
		 if(object == null) {
			 return null;
		 }
		 
		 uid = object.optInt(KEY_UID);
	     status_id = object.optInt(KEY_STATUS_ID);
	     time = object.optString(KEY_TIME);
	     message = object.optString(KEY_MESSAGE);
	     comment_count = object.optInt(KEY_COMMENT_COUNT);
	     source_name = object.optString(KEY_SOURCE_NAME);
	     source_url = object.optString(KEY_SOURCE_URL);
	     forward_message = object.optString(KEY_FORWARD_MESSAGE);
	     root_status_id = object.optInt(KEY_ROOT_STATUS_ID);
	     root_message = object.optString(KEY_ROOT_MESSAGE);
	     root_uid = object.optInt(KEY_ROOT_UID);
	     root_username = object.optString(KEY_ROOT_USERNAME);
	     forward_count = object.optInt(KEY_FORWARD_COUNT);
	     place = Place.parse(object.optJSONArray(KEY_PLACE));
	     
	     return this;
	 }


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public int getStatus_id() {
		return status_id;
	}


	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getComment_count() {
		return comment_count;
	}


	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}


	public String getSource_name() {
		return source_name;
	}


	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}


	public String getSource_url() {
		return source_url;
	}


	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}


	public ArrayList<Place> getPlace() {
		return place;
	}


	public void setPlace(ArrayList<Place> place) {
		this.place = place;
	}


	public String getForward_message() {
		return forward_message;
	}


	public void setForward_message(String forward_message) {
		this.forward_message = forward_message;
	}


	public int getRoot_status_id() {
		return root_status_id;
	}


	public void setRoot_status_id(int root_status_id) {
		this.root_status_id = root_status_id;
	}


	public String getRoot_message() {
		return root_message;
	}


	public void setRoot_message(String root_message) {
		this.root_message = root_message;
	}


	public int getRoot_uid() {
		return root_uid;
	}


	public void setRoot_uid(int root_uid) {
		this.root_uid = root_uid;
	}


	public String getRoot_username() {
		return root_username;
	}


	public void setRoot_username(String root_username) {
		this.root_username = root_username;
	}


	public int getForward_count() {
		return forward_count;
	}


	public void setForward_count(int forward_count) {
		this.forward_count = forward_count;
	}
	 
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
        sb.append(KEY_UID).append(" = ").append(uid).append("\r\n");
        sb.append(KEY_STATUS_ID).append(" = ").append(status_id).append("\r\n");
        sb.append(KEY_TIME).append(" = ").append(time).append("\r\n");
        sb.append(KEY_MESSAGE).append(" = ").append(message).append("\r\n");
        sb.append(KEY_COMMENT_COUNT).append(" = ").append(comment_count).append("\r\n");
        sb.append(KEY_SOURCE_NAME).append(" = ").append(source_name).append("\r\n");
        sb.append(KEY_SOURCE_URL).append(" = ").append(source_url).append("\r\n");
        sb.append(KEY_FORWARD_MESSAGE).append(" = ").append(forward_message).append("\r\n");
        sb.append(KEY_ROOT_STATUS_ID).append(" = ").append(root_status_id).append("\r\n");
        sb.append(KEY_ROOT_MESSAGE).append(" = ").append(root_message).append("\r\n");
        sb.append(KEY_ROOT_UID).append(" = ").append(root_uid).append("\r\n");
        sb.append(KEY_ROOT_USERNAME).append(" = ").append(root_username).append("\r\n");
        sb.append(KEY_FORWARD_COUNT).append(" = ").append(forward_count).append("\r\n");
        if (place != null) {
            sb.append(KEY_PLACE).append(" = ").append("\r\n");
            for (Place p : place) {
                sb.append(p.toString()).append("\r\n");
            }
        }
        
        return sb.toString();
	}
}
