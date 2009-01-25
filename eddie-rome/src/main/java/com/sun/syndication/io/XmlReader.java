package com.sun.syndication.io;

import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;

import uk.org.catnip.eddie.parser.DetectEncoding;

public class XmlReader extends Reader {

	private DetectEncoding de = new DetectEncoding("US-ASCII");
	private String encoding;
	public XmlReader(InputStream is, boolean b) throws IOException {
		this.encoding = de.detect(is);;
	}

	public XmlReader(InputStream is) {
		this.encoding = de.detect(is);
	}

	public XmlReader(InputStream is, String ct, boolean b) throws IOException {
		// TODO Auto-generated constructor stub
		
		if (b) {
			de = new DetectEncoding("US-ASCII");
		} else {
			de = new DetectEncoding("UTF-8");
		}
		this.encoding = de.detect(is);
	}

	public String getEncoding() {
		// TODO Auto-generated method stub

		return encoding;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read(char[] arg0, int arg1, int arg2) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
