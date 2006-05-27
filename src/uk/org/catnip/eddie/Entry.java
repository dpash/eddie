package uk.org.catnip.eddie;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Entry extends FeedContext {
    private List content = new LinkedList();
    private List enclosures = new LinkedList();
    
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(property_map.toString());
        if (!content.isEmpty()) {
        ret.append("content =" + content + ", ");
        }
        if (this.getTitle() != null) {
        ret.append("title_detail = '" + this.getTitle() + "', ");
        }
        if (!contributors.isEmpty()) {
        ret.append("contributors = " + contributors + ", ");
        }
        if (!links.isEmpty()){
        ret.append("links = " + links);
        }
        if (!categories.isEmpty()){
            ret.append("categories = " + categories);
            }
        if (getCreated() != null){
            ret.append("created = " + getCreated());
            }
        if (getIssued() != null){
            ret.append("issued = " + getIssued());
            }
        if (getModified() != null){
            ret.append("modifed = " + getModified());
            }
        return ret.toString();
     }
    
    public void addContent(Detail c) {
        content.add(c);
    }
 
    
    public Iterator contents() {
        return content.iterator();
    }

    public void addEnclosure(Enclosure e) {
        enclosures.add(e);
    }
 
    public Iterator enclosures() {
        return enclosures.iterator();
    }
}
