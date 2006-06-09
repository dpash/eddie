/**
 * 
 */
package uk.org.catnip.eddie.spider;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

/**
 * @author David Pashley <david@davidpashley.com>
 *
 */
public class Main {
	//private static Logger log = Logger.getLogger(Main.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("info")) {
            PropertyConfigurator.configure("log4j.properties.info"); 
        } else if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("warn")) {
            PropertyConfigurator.configure("log4j.properties.warn"); 
        } else {
		PropertyConfigurator.configure("log4j.properties");
        }
	    Spider spider = new Spider();
	    spider.run();
	}

}

