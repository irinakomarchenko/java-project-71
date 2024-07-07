install:
	make -C app install

lint:
	make -C app lint

test:
	make -C app test
	
report:
	make -C app report
	
build:
	make -C app build
	
.PHONY: build