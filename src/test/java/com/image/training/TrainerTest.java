package com.image.training;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerTest {

	private static final Logger logger = LoggerFactory.getLogger(TrainerTest.class);

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
		logger.info("seconds-->" + (diff / 1000));// seconds
	}

	@Test
	public void peopleTest() throws Exception {
		assertTrue(Trainer.people());
	}

	// @Test
	public void sentenceTest() throws Exception {
		assertTrue(Trainer.sentence());
	}
}
