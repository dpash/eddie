package uk.org.catnip.javarss;

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.util.Iterator;

public class Feed extends FeedContext {
    public boolean error = false;
    public String toString() {
       StringBuilder ret = new StringBuilder();
       ret.append("bozo: " + error);
       ret.append(property_map.toString());
       ret.append(entries.toString());
       return ret.toString();
    }
    List entries = new LinkedList();
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
    
    public Iterator entries() {
        return entries.iterator();
    }
    
}
