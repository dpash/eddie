package uk.org.catnip.eddie;

public class Cloud {
    private String domain;

    private String port;

    private String path;

    private String registerProcedure;

    private String protocol;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("domain: '" + this.domain + "', ");
        sb.append("port: '" + this.port + "', ");
        sb.append("registerProcedure: '" + this.registerProcedure + "', ");
        sb.append("path: '" + this.path + "', ");
        sb.append("protocol: '" + this.protocol + "'");
        sb.append("}");
        return sb.toString();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRegisterProcedure() {
        return registerProcedure;
    }

    public void setRegisterProcedure(String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }

}
