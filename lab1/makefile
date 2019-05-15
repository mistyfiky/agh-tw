default : all

include ../java.makefile ../latex.makefile

.PHONY : default all

all : zadanie.dat raport.pdf

raport.pdf : zadanie.tex

zadanie.dat : zadanie.sh Main.class
	./$< > $@
