package uk.org.catnip.eddie;

public class Image {
    private String title;
    private String url;
    private String link;
    private String width;
    private String height;
    private String description;
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
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
        sb.append("href = '" + url + "', ");
        sb.append("link = '" + link + "', ");
        sb.append("width = '" + width + "', ");
        sb.append("height = '" + height + "', ");
        sb.append("description = '" + description + "'");
        sb.append("}");
        return sb.toString();
    }
}
