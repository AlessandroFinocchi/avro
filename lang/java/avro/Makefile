.PHONY: clean jacoco badua pitest all

clean:
	mvn clean
	mvn clean -f ../
	mvn clean -f ../jacoco-module

jacoco:
	mvn verify -P isw2-jacoco-avro-parent -f ../

badua:
	#mvn verify -P isw2-ba-dua -DforkCount=1 -DreuseForks=false -Dsurefire.useManifestOnlyJar=false
	mvn verify -P isw2-ba-dua
pitest:
	mvn verify -P isw2-pitest

all:
	make clean
	make jacoco
	make badua
	make pitest
