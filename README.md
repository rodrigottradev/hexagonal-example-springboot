# springboot-hexagonal-example

## Running the application locally

For building and running the application you need:

- Java 11
- Maven

There are several ways to run the app, for example, this two options:

1) Execute the `main` method in the `src/main/java/com/rttradev/infrastructure/Main.java` class
2) Use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Using the application

After running the app in your local machine, you can access to the database in http://localhost:8080/h2-console with username sa (let the 
password empty)

However, there is a population class, so you do not have to populate database manually. You can test the endpoint with this request: 

In local, post to http://localhost:8080/price with the following body:

```json
{
  "date": "2020-06-14T10:00:00",
  "brandId": "90d3f4a9-1fe1-4fb4-b09a-e563e21c4be3",
  "productId": "c129db85-37c0-4f47-94d5-3274d8c598fa"
}
```
It will return

```json
{
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "brandName": "TEST BRAND",
  "productId": "c129db85-37c0-4f47-94d5-3274d8c598fa",
  "priceList": 1,
  "finalPrice": 35.5
}
```

## Requirement

This application consist on an endpoint that get the price of a product. If there is more than one price available in the database, it gets the one
with more priority. There is an unspecified scenario, what should happen when there are two prices with same priority for the same product, dates and
brand? I think this can be fixed with two different approaches:
- Throw an exception if two prices are created with same priority, brand, dates and product.
- Add one extra criteria: get the last created price, for example.

## Architecture

This project uses [hexagonal architecture](https://alistair.cockburn.us/hexagonal-architecture/), a design pattern that isolates the core business 
logic from external dependencies, providing a high degree of decoupling. This architecture allows us to easily connect to new adapters for various 
clients or databases, enhancing flexibility. By isolating business logic, we can write fast, stable tests that don’t depend on specific web 
technologies. The result is a testable, decoupled, flexible, and maintainable software system.

Specifically, in this project there are two modules, infrastructure and application, and inside the application is the domain. Is important to 
consider that this architecture is a simplified version of the hexagonal architecture, and in a real and bigger project, it would be great to use 
a Domain-Driven Design (DDD) approach.

### Application

Internal layer, it contains business logic and is completely isolated from external layers. The use cases are adapters for input ports (in 
this case, a REST API), also, use cases use output ports to send data to external layers, for example, to message queues with kafka (not available 
in this project), or persist data in a database. Also, there are services that help us to maintain a clean code and help with unit test creation, for
example, PriceLocatorService. In this layer is also the domain.

### Infrastructure

Outer layers. The input layer (or driver side, usually represented by left side of the hexagon) is a REST CONTROLLER that uses an input port implemented
by the application layer. The output layer (or driven side, usually represented by right side of the hexagon) has adapters that implement output ports
defined by the application, to persist the data in a H2 database.

## Other

- There are not all the necessary unit test, for example, mappers have not unit test, coverage is not enough, but I think that with this examples
will be clear enough.
