package uk.org.catnip.javarss.parser;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.apache.log4j.Logger;
import java.util.Stack;

import uk.org.catnip.javarss.Feed;
import uk.org.catnip.javarss.FeedContext;
import uk.org.catnip.javarss.Entry;
import uk.org.catnip.javarss.Detail;

public class BaseSAXParser extends DefaultHandler implements ErrorHandler {
    static Logger log = Logger.getLogger(BaseSAXParser.class);

    protected Locator locator;

    protected String filename;

    protected Feed feed = new Feed();
    protected Entry current_entry;
    protected Detail detail;

    protected boolean in_contributor = false;
    protected boolean in_author = false;
    protected boolean in_feed = false;
    protected boolean in_entry = false;
    protected boolean in_textinput = false;
    protected boolean in_image = false;
    protected int in_content = 0;
    
    protected Stack stack = new Stack();

    public void setFilename(String file) {
        this.filename = file;
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public Feed getFeed() {
        return feed;
    }

    protected State getCurrentState() {
        if (!stack.empty()){
            return (State)stack.peek();
        } 
        return new State();
    }

    protected void push(State state) {
        stack.push(state);     
    }

    protected FeedContext getCurrentContext() {
        if (in_entry) {
            return current_entry;
        } else {
            return feed;
        }
    }
    public void startDocument() throws SAXException {
        log.trace("startDocument:" + this.filename);
    }

    public void endDocument() throws SAXException {
        log.trace("endDocument:" + this.filename);
    }

    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        log.trace("startElement:" + localName);
        State state = new State(uri, localName, qName, atts, getCurrentState());

        try {
            Class[] argTypes = { State.class };
            Object[] values = {state };
            this.getClass().getMethod("startElement_" + state.getElement(), argTypes)
                    .invoke(this, values);
        } catch (NoSuchMethodException e) {
            log.trace("unhandled element " + state.getElement());
            state.expectingText = true;
            push(state);
            // } catch (InvocationTargetException e) {
            // throw e.getCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        log.trace("end_element: " + localName);
        State state = new State(uri, localName, qName);
        try {
            this.getClass().getMethod("endElement_" + state.getElement(), (Class[])null)
                    .invoke(this, (Object[])null);
        } catch (NoSuchMethodException e) {
            log.trace("unhandled element " + localName);
            pop(localName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String pop(String element) {
        if (stack.empty()) { return ""; }
        State state = (State)stack.pop();

        if (!state.getElement().equals(element)) { return "";}
        String output = state.text.toString(); 
        detail = new Detail();
        detail.setLanguage(state.language);
        detail.setType(state.type);
        detail.setValue(output);
        if (!state.expectingText) { return output; }
        
        // If mode == base64 base64-decode text
        // If mode == escaped and content-type == application/octet-stream
        // base64-decode test
        
        // resolve href links
        
        // If element can be relative url, resolve link

        if (in_entry) {
            if (element.equals("category")) {
                // $self->{entries}->[-1]->{$element} = $output;
                // $domain = $self->{entries}->[-1]->{'categories'}->[-1]->[0];
                // $self->{entries}->[-1]->{'categories'}->[-1] = [$domain, $output];
            } else if (element.equals("source")) {
                // $self->{entries}[-1]{'source'}{'value'} = $output;
            } else {
                current_entry.set(element,output);
                if (in_content > 0){
                    if (element.equals("description")) {
                        // $element = 'summary';
                    }
                    // $contentparams = deep_copy($self->{contentparams});
                    // $contentparams->{'value'} = $output;
                    // $self->{entries}->[-1]->{$element . '_detail'} = $contentparams;
                }
            }
        } else if (in_feed && !in_textinput && !in_image) {
            feed.set(element,output);
            if (element.equals("category")) {
                // $domain = $self->{feeddata}->{'categories'}->[-1]->[0];
                // $self->{feeddata}->{'categories'}->[-1] = [$domain, $output];
            } else if (in_content > 0) {
                if ( element.equals( "description")) {
                    element = "tagline";
                }
                // $contentparams = deep_copy($self->{contentparams});
                // $contentparams->{'value'} = $output;
                // $self->{feeddata}->{$element . '_detail'} = $contentparams;
            }
            
        }
        return output;
        
    }
    public void characters(char[] ch, int start, int length) {
        String data =  new String(ch, start,length);
        data.trim();
        log.trace("characters: "+data);
        getCurrentState().addText(data);
    }

    // ErrorHandler
    public void warning(SAXParseException exception) throws SAXException {
        log.debug("warning: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }

    public void error(SAXParseException exception) throws SAXException {
        feed.error = true;
        log.debug("error: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        feed.error = true;
        log.debug("fatalError: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }
}
