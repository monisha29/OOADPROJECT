package Controller;

import java.util.List;

import DBUtils.CommentContract;
import Model.Comment;
import Model.SentimentProcessor;
import helpers.Sentence;

public class SentimentProcessorController {
	
	Comment fetchObj ;
//	
//	public List<Comment> fetchComments()
//	{
//		
//	}

	public List<Comment> fetchComments() {
		// TODO Auto-generated method stub
		fetchObj = new Comment();
		return fetchObj.fetchAllUnprocessedComments();
		
	}
	
	
	public String generateSentiment(String comment)
	{
		
		return SentimentProcessor.generateSentiment(comment);
		
	}
	
	
	public boolean updateCommentSentiment(Comment comment)
	{
		boolean status = false;
		
	 //  status = comment.updateComment(CommentContract.COMMENTSENTIMENT);
	   
	   return status;
	}

	

}
