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
        aliases.put("abstract","description");
        //"content"           => "body",
        //"content_encoded"   => "body",
        //"created"           => "dcterms_created",
        //"dc_author"         => "author",
        //"dc_creator"        => "author",
        //"dc_date"           => "modified",
        //"dc_language"       => "language",
        //"dc_publisher"      => "webmaster",
        //"dc_rights"         => "copyright",
        //"dc_subject"        => "category",
        //"dc_title"          => "title",
        //"dcterms:modified"  => "modified",
        //"entry"             => "item",
        //"feedinfo"          => "channel",
        //"fullitem"          => "body",
        aliases.put("homepage", "url");
        //"keywords"          => "category",
        //"issued"            => "dcterms_issued",
        //"managingeditor"    => "author",
        //"product"           => "item",
        //"producturl"        => "link",
        //"pubdate"           => "modified",
        //"published"         => "dcterms_created",
        //"uri"               => "url",
        //"xhtml_body"        => "body",
        //"updated"           => "modified",
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
        log.trace("aliasing "+element + " to "+(String)element_aliases.get(element) );
        return (String)element_aliases.get(element);
    }
    return element;
}
}
