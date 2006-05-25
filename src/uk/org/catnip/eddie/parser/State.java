package uk.org.catnip.eddie.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.Hashtable;
import org.apache.log4j.Logger;

public class State {
    static Logger log = Logger.getLogger(State.class);

    private static Map element_aliases = createElementAliases();

    private static Map namespace_aliases = createNamespaceAliases();

private static Map createNamespaceAliases() {
        Map aliases = new Hashtable();
        //aliases.put( "", "http://backend.userland.com/rss");
        //aliases.put( "", "http://blogs.law.harvard.edu/tech/rss");
        //aliases.put( "", "http://purl.org/rss/1.0/");
        //aliases.put( "", "http://my.netscape.com/rdf/simple/0.9/");
        //aliases.put( "", "http://example.com/newformat#");
        //aliases.put( "", "http://example.com/necho");
        //aliases.put( "", "http://purl.org/echo/");
        //aliases.put( "", "uri/of/echo/namespace#");
        //aliases.put( "", "http://purl.org/pie/");
        //aliases.put( "", "http://purl.org/atom/ns#");
        //aliases.put( "", "http://purl.org/rss/1.0/modules/rss091#");

        aliases.put( "admin", "http://webns.net/mvcb/");
        aliases.put( "ag", "http://purl.org/rss/1.0/modules/aggregation/");
        aliases.put( "annotate", "http://purl.org/rss/1.0/modules/annotate/");
        aliases.put( "audio", "http://media.tangent.org/rss/1.0/");
        aliases.put( "blogChannel", "http://backend.userland.com/blogChannelModule");
        aliases.put( "cc", "http://web.resource.org/cc/");
        aliases.put( "creativeCommons", "http://backend.userland.com/creativeCommonsRssModule");
        aliases.put( "co", "http://purl.org/rss/1.0/modules/company");
        aliases.put( "content", "http://purl.org/rss/1.0/modules/content/");
        aliases.put( "cp", "http://my.theinfo.org/changed/1.0/rss/");
        aliases.put( "dc", "http://purl.org/dc/elements/1.1/");
        aliases.put( "dcterms", "http://purl.org/dc/terms/");
        aliases.put( "email", "http://purl.org/rss/1.0/modules/email/");
        aliases.put( "ev", "http://purl.org/rss/1.0/modules/event/");
        aliases.put( "icbm", "http://postneo.com/icbm/");
        aliases.put( "image", "http://purl.org/rss/1.0/modules/image/");
        aliases.put( "foaf", "http://xmlns.com/foaf/0.1/");
        aliases.put( "fm", "http://freshmeat.net/rss/fm/");
        aliases.put( "l", "http://purl.org/rss/1.0/modules/link/");
        aliases.put( "pingback", "http://madskills.com/public/xml/rss/module/pingback/");
        aliases.put( "prism", "http://prismstandard.org/namespaces/1.2/basic/");
        aliases.put( "rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        aliases.put( "rdfs", "http://www.w3.org/2000/01/rdf-schema#");
        aliases.put( "ref", "http://purl.org/rss/1.0/modules/reference/");
        aliases.put( "reqv", "http://purl.org/rss/1.0/modules/richequiv/");
        aliases.put( "search", "http://purl.org/rss/1.0/modules/search/");
        aliases.put( "slash", "http://purl.org/rss/1.0/modules/slash/");
        aliases.put( "ss", "http://purl.org/rss/1.0/modules/servicestatus/");
        aliases.put( "str", "http://hacks.benhammersley.com/rss/streaming/");
        aliases.put( "sub", "http://purl.org/rss/1.0/modules/subscription/");
        aliases.put( "sy", "http://purl.org/rss/1.0/modules/syndication/");
        aliases.put( "taxo", "http://purl.org/rss/1.0/modules/taxonomy/");
        aliases.put( "thr", "http://purl.org/rss/1.0/modules/threading/");
        aliases.put( "ti", "http://purl.org/rss/1.0/modules/textinput/");
        aliases.put( "trackback", "http://madskills.com/public/xml/rss/module/trackback/");
        aliases.put( "wfw", "http://wellformedweb.org/CommentAPI/");
        aliases.put( "wiki", "http://purl.org/rss/1.0/modules/wiki/");
        aliases.put( "soap", "http://schemas.xmlsoap.org/soap/envelope/");
        aliases.put( "xhtml", "http://www.w3.org/1999/xhtml");
        aliases.put( "xml", "http://www.w3.org/XML/1998/namespace");
            return aliases;
}   

private static Map createElementAliases() {
        Map aliases = new Hashtable();
        aliases.put("abstract", "description");
        aliases.put("body", "content");
        aliases.put("content_encoded", "body");
        aliases.put("dcterms:created", "created");
        aliases.put("dc:author", "author");
        aliases.put("dc:creator", "author");
        aliases.put("dc:date", "modified");
        aliases.put("dc:language", "language");
        aliases.put("dc:publisher", "webmaster");
        aliases.put("dc:rights", "copyright");
        aliases.put("dc:subject", "category");
        aliases.put("dc:title", "title");
        aliases.put("dcterms:modified", "modified");
        aliases.put("item", "entry");
        aliases.put("feedinfo", "channel");
        aliases.put("fullitem", "body");
        aliases.put("homepage", "url");
        aliases.put("keywords", "category");
        aliases.put("dcterms:issued", "issued");
        aliases.put("managingeditor", "author");
        aliases.put("product", "item");
        aliases.put("producturl", "link");
        aliases.put("pubdate", "modified");
        aliases.put("published", "dcterms_created");
        aliases.put("uri", "url");
        aliases.put("xhtml_body", "body");
        aliases.put("updated", "modified");
        return aliases;
    }

    public State() {
    }

    public State(String uri, String localName, String qName) {
        this.uri = uri;
        this.element = aliasElement(localName);
        this.qName = qName;
    }

    public State(String uri, String localName, String qName, Attributes atts,
            State prev) {
        this.uri = uri;
        this.element = aliasElement(localName);
        this.qName = qName;
        this.atts = atts;
        this.type = this.getAttr("type", prev.type);
        this.mode = this.getAttr("mode",prev.mode);
        if (this.type == null || this.type.equals("")) {
            this.type = "text/plain";
        }
        this.language = this.getAttr("xml:lang", prev.getLanguage());
        this.base = this.getAttr("xml:base", prev.getBase());

        
    }

    private String uri;
    public boolean content = false;
    private String localName;

    private String element;

    public String qName;

    public Attributes atts = new AttributesImpl();

    private String language;

    public String base;

    public String mode;

    public String type;

    public boolean expectingText = false;

    public StringBuilder text = new StringBuilder();

    public void addText(String str) {
        text.append(str);
    }

    public String getAttr(String key) {
        return this.getAttr(key, null);
    }

    public String getAttr(String key, String default_value) {
        //String namespace;
        //if (key.indexOf(':') > 0) {
        //namespace = key.substring(0,key.indexOf(':'));
        //key = key.substring(key.indexOf(':')+1, key.length());
        //} else {
        //    namespace = "";
        //}
        //log.debug("{"+namespace+"}"+key);
        String ret = atts.getValue(key);
        if (ret == null) {
            ret = default_value;
        }
        log.trace("getAttr: "+ key + " = '" + ret +"'");
        return ret;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }
    private String aliasNamespace(String element) {
        if (namespace_aliases.containsKey(element)) {
            log.trace("aliasing namespace " + element + " to "
                    + (String) namespace_aliases.get(element));
            return (String) namespace_aliases.get(element);
        }
        return element;
    }
    private String aliasElement(String element) {
        if (element_aliases.containsKey(element)) {
            log.trace("aliasing " + element + " to "
                    + (String) element_aliases.get(element));
            return (String) element_aliases.get(element);
        }
        return element;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
