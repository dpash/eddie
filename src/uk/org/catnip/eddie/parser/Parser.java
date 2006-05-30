package uk.org.catnip.eddie.parser;

import java.io.*;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.SAXParser;
import uk.org.catnip.eddie.Feed;
import java.util.Map;

public class Parser {
    Map headers;
    public void setHeaders(Map headers) {
        this.headers = headers;
    }

	public Feed parse(String filename) throws SAXException{
        Feed ret = new Feed();
		try {
			SAXParser xr = new SAXParser();
			FeedSAXParser handler = new FeedSAXParser();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
            xr.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
			xr.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
			xr.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
            
            if (headers.containsKey("Content-Location")){
                handler.setContentLocation((String)headers.get("Content-Location"));
            }
            if (headers.containsKey("Content-Language")){
                handler.setContentLanguage((String)headers.get("Content-Language"));
            }
			handler.setFilename("http://127.0.0.1:8097/"+filename);
			// Parse each file provided on the
			// command line.
			InputStream is = new FileInputStream(filename);
             //BufferedReader in
             //  = new BufferedReader(new InputStreamReader(is));
			//FileReader r = new FileReader(filename);
			xr.parse(new InputSource(is));
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
