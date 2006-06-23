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

import java.util.List;
import java.util.Arrays;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.SAXParseException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.XMLReader;

import java.io.StringReader;
public class Sanitize {
    static Logger log = Logger.getLogger(Sanitize.class);

    static String[] acceptable_elements_array = {"a", "abbr", "acronym", "address", "area", "b", "big",
        "blockquote", "br", "button", "caption", "center", "cite", "code", "col",
        "colgroup", "dd", "del", "dfn", "dir", "div", "dl", "dt", "em", "fieldset",
        "font", "form", "h1", "h2", "h3", "h4", "h5", "h6", "hr", "i", "img", "input",
        "ins", "kbd", "label", "legend", "li", "map", "menu", "ol", "optgroup",
        "option", "p", "pre", "q", "s", "samp", "select", "small", "span", "strike",
        "strong", "sub", "sup", "table", "tbody", "td", "textarea", "tfoot", "th",
        "thead", "tr", "tt", "u", "ul", "var"};
    static String[] acceptable_attributes_array = { "abbr", "accept", "accept-charset",
            "accesskey", "action", "align", "alt", "axis", "border",
            "cellpadding", "cellspacing", "char", "charoff", "charset",
            "checked", "cite", "class", "clear", "cols", "colspan",
            "color", "compact", "coords", "datetime", "dir", "disabled",
            "enctype", "for", "frame", "headers", "height", "href",
            "hreflang", "hspace", "id", "ismap", "label", "lang",
            "longdesc", "maxlength", "media", "method", "multiple", "name",
            "nohref", "noshade", "nowrap", "prompt", "readonly", "rel",
            "rev", "rows", "rowspan", "rules", "scope", "selected",
            "shape", "size", "span", "src", "start", "summary", "tabindex",
            "target", "title", "type", "usemap", "valign", "value",
            "vspace", "width" };
    static String[] elements_no_end_tag_array = { "area", "base", "basefont", "br", "col", "frame",
            "hr", "img", "input", "isindex", "link", "meta", "param" };
    static String[] url_attributes_array = {"href", "src"};
    static String[] unsafe_content_elements_array = {"script", "applet"};
    static List acceptable_elements = Arrays.asList(acceptable_elements_array);
    static List acceptable_attributes = Arrays.asList(acceptable_attributes_array);
    static List url_attributes = Arrays.asList(url_attributes_array);
    static List unsafe_content_elements = Arrays.asList(unsafe_content_elements_array);
    static List elements_no_end_tag = Arrays.asList(elements_no_end_tag_array);

    static XMLReader xr;
    
    /**
     * Class to parse html and remove unsafe elements and attributes
     * @author David Pashley <david@davidpashley.com>
     *
     */
    class HTMLSAXParser extends DefaultHandler2 {
        private int errors = 0;
        private boolean started_document = false;
        private State state;

        private boolean unsafe_content = false;
        private static final int MAX_ERRORS=2000;
        private StringBuilder sb = new StringBuilder();
        protected Locator locator;
        public void setDocumentLocator(final Locator locator) {
            this.locator = locator;
        }
        public void ignorableWhitespace(char[] ch, int start, int length) {
            String data =  new String(ch, start,length);
            log.trace("ignorableWhitespace: '"+ data+"'");
            sb.append(data);
        }
        public void characters(char[] ch, int start, int length) {
            if (unsafe_content) { return;}
            String data =  new String(ch, start,length);
            log.trace("characters: '"+ data+"'");
            if (errors > 0 && data.contains(">")) {
                data = data.substring(data.indexOf(">")+1);
            }
            
            sb.append(data);
            
        }
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            started_document = true;
            
            log.trace("startElement: "+ localName);
            if (!acceptable_elements.contains(localName)) { 
                if (unsafe_content_elements.contains(localName)) {
                    unsafe_content = true;
                }
                return; 
            }
            sb.append("<");
            sb.append(localName);
            
            
            for (int i = 0; i < atts.getLength(); i++) {
                if (!acceptable_attributes.contains(atts.getLocalName(i))) {
                    continue;
                }
                String attribute = atts.getLocalName(i);
                String value = atts.getValue(i);
                if (url_attributes.contains(attribute)) {
                    value = state.resolveUri(Entities.decodeEntities(value));
                }
                sb.append(" ");
                sb.append(attribute);
                sb.append("=\"");
                sb.append(value);
                sb.append("\"");
            }
            if (elements_no_end_tag.contains(localName)) {
                sb.append(" /");
            }
            sb.append(">");
        }
        public void endElement(String uri, String localName, String qName)
        throws SAXException {

            log.trace("endElement: "+ localName);
            if (!acceptable_elements.contains(localName)) {
                if (unsafe_content_elements.contains(localName)) {
                    unsafe_content = false;
                }
                return; 
            }
            if (elements_no_end_tag.contains(localName)) { return; }
            sb.append("</");
            sb.append(localName);
            sb.append(">");
        }
        public void comment(char[] ch, int start, int length) throws SAXException {
            if (!started_document) { return; }
            String data =  new String(ch, start,length);
            log.trace("comment: '"+ data+"'");            
            sb.append("<!--"+data+"-->");
        }
        public void endDocument()
        throws SAXException {
            log.trace("endDocument()");

        }
        // ErrorHandler
        public void warning(SAXParseException exception) throws SAXException {
            log.debug("warning: " + exception.getMessage());
            // throw new SAXException(exception);
        }

        public void error(SAXParseException exception) throws SAXException {
            //errors++;
            log.debug("error: " + exception.getMessage());
            if (errors > MAX_ERRORS) {
            throw new SAXParseException("too many errors", locator, exception);
            }
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            errors++;
            log.debug("fatalError: " + exception.getMessage());
            if (errors > MAX_ERRORS) {
            	throw new SAXParseException("too many errors", locator, exception);
               
                }
            // throw new SAXException(exception);
        }
        public void setState(State state) {
            this.state = state;
        }
        public String getResult() {
            return sb.toString().trim();
        }
    }
    
    
    /**
     * Clean unsafe elements and attributes from html. This is the main entrance to the 
     * sanitizing subsystem
     * @param data string to be cleaned
     * @param state current state information
     * @return cleaned string
     */
    static public String clean(String data, State state) {
        Sanitize s = new Sanitize();
        data = data.replace("&", "&amp;");
        if (state.getType().equals("text/plain") && (!"base64".equals(state.getMode()))) {
            // Fix what we just broke
            log.debug(state);
            data = data.replace("&amp;lt;", "&lt;");
            data = data.replace("&amp;gt;", "&gt;");
        }
        return s.real_clean(data, state);
    }    
    
    
    /**
     * This is the class that actually does the sanitizing
     * @param data string to be cleaned
     * @param state current state information
     * @return cleaned string
     */
     public String real_clean(String data, State state) {
        String ret = data;
        if (!data.trim().startsWith("<!DOCTYPE")) {
            data = "<html>"+data+"</html>";
        }
        try {
            
            if (xr == null) {
                try {
                    xr = (XMLReader)Class.forName("org.ccil.cowan.tagsoup.Parser").newInstance();
                    xr.setFeature("http://www.ccil.org/~cowan/tagsoup/features/default-attributes", false);
                } catch (ClassNotFoundException ex) {
                    log.warn("couldn't load tagsoup. Using standard sax parser " + ex);
                    xr = new SAXParser();
                    xr.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
                    xr.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",false);
                }
            }
            HTMLSAXParser handler = new HTMLSAXParser();
            xr.setContentHandler(handler);
            xr.setErrorHandler(handler);
            xr.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
            
            StringReader r = new StringReader(data);
            handler.setState(state);
            xr.parse(new InputSource(r));
            ret = handler.getResult();
        } catch (SAXParseException e) {
            log.info("error", e);
        } catch (SAXException e) {
            log.debug("error", e);
        } catch (Exception e) {
            log.error("error", e);
        }
        
        
        log.debug("Cleaned: '"+data+"'");
        log.debug("     to: '"+ret+"'");
        return ret;
    }
    

    /**
     * Rebuild text from a startElement SAX event
     * Don't need to clean as this will happen later anyway.
     * TODO: Should probably move this to BaseSAXParser
     * @param state current sax event
     * @return text representation of the SAX event
     */
    static public String clean_html_start(State state) {
        StringBuilder sb = new StringBuilder();    
        sb.append("<");
        sb.append(state.getElement());
        for (int i = 0; i < state.getAttributes().getLength(); i++) {
            String attribute = state.getAttributes().getQName(i);
            String value = state.getAttributes().getValue(i);
            sb.append(" ");
            sb.append(attribute);
            sb.append("=\"");
            sb.append(value);
            sb.append("\"");
        }
        if (elements_no_end_tag.contains(state.getElement())) {
            sb.append(" /");
        }
        sb.append(">");
        return sb.toString();
    }

    /**
     * Rebuild text from a endElement SAX event
     * @param tag end tag
     * @return text representation of the SAX event
     */
    static public String clean_html_end(String tag) {
        if (elements_no_end_tag.contains(tag)) {
            return "";
        } else {
            return "</" + tag + ">";
        }
    }

}
