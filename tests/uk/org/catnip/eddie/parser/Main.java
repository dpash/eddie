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

import org.apache.log4j.PropertyConfigurator;


public class Main {

	public static void main(String[] args) {

	    if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("info")) {
            PropertyConfigurator.configure("log4j.properties.info"); 
        } else if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("warn")) {
            PropertyConfigurator.configure("log4j.properties.warn"); 
        } else {
		PropertyConfigurator.configure("log4j.properties");
        }
		Test test = new Test();
		if (args.length > 0) {
			test.parse_dir(args[0]);
		} else {
			test.parse_dir("test");
		}
		System.out.println("   Ran " + test.total_tests + " tests");
		System.out.println("Passed " + test.passed_tests + " tests");
		System.out.println("Failed " + test.failed_tests + " tests");

	}

}
