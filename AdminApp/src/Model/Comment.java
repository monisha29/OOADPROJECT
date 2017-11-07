package Model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import DBUtils.CommentContract;
import DBUtils.DBFunctions;

public class Comment {
	public String id ;
    public String body;
    public String sentiment;
    public String status ;
    
    
    
    public Comment() {
		super();
	}



	public Comment(String id, String body, String sentiment, String status) {
		super();
		this.id = id;
		this.body = body;
		this.sentiment = sentiment;
		this.status = status;
	}

 
   
   public boolean updateComment()
   {
	   try {
	   JSONObject documentJson = CommentContract.generateJson(this);
	   return DBFunctions.updateDocumentOnId(CommentContract.COMMENTTABLENAME,this.id,documentJson);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	return false;
   }
   
   public static  List<Comment> fetchAllUnprocessedComments()
    {
    	List<Comment> listOfComments = new ArrayList<Comment>();
    	String commentJson = DBFunctions.fetchAllDocuments(CommentContract.COMMENTTABLENAME);
    	listOfComments = CommentContract.parseProductJson(commentJson);
    	return listOfComments;
    }
}

