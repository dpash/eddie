package uk.org.catnip.javarss;

import uk.org.catnip.javarss.Detail;

public class Link extends Detail {
    private String href;
    private String title;
    private String rel;
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");        
        sb.append("href: '" + this.href + "', ");
        sb.append("title: '" + this.title + "', ");
        sb.append("rel: '" + this.rel + "', ");
        sb.append("type: '" + this.getType()+ "', ");
        sb.append("language: '" + this.getLanguage() + "', ");
        sb.append("value: '" + this.getValue() +"'");
        sb.append("}");
        return sb.toString();
    }
}
