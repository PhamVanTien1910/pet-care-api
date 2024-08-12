# PETCARE APP

- **Authentication and Authoraizaion with JWT**
- **Manage pet health information and vaccination history**: 
  + Create new pet information
  + Update pet information
  + Create new vaccination history for pets
  + Update vaccination history
- **Search for and schedule appointments with veterinarians**:
  + Search for veterinarians by area and specialty...
  + Schedule an appointment with a veterinarian
  + Retrieve the list of appointments
- **Communicate and share pet care experiences**:
  + Create a post to share experiences
  + Retrieve the list of posts
  + Add a comment
  + Retrieve the list of comments
## Technologies Used

- **Spring Boot (v3.2.2)**: Simplifies the development of Spring applications.
- **Spring Data JPA**: Provides easy integration with relational databases using JPA.
- **Spring Validation**: Handles validation of Java Beans.
- **Spring Security + JWT Token**: Secures your application with JWT-based authentication.
- **MYSQL**: A powerful, open source object-relational database system.
- **Lombok**: Reduces boilerplate code for model objects by generating getters, setters, and other methods.
- **Swagger (Open API)**: Provides interactive API documentation.
- **Spring Boot and OAuth2**

## Customization

### Token Information

You can customize the JWT token information such as secret key, issuer, and expiry date in the [
*application.yml*](https://github.com/dscdut/java-spring-boilerplate/blob/develop/src/main/resources/application.yml)
file.

```yaml
jwt:
  secret: your_secret_key
  issuer: your_issuer
```

### Database Connection

You can customize the database connection information in the [
*application.yml*](https://github.com/dscdut/java-spring-boilerplate/blob/develop/src/main/resources/application.yml)
file.

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_db
    driverClassName: "com.mysql.cj.jdbc.Driver"
    username: your_username
    password: your_password

  jpa:
    hibernate:
      ddl-auto: update
      # ddl-auto: create
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```

## Running the Application

### Prerequisites

- Docker: Make sure Docker is installed and running.
- Java: Ensure you have Java 11 or higher installed.
- Maven: Make sure Maven is installed.

### Deploy with docker

If you're using Docker, you can use the following command to start the containers in detached mode:

```sh
docker-compose up -d
```

## Build the Project

Navigate to the root of the project and run the following command to build the project:

```sh
mvn clean install
```

## Run the Application

Navigate to the target directory and run the application:

```sh
java -jar target/petcare-0.0.1.jar
```

## Using Swagger

Once the application is running, you can access the Swagger UI at:

```sh
http://localhost:8080/swagger-ui/index.html
```

## Use JWT in Swagger

- Clicking on the lock icon opens a login dialog for the user to provide an access token to invoke the operation:

<p align="center">
  <a target="blank"><img src="https://www.baeldung.com/wp-content/uploads/2022/06/operation.png" width="500" alt="Spring Boot Logo" /></a>
</p>

- Available authorizations

<p align="center">
  <a target="blank"><img src="https://www.baeldung.com/wp-content/uploads/2022/06/auth-modal.png" width="500" alt="Spring Boot Logo" /></a>
</p>

- A JWT token can be obtained by providing john/password or jane/password to the authentication API. Once get the JWT
  token, we can pass it in the value textbox and click on Authorize button and then the Close button:

<p align="center">
  <a target="blank"><img src="https://www.baeldung.com/wp-content/uploads/2022/06/Authenticate.png" width="500" alt="Spring Boot Logo" /></a>
</p>

- With the JWT token in place, let’s invoke the deleteUser API:

<p align="center">
  <a target="blank"><img src="https://www.baeldung.com/wp-content/uploads/2022/06/Delete.png" width="500" height="300" alt="Spring Boot Logo" /></a>
</p>
This provides interactive documentation for your API endpoints.

