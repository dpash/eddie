package uk.org.catnip.javarss;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class Entry extends FeedContext {
    private List content = new LinkedList();
    
    
    public String toString() {
        String ret = "";
        ret += property_map.toString();
        return ret;
     }
    
    public void addContent(Detail c) {
        content.add(c);
    }
 
    
    public Iterator contents() {
        return content.iterator();
    }


}
