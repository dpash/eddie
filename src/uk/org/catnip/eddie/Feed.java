package uk.org.catnip.eddie;

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.util.Iterator;
import uk.org.catnip.eddie.Detail;

public class Feed extends FeedContext {
    public boolean error = false;
    private Detail tagline;
    private Detail info;
    private Detail copyright;
    private Generator generator;
    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    public String toString() {
       StringBuilder ret = new StringBuilder();
       //ret.append("bozo: " + error);
       ret.append(property_map.toString());
       if (author != null) {
       ret.append("author = " + author);
       }
       if (info != null) {
           ret.append("info = " + info);
           }
       if (tagline != null) {
           ret.append("tagline = " + tagline);
           }
       if (generator != null) {
           ret.append("generator = " + generator);
       }
       if (!contributors.isEmpty()) {
           ret.append("contributors = " + contributors);
       }
       if (!links.isEmpty()) {
       ret.append("links = " + links.toString());
       }
       if (getTitle() != null) {
           ret.append("title = " + getTitle());
           }
       if (!entries.isEmpty()) {
       ret.append("entries = " + entries.toString());
       }
       return ret.toString();
    }
    List entries = new LinkedList();
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
    
    public Iterator entries() {
        return entries.iterator();
    }

    public Detail getTagline() {
        return tagline;
    }

    public void setTagline(Detail tagline) {
        this.tagline = tagline;
    }

    public Detail getInfo() {
        return info;
    }

    public void setInfo(Detail info) {
        this.info = info;
    }

    public Detail getCopyright() {
        return copyright;
    }

    public void setCopyright(Detail copyright) {
        this.copyright = copyright;
    }
    
}
