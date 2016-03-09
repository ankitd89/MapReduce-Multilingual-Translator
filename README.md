# MapReduce-Multilingual-Translator
## E1
### Objective
MapReduce program that merges a set of languages to-English dictionaries into a single file. The languages include French, German, Italian, Portuguese, and Spanish

### Data
The data you will use for this program is located at the "I Love Languages" Web site:
[IDP](http://www.ilovelanguages.com/IDP/IDPfiles.html)
The data files have been provided in the DATA directory.

### Output
The output of your program a single text file with lines of the following format:
English-word: [part of speech] french:french-translation|german:german-translation|italian:italiantranslation|portuguese:portuguese-translation|spanish:spanish-translation
#####For example,
_hello: [Noun] french:bonjour|german:N/A|italian:ciao|portuguese:N/A|spanish:N/A_


## E2: Enrich translation with another language - Map Side Join (Distributed Cache)
### Objective
The objective of this exercise is to write a MapReduce program that enriches the dictionary you created in Exercise 1 with words in another language.

### Data
The data you will use for this program is located in the DATA directory. You are required to use the distributed cache in 
your mapper to read in the data. You will also be using the output from Exercise 1 as input to this program.

### Output
The output of your program is a single text file with lines of the following format:
English-word: [part of speech] french:frenchtranslation|german:german-translation|italian:italiantranslation|portuguese:portuguese-
translation|spanish:spanish-translation|newlanguage:newlanguage-translation
#####For example,
_hello: [Noun] french:bonjour|german:N/A|italian:ciao|portuguese:N/A|spanish:N/A|latin:N/A_
