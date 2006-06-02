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

import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.apache.log4j.Logger;
import java.util.Stack;
import org.apache.commons.codec.binary.Base64;
import uk.org.catnip.eddie.Detail;
import uk.org.catnip.eddie.parser.Entities;

/**
 * @author David Pashley <david@davidpashley.com>
 *
 */
public class BaseSAXParser extends DefaultHandler2 {
    static Logger log = Logger.getLogger(BaseSAXParser.class);

    /**
     * Set if there has been any XML parsing errors
     */
    protected boolean error;
    protected Locator locator;

    protected String filename;
    private String contentLocation;
    private String contentLanguage;
    protected Detail detail;

    protected int in_content = 0;
    private Stack<State> stack = new Stack<State>();

    public void setFilename(final String file) {
        this.filename = file;
    }

    public void setDocumentLocator(final Locator locator) {
        this.locator = locator;
    }

    
    /**
     * Get the current state
     * It returns a reference to the top item on the stack
     * If the stack is empty (start of the document) we create a new 
     * State object and set the base and language values so they are inherited
     * throughout the document 
     * @return the current state
     */
    protected State getCurrentState() {
        if (!stack.empty()){
            return (State)stack.peek();
        } 
        State state = new State();

        if (contentLocation != null){  
            state.setBase(contentLocation);
        } else {
            state.setBase(filename);  
        }
        if(contentLanguage != null) {
            state.setLanguage(contentLanguage);
        }
        return state;
    }

    /**
     * Add a State object onto the state stack
     * If this is the first object on the stack set the base value
     * from values we've been given by the caller
     * @param state State object to add to stack
     */
    protected void push(final State state) {
 
        if (log.isDebugEnabled()) {
            log.debug("pushing "+ state);
        }
        stack.push(state);     
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        log.trace("startDocument:" + this.filename);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    public void endDocument() throws SAXException {
        log.trace("endDocument:" + this.filename);
    }

    /** 
     * Look for a method to deal with the state of the current element. 
     * Creates a new state object.
     * Deals with inline xml content
     * If the element method fails to push the state, we do it for them. 
     * We default to not expecting content
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(final String uri, final String localName, final String qName,
           final Attributes atts) throws SAXException {
        
        State state = new State(uri, localName, qName, atts, getCurrentState());

        log.trace("startElement:" + localName + " (" + state.getElement() + ")");

        if (in_content > 0 && "escaped".equals(state.getMode())) {
            state.setMode("xml");
        }
        if (in_content > 0 && "xml".equals(state.getMode())) {
            handle_data(state, Sanitize.clean_html_start(state), false);
            state.content = true;
            push(state);
            return;
        }
       
        try {
            Class[] argTypes = { State.class };
            Object[] values = {state };
            int stacksize = stack.size();
            this.getClass().getMethod("startElement_" + state.getElement(), argTypes)
                    .invoke(this, values);
            if (stack.size() == stacksize) {
                //naughty handler didn't push state
                push(state);
            }
        } catch (NoSuchMethodException e) {
            log.trace("unhandled element " + state.getElement());
            state.setExpectingText(true);
            push(state);
            // } catch (InvocationTargetException e) {
            // throw e.getCause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * Look for a method to deal with the end of the current element
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(final String uri, final String localName, final String qName)
            throws SAXException {
        
        State state = new State(uri, localName, qName);
        State prev = getCurrentState();
        log.trace("end_element: " + state);
        
        if (in_content > 0 && "escaped".equals(prev.getMode()) && prev.content) {
            prev.setMode("xml");
        }
        if (in_content > 0 && "xml".equals(prev.getMode()) && prev.content) {
            String data = pop(localName);
            handle_data(prev, data
                    + Sanitize.clean_html_end(state.getElement()), false);
            return;
        }
         
        try {
            this.getClass().getMethod("endElement_" + state.getElement(), (Class[])null)
                    .invoke(this, (Object[])null);
        } catch (NoSuchMethodException e) {
            log.trace("unhandled element " + state.getElement());
            pop(state.getElement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve state off the state stack and fill information into the Detail object
     * @param element name of the current element 
     * @return the text content of the element
     */
    protected String pop(final String element) {
        if (stack.empty()) { return ""; }
   
        if (!getCurrentState().getElement().equals(element)) { return "";}
        State state = (State)stack.pop();
        if (log.isDebugEnabled()) {
            log.debug("popping " + state);
        }
        String output = state.getText(); 
       
        // TODO: we should give Detail the current stack item
        // This would mean that we don't have BaseSAXParser knowing about 
        // objects it shouldn't, making it less tied to RSS and Atom
        detail = new Detail();
        detail.setBase(state.getBase());
        detail.setLanguage(state.getLanguage());
        detail.setType(state.getType());
        detail.setSrc(state.getAttr("src")); //TODO: this seems out of place
        if (!output.equals("")){
        detail.setValue(output);
        }
        if (!state.isExpectingText()) { return output; }
        
        if ("base64".equals(state.getMode())) {
            output = new String(Base64.decodeBase64(output.trim().getBytes()));
            detail.setValue(output);
        }
        if ("application/octet-stream".equals(state.getType())) {
            output = new String(Base64.decodeBase64(output.trim().getBytes()));
            detail.setValue(output);
        }
        
        if (in_content > 0) {
            output = Sanitize.clean(output, state);
            detail.setValue(output);
        }
        return output;
        
    }
    /** 
     * Deal with non markup characters
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length);

        if (in_content > 0) {
            if (getCurrentState().getType().equals("application/xhtml+xml")) {
                if (data.equals("<")) { data = "&lt;"; }
                if (data.equals(">")) { data = "&gt;"; }
                if (data.equals("&")) { data = "&amp;"; }
            } else if (getCurrentState().getType().equals("text/plain")) {
                if (data.equals("<")) { data = "&lt;"; }
            }
        }
        log.trace("characters: " + data);
        getCurrentState().addText(data);
    }

    
    public void handle_data(State state, String data, boolean escape) {
        if (stack.empty()) { return; }
        if (escape && state.getType().equals("application/xhtml+xml")) {
           data = xmlescape(data);
        }
        
        getCurrentState().addText(data);
    }
    
    public String xmlescape(String data) {
        data.replace("&", "&amp;");
        data.replace("<", "&lt;");
        data.replace(">", "&gt;");
        return data;
    }
   
   
    
    public void startEntity(java.lang.String name)
    throws SAXException {
        getCurrentState().addText(Entities.resolveEntity(name));
    }
    
    // ErrorHandler
    public void warning(SAXParseException exception) throws SAXException {
        log.debug("warning: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }

    public void error(SAXParseException exception) throws SAXException {
        this.error = true;
        log.debug("error: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        this.error = true;
        log.debug("fatalError: " + filename + ": " + exception.getMessage());
        // throw new SAXException(exception);
    }

    public String getContentLocation() {
        return contentLocation;
    }

    public void setContentLocation(String contentLocation) {
        this.contentLocation = contentLocation;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }
}
