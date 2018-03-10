package com.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class ImageToTextTest {

	private static final Logger logger = LoggerFactory.getLogger(ImageToTextTest.class);

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

		String fileName = "maxresdefault.jpg";
		String actualResult = ImageToText.convertImgToText(fileName);

		String expectedFileName = "maxresdefault.jpg.txt";
		assertEquals(expectedFileName, actualResult);

		fileName = "invoicesample.png";
		actualResult = ImageToText.convertImgToText(fileName);

		expectedFileName = "invoicesample.png.txt";
		assertEquals(expectedFileName, actualResult);

		fileName = "bill.png";
		actualResult = ImageToText.convertImgToText(fileName);

		expectedFileName = "bill.png.txt";
		assertEquals(expectedFileName, actualResult);

		fileName = "car-invoice.JPG";
		actualResult = ImageToText.convertImgToText(fileName);

		expectedFileName = "car-invoice.JPG.txt";
		assertEquals(expectedFileName, actualResult);

	}

	@Test
	public void writeFileTest() {

		String fileName = "test.txt";
		String content = "Test Cases";

		assertTrue(ImageToText.writeFile(fileName, content));

	}

}
