/**
 * 
 */
package uk.org.catnip.eddie.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;
/**
 * @author david
 *
 */
public class MarkableInputStream extends InputStream {
    private static Logger log = Logger.getLogger(Parser.class);
    private InputStream inputstream;
    private int maxpos = 0;
    private int curpos = 0;
    private int mark = 0;
    private ArrayList<Integer> buffer = new ArrayList<Integer>();
    public MarkableInputStream(InputStream is) {
        inputstream = is;
    }
    /* (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    @Override
    public int read() throws IOException {
        int data;
        if(curpos == maxpos) {
            // No data to be read
            data = inputstream.read();
            buffer.add(data); maxpos++;curpos++;
        } else {
            data = buffer.get(curpos++);
        }
        //log.debug(data);
        //log.debug(buffer);
        return data;
    }
    /* (non-Javadoc)
     * @see java.io.InputStream#mark(int)
     */
    @Override
    public synchronized void mark(int readlimit) {
        mark = curpos;
    }
    /* (non-Javadoc)
     * @see java.io.InputStream#markSupported()
     */
    @Override
    public boolean markSupported() {
        return true;
    }
    /* (non-Javadoc)
     * @see java.io.InputStream#reset()
     */
    @Override
    public synchronized void reset() throws IOException {
        curpos = mark;
    }

    
}
