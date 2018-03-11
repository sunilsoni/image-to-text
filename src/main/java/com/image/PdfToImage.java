package com.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfToImage {

	private static final Logger logger = LoggerFactory.getLogger(PdfToImage.class);

	public static String convertPdfToImg(String filePath) throws InvalidPasswordException, IOException {
		// Loading an existing PDF document
		File file = new File(filePath);// "D:\\Work\\ImgToText\\image-to-text\\pdf.pdf"
		PDDocument document = PDDocument.load(file);

		int pageSize = document.getNumberOfPages();
		logger.info("PDF File pageSize: " + pageSize);

		// Instantiating the PDFRenderer class
		PDFRenderer renderer = new PDFRenderer(document);
		String imageFile = null;
		for (int i = 1; i <= pageSize; i++) {
			// Rendering an image from the PDF document
			// BufferedImage image = renderer.renderImage(i - 1);
			BufferedImage image = renderer.renderImageWithDPI(i - 1, 500, ImageType.BINARY);

			// Writing the image to a file
			imageFile = file.getName();
			String temp = "_" + i + ".png";

			imageFile = imageFile.replaceAll("(?i).pdf", temp);
			ImageIO.write(image, "JPEG", new File(imageFile));
		}

		System.out.println("Image created");

		// Closing the document
		document.close();
		return imageFile;
	}

}
