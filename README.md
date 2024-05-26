/***** Reads a text file and counts how often words occur *****/


Comments about this program:

•	Main Program '**WordCountMain**' accepts input text file. Suppose if you missed to pass input file by default it will take ‘gpl-3.0.txt’.


•	Created Interface ‘FindWordCount‎’ to implement method to reads text file and counts often occurred words.


•	Provided implementation in three ways and printing how much time took for each implementation.

          o	Parallel stream processing.
          
          o	Multiple thread processing.
          
          o	Multiple thread processing by using ExecutorService.
          
•	Verified expected output is correct or not. For example, if the input file is ‘gpl-3.0.txt’ and we should get below words count and format like below:

          o	'non-exercise': 1
          
          o	'2007': 3
          
          o	'all': 21
          
•	And also output should be inside flower braces(‘{}’) and each word should be inside single quotation(‘all’).

