The first test project to get a scala project made up with multiple files
Program here used to get log lines of interest from inputlog
Using the terms found in termsfile - each term must be on a new line
Writes to outputreport

main.scala - main function file to get lines of interest from log files

WeltTools.scala - helper tools required to parse dates and log lines

run with:
sbt "run <termsfile> <inputlog> <outputreport>"

sbt "run /Users/Izham/Downloads/terms.txt /Users/Izham/Downloads/April2016_Welt_der_Wunder_Logfiles.rtf /Users/Izham/Downloads/todeleteReport.csv"