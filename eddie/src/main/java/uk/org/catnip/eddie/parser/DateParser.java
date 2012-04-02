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

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.org.catnip.eddie.Detail;

import java.util.TimeZone;

public class DateParser extends Detail {
	static Logger log = Logger.getLogger(DateParser.class);

	// Thu, 01 Jan 2004 19:48:21 GMT
	// Sun Jan 4 16:29:06 PST 2004
	// Mon, 26 January 2004 16:31:00 ET
	// Mon, 26 January 2004 16:31:00 AT
	// Mon, 26 January 2004 16:31:00 CT
	// Mon, 26 January 2004 16:31:00 MT
	// Mon, 26 January 2004 16:31:00 PT
	// -03-12
	// -0312
	// 03-12-31
	// 031231
	// 2003-335
	// 03335
	// Thu, 01 Jan 2004 19:48:21 GMT
	// Thu, 01 Jan 2004 19:48:21 GMT
	// Thu, 31 Jun 2004 19:48:21 GMT
	// Thu, 01 Jan 04 19:48:21 GMT
	// 2004-02-28T18:14:55-08:00
	// 2000-02-28T18:14:55-08:00
	// 2003-02-28T18:14:55-08:00
	// 2003-12-31T10:14:55-08:00
	// 2003-12-31T18:14:55+08:00
	// 2003-12-31T10:14:55Z
	// 2003
	// 2003-12
	// 2003-12-31
	// 20031231

	@NotNull
    static String[] date_formats = { "yyyy-MM-dd'T'kk:mm:ss'Z'", // ISO
			"yyyy-MM-dd'T'kk:mm:ssz", // ISO
			"yyyy-MM-dd'T'kk:mm:ss", // ISO
			"EEE, d MMM yy kk:mm:ss z", // RFC822
			"EEE, d MMM yyyy kk:mm:ss z", // RFC2882
			"EEE MMM  d kk:mm:ss zzz yyyy", // ASC
			"EEE, dd MMMM yy kk:mm:ss", 
			"EEE, dd MMMM yyyy kk:mm:ss", // Disney Mon, 26 January 2004
											// 16:31:00 ET
			"yyyy-MM-dd kk:mm:ss.0", "-yy-MM", "-yyMM", "yy-MM-dd",
			"yyyy-MM-dd", "yyyy-MM", "yyyy-D", "-yyMM", "yyyyMMdd", "yyMMdd",
			"yyyy", "yyD"

	};

	@Nullable
    public static Date parse(String d) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		d = d.replaceAll("([-+]\\d\\d:\\d\\d)", "GMT$1"); // Correct W3C times
		d = d.replaceAll(" ([ACEMP])T$", " $1ST"); // Correct Disney times
        for (String date_format : date_formats) {
            try {
                if (log.isTraceEnabled()) {
                    log.debug("trying '" + date_format + "'");
                }
                formatter.applyPattern(date_format);
                date = formatter.parse(d);
                break;
            } catch (Exception e) {
                if (log.isTraceEnabled()) {
                    log.debug("parsing of date failed");
                }
                // Oh well. We tried
            }
        }

		return date;

	}

}
