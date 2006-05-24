package uk.org.catnip.eddie;

public class Author {
    private String name = "";
    private String email = "";
    private String url = "";
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("name: '" + this.name+ "', ");
        sb.append("email: '" + this.email+ "', ");
        sb.append("url: '" + this.url +"'");
        sb.append("}");
        return sb.toString();
    }
}
