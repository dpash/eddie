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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.utils.LabelledParameterized;
import uk.org.catnip.eddie.utils.PythonConverter;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;


@RunWith(LabelledParameterized.class)
public class EddieTest {

	private static final String test_dir = "tests";

	private String filename;

	public EddieTest(String label, String filename) {
		this.filename = filename;
	}

	@Parameterized.Parameters
	public static Collection<String[]> findTests() throws IOException {
		List<String[]> files = new LinkedList<String[]>();
		for (Enumeration<URL> e = EddieTest.class.getClassLoader().getResources(
				test_dir); e.hasMoreElements();) {
			URL url = e.nextElement();
			List<File> fl = getFileList(new File(url.getFile()));
			for (File f : fl) {
				String file = null;
				try {
					file = url.toURI().relativize(f.toURI()).toString();
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
				files.add(new String[]{file, test_dir + "/" + file});
			}

		}
		return files;
	}


	protected static List<File> getFileList(File file) {
		List<File> files = new LinkedList<File>();
		if (file.isDirectory()) {
			File filelist[] = file.listFiles();
			for (File f : filelist) {
				files.addAll(getFileList(f));
			}
		} else {
			files.add(file);
		}
		return files;
	}

	@Test
	public void test() throws Exception {
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				filename);
		String encoding = new DetectEncoding("utf-8").detect(getClass()
				.getClassLoader().getResourceAsStream(filename));
		BufferedReader in = new BufferedReader(new InputStreamReader(input,
				encoding));
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
		FeedData feed = parser.parse(getClass().getClassLoader()
				.getResourceAsStream(filename));

		assertTrue(PythonConverter.runPython(feed, test));

	}
}
