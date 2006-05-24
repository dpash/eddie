package uk.org.catnip.javarss.parser;

import java.io.FileReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.SAXParser;
import uk.org.catnip.javarss.Feed;

public class Parser {

	public Feed parse(String filename) throws SAXException{
        Feed ret = new Feed();
		try {
			SAXParser xr = new SAXParser();
			FeedSAXParser handler = new FeedSAXParser();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);

			xr.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
			
			handler.setFilename(filename);
			// Parse each file provided on the
			// command line.

			FileReader r = new FileReader(filename);
			xr.parse(new InputSource(r));
            ret = handler.getFeed();
		} catch (SAXException e) {
			throw e;
		} catch (java.io.FileNotFoundException e) {
			System.out.print(e);
		} catch (java.io.IOException e) {
			System.out.print(e);
		}
        return ret;
	}
}
