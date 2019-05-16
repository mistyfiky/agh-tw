default : all

include ../java.makefile ../latex.makefile

.PHONY : default all clean

all : zadanie.dat raport.pdf

clean ::
	$(RM) *.dat

raport.pdf : zadanie.tex

zadanie.dat : zadanie.sh Main.class
	./$< > $@
