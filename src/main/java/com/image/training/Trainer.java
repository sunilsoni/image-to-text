package com.image.training;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.image.util.CommonUtils;

import opennlp.tools.namefind.BioCodec;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderFactory;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.sentdetect.SentenceSample;
import opennlp.tools.sentdetect.SentenceSampleStream;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 * NOTE: The training data should contain at least 15000 sentences to create a
 * model which performs well.
 */
@Configuration
public class Trainer {
	private static final Logger logger = LoggerFactory.getLogger(Trainer.class);

	public static boolean people() throws Exception {
		InputStreamFactory isf = CommonUtils
				.trainingDataInputStreamFactory(CommonUtils.getResourceFolderFiles("training-data/person"));

		ObjectStream sampleStream = null;
		try {
			sampleStream = new NameSampleDataStream(new PlainTextByLineStream(isf, StandardCharsets.UTF_8));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// setting the parameters for training
		TrainingParameters params = new TrainingParameters();
		params.put(TrainingParameters.ITERATIONS_PARAM, 70);
		params.put(TrainingParameters.CUTOFF_PARAM, 1);

		// training the model using TokenNameFinderModel class
		TokenNameFinderModel nameFinderModel = null;
		try {
			nameFinderModel = NameFinderME.train("en", null, sampleStream, params,
					TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// saving the model to "ner-custom-model.bin" file
		try {
			// File output = new File("ner-custom-model.bin");
			File output = new File("D:\\Work\\ImgToText\\image-to-text\\src\\main\\resources\\models\\custom",
					"en-ner-person.bin");

			FileOutputStream outputStream = new FileOutputStream(output);
			nameFinderModel.serialize(outputStream);
			logger.info("output length: {}", output.length());
			logger.info("output getAbsolutePath {}", output.getAbsolutePath());

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// end
		return false;
	}

	public static boolean sentence() throws Exception {

		InputStreamFactory isf = CommonUtils
				.trainingDataInputStreamFactory(CommonUtils.getResourceFolderFiles("training-data/date"));

		Charset charset = Charset.forName("UTF-8");
		// parameters used by machine learning algorithm, Maxent, to train its weights
		TrainingParameters mlParams = new TrainingParameters();
		mlParams.put(TrainingParameters.ITERATIONS_PARAM, Integer.toString(15));
		mlParams.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(1));

		ObjectStream<String> lineStream = new PlainTextByLineStream(isf, charset);

		SentenceModel sentdetectModel;

		// train the model
		try (ObjectStream<SentenceSample> sampleStream = new SentenceSampleStream(lineStream)) {
			sentdetectModel = SentenceDetectorME.train("en", sampleStream, true, null,
					TrainingParameters.defaultParams());
		}

		// BufferedOutputStream modelOut = null;
		FileOutputStream outFileStream = null;
		// String modelFile = "/models/custom/en-ner-person-custom.bin";
		// File modelFile = new
		// ClassPathResource("models/custom/en-sent.bin").getFile();

		File modelFile = new File("D:\\Work\\ImgToText\\image-to-text\\src\\main\\resources\\models\\custom",
				"en-sent.bin");
		logger.debug("modelFile getAbsolutePath {}", modelFile.getAbsolutePath());

		try {
			// modelOut = new BufferedOutputStream(new FileOutputStream(modelFile));
			// sentdetectModel.serialize(modelOut);

			outFileStream = new FileOutputStream(modelFile);
			sentdetectModel.serialize(outFileStream);

			logger.info("sentdetectModel length: {}", modelFile.length());
			logger.info("sentdetectModel getAbsolutePath: {}", modelFile.getAbsolutePath());

			// CommonUtils.printFile(modelFile.getAbsolutePath());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outFileStream != null)
				outFileStream.close();
		}

		return false;
	}
}
