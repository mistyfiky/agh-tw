all clean :
	$(foreach LAB,$(wildcard ./lab*),pushd $(LAB) && $(MAKE) $@ && popd;)
