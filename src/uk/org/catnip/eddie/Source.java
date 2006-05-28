package uk.org.catnip.eddie;

public class Source extends FeedContext {
    private Generator generator;
    private Detail subtitle;
    

    public Detail getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Detail subtitle) {
        this.subtitle = subtitle;
    }

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
