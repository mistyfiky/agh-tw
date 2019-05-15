JFLAGS ?= -g
JC ?= $(JAVA_HOME)/bin/javac

.SUFFIXES : .java .class
.PHONY : clean

.java.class :
	$(JC) $(JFLAGS) $*.java

clean ::
	$(RM) *.class
