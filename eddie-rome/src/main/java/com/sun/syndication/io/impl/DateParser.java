package com.sun.syndication.io.impl;

import java.util.Date;

public class DateParser {

	public static Date parseRFC822(String date) {
		// TODO Auto-generated method stub
		return parseDate(date);
	}

	public static Date parseDate(String date) {
		Date ret = uk.org.catnip.eddie.parser.DateParser.parse(date);
		return ret;
	}

	public static Date parseW3CDateTime(String date) {
		// TODO Auto-generated method stub
		return parseDate(date);
	}

}
