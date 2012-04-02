package uk.org.catnip.eddie.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.xml.sax.SAXException;

import uk.org.catnip.eddie.FeedData;
import uk.org.catnip.eddie.Link;

public class HTMLSAXParser extends BaseSAXParser {
    @NotNull
    FeedData feed = new FeedData();
    @Nullable
    Link link;
    public void startDocument() throws SAXException {
        log.trace("startDocument:" + this.filename);
        feed.set("format", "html");
    }
    
    public void startElement_link(@NotNull State state) throws SAXException {
        link = new Link();
        link.setHref(state.getAttr("src"));
        link.setType(state.getAttr("type"));
        link.setRel(state.getAttr("rel"));
        feed.addLink(link);
        link = null;
    }
    public void startElement_a(@NotNull State state) throws SAXException {
        state.setExpectingText(true);
        link = new Link();
        link.setHref(state.getAttr("href"));
        push(state);
    }
    public void endElement_a() { 
        String linktext = pop("a");
        if (link != null) {
            link.setTitle(linktext);
            feed.addLink(link);
            link = null;
        }
    }
}
