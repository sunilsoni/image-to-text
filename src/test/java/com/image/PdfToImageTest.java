package com.image;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfToImageTest {

	private static final Logger logger = LoggerFactory.getLogger(PdfToImageTest.class);

	@Rule
	public Timeout globalTimeout = Timeout.seconds(1000);

	static long start, end;
	static double diff;

	@Before
	public void start() {
		start = System.currentTimeMillis();
	}

	@After
	public void end() {
		end = System.currentTimeMillis();

		diff = end - start;
		// logger.info("millis-->" + (diff));// millis
		logger.info("seconds-->" + (diff / 1000));// seconds
	}

	@Test
	public void convertImgToTextTest() throws Exception {

		String file = "D:\\Work\\ImgToText\\image-to-text\\src\\main\\resources\\Images.pdf";

		String actualResult = PdfToImage.convertPdfToImg(file);

		String expectedFileName = file + "_5.jpg";
		assertEquals(expectedFileName, actualResult);

	}
}
