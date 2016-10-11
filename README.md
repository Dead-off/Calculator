# Calculator

## Building 

move to this directory

    $ gradlew build

This command build all project. Executable .jar file placed on build/libs

## Run

for run application print move to build/libs and execute

    $ java -jar Calculator-1.0.jar [PATH_TO_RES_DIR]

where [PATH_TO_RES_DIR] is path to directory with files^
Calculator.xsd - xsd schema for input file
result.xml - empty file for output result
sampleCorrect.xml - file with expression. Must be valid Calculator.xsd 

e.g. you can use test files from src/main/resources. Finally command is 

	$ java -jar Calculator-1.0.jar ../../src/main/resources