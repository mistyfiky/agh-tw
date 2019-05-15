default : all

include ../java.makefile ../latex.makefile

.PHONY : default all clean

all : semaphores.jpg conditions.jpg raport.pdf

clean ::
	$(RM) *.dat *.jpg

raport.pdf : zadanie.tex

semaphores.dat conditions.dat : zadanie.sh Main.class
	./zadanie.sh $(@:.dat=) > $@

semaphores.jpg conditions.jpg : zadanie.gpi semaphores.dat conditions.dat
	gnuplot $<
