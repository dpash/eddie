package uk.org.catnip.eddie;

import java.lang.StringBuilder;

public class Detail {
    private String language;
    private String type;
    private String value;
    private String src;
    private String base;
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getLanguage() {
        return language;
    }

    public void setDetails(Detail detail) {
        this.language = detail.getLanguage();
        this.type = detail.getType();
        this.value = detail.getValue();
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.trim();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("type: '" + this.type+ "', ");
        sb.append("language: '" + this.language + "', ");
        sb.append("src: '" + this.src + "', ");
        sb.append("value: '" + this.value +"'");
        sb.append("}");
        return sb.toString();
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
