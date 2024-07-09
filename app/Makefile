# Makefile

.DEFAULT_GOAL := build-run

install:
	./gradlew install

run-dist:
	./gradlew clean
	./gradlew installDist
	./build/install/app/bin/app -h

build:
	./gradlew build

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

build-run: build run

.PHONY: build