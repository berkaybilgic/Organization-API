# Organization-API

## Architecture

![arc](https://user-images.githubusercontent.com/56771957/217103068-ce8e17b0-d812-4393-ad1b-c43fa9227a4f.jpeg)

## Project Descriptions

#### API

Keys;

- Java
- Spring Boot 
- Postgresql
- API Testing (JUnit)
- Swagger Documentation ( address : http://localhost:8080/swagger-ui/index.html)
- Docker

### Run Postgresql

```sh
$ docker run --name organizationdb --rm -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=12345 -p 5432:5432 -d  postgres
```

### Create DB
```sh
$ docker exec -it organizationdb psql -U postgres -c "CREATE DATABASE organization"
```

### Build API IMAGE
```sh
$ docker build -t organization-api .
```

### Run API 
```sh
$ docker run -p 8080:8080 organization-api
```

### Create Default User
```sh
$ docker exec -it organizationdb psql organization -U postgres -c "INSERT INTO users (id, created_by, created_date, email, full_name, normalized_name, status)
VALUES ('4675c925-d6c0-4048-9f1d-42bf00a132a3', '4675c925-d6c0-4048-9f1d-42bf00a132a3', current_timestamp, 'test_user@gmail.com','test','test','ACTIVE');"
```

