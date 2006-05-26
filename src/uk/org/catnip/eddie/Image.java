package uk.org.catnip.eddie;

public class Image {
    String title;
    String href;
    String link;
    String width;
    String height;
    String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("title = '" + title + "', ");
        sb.append("href = '" + href + "', ");
        sb.append("link = '" + link + "', ");
        sb.append("width = '" + width + "', ");
        sb.append("height = '" + height + "', ");
        sb.append("description = '" + description + "'");
        sb.append("}");
        return sb.toString();
    }
}
