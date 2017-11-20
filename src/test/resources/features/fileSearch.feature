Feature: User should be able to search for a single or with multiple words in multiple files.
    
  Scenario Outline: Check for Uri
  	Given uri "<uri>"
  	Then a response is returned with statusCode <statusCode>
  	Examples:
  	|	uri														|	statusCode	|
  	|http://localhost:8080/elsevier/v1/search?text=textToSearch |	200			|
  	|http://localhost:8080/elsevier/v2/search?text=textToSearch |	404			|
    
  Scenario Outline: Searching for a exact text and should return matching file name
    Given a text "<query>" to search 
    When fileSearch api is called
    Then a valid response is returned with statusCode 200
	And the number of occurrences is <number>
    Examples:
    |	query					|	number	|
    |	Cucumber				|	1		|
    |	rest api application	|	1		|
    |	rest api application1	|	0		|
    |	application				|	2		|
    |	I loved It				|	0		|

    
