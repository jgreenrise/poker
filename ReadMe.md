Here's an updated README with additional positive and negative test scenarios using `curl` commands for the Poker Service API:

```markdown
# Poker Service API

## Project Description

The Poker Service API is a Spring Boot application designed to evaluate poker hands, specifically checking for the validity of a Straight Poker Hand. This API includes features such as JWT authentication, caching for improved performance, and detailed error handling.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Example CURL Commands](#example-curl-commands)
- [Contributing](#contributing)
- [Future Considerations](#future-considerations)
- [License](#license)

## Installation

### Prerequisites

- Java 20 or higher
- Gradle 7.x or higher
- Redis (for caching)
- Your preferred IDE (e.g., IntelliJ IDEA, Eclipse)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/poker-service.git
   cd poker-service
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

4. Access the application at `http://localhost:8080`.

## Usage

### API Endpoints

- **Check Straight Poker Hand**
    - **Endpoint:** `/poker-service/v1/hands/straight`
    - **Method:** `POST`
    - **Request Body:**
      ```json
      {
        "ranks": ["10", "J", "Q", "K", "A"],
        "suits": ["hearts", "hearts", "hearts", "hearts", "hearts"]
      }
      ```

    - **Response:**
      ```json
      {
        "success": true,
        "data": true,
        "errorCode": null,
        "errorMessage": null
      }
      ```

## API Documentation

The API is documented using OpenAPI specifications. You can view the documentation at:
- [OpenAPI Specification](http://localhost:8080/openapi.yaml)
- [Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Example CURL Commands

### Positive Test Scenarios

#### Valid Straight Hand

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["10", "J", "Q", "K", "A"],
  "suits": ["hearts", "hearts", "hearts", "hearts", "hearts"]
}'
```

**Expected Response:**

```json
{
  "success": true,
  "data": true,
  "errorCode": null,
  "errorMessage": null
}
```

#### Valid Straight Hand with Different Suits

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["3", "4", "5", "6", "7"],
  "suits": ["clubs", "diamonds", "hearts", "spades", "clubs"]
}'
```

**Expected Response:**

```json
{
  "success": true,
  "data": true,
  "errorCode": null,
  "errorMessage": null
}
```

#### Valid Straight Hand with 7 Cards

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["2", "3", "4", "5", "6", "7", "8"],
  "suits": ["spades", "spades", "spades", "spades", "spades", "spades", "spades"]
}'
```

**Expected Response:**

```json
{
  "success": true,
  "data": true,
  "errorCode": null,
  "errorMessage": null
}
```

### Negative Test Scenarios

#### Invalid Hand Size (Less than 5 Cards)

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["10", "J", "Q"],
  "suits": ["hearts", "hearts"]
}'
```

**Expected Response:**

```json
{
  "success": false,
  "data": null,
  "errorCode": "ERR001",
  "errorMessage": "Both ranks and suits must be of size 5 or 7"
}
```

#### Invalid Hand Size (More than 7 Cards)

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["2", "3", "4", "5", "6", "7", "8", "9"],
  "suits": ["hearts", "hearts", "hearts", "hearts", "hearts", "hearts", "hearts", "hearts"]
}'
```

**Expected Response:**

```json
{
  "success": false,
  "data": null,
  "errorCode": "ERR001",
  "errorMessage": "Both ranks and suits must be of size 5 or 7"
}
```

#### Null Lists

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": null,
  "suits": null
}'
```

**Expected Response:**

```json
{
  "success": false,
  "data": null,
  "errorCode": "ERR002",
  "errorMessage": "Ranks and suits cannot be null."
}
```

#### Mismatched Sizes (Ranks and Suits)

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["10", "J", "Q"],
  "suits": ["hearts", "diamonds", "clubs", "spades"]
}'
```

**Expected Response:**

```json
{
  "success": false,
  "data": null,
  "errorCode": "ERR003",
  "errorMessage": "Ranks and suits must be of the same size"
}
```

#### Invalid Rank Values

```bash
curl -X POST http://localhost:8080/poker-service/v1/hands/straight \
-H "Content-Type: application/json" \
-d '{
  "ranks": ["A", "B", "C", "D", "E"],
  "suits": ["hearts", "hearts", "hearts", "hearts", "hearts"]
}'
```

**Expected Response:**

```json
{
  "success": false,
  "data": null,
  "errorCode": "ERR000",
  "errorMessage": "Invalid rank values provided."
}
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with a descriptive message.
4. Push to your branch and create a pull request.

## Future Considerations

- **Security:** Secure API calls.
- **Enhance Error Handling:** Improve error handling by providing more specific error messages and error codes in the API responses.
- **Expand API Functionality:** Add additional endpoints to check for other poker hands, such as Flush, Full House, etc.
- **Rate Limiting:** Implement rate limiting on API calls to prevent abuse and ensure fair use of the service.
- **Deployment:** Consider containerizing the application using Docker for easier deployment and scalability.
- **Unit and Integration Tests:** Enhance the test coverage to ensure robust and reliable application behavior.
- **Performance Monitoring:** Implement monitoring tools (e.g., Spring Actuator) to observe the application's health and performance metrics.
