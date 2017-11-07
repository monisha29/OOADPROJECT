package Model;

import helpers.Sentence;

public class SentimentProcessor {
	
	
	
	
	
	
	public static String generateSentiment(String comment)
	{
		String outcome = "unknown";
		Sentence sentence = new Sentence(comment);
		try {
			sentence.preprocess();
		
		System.out.println("Sentence: " + sentence.getSentence());
		double value = sentence.getSentimentWordNet();
		System.out.println("Sentiment (SentiWordNet): " + value );
		String res = "";
		if(value>0)
		{
			outcome = "positive";
		}
		else
			outcome = "negative";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outcome;
		
	}
	

}
