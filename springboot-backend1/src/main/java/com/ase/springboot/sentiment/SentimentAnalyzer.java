package com.ase.springboot.sentiment;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {

	/*
	 * "Very negative" = 0 "Negative" = 1 "Neutral" = 2 "Positive" = 3
	 * "Very positive" = 4
	 */

	static Properties props;
	static StanfordCoreNLP pipeline;

	public void initialize() {
		 // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and sentiment
		props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
	}

	//public String 
	
	public String getSentimentResult(String text) {

		double score=0;

		if (text != null && text.length() > 0) {
			
			// run all Annotators on the text
			Annotation annotation = pipeline.process(text);
			
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				// this is the parse tree of the current sentence
				Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
	
				String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
				
				System.out.println(sentence + "           in function");
				System.out.println(sentimentType);
				
				score= RNNCoreAnnotations.getPredictedClass(tree);
			
			}

		}
		
		if(score==1.0) return "Negative";
		if(score==2.0) return "Neutral";
		 return "Positive";
	}
	
	
	
}
