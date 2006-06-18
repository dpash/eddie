package uk.org.catnip.eddie.parser;

import junit.framework.TestCase;
import java.io.*;

public class MarkableInputStreamTest extends TestCase {
    public void testReset() throws IOException {
        byte[] string = { 'h', 'e', 'l', 'l', 'o' };
        InputStream is = new MarkableInputStream(new ByteArrayInputStream(string));
        assertTrue(is.read() == 'h');
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'h');
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
    }
    public void testMark() throws IOException {
        byte[] string = { 'h', 'e', 'l', 'l', 'o' };
        InputStream is = new MarkableInputStream(new ByteArrayInputStream(string));
        assertTrue(is.read() == 'h');
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        is.mark(0);
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
    }
    public void testMark2() throws IOException {
        byte[] string = { 'h', 'e', 'l', 'l', 'o' };
        InputStream is = new MarkableInputStream(new ByteArrayInputStream(string));
        is.mark(0);
        assertTrue(is.read() == 'h');
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'h');
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
    }
    public void testMark3() throws IOException {
        byte[] string = { 'h', 'e', 'l', 'l', 'o' };
        InputStream is = new MarkableInputStream(new ByteArrayInputStream(string));

        assertTrue(is.read() == 'h');
        is.mark(0);
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
    }
    public void testMark4() throws IOException {
        byte[] string = { 'h', 'e', 'l', 'l', 'o' };
        InputStream is = new MarkableInputStream(new ByteArrayInputStream(string));

        assertTrue(is.read() == 'h');
        is.mark(0);
        assertTrue(is.read() == 'e');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'e');
        is.mark(0);
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
        is.reset();
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'l');
        assertTrue(is.read() == 'o');
        assertTrue(is.read() == -1);
    }
}
