package DBUtils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Comment;

public class CommentContract {
	    public  static String COMMENTTABLENAME = "comment";
	    public static String COMMENTID = "commentid";
	    public static String COMMENTBODY = "commentbody";
	    public static String COMMENTSENTIMENT = "commentsentiment";
	    public static String COMMENTSTATUS = "status";
	    public static String COMMENTUSER = "userid";
	    public static String COMMENTPRODUCTID = "productid";
	    
	    public static JSONObject jsonForUpdation(String field , String value)
	    {
	    	JSONObject json = new JSONObject();
	    	try {
				json.put(field, value);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return json;
	    	
	    }
	    public static JSONObject generateJson(Comment comment)
	    {
	    	JSONObject json = new JSONObject();
	    	try {
				 json.put(COMMENTID, comment.id);
				 json.put(COMMENTBODY, comment.body);
				 json.put(COMMENTSENTIMENT, comment.sentiment);
				 json.put(COMMENTSTATUS,"done");
				 json.put(COMMENTUSER,"dummy");
				 json.put(COMMENTPRODUCTID,"1" );
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return json;
	    	
	    }
	    
	    public static List<Comment> parseProductJson(String s)
	    {
	        List<Comment> commentList = new ArrayList<Comment>();
	        try {
	        	JSONArray commentDocuments = new JSONArray(s);
	            for (int i = 0; i < commentDocuments.length(); i++) {
	                JSONObject document = new JSONObject();
	                System.out.println(document.toString());
	                Comment comment = new Comment();
	                document = commentDocuments.getJSONObject(i);
	                System.out.println(document.toString());
	                JSONObject id  = (JSONObject) document.get("_id");
	                comment.id = id.get("$oid").toString();
	                System.out.println(comment.id);
	                comment.body = document.get(COMMENTBODY).toString();
	                System.out.println(comment.body);
	                comment.sentiment = document.get(COMMENTSENTIMENT).toString();
	                commentList.add(comment);
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }


	        return  commentList;
	    }
}
