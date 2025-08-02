# Testing a Spring Boot Car API – NOVI Backend Module

This repository contains my work for an assignment focused on **unit- and integration testing** of a Spring Boot REST API.  
This is assignment was part of the Backend module at [Novi University](https://www.novi.nl).
You’ll see how to test **service logic with Mockito** and **end‑to‑end controller behavior** using **MockMvc**, with an **H2 in‑memory database** configured for the test phase.

---

## Table of Contents
- [Tech Stack](#tech-stack)
- [Key Features](#key-features)
- [Notes on Maven dependency scopes](#notes-on-maven-dependency-scopes)
- [API Endpoints](#api-endpoints)
- [How to Run](#how-to-run)
- [Credits](#credits)
- [License](#license)


---

## Tech Stack
- **Java 17+**
- **Spring Boot 3.x** (Web, Data JPA, Security)
- **Maven**
- **H2** (test-only)
- **JUnit 5**, **Mockito**, **Spring Security Test**
- **MockMvc**

---

## Key Features
- **Separate test configuration**: Dedicated `application.properties` under `src/test/resources` to isolate test settings from production.
- **H2 in-memory database**: Fast, temporary database for running tests without affecting production data.
- **Comprehensive unit tests**: Validating `CarService#getCars(brand, model)` behavior using **JUnit 5** and **Mockito**.
- **Integration tests for REST controllers**: Testing `CarController` endpoints with **Spring Boot Test** and **MockMvc**, including authentication via **Spring Security**.
- **Clean and isolated test environment**: Ensures deterministic results, independent of external databases or services.


### Notes on Maven dependency scopes

The `scope` in Maven declares in which context a dependency is available.

- `compile` (default): available at compile and runtime.
- `provided`: needed to compile, but not packaged (container provides it).
- `runtime`: not needed at compile time, only at runtime.
- `test`: available only during tests (used for h2 and spring-security-test in this project).

---

___

## API Endpoints

## Authentication

| Method | Endpoint | Description                              |
|--------|----------|------------------------------------------|
| POST   | `/login` | Authenticate user and receive JWT token  |

---

## Car API (secured)

| Method | Endpoint        | Description                                   |
|--------|-----------------|-----------------------------------------------|
| GET    | `/cars`         | Retrieve all cars (requires valid JWT token)  |
| POST   | `/cars`         | Create a new car                              |
| PUT    | `/cars/{id}`    | Update car details                            |
| DELETE | `/cars/{id}`    | Delete a car                                  |


### Car Registration Controller (`/cars/{carId}/carregistrations`)
| Method | Endpoint                                                      | Description                         |
|--------|---------------------------------------------------------------|-------------------------------------|
| POST   | `/cars/{carId}/carregistrations`                              | Create a new registration for car   |
| GET    | `/cars/{carId}/carregistrations/{registrationId}`             | Get a specific registration         |
| PUT    | `/cars/{carId}/carregistrations/{registrationId}`             | Update a registration               |
| DELETE | `/cars/{carId}/carregistrations/{registrationId}`             | Delete a registration               |

---

## How to Run

### Using IntelliJ IDEA

1. Open the project.
2. Locate the `CarApplication` main class.
3. Click the green play️ button to run the application or use the terminal:
    ```bash
    ./mvnw spring-boot:run
   ```
4. The server will start at: `http://localhost:8080`

---

## Credits
> "This assignment was developed as part of the Backend Java module in the NOVI Software Development program. All instructions, logic, and structure are part of the official coursework."

## License
> "This repository is intended for educational purposes only. You are welcome to use the code for learning, but not for commercial use."