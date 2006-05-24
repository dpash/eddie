package uk.org.catnip.javarss;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) {


		PropertyConfigurator.configure("log4j.properties");
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
