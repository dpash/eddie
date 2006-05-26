package uk.org.catnip.eddie;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) {

	    if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("info")) {
            PropertyConfigurator.configure("log4j.properties.info"); 
        } else if (System.getenv("EDDIE_LOG") != null && System.getenv("EDDIE_LOG").equals("warn")) {
            PropertyConfigurator.configure("log4j.properties.warn"); 
        } else {
		PropertyConfigurator.configure("log4j.properties");
        }
		Test test = new Test();
		if (args.length > 0) {
			test.parse_dir(args[0]);
		} else {
			test.parse_dir("test");
		}
		System.out.println("   Ran " + test.total_tests + " tests");
		System.out.println("Passed " + test.passed_tests + " tests");
		System.out.println("Failed " + test.failed_tests + " tests");

	}

}
