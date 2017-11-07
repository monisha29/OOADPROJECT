package helpers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Reinald Kim Amplayo & Min Song
 */
public class Sentence extends ArrayList<Token> {

	private static final long serialVersionUID = 6274823696191382944L;
	private String sentence, parseTree;
	private String[] dependencies;
	private double sentimentCore, sentimentLing, sentimentWord;
	
	public Sentence(String s) {
		sentence = s;
		parseTree = null;
		dependencies = null;
		sentimentCore = -2000000000;
		sentimentLing = -2000000000;
		sentimentWord = -2000000000;
	}
	
	public void setParseTree(String p) {
		parseTree = p;
	}
	
	public String getParseTree() {
		return parseTree;
	}
	
	public void setDependencies(String[] dep) {
		dependencies = dep;
	}
	
	public String[] getDependencies() {
		return dependencies;
	}
	
	public void preprocess() throws Exception {
		CoreNLPPreprocess cnlpp = new CoreNLPPreprocess("data/util/stopwords.txt");
		cnlpp.preprocess(this);
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public void setSentence(String s) {
		sentence = s;
	}
	
	
	public double getSentimentWordNet() throws Exception {
		if(sentimentWord == -2000000000) {
			File serFile = new File("model/sentiwordnet/dictionary.model");
			if(serFile.exists()) {
				SentiWordNet swn = new SentiWordNet(serFile);
				sentimentWord = swn.getSentiment(this);
			}
			else {
				SentiWordNet swn = new SentiWordNet("model/sentiwordnet/SentiWordNet_3.0.0_20130122.txt");
				sentimentWord = swn.getSentiment(this);
			}
		}
		return sentimentWord;
	}
	
	public List<String> getNGrams(int n) {
		List<String> ret = new ArrayList<String>();
		for(int i=0; i<size()-n; i++) {
			String ngram = "";
			for(int j=i; j<i+n; j++)
				ngram += get(j).getLemma() + " ";
			ngram = ngram.trim();
			ret.add(ngram);
		}
		return ret;
	}

}
