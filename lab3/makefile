default : all

include ../java.makefile ../latex.makefile

.PHONY : default all clean

all : zadanie.out raport.pdf

raport.pdf : zadanie.tex

zadanie.out : Main.class
	java Main conditions 1 1 10 10 10 > zadanie.out
