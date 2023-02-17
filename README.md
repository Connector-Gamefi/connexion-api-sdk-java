# Java SDK for Connexion API

[![Maven](https://img.shields.io/badge/maven-3.8.4-blue)](https://connexion.games/)
![GitHub last commit](https://img.shields.io/github/last-commit/Connector-Gamefi/connexion-api-sdk-java)
![GitHub top language](https://img.shields.io/github/languages/top/Connector-Gamefi/connexion-api-sdk-java?color=red)

# API Documentation
- [Official documentation](https://docs.connexion.games/openapi-en)

# Usage
## Download and build
```shell
$ git clone https://github.com/Connector-Gamefi/connexion-api-sdk-java
$ cd connexion-api-sdk-java
$ mvn install -Dmaven.test.skip=true
```
## Test

## Test api request
    public void requestGetOpenApi(){
        //Request interface example
        String url = OPEN_API_DOMAIN.concat("/global/topList/roleLevel");
        Map<String, Object> req = new HashMap<>();
        //Request parameters
        req.put("topNum", "5");
        //Timestamp is required (here is an example). The actual parameter is unix timestamp in seconds
        req.put("timestamp", 1675998834);

        //Your API_KEY
        Map<String, String> headers = new HashMap<>();
        headers.put(HeaderConstants.API_KEY, "fa61655a1aca4804b5e2c3c7a10c6257");
        //SignUtils can be used directly in the sample project .The last signature parameter is yours secretKey
        String signResult = SignUtils.sign((HashMap<String, Object>) req, "a05315753c2842598ee5daca4f7ef399");
        headers.put(HeaderConstants.SIGNATURE_KEY, signResult);
        log.info(signResult);
        //Sample Signature Result  40883fa6390c7060a874f2a310144af92586e7fe60ea9ae7adea4d4908146f94
        //Interface request
        String res = HttpRequest.get(url).form(req).addHeaders(headers).execute().body();
        log.info(res);
    }
    
* JUnit
  Execute `openApiTest` unit in `/src/test/java/com/connextion/open/api/demo/OpenApiDemoServiceTest.java` Java file
