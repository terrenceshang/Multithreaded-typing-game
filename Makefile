JAVAC=/usr/bin/javac
DOCDIR= ./javadocs

.SUFFIXES: .java .class
SRCDIR=skeletonCodeAssgnmt2
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES= skeletonCodeAssgnmt2 Score.class WordDictionary.class\
WordRecord.class WordPanel.class WordApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class

runApp: $(CLASS_FILES)
	java -cp $(BINDIR) WordApp
       
docs:
	javadoc -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java
        
cleandocs:
	rm -rf ${DOCDIR}/*

