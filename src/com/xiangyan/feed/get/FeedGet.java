package com.xiangyan.feed.get;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.renren.api.connect.android.exception.RenrenException;
import com.xiangyan.status.gets.StatusGets;
import com.xiangyan.status.gets.StatusGets.Place;

public class FeedGet {
	public static final String KEY_POST_ID = "post_id";
	public static final String KEY_SOURCE_ID = "source_id";
	public static final String KEY_FEED_TYPE = "feed_type";
	public static final String KEY_UPDATE_TIME = "update_time";
	public static final String KEY_ACTOR_ID = "actor_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_ACTOR_TYPE = "actor_type";
	public static final String KEY_HEADURL = "headurl";
	public static final String KEY_PREFIX = "prefix";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_TITLE = "title";
	public static final String KEY_HREF = "href";
	public static final String KEY_DESCRIPTION = "description";
	
	public static final String KEY_ATTACHMENT = "attachment";
	public static final String KEY_COMMENTS = "comments";
	public static final String KEY_LIKES = "likes";
	public static final String KEY_SOURCE = "source";
	public static final String KEY_PLACE = "place";
	
	public static class Attachment {
		public static final String KEY_HREF = "href";
		public static final String KEY_MEDIA_TYPE = "media_type";
		public static final String KEY_SRC = "src";
		public static final String KEY_MEDIA_ID = "media_id";
		public static final String KEY_OWNER_ID = "owner_id";
		public static final String KEY_CONTENT = "content";
		
		private String href;
		private String media_type;
		private String src;
		private int media_id;
		private int owner_id;
		private String content;
		
		public static ArrayList<Attachment> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Attachment> attachment = new ArrayList<FeedGet.Attachment>();
			 int size = array.length();
			 for(int i = 0; i < size; i++) {
				 Attachment temp = parse(array.optJSONObject(i));
				 if(temp != null) {
					 attachment.add(temp);
				 }
			 }
			 return attachment;
		 }
		
		public static Attachment parse(JSONObject object) {
			 if(object == null) {
				 return null;
			 }
			 Attachment attachment = new Attachment();
			 attachment.href = object.optString(KEY_HREF);
			 attachment.media_type = object.optString(KEY_MEDIA_TYPE);
			 attachment.src = object.optString(KEY_SRC);
			 attachment.media_id = object.optInt(KEY_MEDIA_ID);
			 attachment.owner_id = object.optInt(KEY_OWNER_ID);
			 attachment.content = object.optString(KEY_CONTENT);
			 return attachment;
		 }
		

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String getMedia_type() {
			return media_type;
		}

		public void setMedia_type(String media_type) {
			this.media_type = media_type;
		}

		public String getSrc() {
			return src;
		}

		public void setSrc(String src) {
			this.src = src;
		}

		public int getMedia_id() {
			return media_id;
		}

		public void setMedia_id(int media_id) {
			this.media_id = media_id;
		}

		public int getOwner_id() {
			return owner_id;
		}

		public void setOwner_id(int owner_id) {
			this.owner_id = owner_id;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
            sb.append("\t").append(KEY_HREF).append(" = ").append(href).append("\r\n");
            sb.append("\t").append(KEY_MEDIA_TYPE).append(" = ").append(media_type).append("\r\n");
            sb.append("\t").append(KEY_SRC).append(" = ").append(src).append("\r\n");
            sb.append("\t").append(KEY_MEDIA_ID).append(" = ").append(media_id).append("\r\n");
            sb.append("\t").append(KEY_OWNER_ID).append(" = ").append(owner_id).append("\r\n");
            sb.append("\t").append(KEY_CONTENT).append(" = ").append(content).append("\r\n");
            return sb.toString();
		}
		
	}
	
	
	
	public static class Comments {
		public static final String KEY_COUNT = "count";
		public static final String KEY_COMMENT = "comments";
		
		public static class Comment {
			public static final String KEY_UID = "uid";
			public static final String KEY_NAME = "name";
			public static final String KEY_HEADURL = "headurl";
			public static final String KEY_TIME = "time";
			public static final String KEY_COMMENT_ID = "comment_id";
			public static final String KEY_TEXT = "text";
			
			private int uid;
			private String name;
			private String headurl;
			private String time;
			private int comment_id;
			private String text;
			
			public static ArrayList<Comment> parse(JSONArray array) {
				 if(array == null) {
					 return null;
				 }
				 ArrayList<Comment> comment = new ArrayList<FeedGet.Comments.Comment>();
				 int size = array.length();
				 for(int i = 0; i < size; i++) {
					 Comment temp = parse(array.optJSONObject(i));
					 if(temp != null) {
						 comment.add(temp);
					 }
				 }
				 return comment;
			 }
			
			public static Comment parse(JSONObject object) {
				 if(object == null) {
					 return null;
				 }
				 Comment comment = new Comment();
				 comment.uid = object.optInt(KEY_UID);
				 comment.name = object.optString(KEY_NAME);
				 comment.headurl = object.optString(KEY_HEADURL);
				 comment.time = object.optString(KEY_TIME);
				 comment.comment_id = object.optInt(KEY_COMMENT_ID);
				 comment.text = object.optString(KEY_TEXT);
				 return comment;
			 }

			public int getUid() {
				return uid;
			}

			public void setUid(int uid) {
				this.uid = uid;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public String getHeadurl() {
				return headurl;
			}

			public void setHeadurl(String headurl) {
				this.headurl = headurl;
			}

			public String getTime() {
				return time;
			}

			public void setTime(String time) {
				this.time = time;
			}

			public int getComment_id() {
				return comment_id;
			}

			public void setComment_id(int comment_id) {
				this.comment_id = comment_id;
			}

			public String getText() {
				return text;
			}

			public void setText(String text) {
				this.text = text;
			}
			
			public String toString() {
				StringBuffer sb = new StringBuffer();
	            sb.append("\t").append(KEY_UID).append(" = ").append(uid).append("\r\n");
	            sb.append("\t").append(KEY_NAME).append(" = ").append(name).append("\r\n");
	            sb.append("\t").append(KEY_HEADURL).append(" = ").append(headurl).append("\r\n");
	            sb.append("\t").append(KEY_TIME).append(" = ").append(time).append("\r\n");
	            sb.append("\t").append(KEY_COMMENT_ID).append(" = ").append(comment_id).append("\r\n");
	            sb.append("\t").append(KEY_TEXT).append(" = ").append(text).append("\r\n");
	            return sb.toString();
			}
			
		}
		
		private int count;
		private ArrayList<Comment> comment;
		
		public static ArrayList<Comments> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Comments> comments = new ArrayList<FeedGet.Comments>();
			 int size = array.length();
			 for(int i = 0; i < size; i++) {
				 Comments temp = parse(array.optJSONObject(i));
				 if(temp != null) {
					 comments.add(temp);
				 }
			 }
			 return comments;
		 }
		
		public static Comments parse(JSONObject object) {
			 if(object == null) {
				 return null;
			 }
			 Comments comments = new Comments();
			 comments.count = object.optInt(KEY_COUNT);
			 comments.comment = Comment.parse(object.optJSONArray(KEY_COMMENT));
		     
		     return comments;
		 }

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public ArrayList<Comment> getComment() {
			return comment;
		}

		public void setComment(ArrayList<Comment> comment) {
			this.comment = comment;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
	        sb.append(KEY_COUNT).append(" = ").append(count).append("\r\n");
	        if (comment != null) {
	            sb.append(KEY_COMMENT).append(" = ").append("\r\n");
	            for (Comment p : comment) {
	                sb.append(p.toString()).append("\r\n");
	            }
	        }
	        
	        return sb.toString();
		}
		
		
	}
	
	public static class Likes {
		public static final String KEY_TOTAL_COUNT = "total_count";
		public static final String KEY_FRIEND_COUNT = "friend_count";
		public static final String KEY_USER_LIKE = "user_like";
		//public static final String KEY_UID = "uid";
		
		private int total_count;
		private int friend_count;
		private int user_like;
		//private int[] uid;
		
		public static ArrayList<Likes> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Likes> likes = new ArrayList<FeedGet.Likes>();
			 int size = array.length();
			 for(int i = 0; i < size; i++) {
				 Likes temp = parse(array.optJSONObject(i));
				 if(temp != null) {
					 likes.add(temp);
				 }
			 }
			 return likes;
		 }
		
		public static Likes parse(JSONObject object) {
			 if(object == null) {
				 return null;
			 }
			 Likes likes = new Likes();
			 likes.total_count = object.optInt(KEY_TOTAL_COUNT);
			 likes.friend_count = object.optInt(KEY_FRIEND_COUNT);
			 likes.user_like = object.optInt(KEY_USER_LIKE);
			 return likes;
		 }

		public int getTotal_count() {
			return total_count;
		}

		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}

		public int getFriend_count() {
			return friend_count;
		}

		public void setFriend_count(int friend_count) {
			this.friend_count = friend_count;
		}

		public int getUser_like() {
			return user_like;
		}

		public void setUser_like(int user_like) {
			this.user_like = user_like;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
            sb.append("\t").append(KEY_TOTAL_COUNT).append(" = ").append(total_count).append("\r\n");
            sb.append("\t").append(KEY_FRIEND_COUNT).append(" = ").append(friend_count).append("\r\n");
            sb.append("\t").append(KEY_USER_LIKE).append(" = ").append(user_like).append("\r\n");
            return sb.toString();
		}
	}
	
	public static class Source {
		public static final String KEY_SOURCE_TEXT = "source_text";
		public static final String KEY_SOURCE_HREF = "source_href";
		
		private String source_text;
		private String source_href;
		
		public static ArrayList<Source> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Source> source = new ArrayList<FeedGet.Source>();
			 int size = array.length();
			 for(int i = 0; i < size; i++) {
				 Source temp = parse(array.optJSONObject(i));
				 if(temp != null) {
					 source.add(temp);
				 }
			 }
			 return source;
		 }
		
		public static Source parse(JSONObject object) {
			 if(object == null) {
				 return null;
			 }
			 Source source = new Source();
			 source.source_text = object.optString(KEY_SOURCE_TEXT);
			 source.source_href = object.optString(KEY_SOURCE_HREF);
			 return source;
		 }

		public String getSource_text() {
			return source_text;
		}

		public void setSource_text(String source_text) {
			this.source_text = source_text;
		}

		public String getSource_href() {
			return source_href;
		}

		public void setSource_href(String source_href) {
			this.source_href = source_href;
		}
		
		public String toString() {
			StringBuffer sb = new StringBuffer();
            sb.append("\t").append(KEY_SOURCE_TEXT).append(" = ").append(source_text).append("\r\n");
            sb.append("\t").append(KEY_SOURCE_HREF).append(" = ").append(source_href).append("\r\n");
            return sb.toString();
		}
	}
	
	public static class Place {
		 public static final String KEY_LATITUDE = "latitude";
		 public static final String KEY_LBS_ID = "lbs_id";
		 public static final String KEY_ADDRESS = "address";
		 public static final String KEY_LONGITUDE = "longitude";
		 public static final String KEY_NAME = "name";
		 public static final String KEY_URL = "url";
		 
		 private String latitude;
		 private int lbs_id;
		 private String address;
		 private String longitude;
		 private String name;
		 private String url;
		 
		 public static ArrayList<Place> parse(JSONArray array) {
			 if(array == null) {
				 return null;
			 }
			 ArrayList<Place> place = new ArrayList<FeedGet.Place>();
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
			 place.address = object.optString(KEY_ADDRESS);
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
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
           sb.append("\t").append(KEY_ADDRESS).append(" = ").append(address).append("\r\n");
           sb.append("\t").append(KEY_LONGITUDE).append(" = ").append(longitude).append("\r\n");
           sb.append("\t").append(KEY_NAME).append(" = ").append(name).append("\r\n");
           sb.append("\t").append(KEY_URL).append(" = ").append(url).append("\r\n");
           return sb.toString();
		}
	 }
	
	private int post_id;
	private int source_id;
	private int feed_type;
	private String update_time;
	private int actor_id;
	private String name;
	private String actor_type;
	private String headurl;
	private String prefix;
	private String message;
	private String title;
	private String href;
	private String description;
	private ArrayList<Attachment> attachment;
	private ArrayList<Comments> comments;
	private ArrayList<Likes> likes;
	private ArrayList<Source> source;
	private ArrayList<Place> place;
	
	public FeedGet parse(JSONObject object) throws RenrenException {
		 if(object == null) {
			 return null;
		 }
		 
		 post_id = object.optInt(KEY_POST_ID);
	     source_id = object.optInt(KEY_SOURCE_ID);
	     feed_type = object.optInt(KEY_FEED_TYPE);
	     update_time = object.optString(KEY_UPDATE_TIME);
	     actor_id = object.optInt(KEY_ACTOR_ID);
	     name = object.optString(KEY_NAME);
	     actor_type = object.optString(KEY_ACTOR_TYPE);
	     headurl = object.optString(KEY_HEADURL);
	     prefix = object.optString(KEY_PREFIX);
	     message = object.optString(KEY_MESSAGE);
	     title = object.optString(KEY_TITLE);
	     href = object.optString(KEY_HREF);
	     description = object.optString(KEY_DESCRIPTION);
	     attachment = Attachment.parse(object.optJSONArray(KEY_ATTACHMENT));
	     comments = Comments.parse(object.optJSONArray(KEY_COMMENTS));
	     likes = Likes.parse(object.optJSONArray(KEY_LIKES));
	     source = Source.parse(object.optJSONArray(KEY_SOURCE));
	     place = Place.parse(object.optJSONArray(KEY_PLACE));
	     
	     return this;
	 }
	
	public int getPost_id() {
		return post_id;
	}



	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}



	public int getSource_id() {
		return source_id;
	}



	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}



	public int getFeed_type() {
		return feed_type;
	}



	public void setFeed_type(int feed_type) {
		this.feed_type = feed_type;
	}



	public String getUpdate_time() {
		return update_time;
	}



	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}



	public int getActor_id() {
		return actor_id;
	}



	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getActor_type() {
		return actor_type;
	}



	public void setActor_type(String actor_type) {
		this.actor_type = actor_type;
	}



	public String getHeadurl() {
		return headurl;
	}



	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}



	public String getPrefix() {
		return prefix;
	}



	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getHref() {
		return href;
	}



	public void setHref(String href) {
		this.href = href;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public ArrayList<Attachment> getAttachment() {
		return attachment;
	}



	public void setAttachment(ArrayList<Attachment> attachment) {
		this.attachment = attachment;
	}



	public ArrayList<Comments> getComments() {
		return comments;
	}



	public void setComments(ArrayList<Comments> comments) {
		this.comments = comments;
	}



	public ArrayList<Likes> getLikes() {
		return likes;
	}



	public void setLikes(ArrayList<Likes> likes) {
		this.likes = likes;
	}



	public ArrayList<Source> getSource() {
		return source;
	}



	public void setSource(ArrayList<Source> source) {
		this.source = source;
	}



	public ArrayList<Place> getPlace() {
		return place;
	}



	public void setPlace(ArrayList<Place> place) {
		this.place = place;
	}



	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
        sb.append(KEY_POST_ID).append(" = ").append(post_id).append("\r\n");
        sb.append(KEY_SOURCE_ID).append(" = ").append(source_id).append("\r\n");
        sb.append(KEY_FEED_TYPE).append(" = ").append(feed_type).append("\r\n");
        sb.append(KEY_UPDATE_TIME).append(" = ").append(update_time).append("\r\n");
        sb.append(KEY_ACTOR_ID).append(" = ").append(actor_id).append("\r\n");
        sb.append(KEY_NAME).append(" = ").append(name).append("\r\n");
        sb.append(KEY_ACTOR_TYPE).append(" = ").append(actor_type).append("\r\n");
        sb.append(KEY_HEADURL).append(" = ").append(headurl).append("\r\n");
        sb.append(KEY_PREFIX).append(" = ").append(prefix).append("\r\n");
        sb.append(KEY_MESSAGE).append(" = ").append(message).append("\r\n");
        sb.append(KEY_TITLE).append(" = ").append(title).append("\r\n");
        sb.append(KEY_HREF).append(" = ").append(href).append("\r\n");
        sb.append(KEY_DESCRIPTION).append(" = ").append(description).append("\r\n");
        if(attachment != null) {
        	sb.append(KEY_ATTACHMENT).append(" = ").append("\r\n");
        	for(Attachment a : attachment) {
        		sb.append(a.toString()).append("\r\n");
        	}
        }
        if(comments != null) {
        	sb.append(KEY_COMMENTS).append(" = ").append("\r\n");
        	for(Comments a : comments) {
        		sb.append(a.toString()).append("\r\n");
        	}
        }
        if(likes != null) {
        	sb.append(KEY_LIKES).append(" = ").append("\r\n");
        	for(Likes a : likes) {
        		sb.append(a.toString()).append("\r\n");
        	}
        }
        if(source != null) {
        	sb.append(KEY_SOURCE).append(" = ").append("\r\n");
        	for(Source a : source) {
        		sb.append(a.toString()).append("\r\n");
        	}
        }
        if (place != null) {
            sb.append(KEY_PLACE).append(" = ").append("\r\n");
            for (Place p : place) {
                sb.append(p.toString()).append("\r\n");
            }
        }
        
        return sb.toString();
	}
}
