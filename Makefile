SHELL := /bin/bash
all: src/*.java
	S=$$(uname -r | grep 'Microsoft$$') ; if [ $$? -eq 0 ] ; then javac.exe -sourcepath src -d bin src/Main.java ; else javac -sourcepath src -d bin src/Main.java ; fi

clean:
	rm -rf bin/*
