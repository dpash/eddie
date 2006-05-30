/* 
 * Eddie RSS and Atom feed parser
 * Copyright (C) 2006  David Pashley
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Linking this library statically or dynamically with other modules is making a
 * combined work based on this library. Thus, the terms and conditions of the GNU
 * General Public License cover the whole combination.
 * 
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules, and
 * to copy and distribute the resulting executable under a liense certified by the
 * Open Source Initative (http://www.opensource.org), provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this
 * exception to your version of the library, but you are not obligated to do so.
 * If you do not wish to do so, delete this exception statement from your version.
 */
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
