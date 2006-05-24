package uk.org.catnip.eddie;

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
        if (this.getName() != null) {
        sb.append("name: '" + this.getName()+ "', ");
        }
        if (this.getUrl() != null) {
        sb.append("url: '" + this.getUrl()+ "', ");
        }
        if (this.getVersion() != null) {
        sb.append("version: '" + this.getVersion()+ "', ");
        }
        if (this.getType() != null) {
        sb.append("type: '" + this.getType()+ "', ");
        }
        if (this.getLanguage() != null) {
        sb.append("language: '" + this.getLanguage() + "', ");
        }
        if (this.getValue() != null) {
        sb.append("value: '" + this.getValue() +"'");
        }
        sb.append("}");
        return sb.toString();
    }
}
