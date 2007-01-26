#!/bin/sh

export CLASSPATH="lib/tagsoup-1.0.1.jar:/usr/share/java/commons-codec.jar:/usr/share/java/xercesImpl.jar:/usr/share/java/log4j-1.2.jar:lib/jython.jar:eddie.jar:eddie-test.jar"
export JAVA_HOME=/usr/lib/jvm/java-6-sun
$JAVA_HOME/bin/java uk.org.catnip.eddie.parser.Main "$@"

