package uk.org.catnip.javarss;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Entry extends FeedContext {
    private List content = new LinkedList();
    
    
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(property_map.toString());
        ret.append("content =" + content + ", ");
        ret.append("title_detail = " + this.getTitle() + ", ");
        ret.append("contributors = " + contributors + ", ");
        ret.append("links = " + links);
        return ret.toString();
     }
    
    public void addContent(Detail c) {
        content.add(c);
    }
 
    
    public Iterator contents() {
        return content.iterator();
    }


}
