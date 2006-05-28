package uk.org.catnip.eddie;

public class Author {
    private String name = "";
    private String email = "";
    private String href = "";
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
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("name: '" + this.name+ "', ");
        sb.append("email: '" + this.email+ "', ");
        sb.append("url: '" + this.href +"'");
        sb.append("}");
        return sb.toString();
    }
}
