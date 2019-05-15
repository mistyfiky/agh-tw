LATEXMK ?= latexmk
PDFLATEX ?= pdflatex

.SUFFIXES : .tex .pdf
.PHONY : clean

.tex.pdf :
	$(LATEXMK) -pdflatex="$(PDFLATEX) -interaction=nonstopmode" -pdf $*

clean ::
	$(LATEXMK) -C
