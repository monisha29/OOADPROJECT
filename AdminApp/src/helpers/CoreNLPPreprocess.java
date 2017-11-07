package helpers;

import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeSet;





/**
 * @author Reinald Kim Amplayo & Min Song
 */
public class CoreNLPPreprocess {
	
	private TreeSet<String> stopSet;
	private Porter porter;
	
	public CoreNLPPreprocess(String stopFile) throws Exception {
		stopSet = new TreeSet<String>();
		porter = new Porter();
		initializeStopwords(stopFile);
	}
	
	public void initializeStopwords(String stopFile) throws Exception {
		Scanner s = new Scanner(new FileReader(stopFile));
		while(s.hasNext())
			stopSet.add(s.next());
		s.close();
	}
	
	public void preprocess(Token t) {
		edu.stanford.nlp.simple.Sentence sent = new edu.stanford.nlp.simple.Sentence(t.getToken());
		t.setLemma(sent.lemma(0));
		t.setPOS(sent.posTag(0));
		t.setNER(sent.nerTag(0));
		t.setStop(stopSet.contains(sent.lemma(0)) || stopSet.contains(t.getToken()));
	}
	
	public void preprocess(Sentence s) {
		edu.stanford.nlp.simple.Sentence sent = new edu.stanford.nlp.simple.Sentence(s.getSentence());
		preprocess(s, sent);
	}
	
	private void preprocess(Sentence s, edu.stanford.nlp.simple.Sentence sent) {
		s.setParseTree(sent.parse().toString());
		s.setDependencies(sent.dependencyGraph().toList().split("\n"));
		for(int i=0; i<sent.length(); i++) {
			boolean stop = stopSet.contains(sent.lemma(i)) || stopSet.contains(sent.originalText(i));
			String stem = porter.stripAffixes(sent.originalText(i));
			Token t = new Token(sent.originalText(i), sent.lemma(i), sent.posTag(i), sent.nerTag(i), stem, stop);
			s.add(t);
		}
	}
	


}
