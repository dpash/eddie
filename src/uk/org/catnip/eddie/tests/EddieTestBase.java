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
package uk.org.catnip.eddie.tests;

import java.io.*;
import java.util.regex.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Hashtable;
import org.apache.log4j.Logger;

import uk.org.catnip.eddie.Entry;
import uk.org.catnip.eddie.FeedData;

import uk.org.catnip.eddie.parser.DetectEncoding;
import uk.org.catnip.eddie.parser.Parser;
import org.python.util.PythonInterpreter;
import org.python.core.*;

import junit.framework.*;

public class EddieTestBase extends TestCase {
        
    static Logger log = Logger.getLogger(EddieTestBase.class);

    private PythonInterpreter interp = new PythonInterpreter();

    public boolean test(String filename) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), new DetectEncoding().detect(filename, "utf-8")));
		String line;
		String test = "";
		Pattern test_pattern = Pattern.compile("^Expect:\\s+(.*)$");
		Pattern header_pattern = Pattern
				.compile("^Header:\\s+([^:]*):\\s+(.*)$");

		Map<String, String> headers = new Hashtable<String, String>();
		headers.put("Content-Location", "http://127.0.0.1:8097/" + filename);
		while ((line = in.readLine()) != null) {
			Matcher test_matcher = test_pattern.matcher(line);
			Matcher header_matcher = header_pattern.matcher(line);
			if (test_matcher.matches()) {
				test = test_matcher.group(1);
			} else if (header_matcher.matches()) {
				headers.put(header_matcher.group(1), header_matcher.group(2));
			}
		}
		
		in.close();
		
		Parser parser = new Parser();
		parser.setHeaders(headers);
		FeedData feed = parser.parse(filename);

		return runPython(feed, test);

	}

    public boolean runPython(FeedData feed, String test) {

        if (feed.error) {
            interp.set("bozo", new PyInteger(1));
        } else {
            interp.set("bozo", new PyInteger(0));
        }

        PyList entries_list = new PyList();
        Iterator entries = feed.entries();
        while (entries.hasNext()) {
            entries_list.append(Test.convertEntry((Entry) entries.next()));
        }
        if (feed.get("format") != null) {
        interp.set("version", new PyString(feed.get("format")));
        }
        interp.set("feed", Test.convertFeed(feed));
        interp.set("entries", entries_list);
        interp.exec("ret ="+ test);
        PyInteger ret = (PyInteger)interp.get("ret");
        return (ret.getValue() != 0);
    }
    
}
