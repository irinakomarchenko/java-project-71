### Hexlet tests and linter status:
[![Actions Status](https://github.com/irinakomarchenko/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/irinakomarchenko/java-project-71/actions)
<a href="https://codeclimate.com/github/irinakomarchenko/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/f4ea3f7cd41c764a50d2/maintainability" /></a>
<a href="https://codeclimate.com/github/irinakomarchenko/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/f4ea3f7cd41c764a50d2/test_coverage" /></a>

### Description:

A project is a utility for comparing two configuration files (JSON or YAML) and outputting the difference between them. 
The entire cycle of the program can be divided into several stages:

1. Launch the program
   The program is run via the command line using Picocli as specified in the App class. The user specifies two files and
   an optional output format (the default is stylish).

Command to run-dist:

    ./build/install/app/bin/app -h


[![asciicast](https://asciinema.org/a/u2KoN3MRq4iG3Py4ce01DIPNN.svg)](https://asciinema.org/a/u2KoN3MRq4iG3Py4ce01DIPNN)



2. Argument processing
   The App class handles command line arguments using the Picocli library. It takes two required arguments - paths to 
   files to compare, and optional argument - output format.
3. Reading and parsing files
   The Differ.generate method reads the contents of files and determines their extensions, then parses them into 
   a Map<String, Object> data structure.
4. Description of the TreeDiffer.compareData and Node methods
   The TreeDiffer.compareData method compares two parsed files, creating a list of DiffProperty objects, each of which
   describes one difference (a key added, removed, changed, or unchanged).
   The Node method is a helper class used to store data and information about each key. It represents a node in the diff 
   tree.
5. Formatting the result
   The comparison result is formatted depending on the format selected by the user. Stylish, json and plain formats are 
   supported. The formatter converts list DiffProperty into a string representation.
6. Output of the result
   The formatted result is displayed on the screen.

### Demo

[![asciicast](https://asciinema.org/a/EotE0chIPYK8Zg8tRm1nolGjt.svg)](https://asciinema.org/a/EotE0chIPYK8Zg8tRm1nolGjt)

