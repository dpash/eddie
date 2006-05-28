package uk.org.catnip.eddie;

public class Source extends FeedContext {
    private Generator generator;

    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
