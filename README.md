## How to run tests locally:
 - Clone or download the project
 - Download and unpack maven from https://maven.apache.org/download.cgi. In the environment variables add a new value **MAVEN_PATH** with path to folder with unpacked maven. Also add new value **%MAVEN_HOME%\bin** to the system PATH variable.
 - Open project folder in terminal and run command:
> mvn test

## Cover letter about test selection:
In current realisation tested "search" method works correctly only with "country" and "name" parameters. Searching by other parameters always returns a full list of universities, so I don't sure it should be tested with current formulation of test task. Also this metohd works with only one specified value for a country or an university name (if specifiy more than one name, it returns an empty response), so I also wasn't sure this cases should be tested. But I can extend current set of tests if it would be necessary.
Other cases description:
1. *testSearchByCountry* - search of several universities from specified country (check how method works with one parameter)
2. *testSearchByName* - search universities with specified word in name (check how metohd works with another parameter)
3. *testSearchByCountryAndUniversityName* - more strict search with both parameters
4. *testEmptySearchResponse* - both parameters have correct types, but nothing was found
5. *testEmptySearchResponseWithInvalidParameter* - query parameter has invalid type
