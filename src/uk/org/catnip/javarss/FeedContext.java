package uk.org.catnip.javarss;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

public class FeedContext {
    protected Hashtable property_map = new Hashtable();
    protected Author author;
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
    

}
