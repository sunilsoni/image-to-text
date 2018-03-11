package com.image.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

@Service
@Configuration
public class EnglishService {

	@Value("${tokenizer.model.path}")
	private String tokenizerModel;

	@Value("${name.finder.model.path}")
	private String tokenNameModel;

	@Value("${pos.model.path}")
	private String posModel;

	@Value("${sentence.detector.model.path}")
	private String sentenceDetectorModel;

	@Bean
	public SentenceModel getSentenceModel(@Value("${sentence.model.path}") String modelPath) throws IOException {
		return new SentenceModel(getInputStream(modelPath));
	}

	@Bean
	public TokenizerModel getTokenizerModel() throws IOException {
		return new TokenizerModel(getInputStream(tokenizerModel));
	}

	@Bean
	public TokenNameFinderModel getTokenNameFinderModel() throws IOException {
		return new TokenNameFinderModel(getInputStream(tokenNameModel));
	}

	@Bean
	public POSModel getPOSModel() throws IOException {
		return new POSModel(getInputStream(posModel));
	}

	@Bean
	public SentenceDetectorME getSentenceDetectorME() throws IOException {
		return new SentenceDetectorME(new SentenceModel(getInputStream(sentenceDetectorModel)));
	}

	@Bean
	public TokenizerME getTokenizerME() throws IOException {
		return new TokenizerME(getTokenizerModel());
	}

	@Bean
	public NameFinderME getNameFinderME() throws IOException {
		return new NameFinderME(getTokenNameFinderModel());
	}

	@Bean
	public POSTaggerME getPOSTaggerME() throws IOException {
		return new POSTaggerME(getPOSModel());
	}

	private InputStream getInputStream(String resource) throws FileNotFoundException {
		File modelFile = null;
		try {
			modelFile = new ClassPathResource(resource).getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new FileInputStream(modelFile);
	}

	public String[] segmentSentences(String document) throws IOException {
		return getSentenceDetectorME().sentDetect(document);
	}

	public String[] tokenizeSentence(String sentence) throws IOException {
		return getTokenizerME().tokenize(sentence);
	}

	public Span[] findNames(String[] tokens) throws IOException {
		return getNameFinderME().find(tokens);
	}

	public String[] tagPartOfSpeech(String[] tokens) throws IOException {
		return getPOSTaggerME().tag(tokens);
	}

	public double[] getPartOfSpeechProbabilities() throws IOException {
		return getPOSTaggerME().probs();
	}

}
