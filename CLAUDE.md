# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

- **Build:** `mvn compile`
- **Run:** `mvn spring-boot:run`
- **Test (all):** `mvn test`
- **Test (single class):** `mvn test -Dtest=AiLearningProfileBackendApplicationTests`
- **Package:** `mvn package -DskipTests`

The app starts on **port 8081** (configured in `application.yaml`).

## Prerequisites

- Java 17
- MySQL running on `localhost:3306` with database `learning_profile`
- Maven (or use the included Maven wrapper if available)

## Architecture

Spring Boot 4.0.6 backend using **Spring JDBC (JdbcTemplate)** — no JPA/Hibernate. All SQL is handwritten with Java text blocks.

### Layered structure

```
Controller → Service → Repository (JdbcTemplate)
    ↓            ↓           ↓
  DTO         Entity       Entity
    ↓
   VO
```

- **Controller** (`controller/`): REST endpoints under `/api/`, returns `Result<T>` wrapper
- **Service** (`service/`): Business logic, converts Entity ↔ VO/DTO
- **Repository** (`repository/`): Raw SQL via `JdbcTemplate`, manual `RowMapper` lambdas
- **Entity** (`entity/`): 1:1 mapping to database tables (includes all columns like `password`, `is_deleted`)
- **DTO** (`dto/`): Inbound request objects (what the frontend sends)
- **VO** (`vo/`): Outbound response objects (what the frontend sees — strips sensitive fields like `password`)

### Key patterns

- **`Result<T>`** (`common/Result.java`): Generic API response wrapper with `code`, `message`, `data`. Use `Result.success(data)` / `Result.error(code, msg)`.
- **Soft delete**: All queries filter `WHERE is_deleted = 0`. Never physically delete rows.
- **Constructor injection**: No `@Autowired` field injection — all dependencies are injected via constructors.
- **No Lombok**: All getters/setters are written manually.
- **Chinese language**: Error messages, comments, and API responses are in Chinese. Keep this consistent.

### Database

MySQL database `learning_profile` with a `user` table. Connection config is in `application.yaml`. The `DbConnectionTest` class prints a connection confirmation on startup.

### API convention

All endpoints are prefixed with `/api/`. Current endpoints:
- `GET /api/users` — list all users
- `GET /api/users/{id}` — get user by ID
- `POST /api/users` — create user

### Adding a new feature

Follow the existing pattern: Entity → Repository (with JdbcTemplate SQL) → Service (with Entity↔VO conversion) → Controller (with `Result<T>` return type and `/api/` prefix). Create a corresponding DTO for inbound data and VO for outbound data.
