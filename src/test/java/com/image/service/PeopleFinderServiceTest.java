package com.image.service;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeopleFinderServiceTest {

	@Autowired
	private PeopleFinderService peopleFinderService;

	private static final Logger logger = LoggerFactory.getLogger(PeopleFinderServiceTest.class);

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
	public void findPeopleNamesTest() throws Exception {

		String document = "Ocean Bill of Lading  Exporter Booking Number Document Number  WING LLC LN-589654 WEL-5689  Export References  NY  Ultimate Consignee Forwarding Agent  Royal Foodie WING LLC  «m—  Pre-Carriage By Place of Receipt Domestic Routing  ACE LLC New York, New York, USA NA  Exporting Carrier Port of Loading Loading Pier/Tenninal  Red Hook Terminal Red Hook Terminal  Port of Discharge Place of Receipt on Carrier Type of Move  Old Port Of Montreal Ocean  Marks and Numbers No. of ex:   Biscuits - 85 500000 A Local made biscuits which are made out of wheat, milk and sugar with special ingredients 250000 10*4*7 cms  consumable by people of any ages above 3 years safely.  attnttttt. tnene  pages, tnnutng attatnnents to tnn Ocean at ot tattng.  These commodities, technology or software were exported from the United States in accordance with the Export Administration Regulations. Diversion contrary to US. law prohibited.  Carrier has a policy against payment solicitation. or receipt of any rebate, directly or indirectly. which would be unlawful under the United States Shipping Act, 1984 as amended.  .1,    FREIGHT RATES, CHARGES, WEIGHTS ANDIOR MEASUREMENTS Received by Carrier for shipment by ocean vessel between port of loading and port of discharge,  and for arrangement or procurement of pre-carriage from place of receipt and on-carriage to  place 0f delivery. where stated above, the goods as specified above in apparent good order and    Subject to correction condition unless otherwise stated. The goods to be delivered at the above mentioned port of  — discharge or place of delivery. whichever is applicable.  Grand T otalr  IN WITNESS WHEREOF original Bills of Lading have been signed, not otherwise  stated above, one of which being accomplished the others  anal! be void.  DATED AT 1010520] 0 RICHARD AL  BY —   Agent  B/L NO: JY123897 .  My name  Sunil BL .  Anupam CO .  Deepak CI  .  My name  Kiran OBL .  My name  Deepak CI.  My name  Deepak OBL.  My name  Sunil OBL .";

		List<String> list = peopleFinderService.findPeopleNames(document);

		assertThat(list.isEmpty(), Matchers.is(false));

	}
}
