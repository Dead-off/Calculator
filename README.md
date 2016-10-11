# Calculator

## Building 

move to this directory

    $ gradlew build

This command build all project. Executable .jar file placed on build/libs

## Run

for run application move to build/libs and execute

    $ java -jar Calculator-1.0.jar [PATH_TO_RES_DIR]

where [PATH_TO_RES_DIR] is path to directory with files: <br />
Calculator.xsd - xsd schema for input file <br />
result.xml - empty file for output result <br />
sampleCorrect.xml - file with expression. Must be valid Calculator.xsd <br />

e.g. you can use test files from src/main/resources. Finally command is 

	$ java -jar Calculator-1.0.jar ../../src/main/resources