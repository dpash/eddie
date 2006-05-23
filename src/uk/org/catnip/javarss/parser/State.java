package uk.org.catnip.javarss.parser;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.Hashtable;
import org.apache.log4j.Logger;
public class State {
    static Logger log = Logger.getLogger(BaseSAXParser.class);
    private static Map element_aliases = createElementAliases();
    private static Map createElementAliases(){
        Map aliases = new Hashtable();
        aliases.put("abstract"          , "description");
        aliases.put("content"           , "body");
        aliases.put("content_encoded"   , "body");
        aliases.put("created"           , "dcterms_created");
        aliases.put("dc_author"         , "author");
        aliases.put("dc_creator"        , "author");
        aliases.put("dc_date"           , "modified");
        aliases.put("dc_language"       , "language");
        aliases.put("dc_publisher"      , "webmaster");
        aliases.put("dc_rights"         , "copyright");
        aliases.put("dc_subject"        , "category");
        aliases.put("dc_title"          , "title");
        aliases.put("dcterms:modified"  , "modified");
        aliases.put("item"              , "entry");
        aliases.put("feedinfo"          , "channel");
        aliases.put("fullitem"          , "body");
        aliases.put("homepage"          , "url");
        aliases.put("keywords"          , "category");
        aliases.put("issued"            , "dcterms_issued");
        aliases.put("managingeditor"    , "author");
        aliases.put("product"           , "item");
        aliases.put("producturl"        , "link");
        aliases.put("pubdate"           , "modified");
        aliases.put("published"         , "dcterms_created");
        aliases.put("uri"               , "url");
        aliases.put("xhtml_body"        , "body");
        aliases.put("updated"           , "modified");
        return aliases;
    }
    public State() {}
    public State(String uri, String localName, String qName) {
        this.uri = uri;
        this.element = aliasElement(localName);
        this.qName = qName;
    }
     public State(String uri, String localName, String qName,
            Attributes atts, State prev) {
        this.uri = uri;
        this.element = aliasElement(localName);
        this.qName = qName;
        this.atts = atts;
        this.type = atts.getValue("type");
        if (this.type == null || this.type.equals("")) {
            this.type = prev.type;
        }
        if (this.type == null || this.type.equals("")) {
            this.type = "text/plain";
        }
        this.language = atts.getValue("xml:lang");
        if (this.language == null || this.language.equals("")) {
            this.language = prev.language;
        }
        if (this.language == null || this.language.equals("")) {
            this.language = "en";
        }
    }
    private String uri = "";
    private String localName = "";
    private String element = "";
    public String qName = "";
    public Attributes atts = new AttributesImpl();
    public String language = "";
    public String base = "";
    public String mode = "";
    public String type = "";
    public boolean expectingText = false;
    public StringBuilder text = new StringBuilder();
    
    public void addText(String str) {
        text.append(str);
    }
public String getAttr(String key) {
    return this.getAttr(key, "");
}
public String getAttr(String key, String default_value) {
    String ret = atts.getValue("", key);
    if (ret == null) {
        ret = default_value;
    }
    return ret;
}
public String getElement() {
    return element;
}
public void setElement(String element) {
    this.element = element;
}

private String aliasElement(String element) {
    if (element_aliases.containsKey(element)) {
        log.debug("aliasing "+element + " to "+(String)element_aliases.get(element) );
        return (String)element_aliases.get(element);
    }
    return element;
}
}
