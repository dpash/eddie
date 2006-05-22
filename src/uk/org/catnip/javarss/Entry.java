package uk.org.catnip.javarss;

public class Entry extends FeedContext {
    public String toString() {
        String ret = "";
        ret += property_map.toString();
        return ret;
     }
}
