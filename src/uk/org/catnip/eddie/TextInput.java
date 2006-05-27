package uk.org.catnip.eddie;

public class TextInput {
    private String title;
    private String link;
    private String name;
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("title = '" + title + "', ");
        sb.append("link = '" + link + "', ");
        sb.append("name = '" + name + "', ");
        sb.append("description = '" + description + "'");
        sb.append("}");
        return sb.toString();
    }
}
