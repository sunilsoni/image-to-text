package com.image;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageToText {

	private static final Logger logger = LoggerFactory.getLogger(ImageToText.class);

	public static String convertImgToText(String fileName) throws Exception {

		BytePointer outText = null;
		PIX image = null;
		TessBaseAPI api = new TessBaseAPI();

		try {

			// Initializing tesseract-ocr with English, without specifying tessdata path
			if (api.Init(".", "english") != 0) {
				throw new Exception(" Could not initialize tesseract! ");
			}

			// Open input image with leptonica library
			/// PIX image = pixReadfileName);

			image = pixRead(fileName);

			api.SetImage(image);

			// Get OCR result
			outText = api.GetUTF8Text();
			String output = outText.getString();

			 
			logger.debug("OCR output String {}", output);

			String textFileName = fileName;

			textFileName = textFileName.replaceAll("(?i).png", ".txt");

			boolean isSuccess = writeFile(textFileName, output);

			if (isSuccess) {
				return textFileName;
			}
		} finally { // cleanup
			api.End();
			// api.close();
			outText.deallocate();
			pixDestroy(image);
		}

		return null;

	}

	public static boolean writeFile(String filePath, String content) {
		logger.info("Starting file writing  for {}", filePath);
		String fileName = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileName = file.getName();
			logger.trace("File {} is created. ", file.getName());

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			logger.info("Writing for {} file successfully completed.", fileName);
			return true;
		} catch (Exception e) {
			logger.error("Writing of file {} failed .", fileName, e);
		}
		return false;
	}
}
