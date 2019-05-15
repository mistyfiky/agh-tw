JFLAGS ?= -g
JC ?= $(JAVA_HOME)/bin/javac
JVM ?= $(JAVA_HOME)/bin/java

.SUFFIXES : .java .class
.PHONY : clean

.java.class :
	$(JC) $(JFLAGS) $*.java

% : %.class
	$(JVM) $*

clean ::
	$(RM) *.class
