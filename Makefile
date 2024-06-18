run-dist:
	make -C app run-dist

lint:
	make -C app lint
	
report:
	make -C app report
	
build:
	make -C app build
	
.PHONY: build