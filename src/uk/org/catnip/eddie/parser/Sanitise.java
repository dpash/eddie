package uk.org.catnip.eddie.parser;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import java.util.regex.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import java.io.StringReader;
public class Sanitise {
    static Logger log = Logger.getLogger(Sanitise.class);

    static String[] acceptable_elements_array = {"a", "abbr", "acronym", "address", "area", "b", "big",
        "blockquote", "br", "button", "caption", "center", "cite", "code", "col",
        "colgroup", "dd", "del", "dfn", "dir", "div", "dl", "dt", "em", "fieldset",
        "font", "form", "h1", "h2", "h3", "h4", "h5", "h6", "hr", "i", "img", "input",
        "ins", "kbd", "label", "legend", "li", "map", "menu", "ol", "optgroup",
        "option", "p", "pre", "q", "s", "samp", "select", "small", "span", "strike",
        "strong", "sub", "sup", "table", "tbody", "td", "textarea", "tfoot", "th",
        "thead", "tr", "tt", "u", "ul", "var"};
    static String[] url_attributes_array = {"href", "src"};
    static List acceptable_elements = Arrays.asList(acceptable_elements_array);
    static List acceptable_attributes = init_acceptable_attribues();
    static List url_attributes = Arrays.asList(url_attributes_array);
    static List elements_no_end_tag = init_elements_no_end_tag();

    static private List init_acceptable_attribues() {
        List list = new LinkedList();
        String[] attributes = { "abbr", "accept", "accept-charset",
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
        for (int i = 0; i < attributes.length; i++) {
            list.add(attributes[i]);
        }
        return list;
    }

    static private List init_elements_no_end_tag() {
        List list = new LinkedList();
        String[] elements = { "area", "base", "basefont", "br", "col", "frame",
                "hr", "img", "input", "isindex", "link", "meta", "param" };
        for (int i = 0; i < elements.length; i++) {
            list.add(elements[i]);
        }
        return list;
    }
    class HTMLSAXParser extends DefaultHandler2 {
        private boolean error = false;
        private StringBuilder sb = new StringBuilder();
        public void characters(char[] ch, int start, int length) {
            
            String data =  new String(ch, start,length);
            log.debug("characters: '"+ data+"'");
            if (error && data.contains(">")) {
                data = data.substring(data.indexOf(">")+1);
            }
            
            sb.append(data);
            
        }
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            log.debug("startElement: "+ localName);
            if (!acceptable_elements.contains(localName)) { return; }
            sb.append("<");
            sb.append(localName);
            
            for (int i = 0; i < atts.getLength(); i++) {
                if (!acceptable_attributes.contains(atts.getLocalName(i))) {
                    continue;
                }
                String attribute = atts.getLocalName(i);
                String value = atts.getValue(i);
                //if (url_attributes.contains(attribute)) {
                //    value = resolveUri(value);
                //}
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
            log.debug("endElement: "+ localName);
            if (!acceptable_elements.contains(localName)) { return; }
            if (elements_no_end_tag.contains(localName)) { return; }
            sb.append("</");
            sb.append(localName);
            sb.append(">");
        }
        // ErrorHandler
        public void warning(SAXParseException exception) throws SAXException {
            log.debug("warning: " + exception.getMessage());
            // throw new SAXException(exception);
        }

        public void error(SAXParseException exception) throws SAXException {
            error = true;
            log.debug("error: " + exception.getMessage());
            // throw new SAXException(exception);
        }

        public void fatalError(SAXParseException exception) throws SAXException {
            error = true;
            log.debug("fatalError: " + exception.getMessage());
            // throw new SAXException(exception);
        }
        public String getResult() {
            return sb.toString();
        }
    }
    static public String clean(String data) {
        Sanitise s = new Sanitise();
        data = data.replace("&", "&amp;");
        return s.real_clean("<html>"+data+"</html>");
    }    
    public String real_clean(String data) {
        String ret = data;
        try {
        SAXParser xr = new SAXParser();
        HTMLSAXParser handler = new HTMLSAXParser();
        xr.setContentHandler(handler);
        xr.setErrorHandler(handler);
        xr.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
        xr.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);
        StringReader r = new StringReader(data);
        
        xr.parse(new InputSource(r));
        ret = handler.getResult();
        } catch (SAXException e) {
            log.debug(e);
        } catch (Exception e) {
            log.error(e);
        }
        
       
        log.debug("Cleaned: '"+data+"'");
        log.debug("     to: '"+ret+"'");
        return ret;
    }
    
 
    static public String clean_html_start(State state) {
        StringBuilder sb = new StringBuilder();
        if (!acceptable_elements.contains(state.getElement())) {
        // if (in($tag, $unacceptable_elements_with_end_tag)) {
        // $self->{unsafe_content} = 1;
        // }
        return "";
        }
        
        sb.append("<");
        sb.append(state.getElement());
        for (int i = 0; i < state.atts.getLength(); i++) {
            if (!acceptable_attributes.contains(state.atts.getLocalName(i))) {
                continue;
            }
            String attribute = state.atts.getLocalName(i);
            String value = state.atts.getValue(i);
            if (url_attributes.contains(attribute)) {
                value = state.resolveUri(value);
            }
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
        // }
        return sb.toString();
    }

    static public String clean_html_end(String tag) {
        if (!acceptable_elements.contains(tag)) {
        // if (in($tag, $unacceptable_elements_with_end_tag)) {
        // $self->{unsafe_content} = 0;
        // }
        return "";
        }
        if (elements_no_end_tag.contains(tag)) {
            return "";
        } else {
            return "</" + tag + ">";
        }
    }

}
