package uk.org.catnip.javarss.parser;

import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.apache.log4j.Logger;
import uk.org.catnip.javarss.Entry;
import uk.org.catnip.javarss.Author;
import uk.org.catnip.javarss.FeedContext;


public class FeedSAXParser extends BaseSAXParser {
    private Author author;
    
    static Logger log = Logger.getLogger(FeedSAXParser.class);

    public void startElement_title(State state) throws SAXException {
        in_content++;
        state.mode = state.getAttr("mode", "escaped");
        state.type = state.getAttr("type", "text/plain");
        state.expectingText = (this.in_feed || this.in_entry);
        push(state);

    }

    public void startElement_feed(State state) throws SAXException {

        this.in_feed = true;
        String attr_version = state.getAttr("version");
        String attr_namespace = state.getAttr("xmlns");
        if (!this.feed.has("format"))
            if (attr_version.equals("")) {
                if (attr_namespace.equals("http://www.w3.org/2005/Atom")) {
                    this.feed.set("format","atom10");
                } else {
                    this.feed.set("format","atom");
                }
            } else {
                if (attr_version.equals("0.1")) {
                    this.feed.set("format", "atom01");
                } else if (attr_version.equals("0.2")) {
                    this.feed.set("format","atom02");
                } else if (attr_version.equals("0.3")) {
                    this.feed.set("format","atom03");
                } else {
                    this.feed.set("format","atom");
                }
            }
        // Do some sanity checking
        if (!this.feed.has("format")) {
            throw new SAXParseException("Failed to detect Atom format",
                    this.locator);
        }
    }

    public void endElement_title() throws SAXException {
        String content = pop("title");
        in_content--;
    }
    public void startElement_entry(State state) throws SAXException {
        in_entry = true;
        current_entry = new Entry();
        state.expectingText = false;
        push(state);
    }
    public void endElement_entry() throws SAXException {
        String content = pop("entry");
        in_entry = false;
        feed.addEntry(current_entry);
        current_entry = null;
    }
    
    public void startElement_url(State state) throws SAXException {
        in_author = true;
        state.expectingText = true;
        push(state);
    }  
    
    public void startElement_author(State state) throws SAXException {
        in_author = true;
        state.expectingText = true;
        author = new Author();
        push(state);
    }  
    public void endElement_author() throws SAXException {
        in_author = false;
        pop("author");
        getCurrentContext().setAuthor(author);
        author = null;
    }
    
    public void endElement_name() throws SAXException {
       String value = pop("name");
       if (in_author) {
           save_author("name", value);
       } else if (in_contributor) {
           save_contributor("name", value);
       //} else if (in_textinput){
       //FeedContext context = getCurrentContext();
       //context.getTextInput().set("name", value);
       }

    }  
    public void endElement_email() throws SAXException {
    String value = pop("email");
    if (in_author) {
       save_author("email", value);
    } else if (in_contributor) {
       save_contributor("email", value);
    }
    }
    public void endElement_url() throws SAXException {
    
    String value = pop("url");
    if (in_author) {
       save_author("url", value);
    }else if (in_contributor) {
      save_contributor("url", value);
    }else if (in_image) {
       // TODO
    }else if (in_contributor) {
       // TODO
    }
    }
    
    public void sync_author_details() {
        log.debug("sync_author_details: not implemented");
}
    public void save_author(String key, String value) {
        log.debug("save_authors: not implemented");
}    
    public void save_contributor(String key, String value) {
        log.debug("save_contributors: not implemented");
}
    
}
