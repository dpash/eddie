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

import java.util.Map;
import java.util.Hashtable;

public class Entities {
    static private Map entity_map = createEntities();
    
    private static Map createEntities() {
        Map entities = new Hashtable();
        entities.put("amp", "");
        entities.put("gt", "");
        entities.put("lt", "");
        entities.put("apos", "");
        entities.put("160", "\u00a0");
        entities.put("732", "\u02dc");
        entities.put("8216", "\u2018");
        entities.put("8217", "\u2019");
        entities.put("8220", "\u201c");
        entities.put("8221", "\u201d");
        entities.put("9830", "\u2666");
        entities.put("aacute", "\u00e1");
        entities.put("acirc", "\u00e2");
        entities.put("acute", "\u00b4");
        entities.put("aelig", "\u00e6");
        entities.put("agrave", "\u00e0");
        entities.put("alefsym", "\u2135");
        entities.put("alpha", "\u03b1");
        entities.put("and", "\u2227");
        entities.put("ang", "\u2220");
        entities.put("aring", "\u00e5");
        entities.put("asymp", "\u2248");
        entities.put("atilde", "\u00e3");
        entities.put("auml", "\u00e4");
        entities.put("bdquo", "\u201e");
        entities.put("beta", "\u03b2");
        entities.put("brvbar", "\u00a6");
        entities.put("bull", "\u2022");
        entities.put("cap", "\u2229");
        entities.put("ccedil", "\u00e7");
        entities.put("cedil", "\u00b8");
        entities.put("cent", "\u00a2");
        entities.put("chi", "\u03c7");
        entities.put("circ", "\u02c6");
        entities.put("clubs", "\u2663");
        entities.put("cong", "\u2245");
        entities.put("copy", "\u00a9");
        entities.put("crarr", "\u21b5");
        entities.put("cup", "\u222a");
        entities.put("curren", "\u00a4");
        entities.put("dagger", "\u2020");
        entities.put("dArr", "\u21d3");
        entities.put("deg", "\u00b0");
        entities.put("delta", "\u03b4");
        entities.put("diams", "\u2666");
        entities.put("divide", "\u00f7");
        entities.put("doesnotexist", "&doesnotexist;");
        entities.put("eacute", "\u00e9");
        entities.put("ecirc", "\u00ea");
        entities.put("egrave", "\u00e8");
        entities.put("empty", "\u2205");
        entities.put("emsp", "\u2003");
        entities.put("ensp", "\u2002");
        entities.put("epsilon", "\u03b5");
        entities.put("equiv", "\u2261");
        entities.put("eta", "\u03b7");
        entities.put("eth", "\u00f0");
        entities.put("euml", "\u00eb");
        entities.put("euro", "\u20ac");
        entities.put("exist", "\u2203");
        entities.put("fnof", "\u0192");
        entities.put("forall", "\u2200");
        entities.put("frac12", "\u00bd");
        entities.put("frac14", "\u00bc");
        entities.put("frac34", "\u00be");
        entities.put("frasl", "\u2044");
        entities.put("gamma", "\u03b3");
        entities.put("ge", "\u2265");
        entities.put("harr", "\u2194");
        entities.put("hearts", "\u2665");
        entities.put("hellip", "\u2026");
        entities.put("iacute", "\u00ed");
        entities.put("icirc", "\u00ee");
        entities.put("iexcl", "\u00a1");
        entities.put("igrave", "\u00ec");
        entities.put("image", "\u2111");
        entities.put("infin", "\u221e");
        entities.put("int", "\u222b");
        entities.put("iota", "\u03b9");
        entities.put("iquest", "\u00bf");
        entities.put("isin", "\u2208");
        entities.put("iuml", "\u00ef");
        entities.put("kappa", "\u03ba");
        entities.put("lambda", "\u03bb");
        entities.put("lang", "\u2329");
        entities.put("laquo", "\u00ab");
        entities.put("larr", "\u2190");
        entities.put("lceil", "\u2308");
        entities.put("ldquo", "\u201c");
        entities.put("le", "\u2264");
        entities.put("lfloor", "\u230a");
        entities.put("lowast", "\u2217");
        entities.put("loz", "\u25ca");
        entities.put("lrm", "\u200e");
        entities.put("lsaquo", "\u2039");
        entities.put("lsquo", "\u2018");
        entities.put("macr", "\u00af");
        entities.put("mdash", "\u2014");
        entities.put("micro", "\u00b5");
        entities.put("middot", "\u00b7");
        entities.put("minus", "\u2212");
        entities.put("mu", "\u03bc");
        entities.put("nabla", "\u2207");
        entities.put("nbsp", "\u00a0");
        entities.put("ndash", "\u2013");
        entities.put("ne", "\u2260");
        entities.put("ni", "\u220b");
        entities.put("notin", "\u2209");
        entities.put("not", "\u00ac");
        entities.put("nsub", "\u2284");
        entities.put("ntilde", "\u00f1");
        entities.put("nu", "\u03bd");
        entities.put("oacute", "\u00f3");
        entities.put("ocirc", "\u00f4");
        entities.put("oelig", "\u0153");
        entities.put("ograve", "\u00f2");
        entities.put("oline", "\u203e");
        entities.put("omega", "\u03c9");
        entities.put("omicron", "\u03bf");
        entities.put("oplus", "\u2295");
        entities.put("ordf", "\u00aa");
        entities.put("ordm", "\u00ba");
        entities.put("or", "\u2228");
        entities.put("oslash", "\u00f8");
        entities.put("otilde", "\u00f5");
        entities.put("otimes", "\u2297");
        entities.put("ouml", "\u00f6");
        entities.put("para", "\u00b6");
        entities.put("part", "\u2202");
        entities.put("permil", "\u2030");
        entities.put("perp", "\u22a5");
        entities.put("phi", "\u03c6");
        entities.put("piv", "\u03d6");
        entities.put("pi", "\u03c0");
        entities.put("plusmn", "\u00b1");
        entities.put("pound", "\u00a3");
        entities.put("prime", "\u2032");
        entities.put("prod", "\u220f");
        entities.put("prop", "\u221d");
        entities.put("psi", "\u03c8");
        entities.put("radic", "\u221a");
        entities.put("rang", "\u232a");
        entities.put("raquo", "\u00bb");
        entities.put("rArr", "\u21d2");
        entities.put("rceil", "\u2309");
        entities.put("rdquo", "\u201d");
        entities.put("real", "\u211c");
        entities.put("reg", "\u00ae");
        entities.put("rfloor", "\u230b");
        entities.put("rho", "\u03c1");
        entities.put("rlm", "\u200f");
        entities.put("rsaquo", "\u203a");
        entities.put("rsquo", "\u2019");
        entities.put("sbquo", "\u201a");
        entities.put("scaron", "\u0161");
        entities.put("sdot", "\u22c5");
        entities.put("sect", "\u00a7");
        entities.put("shy", "\u00ad");
        entities.put("sigmaf", "\u03c2");
        entities.put("sigma", "\u03c3");
        entities.put("sim", "\u223c");
        entities.put("spades", "\u2660");
        entities.put("sube", "\u2286");
        entities.put("sub", "\u2282");
        entities.put("sum", "\u2211");
        entities.put("sup1", "\u00b9");
        entities.put("sup2", "\u00b2");
        entities.put("sup3", "\u00b3");
        entities.put("supe", "\u2287");
        entities.put("sup", "\u2283");
        entities.put("szlig", "\u00df");
        entities.put("tau", "\u03c4");
        entities.put("there4", "\u2234");
        entities.put("thetasym", "\u03d1");
        entities.put("theta", "\u03b8");
        entities.put("thinsp", "\u2009");
        entities.put("thorn", "\u00fe");
        entities.put("tilde", "\u02dc");
        entities.put("times", "\u00d7");
        entities.put("trade", "\u2122");
        entities.put("uacute", "\u00fa");
        entities.put("uArr", "\u21d1");
        entities.put("ucirc", "\u00fb");
        entities.put("ugrave", "\u00f9");
        entities.put("uml", "\u00a8");
        entities.put("Aacute", "\u00c1");
        entities.put("Acirc", "\u00c2");
        entities.put("AElig", "\u00c6");
        entities.put("Agrave", "\u00c0");
        entities.put("Alpha", "\u0391");
        entities.put("Aring", "\u00c5");
        entities.put("Atilde", "\u00c3");
        entities.put("Auml", "\u00c4");
        entities.put("Beta", "\u0392");
        entities.put("Ccedil", "\u00c7");
        entities.put("Chi", "\u03a7");
        entities.put("Dagger", "\u2021");
        entities.put("Delta", "\u0394");
        entities.put("Eacute", "\u00c9");
        entities.put("Ecirc", "\u00ca");
        entities.put("Egrave", "\u00c8");
        entities.put("Epsilon", "\u0395");
        entities.put("Eta", "\u0397");
        entities.put("ETH", "\u00d0");
        entities.put("Euml", "\u00cb");
        entities.put("Gamma", "\u0393");
        entities.put("Iacute", "\u00cd");
        entities.put("Icirc", "\u00ce");
        entities.put("Igrave", "\u00cc");
        entities.put("Iota", "\u0399");
        entities.put("Iuml", "\u00cf");
        entities.put("Kappa", "\u039a");
        entities.put("Lambda", "\u039b");
        entities.put("Mu", "\u039c");
        entities.put("Ntilde", "\u00d1");
        entities.put("Nu", "\u039d");
        entities.put("Oacute", "\u00d3");
        entities.put("Ocirc", "\u00d4");
        entities.put("OElig", "\u0152");
        entities.put("Ograve", "\u00d2");
        entities.put("Omega", "\u03a9");
        entities.put("Omicron", "\u039f");
        entities.put("Oslash", "\u00d8");
        entities.put("Otilde", "\u00d5");
        entities.put("Ouml", "\u00d6");
        entities.put("Phi", "\u03a6");
        entities.put("Pi", "\u03a0");
        entities.put("Prime", "\u2033");
        entities.put("Psi", "\u03a8");
        entities.put("Rho", "\u03a1");
        entities.put("Scaron", "\u0160");
        entities.put("Sigma", "\u03a3");
        entities.put("Tau", "\u03a4");
        entities.put("Theta", "\u0398");
        entities.put("THORN", "\u00de");
        entities.put("Uacute", "\u00da");
        entities.put("Ucirc", "\u00db");
        entities.put("Ugrave", "\u00d9");
        entities.put("Upsilon", "\u03a5");
        entities.put("Uuml", "\u00dc");
        entities.put("Xi", "\u039e");
        entities.put("Yacute", "\u00dd");
        entities.put("Yuml", "\u0178");
        entities.put("Zeta", "\u0396");
        entities.put("upsih", "\u03d2");
        entities.put("upsilon", "\u03c5");
        entities.put("uuml", "\u00fc");
        entities.put("weierp", "\u2118");
        entities.put("xi", "\u03be");
        entities.put("yacute", "\u00fd");
        entities.put("yen", "\u00a5");
        entities.put("yuml", "\u00ff");
        entities.put("zeta", "\u03b6");
        entities.put("zwj", "\u200d");
        entities.put("zwnj", "\u200c");
        return entities;
    }
    
    static public String resolveEntity(String entity) {
        if (entity_map.containsKey(entity)) {
            return (String)entity_map.get(entity);
        } else {
            return "&"+entity+";";
        }
    }
}
