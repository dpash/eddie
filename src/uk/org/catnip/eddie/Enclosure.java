package uk.org.catnip.eddie;

public class Enclosure {
    private String url;
    private String length;
    private String type;
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("url: '" + this.url+ "', ");
        sb.append("length: '" + this.length+ "', ");
        sb.append("type: '" + this.type +"'");
        sb.append("}");
        return sb.toString();
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

}
