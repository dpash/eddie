package uk.org.catnip.eddie;

import java.util.List;
import java.util.LinkedList;
import java.lang.StringBuilder;
import java.util.Iterator;
import uk.org.catnip.eddie.Detail;

public class Feed extends FeedContext {
    public boolean error = false;
    private Detail info;
    private Detail subtitle;
    private Generator generator;
    private Image image;
    private TextInput textinput;
    public TextInput getTextinput() {
        return textinput;
    }

    public void setTextinput(TextInput textinput) {
        this.textinput = textinput;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

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
       ret.append("author = " + author + ", ");
       }
       if (info != null) {
           ret.append("info = " + info + ", ");
           }
       if (image != null) {
           ret.append("image = " + image + ", ");
           }
       if (textinput != null) {
           ret.append("textinput = " + textinput + ", ");
           }
       if (generator != null) {
           ret.append("generator = " + generator + ", ");
       }
       if (!contributors.isEmpty()) {
           ret.append("contributors = " + contributors + ", ");
       }
       if (!links.isEmpty()) {
       ret.append("links = " + links + ", ");
       }
       if (getTitle() != null) {
           ret.append("title = " + getTitle() + ", ");
           }
       if (!categories.isEmpty()){
           ret.append("categories = " + categories + ", ");
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

    public Detail getInfo() {
        return info;
    }

    public void setInfo(Detail info) {
        this.info = info;
    }

    public Detail getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Detail subtitle) {
        this.subtitle = subtitle;
    }
    
}
