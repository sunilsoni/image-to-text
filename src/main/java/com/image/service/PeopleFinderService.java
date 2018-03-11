package com.image.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import opennlp.tools.util.Span;

@Service
public class PeopleFinderService {

	@Autowired
	private EnglishService englishService;

	private static final Logger logger = LoggerFactory.getLogger(PeopleFinderService.class);

	public List<String> findPeopleNames(String document) throws IOException {
		String[] documentArr = document.split(" ");
		Span[] names = englishService.findNames(documentArr);
		logger.info(" documentArr.length,names.length {}, {} ", documentArr.length, names.length);

		List<String> list = new ArrayList<>();
		for (Span name : names) {
			String personName = "";
			for (int i = name.getStart(); i < name.getEnd(); i++) {
				personName += documentArr[i] + " ";
				list.add(personName);
			}

			logger.info(name.getType() + " : " + personName + "\t [probability=" + name.getProb() + "]");
		}

		return list;

	}

}
