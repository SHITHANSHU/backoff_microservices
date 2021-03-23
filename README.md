# Microservices based on Dropwizard and SpringBoot

- `serviceRouge` - Dropwizard microservice which returns only one response with Response Code 202 others as 503 when they hit http://localhost:8085/service-walrus/hello
- `serviceundererred` - Spring boot based microservice with exponential backoff mechanism for retries while interacting with serviecRouge. It hosts a web page at http://localhost:8090/. To test go to http://localhost:8090/ click on connect and then click on start it will start sending you intermediate response and increase the delay exponentially.

