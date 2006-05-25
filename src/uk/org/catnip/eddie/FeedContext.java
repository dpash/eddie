package uk.org.catnip.eddie;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import uk.org.catnip.eddie.Link;
import org.apache.log4j.Logger;
public class FeedContext {
    static Logger log = Logger.getLogger(FeedContext.class);
    protected Hashtable property_map = new Hashtable();
    protected Author author;
    private Detail title;
    private Date issued;
    private Date modified;
    private Date created;
    protected List contributors = new LinkedList();
    protected List links = new LinkedList();
    private Detail summary;
    public String set(String key, String value) {
        property_map.put(key,value);
        return value;
    }
    public String get(String key){
        return (String)property_map.get(key);
    }
    public boolean has(String key) {
        return property_map.containsKey(key);
    }
    public Iterator keys() {
        return property_map.keySet().iterator();
    }
    
    public Hashtable getHashtable() {
        return property_map;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public void addContributor(Author a) {
        contributors.add(a);
    }
    public void addLink(Link link) {
        log.trace("adding link: " + link);
        links.add(link);
    }
    public Iterator contributors() {
        return contributors.iterator();
    }
    public Iterator links() {
        return links.iterator();
    }
    public Detail getTitle() {
        return title;
    }

    public void setTitle(Detail title_detail) {
        this.title = title_detail;
    }
    public Detail getSummary() {
        return summary;
    }
    public void setSummary(Detail summary) {
        this.summary = summary;
    }
    public Date getIssued() {
        return issued;
    }
    public void setIssued(Date issued) {
        this.issued = issued;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }

}
