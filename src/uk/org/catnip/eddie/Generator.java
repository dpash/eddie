package uk.org.catnip.javarss;

public class Generator extends Detail {
    private String name;
    private String url;
    private String version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("name: '" + this.getName()+ "', ");
        sb.append("url: '" + this.getUrl()+ "', ");
        sb.append("version: '" + this.getVersion()+ "', ");
        sb.append("type: '" + this.getType()+ "', ");
        sb.append("language: '" + this.getLanguage() + "', ");
        sb.append("value: '" + this.getValue() +"'");
        sb.append("}");
        return sb.toString();
    }
}
