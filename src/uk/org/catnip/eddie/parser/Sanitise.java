package uk.org.catnip.eddie.parser;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import java.util.regex.*;

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
    static List acceptable_elements = Arrays.asList(acceptable_elements_array);
    static List acceptable_attributes = init_acceptable_attribues();

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

    static public String handle_inline_data(String string) {
        log.debug("handle_inline_data('"+string+"')");
        Matcher endtag_regex = Pattern.compile("^/(\\w+)\\s*>(.*)").matcher(string);
        Matcher starttag_regex = Pattern.compile("^(\\w+)(\\s+([^/>]+)|\\s*)(/?)>(.*)").matcher(string);
        if (endtag_regex.matches()) {
            // We have an end tag
            String endtag = endtag_regex.group(1);
            log.debug("found end element: " +endtag);
            if (acceptable_elements.contains(endtag)) {
                return "<" + string;
            } else {
                return endtag_regex.group(2);
            }
        } else if(starttag_regex.matches()) {
            String starttag = starttag_regex.group(1);
            boolean endtag = starttag_regex.group(4).equals("/");
            String attributes = starttag_regex.group(3);
            log.debug("found start element: " +starttag);
            if (acceptable_elements.contains(starttag)) {
                StringBuilder sb = new StringBuilder(); 
                sb.append("<");
                sb.append(starttag);
                if (!attributes.equals("")){
                    sb.append(" "+ attributes);
                }
                if (endtag) {
                    sb.append("/");
                }
                sb.append(">");
                sb.append(starttag_regex.group(5));
                return sb.toString();
            } else {
                return starttag_regex.group(5);
            }
        }
        return "<" + string;
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

            sb.append(" ");
            sb.append(state.atts.getLocalName(i));
            sb.append("=\"");
            sb.append(state.atts.getValue(i));
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
